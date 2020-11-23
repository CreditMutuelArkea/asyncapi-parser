package com.arkea.asyncapi.v2.models.messages;

/**
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#correlationIdObject"
 */
public class CorrelationID {

    /** An optional description of the identifier. CommonMark syntax can be used for rich text representation. */
    private String description = null;

    /** REQUIRED. A runtime expression that specifies the location of the correlation ID. */
    private String location = null;

    /** Allows for an external definition of this channel item. */
    private String $ref = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public java.util.Map<String, Object> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    /**
     * returns the $ref property from a CorrelationID instance.
     *
     * @return String $ref
     **/
    public String get$ref() {
        return this.$ref;
    }

    // TODO verifier si c'est la bonne ref
    public void set$ref(String $ref) {
        if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
            $ref = "#/components/messages/correlationId/" + $ref;
        }
        this.$ref = $ref;
    }

    public CorrelationID $ref(final String $ref) {

        set$ref($ref);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.$ref == null ? 0 : this.$ref.hashCode());
        result = prime * result + (this.description == null ? 0 : this.description.hashCode());
        result = prime * result + (this.extensions == null ? 0 : this.extensions.hashCode());
        result = prime * result + (this.location == null ? 0 : this.location.hashCode());
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
        final CorrelationID other = (CorrelationID) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "CorrelationID [description=" + this.description + ", location=" + this.location + ", $ref=" + this.$ref
                        + ", extensions=" + this.extensions + "]";
    }

}
