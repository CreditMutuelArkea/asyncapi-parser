package com.arkea.asyncapi.v2.models.security;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Scopes
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#oauthFlowObject"
 *
 * The available scopes for the OAuth2 security scheme.
 * A map between the scope name and a short description for it.
 */

public class Scopes extends LinkedHashMap<String, String> {
    /**
	 *
	 */
	private static final long serialVersionUID = -7422763172095006854L;

	public Scopes() {
    }
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    public Scopes addString(final String name, final String item) {
        this.put(name, item);
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
        final Scopes scopes = (Scopes) o;
        return Objects.equals(this.extensions, scopes.extensions) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.extensions, super.hashCode());
    }

    /**
     * @deprecated As extensions don't make sense at this level
     */
    @Deprecated
    public java.util.Map<String, Object> getExtensions() {
        return this.extensions;
    }

    /**
     * @deprecated As extensions don't make sense at this level
     */
    @Deprecated
    public void addExtension(final String name, final Object value) {
        if (name == null || name.isEmpty() || !name.startsWith("x-")) {
            return;
        }
        if (this.extensions == null) {
            this.extensions = new java.util.LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    /**
     * @deprecated As extensions don't make sense at this level
     */
    @Deprecated
    public void setExtensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    /**
     * @deprecated As extensions don't make sense at this level
     */
    @Deprecated
    public Scopes extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Scopes {\n");
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

