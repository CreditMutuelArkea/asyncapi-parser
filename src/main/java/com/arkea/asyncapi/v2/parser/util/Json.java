package com.arkea.asyncapi.v2.parser.util;

import com.arkea.asyncapi.v2.parser.ObjectMapperFactory;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Json {

    private static ObjectMapper mapper;

    public static ObjectMapper mapper() {
        if (mapper == null) {
            mapper = ObjectMapperFactory.createJson();
        }
        return mapper;
    }

    public static ObjectWriter pretty() {
        return mapper().writer(new DefaultPrettyPrinter());
    }

    public static String pretty(final Object o) {
        try {
            return pretty().writeValueAsString(o);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void prettyPrint(final Object o) {
        try {
            System.out.println(pretty().writeValueAsString(o).replace("\r", ""));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
