package com.arkea.asyncapi.v2.models.media;

import java.util.Objects;

/**
 * FileSchema
 */

public class FileSchema extends Schema<String> {

    public FileSchema() {
        super("string", "binary");
    }

    @Override
    public FileSchema type(final String type) {
        super.setType(type);
        return this;
    }

    @Override
    public FileSchema format(final String format) {
        super.setFormat(format);
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
        sb.append("class FileSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
