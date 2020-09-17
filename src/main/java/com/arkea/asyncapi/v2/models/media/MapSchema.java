package com.arkea.asyncapi.v2.models.media;

import java.util.Objects;

/**
 * MapSchema
 */

public class MapSchema extends Schema<Object> {

    public MapSchema() {
        super("object", null);
    }

    @Override
    public MapSchema type(final String type) {
        super.setType(type);
        return this;
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
        sb.append("class MapSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
