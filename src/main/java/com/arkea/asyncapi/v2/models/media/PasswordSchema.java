package com.arkea.asyncapi.v2.models.media;

import java.util.Objects;

/**
 * PasswordSchema
 */

public class PasswordSchema extends Schema<String> {

    public PasswordSchema() {
        super("string", "password");
    }

    @Override
    public PasswordSchema type(final String type) {
        super.setType(type);
        return this;
    }

    @Override
    public PasswordSchema format(final String format) {
        super.setFormat(format);
        return this;
    }

    public PasswordSchema _default(final String _default) {
        super.setDefault(_default);
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

    public PasswordSchema addEnumItem(final String _enumItem) {
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
        sb.append("class PasswordSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
