package com.arkea.asyncapi.v2.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ObjectMapperFactory {

    public static ObjectMapper createJson() {
        return createJson(true, true);
    }

    protected static ObjectMapper createJson(final boolean includePathDeserializer, final boolean includeResponseDeserializer) {
        return create(createJsonFactory(), includePathDeserializer, includeResponseDeserializer);
    }

    public static ObjectMapper createYaml() {
        return createYaml(true, true);
    }

    protected static ObjectMapper createYaml(final boolean includePathDeserializer, final boolean includeResponseDeserializer) {
        return create(createYamlFactory(), includePathDeserializer, includeResponseDeserializer);
    }

    private static ObjectMapper create(final JsonFactory jsonFactory, final boolean includePathDeserializer, final boolean includeResponseDeserializer) {
        final ObjectMapper mapper = new ObjectMapper(jsonFactory);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }

    private static JsonFactory createJsonFactory() {
        return new JsonFactoryBuilder()
                        .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
                        .build();
    }

    private static JsonFactory createYamlFactory() {
        return YAMLFactory.builder()
                        .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
                        .build();
    }
}
