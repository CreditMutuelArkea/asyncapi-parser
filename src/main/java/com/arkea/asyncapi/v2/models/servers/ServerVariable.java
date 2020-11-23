package com.arkea.asyncapi.v2.models.servers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ServerVariable
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#serverVariableObject"
 */

public class ServerVariable {

    /** An enumeration of string values to be used if the substitution options are from a limited set. */
    private List<String> _enum = null;

    /** The default value to use for substitution, and to send, if an alternate value is not supplied. */
    private String _default = null;

    /** An optional description for the server variable. CommonMark syntax MAY be used for rich text representation. */
    private String description = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the _enum property from a ServerVariable instance.
     *
     * @return List<String> _enum
     **/

    public List<String> getEnum() {
        return this._enum;
    }

    public void setEnum(final List<String> _enum) {
        this._enum = _enum;
    }

    public ServerVariable _enum(final List<String> _enum) {
        this._enum = _enum;
        return this;
    }

    public ServerVariable addEnumItem(final String _enumItem) {
        if (this._enum == null) {
            this._enum = new ArrayList<>();
        }
        this._enum.add(_enumItem);
        return this;
    }

    /**
     * returns the _default property from a ServerVariable instance.
     *
     * @return String _default
     **/

    public String getDefault() {
        return this._default;
    }

    public void setDefault(final String _default) {
        this._default = _default;
    }

    public ServerVariable _default(final String _default) {
        this._default = _default;
        return this;
    }

    /**
     * returns the description property from a ServerVariable instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ServerVariable description(final String description) {
        this.description = description;
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
        final ServerVariable serverVariable = (ServerVariable) o;
        return Objects.equals(this._enum, serverVariable._enum) &&
                        Objects.equals(this._default, serverVariable._default) &&
                        Objects.equals(this.description, serverVariable.description) &&
                        Objects.equals(this.extensions, serverVariable.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._enum, this._default, this.description, this.extensions);
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

    public ServerVariable extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ServerVariable {\n");

        sb.append("    _enum: ").append(toIndentedString(this._enum)).append("\n");
        sb.append("    _default: ").append(toIndentedString(this._default)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
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
