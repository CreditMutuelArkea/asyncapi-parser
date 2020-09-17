package com.arkea.asyncapi.v2.models.info;

import java.util.Objects;

/**
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#info-object"
 */

public class Info {
	/** Required. The title of the application. */
    private String title = null;
    /** Required Provides the version of the application API (not to be confused with the specification version). */
    private String version = null;
    /** A short description of the application. CommonMark syntax can be used for rich text representation. */
    private String description = null;
    /** A URL to the Terms of Service for the API. MUST be in the format of a URL. */
    private String termsOfService = null;
    /** The contact information for the exposed API. */
    private Contact contact = null;
    /** The license information for the exposed API. */
    private License license = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * Returns the title property from an Info instance.
     *
     * @return String title
     **/
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title property to an Info instance.
     *
     * @param String title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Set the title property to an Info instance.
     *
     * @param String title
     *
     * @return Info instance
     **/
    public Info title(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Returns the description property from Info instance.
     *
     * @return String description
     **/
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property to a Info instance.
     *
     * @param String description
     **/
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Set the description property to a Info instance.
     *
     * @param String description
     * @return Info instance
     **/
    public Info description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the termsOfService property from a Info instance.
     *
     * @return String termsOfService
     **/
    public String getTermsOfService() {
        return this.termsOfService;
    }

    /**
     * Set the termsOfService property to Info instance.
     *
     * @param String termsOfService
     **/
    public void setTermsOfService(final String termsOfService) {
        this.termsOfService = termsOfService;
    }

    /**
     * Set the termsOfService property from a Info instance.
     *
     * @param String termsOfService
     *
     * @return Info instance
     **/
    public Info termsOfService(final String termsOfService) {
        this.termsOfService = termsOfService;
        return this;
    }

    /**
     * Returns the contact property from a Info instance.
     *
     * @return Contact contact
     **/
    public Contact getContact() {
        return this.contact;
    }

    /**
     *  Set the title property to an Info instance.
     *
     * @param contact
     */
    public void setContact(final Contact contact) {
        this.contact = contact;
    }

    /**
     * Returns the contact property from a Info instance.
     *
     * @param Contact contact
     * @return Info instance
     */
    public Info contact(final Contact contact) {
        this.contact = contact;
        return this;
    }

    /**
     * Returns the license property from a Info instance.
     *
     * @return License license
     **/

    public License getLicense() {
        return this.license;
    }

    /**
     * Set the title license to an Info instance.
     *
     * @param License license
     */
    public void setLicense(final License license) {
        this.license = license;
    }

    /**
     * Set the title license to an Info instance.
     *
     * @param License license
     * @return Info instance
     */
    public Info license(final License license) {
        this.license = license;
        return this;
    }

    /**
     * Returns the version property from a Info instance.
     *
     * @return String version
     **/

    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property to an Info instance.
     *
     * @param String version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Set the version property to an Info instance.
     *
     * @param String version
     * @return Info instance
     */
    public Info version(final String version) {
        this.version = version;
        return this;
    }

    /**
     * Returns the extensions property from Info instance.
     *
     * @return Map<String, Object> extensions
     */
    public java.util.Map<String, Object> getExtensions() {
    	return this.extensions;
    }

    /**
     * add an extention to the extention list of Info instance.
     *
     * @param String name
     * @param Object value
     */
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
     * Set the extensions property to an Info instance.
     *
     * @param Map<String, Object> extensions
     */
    public void setExtensions(final java.util.Map<String, Object> extensions) {
    	this.extensions = extensions;
    }

    /**
     * Set the extensions property to an Info instance.
     *
     * @param Map<String, Object> extensions
     * @return Info instance
     */
    public Info extensions(final java.util.Map<String, Object> extensions) {
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
        final Info info = (Info) o;
        return Objects.equals(this.title, info.title) &&
                Objects.equals(this.description, info.description) &&
                Objects.equals(this.termsOfService, info.termsOfService) &&
                Objects.equals(this.contact, info.contact) &&
                Objects.equals(this.license, info.license) &&
                Objects.equals(this.version, info.version) &&
                Objects.equals(this.extensions, info.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.description, this.termsOfService, this.contact, this.license, this.version, this.extensions);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Info {\n");

        sb.append("    title: ").append(toIndentedString(this.title)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    termsOfService: ").append(toIndentedString(this.termsOfService)).append("\n");
        sb.append("    contact: ").append(toIndentedString(this.contact)).append("\n");
        sb.append("    license: ").append(toIndentedString(this.license)).append("\n");
        sb.append("    version: ").append(toIndentedString(this.version)).append("\n");
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

