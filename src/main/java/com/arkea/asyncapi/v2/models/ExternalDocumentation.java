package com.arkea.asyncapi.v2.models;

import java.util.Objects;

/**
 * ExternalDocumentation
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#externalDocumentationObject"
 */

/**
 * @author CNO
 *
 */
public class ExternalDocumentation {
	/** A short description of the target documentation. CommonMark syntax can be used for rich text representation. */
    private String description = null;
    /** Required. The URL for the target documentation. Value MUST be in the format of a URL. */
    private String url = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * Returns the description property from a ExternalDocumentation instance.
     *
     * @return String description
     **/
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property of ExternalDocumentation instance.
     *
     * @param description
     * @return ExternalDocumentation this
     */
    public ExternalDocumentation description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the url property from a ExternalDocumentation instance.
     *
     * @return String url
     **/
    public String getUrl() {
        return this.url;
    }


    /**
     * Set the url property of ExternalDocumentation instance.
     *
     * @param url
     * @return ExternalDocumentation this
     */
    public ExternalDocumentation url(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Returns the extensions property from a ExternalDocumentation instance.
     * @return Map<String, Object> extensions
     */
    public java.util.Map<String, Object> getExtensions() {
    	return this.extensions;
    }

    /**
     * Set the extensions property of ExternalDocumentation instance.
     *
     * @param extensions
     * @return ExternalDocumentation this
     */
    public ExternalDocumentation extensions(final java.util.Map<String, Object> extensions) {
    	this.extensions = extensions;
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
        final ExternalDocumentation externalDocumentation = (ExternalDocumentation) o;
        return Objects.equals(this.description, externalDocumentation.description) &&
                Objects.equals(this.url, externalDocumentation.url) &&
                Objects.equals(this.extensions, externalDocumentation.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.url, this.extensions);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ExternalDocumentation {\n");

        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    url: ").append(toIndentedString(this.url)).append("\n");
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

