package com.arkea.asyncapi.v2.models.media;

import java.util.Objects;

/**
 * ArraySchema
 */

public class ArraySchema extends Schema<Object> {

    private Schema<?> items = null;

    public ArraySchema() {
        super("array", null);
    }

    @Override
    public ArraySchema type(final String type) {
        super.setType(type);
        return this;
    }

    /**
     * returns the items property from a ArraySchema instance.
     *
     * @return Schema items
     **/

    public Schema<?> getItems() {
        return this.items;
    }

    public void setItems(final Schema<?> items) {
        this.items = items;
    }

    public ArraySchema items(final Schema<?> items) {
        this.items = items;
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
        final ArraySchema arraySchema = (ArraySchema) o;
        return Objects.equals(this.items, arraySchema.items) &&
                        super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.items, super.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ArraySchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    items: ").append(toIndentedString(this.items)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
