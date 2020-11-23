package com.arkea.asyncapi.v2.models.tags;

import java.util.LinkedList;
import java.util.Objects;

import com.arkea.asyncapi.v2.models.ExternalDocumentation;

/**
 * Tag
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#tagsObject"
 */
// TODO extends LinkedList directement ?
public class Tag extends LinkedList {

    /** Required. The name of the tag. */
    private String name = null;

    /** A short description for the tag. CommonMark syntax can be used for rich text representation. */
    private String description = null;

    /** Additional external documentation for this tag. */
    private ExternalDocumentation externalDocs = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the name property from a Tag instance.
     *
     * @return String name
     **/

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Tag name(final String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the description property from a Tag instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Tag description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the externalDocs property from a Tag instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return this.externalDocs;
    }

    public void setExternalDocs(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public Tag externalDocs(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
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
        final Tag tag = (Tag) o;
        return Objects.equals(this.name, tag.name) &&
                        Objects.equals(this.description, tag.description) &&
                        Objects.equals(this.externalDocs, tag.externalDocs) &&
                        Objects.equals(this.extensions, tag.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.description, this.externalDocs, this.extensions);
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

    public Tag extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Tag {\n");

        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(this.externalDocs)).append("\n");
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
