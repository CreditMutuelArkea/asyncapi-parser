package com.arkea.asyncapi.v2.models.media;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ComposedSchema
 */

public class ComposedSchema extends Schema<Object> {

    private List<Schema> allOf = null;

    private List<Schema> anyOf = null;

    private List<Schema> oneOf = null;

    /**
     * returns the allOf property from a ComposedSchema instance.
     *
     * @return List&lt;Schema&gt; allOf
     **/

    public List<Schema> getAllOf() {
        return this.allOf;
    }

    public void setAllOf(final List<Schema> allOf) {
        this.allOf = allOf;
    }

    public ComposedSchema allOf(final List<Schema> allOf) {
        this.allOf = allOf;
        return this;
    }

    public ComposedSchema addAllOfItem(final Schema allOfItem) {
        if (this.allOf == null) {
            this.allOf = new ArrayList<>();
        }
        this.allOf.add(allOfItem);
        return this;
    }

    /**
     * returns the anyOf property from a ComposedSchema instance.
     *
     * @return List&lt;Schema&gt; anyOf
     **/

    public List<Schema> getAnyOf() {
        return this.anyOf;
    }

    public void setAnyOf(final List<Schema> anyOf) {
        this.anyOf = anyOf;
    }

    public ComposedSchema anyOf(final List<Schema> anyOf) {
        this.anyOf = anyOf;
        return this;
    }

    public ComposedSchema addAnyOfItem(final Schema anyOfItem) {
        if (this.anyOf == null) {
            this.anyOf = new ArrayList<>();
        }
        this.anyOf.add(anyOfItem);
        return this;
    }

    /**
     * returns the oneOf property from a ComposedSchema instance.
     *
     * @return List&lt;Schema&gt; oneOf
     **/

    public List<Schema> getOneOf() {
        return this.oneOf;
    }

    public void setOneOf(final List<Schema> oneOf) {
        this.oneOf = oneOf;
    }

    public ComposedSchema oneOf(final List<Schema> oneOf) {
        this.oneOf = oneOf;
        return this;
    }

    public ComposedSchema addOneOfItem(final Schema oneOfItem) {
        if (this.oneOf == null) {
            this.oneOf = new ArrayList<>();
        }
        this.oneOf.add(oneOfItem);
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ComposedSchema allOfSchema = (ComposedSchema) o;
        return Objects.equals(this.allOf, allOfSchema.allOf) &&
                        Objects.equals(this.anyOf, allOfSchema.anyOf) &&
                        Objects.equals(this.oneOf, allOfSchema.oneOf) &&
                        super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.allOf, this.anyOf, this.oneOf, super.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ComposedSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    allOf: ").append(toIndentedString(this.allOf)).append("\n");
        sb.append("    anyOf: ").append(toIndentedString(this.anyOf)).append("\n");
        sb.append("    oneOf: ").append(toIndentedString(this.oneOf)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
