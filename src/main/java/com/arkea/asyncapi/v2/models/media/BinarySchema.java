package com.arkea.asyncapi.v2.models.media;

import java.util.List;
import java.util.Objects;

/**
 * BinarySchema
 */

public class BinarySchema extends Schema<byte[]> {

    public BinarySchema() {
        super("string", "binary");
    }

    @Override
    public BinarySchema type(final String type) {
        super.setType(type);
        return this;
    }

    @Override
    public BinarySchema format(final String format) {
        super.setFormat(format);
        return this;
    }

    public BinarySchema _default(final byte[] _default) {
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

    public BinarySchema _enum(final List<byte[]> _enum) {
        super.setEnum(_enum);
        return this;
    }

    public BinarySchema addEnumItem(final byte[] _enumItem) {
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
        return Objects.hash(this._default, this._enum, super.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class BinarySchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}

