package com.arkea.asyncapi.v2.parser.util;

import java.io.IOException;

import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by russellb337 on 7/14/15.
 */
public class DeserializationUtils {
    public static JsonNode deserializeIntoTree(final String contents, final String fileOrHost) {
        JsonNode result;

        try {
            if (isJson(contents)) {
                result = Json.mapper().readTree(contents);
            } else {
                result = readYamlTree(contents);
            }
        } catch (final IOException e) {
            throw new RuntimeException("An exception was thrown while trying to deserialize the contents of " + fileOrHost + " into a JsonNode tree", e);
        }

        return result;
    }

    public static <T> T deserialize(final Object contents, final String fileOrHost, final Class<T> expectedType) {
        T result;

        boolean isJson = false;

        if(contents instanceof String && isJson((String)contents)) {
            isJson = true;
        }

        try {
            if (contents instanceof String) {
                if (isJson) {
                    result = Json.mapper().readValue((String) contents, expectedType);
                } else {
                    result = Yaml.mapper().readValue((String) contents, expectedType);
                }
            } else {
                result = Json.mapper().convertValue(contents, expectedType);
            }
        } catch (final Exception e) {
            throw new RuntimeException("An exception was thrown while trying to deserialize the contents of " + fileOrHost + " into type " + expectedType, e);
        }

        return result;
    }

    private static boolean isJson(final String contents) {
        return contents.toString().trim().startsWith("{");
    }

    public static JsonNode readYamlTree(final String contents) {
        final org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml(new SafeConstructor());
        return Json.mapper().convertValue(yaml.load(contents), JsonNode.class);
    }

    public static <T> T readYamlValue(final String contents, final Class<T> expectedType) {
        final org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml(new SafeConstructor());
        return Json.mapper().convertValue(yaml.load(contents), expectedType);
    }
}