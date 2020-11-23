package com.arkea.asyncapi.v2.models.media;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BooleanSchema
 */

public class BooleanSchema extends Schema<Boolean> {

    public BooleanSchema() {
        super("boolean", null);
    }

    @Override
    public BooleanSchema type(final String type) {
        super.setType(type);
        return this;
    }

    public BooleanSchema _default(final Boolean _default) {
        super.setDefault(_default);
        return this;
    }

    @Override
    protected Boolean cast(final Object value) {
        if (value != null) {
            try {
                return Boolean.parseBoolean(value.toString());
            } catch (final Exception e) {
            }
        }
        return null;
    }

    public BooleanSchema _enum(final List<Boolean> _enum) {
        this._enum = _enum;
        return this;
    }

    public BooleanSchema addEnumItem(final Boolean _enumItem) {
        if (this._enum == null) {
            this._enum = new ArrayList<Boolean>();
        }
        this._enum.add(_enumItem);
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
        sb.append("class BooleanSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
