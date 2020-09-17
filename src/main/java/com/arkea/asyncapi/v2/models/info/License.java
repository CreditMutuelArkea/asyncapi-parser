package com.arkea.asyncapi.v2.models.info;

import java.util.Objects;

/**
 * License
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#licenseObject"
 */

public class License {
	/** Required. The license name used for the API. */
    private String name = null;
    /** A URL to the license used for the API. MUST be in the format of a URL. */
    private String url = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the name property from a License instance.
     *
     * @return String name
     **/

    public String getName() {
        return this.name;
    }

    /**
     * Set the name property to an License instance.
     *
     * @param String name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the name property to an License instance.
     *
     * @param String name
     * @return License instance
     */
    public License name(final String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the url property from a License instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return this.url;
    }

    /**
     * Set the url property to an License instance.
     *
     * @param String url
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Set the url property to an License instance.
     *
     * @param String url
     * @return  License instance
     */
    public License url(final String url) {
        this.url = url;
        return this;
    }

    /**
     * add an extention to the extention list of License instance.
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
     * Set the extensions property to an License instance.
     *
     * @param Map<String, Object> extensions
     */
    public void setExtensions(final java.util.Map<String, Object> extensions) {
    	this.extensions = extensions;
    }

    /**
     * Set the extensions property to an License instance.
     *
     * @param Map<String, Object> extensions
     * @return License instance
     */
    public License extensions(final java.util.Map<String, Object> extensions) {
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
        final License license = (License) o;
        return Objects.equals(this.name, license.name) &&
                Objects.equals(this.url, license.url) &&
                Objects.equals(this.extensions, license.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.url, this.extensions);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class License {\n");

        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
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

