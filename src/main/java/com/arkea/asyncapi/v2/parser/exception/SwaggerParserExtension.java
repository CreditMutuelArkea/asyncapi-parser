package com.arkea.asyncapi.v2.parser.exception;


import com.arkea.asyncapi.v2.parser.models.AsyncParseResult;
import com.arkea.asyncapi.v2.parser.models.ParseOptions;

public interface SwaggerParserExtension {
//    SwaggerParseResult readLocation(String url,  ParseOptions options);

    AsyncParseResult readContents(String swaggerAsString,  ParseOptions options);
}
