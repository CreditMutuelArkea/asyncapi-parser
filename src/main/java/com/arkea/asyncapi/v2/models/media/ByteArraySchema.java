package com.arkea.asyncapi.v2.models.media;

import java.util.List;
import java.util.Objects;

/**
 * ByteArraySchema
 */

public class ByteArraySchema extends Schema<byte[]> {

    public ByteArraySchema() {
        super("string", "byte");
    }

    @Override
    public ByteArraySchema type(final String type) {
        super.setType(type);
        return this;
    }

    @Override
    public ByteArraySchema format(final String format) {
        super.setFormat(format);
        return this;
    }

    public ByteArraySchema _default(final byte[] _default) {
        super.setDefault(_default);
        return this;
    }

    @Override
    protected byte[] cast(final Object value) {
        if (value != null) {
            try {
                if (value instanceof byte[]) {
                    return (byte[]) value;
                }
            } catch (final Exception e) {
            }
        }
        return null;
    }

    public ByteArraySchema _enum(final List<byte[]> _enum) {
        super.setEnum(_enum);
        return this;
    }

    public ByteArraySchema addEnumItem(final byte[] _enumItem) {
        super.addEnumItemObject(_enumItem);
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
        sb.append("class ByteArraySchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
