package com.arkea.asyncapi.v2.parser.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.arkea.asyncapi.v2.parser.models.RefFormat;

public class RefUtils {

    private static final String REFERENCE_SEPARATOR = "#/";

    private RefUtils() {
        // static access only
    }

    public static String computeDefinitionName(final String ref) {

        final String[] refParts = ref.split(REFERENCE_SEPARATOR);

        if (refParts.length > 2) {
            throw new RuntimeException("Invalid ref format: " + ref);
        }

        final String file = refParts[0];
        final String definitionPath = refParts.length == 2 ? refParts[1] : null;

        String plausibleName;

        if (definitionPath != null) { // the name will come from the last element of the definition path
            final String[] jsonPathElements = definitionPath.split("/");
            plausibleName = jsonPathElements[jsonPathElements.length - 1];
        } else { // no definition path, so we must come up with a name from the file
            final String[] filePathElements = file.split("/");
            plausibleName = filePathElements[filePathElements.length - 1];

            final String[] split = plausibleName.split("\\.");
            plausibleName = split[0];
        }

        return plausibleName;
    }

    public static Optional<String> getExternalPath(final String ref) {
        if (ref == null) {
            return Optional.empty();
        }
        return Optional.of(ref.split(REFERENCE_SEPARATOR))
                        .filter(it -> it.length == 2)
                        .map(it -> it[0])
                        .filter(it -> !it.isEmpty());
    }

    public static boolean isAnExternalRefFormat(final RefFormat refFormat) {
        return refFormat == RefFormat.URL || refFormat == RefFormat.RELATIVE;
    }

    public static RefFormat computeRefFormat(String ref) {
        RefFormat result = RefFormat.INTERNAL;
        ref = mungedRef(ref);
        if (ref.startsWith("http") || ref.startsWith("https")) {
            result = RefFormat.URL;
        } else if (ref.startsWith(REFERENCE_SEPARATOR)) {
            result = RefFormat.INTERNAL;
        } else if (ref.startsWith(".") || ref.startsWith("/") || ref.indexOf(REFERENCE_SEPARATOR) > 0) {
            result = RefFormat.RELATIVE;
        }

        return result;
    }

    public static String mungedRef(final String refString) {
        // Ref: IETF RFC 3966, Section 5.2.2
        if (!refString.contains(":") && // No scheme
                        !refString.startsWith("#") && // Path is not empty
                        !refString.startsWith("/") && // Path is not absolute
                        !refString.contains("$") &&
                        refString.indexOf(".") > 0) { // Path does not start with dot but contains "." (file extension)
            return "./" + refString;
        }
        return refString;
    }

    public static String readExternalUrlRef(final String file, final RefFormat refFormat,
                    final String rootPath) {

        if (!RefUtils.isAnExternalRefFormat(refFormat)) {
            throw new RuntimeException("Ref is not external");
        }

        String result;

        try {
            if (refFormat == RefFormat.URL) {
                result = RemoteUrl.urlToString(file);
            } else {
                // its assumed to be a relative ref
                final String url = buildUrl(rootPath, file);

                return readExternalRef(url, RefFormat.URL, null);
            }
        } catch (final Exception e) {
            throw new RuntimeException("Unable to load " + refFormat + " ref: " + file, e);
        }

        return result;

    }

    public static String readExternalClasspathRef(final String file, final RefFormat refFormat,
                    final String rootPath) {

        if (!RefUtils.isAnExternalRefFormat(refFormat)) {
            throw new RuntimeException("Ref is not external");
        }

        String result = null;

        try {
            if (refFormat == RefFormat.URL) {
                result = RemoteUrl.urlToString(file);
            } else {
                // its assumed to be a relative ref
                // String pathRef = ExternalRefProcessor.join(rootPath, file);
                //
                // result = ClasspathHelper.loadFileFromClasspath(pathRef);
                // TOD gestion des external ref
                throw new NotImplementedException("ExternalClasspathRef not implemented");
            }
        } catch (final Exception e) {
            throw new RuntimeException("Unable to load " + refFormat + " ref: " + file, e);
        }

        return result;

    }

    public static String buildUrl(final String rootPath, final String relativePath) {
        final String[] rootPathParts = rootPath.split("/");
        final String[] relPathParts = relativePath.split("/");

        if (rootPath == null || relativePath == null) {
            return null;
        }

        int trimRoot = 0;
        int trimRel = 0;

        if (!"".equals(rootPathParts[rootPathParts.length - 1])) {
            trimRoot = 1;
        }
        if ("".equals(relPathParts[0])) {
            trimRel = 1;
            trimRoot = rootPathParts.length - 3;
        }
        for (final String rootPathPart : rootPathParts) {
            if ("".equals(rootPathPart)) {
                trimRel += 1;
            } else {
                break;
            }
        }
        for (final String relPathPart : relPathParts) {
            if (".".equals(relPathPart)) {
                trimRel += 1;
            } else if ("..".equals(relPathPart)) {
                trimRel += 1;
                trimRoot += 1;
            }
        }

        final String[] outputParts = new String[rootPathParts.length + relPathParts.length - trimRoot - trimRel];
        System.arraycopy(rootPathParts, 0, outputParts, 0, rootPathParts.length - trimRoot);
        System.arraycopy(relPathParts,
                        trimRel,
                        outputParts,
                        rootPathParts.length - trimRoot,
                        relPathParts.length - trimRel);

        return StringUtils.join(outputParts, "/");
    }

    public static String readExternalRef(final String file, final RefFormat refFormat,
                    Path parentDirectory) {

        if (!RefUtils.isAnExternalRefFormat(refFormat)) {
            throw new RuntimeException("Ref is not external");
        }

        String result = null;

        try {
            if (refFormat == RefFormat.URL) {
                result = RemoteUrl.urlToString(file);
            } else {
                // its assumed to be a relative file ref
                final Path pathToUse = parentDirectory.resolve(file).normalize();

                if (Files.exists(pathToUse)) {
                    result = readAll(pathToUse);
                } else {
                    String url = file;
                    if (url.contains("..")) {
                        int parentCount = 0;
                        while (url.contains("..")) {
                            url = url.substring(url.indexOf(".") + 2);
                            parentCount++;
                        }
                        for (int i = 0; i < parentCount - 1; i++) {
                            parentDirectory = parentDirectory.getParent();
                        }
                        url = parentDirectory + url;
                    } else {
                        url = parentDirectory + url.substring(url.indexOf(".") + 1);
                    }
                    final Path pathToUse2 = parentDirectory.resolve(url).normalize();

                    if (Files.exists(pathToUse2)) {
                        result = readAll(pathToUse2);
                    }
                }
                if (result == null) {
                    result = ClasspathHelper.loadFileFromClasspath(file);
                }

            }
        } catch (final Exception e) {
            throw new RuntimeException("Unable to load " + refFormat + " ref: " + file + " path: " + parentDirectory, e);
        }

        return result;

    }

    private static String readAll(final Path path) throws IOException {
        try (InputStream inputStream = new FileInputStream(path.toFile())) {
            return IOUtils.toString(inputStream, UTF_8);
        }
    }

    public static String constructRef(final String simpleRef) {
        return "#/components/schemas/" + simpleRef;
    }

    public static String constructRef(final String simpleRef, final String prefix) {
        return prefix + simpleRef;
    }

    public static Pair extractSimpleName(final String ref) {
        final int idx = ref.lastIndexOf('/');
        if (idx > 0) {
            final String simple = ref.substring(idx + 1);
            if (!StringUtils.isBlank(simple)) {
                return new ImmutablePair<>(simple, ref.substring(0, idx + 1));
            }
        }
        return new ImmutablePair<>(ref, null);

    }
}