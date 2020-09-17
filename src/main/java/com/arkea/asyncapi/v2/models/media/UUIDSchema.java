package com.arkea.asyncapi.v2.models.media;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * UUIDSchema
 */

public class UUIDSchema extends Schema<UUID> {

    public UUIDSchema() {
        super("string", "uuid");
    }

    @Override
    public UUIDSchema type(final String type) {
        super.setType(type);
        return this;
    }

    @Override
    public UUIDSchema format(final String format) {
        super.setFormat(format);
        return this;
    }

    public UUIDSchema _default(final UUID _default) {
        super.setDefault(_default);
        return this;
    }

    public UUIDSchema _default(final String _default) {
        if (_default != null) {
            super.setDefault(UUID.fromString(_default));
        }
        return this;
    }

    public UUIDSchema _enum(final List<UUID> _enum) {
        super.setEnum(_enum);
        return this;
    }

    public UUIDSchema addEnumItem(final UUID _enumItem) {
        super.addEnumItemObject(_enumItem);
        return this;
    }

    @Override
    protected UUID cast(final Object value) {
        if (value != null) {
            try {
                return UUID.fromString(value.toString());
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
        sb.append("class UUIDSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
