package com.arkea.asyncapi.v2.models.info;

import java.util.Objects;

/**
 * Contact
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#contactObject"
 */

public class Contact {
	/** The identifying name of the contact person/organization. */
    private String name = null;
    /** The URL pointing to the contact information. MUST be in the format of a URL. */
    private String url = null;
    /** The email address of the contact person/organization. MUST be in the format of an email address. */
    private String email = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * Set the name property from a Contact instance.
     *
     * @return Contact instance
     **/

    public Contact name(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the url property from a Contact instance.
     *
     * @return Contact instance
     **/

    public Contact url(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Set the email property from a Contact instance.
     *
     * @return Contact instance
     **/

    public Contact email(final String email) {
        this.email = email;
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
        final Contact contact = (Contact) o;
        return Objects.equals(this.name, contact.name) &&
                Objects.equals(this.url, contact.url) &&
                Objects.equals(this.email, contact.email) &&
                Objects.equals(this.extensions, contact.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.url, this.email, this.extensions);
    }

    public java.util.Map<String, Object> getExtensions() {
        return this.extensions;
    }

    public void addExtension(final String name, final Object value) {
        if (name == null || name.isEmpty() || !name.startsWith("x-")) {
            return;
        }
        if (this.extensions == null) {
            this.extensions = new java.util.LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    public void setExtensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public Contact extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Contact {\n");

        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    url: ").append(toIndentedString(this.url)).append("\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
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

