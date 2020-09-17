package com.arkea.asyncapi.v2.models.media;

import java.util.List;
import java.util.Objects;

/**
 * StringSchema
 */

public class StringSchema extends Schema<String> {

    public StringSchema() {
        super("string", null);
    }

    @Override
    public StringSchema type(final String type) {
        super.setType(type);
        return this;
    }

    public StringSchema _default(final String _default) {
        super.setDefault(_default);
        return this;
    }

    public StringSchema _enum(final List<String> _enum) {
        super.setEnum(_enum);
        return this;
    }

    public StringSchema addEnumItem(final String _enumItem) {
        super.addEnumItemObject(_enumItem);
        return this;
    }

    @Override
    protected String cast(final Object value) {
        if (value != null) {
            try {
                return value.toString();
            } catch (final Exception e) {
            }
        }
        return null;
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
        sb.append("class StringSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
