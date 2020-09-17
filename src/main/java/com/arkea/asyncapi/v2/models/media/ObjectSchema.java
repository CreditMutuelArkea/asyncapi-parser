package com.arkea.asyncapi.v2.models.media;

import java.util.Objects;

/**
 * ObjectSchema
 */

public class ObjectSchema extends Schema<Object> {

    public ObjectSchema() {
        super("object", null);
    }

    @Override
    public ObjectSchema type(final String type) {
        super.setType(type);
        return this;
    }

    @Override
    public ObjectSchema example(final Object example) {
        if (example != null) {
            super.setExample(example.toString());
        }
        return this;
    }

    @Override
    protected Object cast(final Object value) {
        return value;
    }

    @Override
    public boolean equals(final java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ObjectSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
