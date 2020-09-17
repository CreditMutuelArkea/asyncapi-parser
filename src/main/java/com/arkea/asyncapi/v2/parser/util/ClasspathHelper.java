package com.arkea.asyncapi.v2.parser.util;


import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ClasspathHelper {

    public static String loadFileFromClasspath(final String location) {

        InputStream inputStream = ClasspathHelper.class.getResourceAsStream(location);

        if(inputStream == null) {
            inputStream = ClasspathHelper.class.getClassLoader().getResourceAsStream(location);
        }

        if(inputStream == null) {
            inputStream = ClassLoader.getSystemResourceAsStream(location);
        }

        if(inputStream != null) {
            try {
                return IOUtils.toString(inputStream);
            } catch (final IOException e) {
                throw new RuntimeException("Could not read " + location + " from the classpath", e);
            }
        }

        throw new RuntimeException("Could not find " + location + " on the classpath");
    }
}
