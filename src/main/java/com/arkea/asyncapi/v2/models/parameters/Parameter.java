package com.arkea.asyncapi.v2.models.parameters;

import java.util.Map;

import com.arkea.asyncapi.v2.models.media.Schema;

/**
 * Parameter
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#parameterObject"
 */

public class Parameter {

    private String description = null;

    private Schema<?> schema = null;

    private String location = null;

    /** Allows for an external definition of this channel item. */
    private String $ref = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private Map<String, Object> extensions = null;

    /**
     * returns the description property from a Parameter instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Parameter description(final String description) {
        this.description = description;
        return this;
    }

    public Schema<?> getSchema() {
        return this.schema;
    }

    public void setSchema(final Schema<?> schema) {
        this.schema = schema;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String get$ref() {
        return this.$ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
            $ref = "#/components/parameters/" + $ref;
        }
        this.$ref = $ref;
    }

    public Parameter $ref(final String $ref) {
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

    public Parameter extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    static String toIndentedString(final java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.$ref == null ? 0 : this.$ref.hashCode());
        result = prime * result + (this.description == null ? 0 : this.description.hashCode());
        result = prime * result + (this.extensions == null ? 0 : this.extensions.hashCode());
        result = prime * result + (this.location == null ? 0 : this.location.hashCode());
        result = prime * result + (this.schema == null ? 0 : this.schema.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parameter other = (Parameter) obj;
        if (this.$ref == null) {
            if (other.$ref != null) {
                return false;
            }
        } else if (!this.$ref.equals(other.$ref)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        if (this.extensions == null) {
            if (other.extensions != null) {
                return false;
            }
        } else if (!this.extensions.equals(other.extensions)) {
            return false;
        }
        if (this.location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!this.location.equals(other.location)) {
            return false;
        }
        if (this.schema == null) {
            if (other.schema != null) {
                return false;
            }
        } else if (!this.schema.equals(other.schema)) {
            return false;
        }
        return true;
    }

}
