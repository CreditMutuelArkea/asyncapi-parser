package com.arkea.asyncapi.v2.parser;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.arkea.asyncapi.v2.parser.exception.SwaggerParserExtension;
import com.arkea.asyncapi.v2.parser.models.AsyncParseResult;
import com.arkea.asyncapi.v2.parser.models.ParseOptions;
import com.arkea.asyncapi.v2.parser.util.AsyncAPIDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AsyncAPIV2Parser implements SwaggerParserExtension {

    private static ObjectMapper JSON_MAPPER, YAML_MAPPER;

    /**
     * Encoding of the resource content with AsyncAPI spec to parse.
     */
    private static String encoding = StandardCharsets.UTF_8.displayName();

    static {
        JSON_MAPPER = ObjectMapperFactory.createJson();
        YAML_MAPPER = ObjectMapperFactory.createYaml();
    }

    public AsyncParseResult parseJsonNode(final JsonNode node) {
        return new AsyncAPIDeserializer().deserialize(node);
    }

    public AsyncParseResult readContents(final String yaml) {
        final ParseOptions options = new ParseOptions();
        options.setResolve(true);
        return readContents(yaml, options);
    }

    @Override
    public AsyncParseResult readContents(final String swaggerAsString, final ParseOptions options) {
        if (swaggerAsString == null || swaggerAsString.trim().isEmpty()) {
            return AsyncParseResult.ofError("Null or empty definition");
        }

        try {
            final ObjectMapper mapper = getRightMapper(swaggerAsString);
            final JsonNode rootNode = mapper.readTree(swaggerAsString);
            final AsyncParseResult result = parseJsonNode(rootNode);
            return result;
        } catch (final JsonProcessingException e) {
            final String message = getParseErrorMessage(e.getOriginalMessage());
            return AsyncParseResult.ofError(message);
        }
    }

    private String getParseErrorMessage(final String originalMessage) {
        if (Objects.isNull(originalMessage)) {
            return String.format("Unable to parse `%s`", originalMessage);
        }
        if (originalMessage.startsWith("Duplicate field")) {
            return String.format(originalMessage + " in `%s`", originalMessage);
        }
        return originalMessage;
    }

    private ObjectMapper getRightMapper(final String data) {
        if (data.trim().startsWith("{")) {
            return JSON_MAPPER;
        }
        return YAML_MAPPER;
    }

}
