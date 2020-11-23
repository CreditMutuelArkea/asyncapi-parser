package com.arkea.asyncapi.v2.models.security;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * SecurityRequirement
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#securityRequirementObject"
 */
public class SecurityRequirement<K> extends LinkedList<String> {

    /**
     *
     */
    private static final long serialVersionUID = 8279374414896857319L;

    public SecurityRequirement() {
    }

    public SecurityRequirement<K> addItem(final String item) {
        if (SecurityScheme.In.isIn(item)) {
            this.add(item);
        }
        return this;
    }

    public SecurityRequirement<K> addList(final List<String> item) {
        this.addAll(item);
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
        sb.append("class SecurityRequirement {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(final java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
