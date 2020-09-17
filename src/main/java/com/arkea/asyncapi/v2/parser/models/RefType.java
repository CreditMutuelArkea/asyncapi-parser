package com.arkea.asyncapi.v2.parser.models;

/**
 * Created by CS552.
 */
public enum RefType {


    COMPONENTS("#/components/"),
    SCHEMAS("#/components/schemas/"),
    OPERATIONTRAITS("#/components/operationTraits/"),
    MESSAGESTRAITS("#/components/messageTraits/"),
    MESSAGES("#/components/messages/");


    private final String internalPrefix;

    RefType(final String prefix) {
        this.internalPrefix = prefix;
    }

    /**
     * The prefix in an internal reference of this type.
     */
    public String getInternalPrefix() {
        return this.internalPrefix;
    }
}