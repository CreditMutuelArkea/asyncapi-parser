package com.arkea.asyncapi.v2.models.examples;

/**
 * Example
 */

public class Example {

    private String summary = null;

    private String description = null;

    private Object value = null;

    private String externalValue = null;

    /** Allows for an external definition of this channel item. */
    private String $ref = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the summary property from a Example instance.
     *
     * @return String summary
     **/

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public Example summary(final String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * returns the description property from a Example instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Example description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the value property from a Example instance.
     *
     * @return Object value
     **/

    public Object getValue() {
        return this.value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public Example value(final Object value) {
        this.value = value;
        return this;
    }

    /**
     * returns the externalValue property from a Example instance.
     *
     * @return String externalValue
     **/

    public String getExternalValue() {
        return this.externalValue;
    }

    public void setExternalValue(final String externalValue) {
        this.externalValue = externalValue;
    }

    public Example externalValue(final String externalValue) {
        this.externalValue = externalValue;
        return this;
    }

    public String get$ref() {
        return this.$ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
            $ref = "#/components/examples/" + $ref;
        }
        this.$ref = $ref;
    }

    public Example $ref(final String $ref) {
        set$ref($ref);
        return this;
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

    public Example extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Example)) {
            return false;
        }

        final Example example = (Example) o;

        if (this.summary != null ? !this.summary.equals(example.summary) : example.summary != null) {
            return false;
        }
        if (this.description != null ? !this.description.equals(example.description) : example.description != null) {
            return false;
        }
        if (this.value != null ? !this.value.equals(example.value) : example.value != null) {
            return false;
        }
        if (this.externalValue != null ? !this.externalValue.equals(example.externalValue) : example.externalValue != null) {
            return false;
        }
        if (this.$ref != null ? !this.$ref.equals(example.$ref) : example.$ref != null) {
            return false;
        }
        return this.extensions != null ? this.extensions.equals(example.extensions) : example.extensions == null;

    }

    @Override
    public int hashCode() {
        int result = this.summary != null ? this.summary.hashCode() : 0;
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.value != null ? this.value.hashCode() : 0);
        result = 31 * result + (this.externalValue != null ? this.externalValue.hashCode() : 0);
        result = 31 * result + (this.$ref != null ? this.$ref.hashCode() : 0);
        result = 31 * result + (this.extensions != null ? this.extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Example {\n");

        sb.append("    summary: ").append(toIndentedString(this.summary)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    value: ").append(toIndentedString(this.value)).append("\n");
        sb.append("    externalValue: ").append(toIndentedString(this.externalValue)).append("\n");
        sb.append("    $ref: ").append(toIndentedString(this.$ref)).append("\n");
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
