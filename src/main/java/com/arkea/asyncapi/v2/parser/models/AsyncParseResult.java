package com.arkea.asyncapi.v2.parser.models;

import java.util.Collections;
import java.util.List;

import com.arkea.asyncapi.v2.models.AsyncAPI;

public class AsyncParseResult {
    private List<String> messages = null;
    private AsyncAPI asyncAPI;

    /**
     * Set the list of messages that happend during parsing.
     * @param messages
     * @return  AsyncParseResult instance
     */
    public AsyncParseResult messages(final List<String> messages) {
        this.messages = messages;
        return this;
    }

    /**
     * @return
     */
    public List<String> getMessages() {
        return this.messages;
    }

    public void setMessages(final List<String> messages) {
        this.messages = messages;
    }

    /**
     * @return
     */
    public AsyncAPI getAsyncAPI() {
        return this.asyncAPI;
    }

    /**
     * @param asyncAPI
     */
    public void setAsyncAPI(final AsyncAPI asyncAPI) {
        this.asyncAPI = asyncAPI;
    }

    public static AsyncParseResult ofError(final String message){
        final AsyncParseResult result = new AsyncParseResult();
        result.setMessages(Collections.singletonList(message));
        return result;
    }
}
