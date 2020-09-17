package com.arkea.asyncapi.v2.models.media;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.arkea.asyncapi.v2.models.ExternalDocumentation;


/**
 * Schema
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#schemaObject"
 */

public class Schema<T> {

	/** //The default value represents what would be assumed by the consumer of the input as the value of the schema if one is not provided.
	 *  Unlike JSON Schema, the value MUST conform to the defined type for the Schema Object defined at the same level.
	 *   For example, of type is string, then default can be "foo" but cannot be 1. */
    protected T _default;

//TODO finir de gerer tous les types


    private String title = null;
    private String type = null;
    private List<String> required = null;
    private BigDecimal multipleOf = null;
    private BigDecimal maximum = null;
    private Boolean exclusiveMaximum = null;
    private BigDecimal minimum = null;
    private Boolean exclusiveMinimum = null;
    private Integer maxLength = null;
    private Integer minLength = null;
    /** This string SHOULD be a valid regular expression, according to the ECMA 262 regular expression dialect */
    private String pattern = null;
    private Integer maxItems = null;
    private Integer minItems = null;
    private Boolean uniqueItems = null;
    private Integer maxProperties = null;
    private Integer minProperties = null;
    protected List<T> _enum = null;
//    const
    protected T example = null;
//  if / then / else
    private Boolean readOnly = null;
    private Boolean writeOnly = null;
    private Map<String, Schema> properties = null;
//  patternProperties
    private Object additionalProperties = null;
//  additionalItems
//  items
//  propertyNames
//  contains
//  allOf
//  oneOf
//  anyOf
  private Schema not = null;

    /** CommonMark syntax can be used for rich text representation. */
    private String description = null;
    /** See Data Type Formats for further details.
     * While relying on JSON Schema's defined formats, the AsyncAPI Specification offers a few additional predefined formats. */
    private String format = null;
    /** Allows for an external definition of this channel item. */
    private String $ref = null;

    /** Adds support for polymorphism. The discriminator is the schema property name that is used to differentiate between other schema that inherit this schema.
     *  The property name used MUST be defined at this schema and it MUST be in the required property list.
     *   When used, the value MUST be the name of this schema or any schema that inherits it. See Composition and Inheritance for more details. */
    private String discriminator = null;
    /** Additional external documentation for this schema. */
    private ExternalDocumentation externalDocs = null;
    /** Specifies that a schema is deprecated and SHOULD be transitioned out of usage. Default value is false. */
    private Boolean deprecated = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private Map<String, Object> extensions = null;



    public Schema() {
    }

    protected Schema(final String type, final String format) {
        this.type = type;
        this.format = format;
    }


    /**
     * returns the discriminator property from a Schema instance.
     *
     * @return
     */
    public String getDiscriminator() {
		return this.discriminator;
	}

	/**
	 * Set the discriminator property to Schema instance.
	 *
	 * @param discriminator
	 */
	public void setDiscriminator(final String discriminator) {
		this.discriminator = discriminator;
	}

	/**
     * returns the title property from a Schema instance.
     *
     * @return String title
     **/

    public String getTitle() {
        return this.title;
    }

    /**
     * Set the discriminator property to Schema instance.
     *
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Set the discriminator property to Schema instance.
     *
     * @param title
     * @return Schema instance
     */
    public Schema title(final String title) {
        this.title = title;
        return this;
    }

    /**
     * returns the _default property from a Schema instance.
     *
     * @return String _default
     **/

    public T getDefault() {
        return this._default;
    }

    public void setDefault(final Object _default) {
        this._default = cast(_default);
    }

    @SuppressWarnings("unchecked")
    protected T cast(final Object value) {
        return (T) value;
    }

    public List<T> getEnum() {
        return this._enum;
    }

    public void setEnum(final List<T> _enum) {
        this._enum = _enum;
    }

    public void addEnumItemObject(final T _enumItem) {
        if (this._enum == null) {
            this._enum = new ArrayList<>();
        }
        this._enum.add(cast(_enumItem));
    }

    /**
     * returns the multipleOf property from a Schema instance.
     *
     * minimum: 0
     *
     * @return BigDecimal multipleOf
     **/

    public BigDecimal getMultipleOf() {
        return this.multipleOf;
    }

    public void setMultipleOf(final BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
    }

    /**
     * @param multipleOf
     * @return Schema instance
     */
    public Schema multipleOf(final BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
        return this;
    }

    /**
     * returns the maximum property from a Schema instance.
     *
     * @return BigDecimal maximum
     **/

    public BigDecimal getMaximum() {
        return this.maximum;
    }

    public void setMaximum(final BigDecimal maximum) {
        this.maximum = maximum;
    }

    /**
     * @param maximum
     * @return Schema instance
     */
    public Schema maximum(final BigDecimal maximum) {
        this.maximum = maximum;
        return this;
    }

    /**
     * returns the exclusiveMaximum property from a Schema instance.
     *
     * @return Boolean exclusiveMaximum
     **/

    public Boolean getExclusiveMaximum() {
        return this.exclusiveMaximum;
    }

    public void setExclusiveMaximum(final Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    /**
     * @param exclusiveMaximum
     * @return Schema instance
     */
    public Schema exclusiveMaximum(final Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
        return this;
    }

    /**
     * returns the minimum property from a Schema instance.
     *
     * @return BigDecimal minimum
     **/

    public BigDecimal getMinimum() {
        return this.minimum;
    }

    public void setMinimum(final BigDecimal minimum) {
        this.minimum = minimum;
    }

    /**
     * @param minimum
     * @return Schema instance
     */
    public Schema minimum(final BigDecimal minimum) {
        this.minimum = minimum;
        return this;
    }

    /**
     * returns the exclusiveMinimum property from a Schema instance.
     *
     * @return Boolean exclusiveMinimum
     **/

    public Boolean getExclusiveMinimum() {
        return this.exclusiveMinimum;
    }

    public void setExclusiveMinimum(final Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public Schema exclusiveMinimum(final Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
        return this;
    }

    /**
     * returns the maxLength property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer maxLength
     **/

    public Integer getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(final Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Schema maxLength(final Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    /**
     * returns the minLength property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer minLength
     **/

    public Integer getMinLength() {
        return this.minLength;
    }

    public void setMinLength(final Integer minLength) {
        this.minLength = minLength;
    }

    public Schema minLength(final Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    /**
     * returns the pattern property from a Schema instance.
     *
     * @return String pattern
     **/

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public Schema pattern(final String pattern) {
        this.pattern = pattern;
        return this;
    }

    /**
     * returns the maxItems property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer maxItems
     **/

    public Integer getMaxItems() {
        return this.maxItems;
    }

    public void setMaxItems(final Integer maxItems) {
        this.maxItems = maxItems;
    }

    public Schema maxItems(final Integer maxItems) {
        this.maxItems = maxItems;
        return this;
    }

    /**
     * returns the minItems property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer minItems
     **/

    public Integer getMinItems() {
        return this.minItems;
    }

    public void setMinItems(final Integer minItems) {
        this.minItems = minItems;
    }

    public Schema minItems(final Integer minItems) {
        this.minItems = minItems;
        return this;
    }

    /**
     * returns the uniqueItems property from a Schema instance.
     *
     * @return Boolean uniqueItems
     **/

    public Boolean getUniqueItems() {
        return this.uniqueItems;
    }

    public void setUniqueItems(final Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public Schema uniqueItems(final Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
        return this;
    }

    /**
     * returns the maxProperties property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer maxProperties
     **/

    public Integer getMaxProperties() {
        return this.maxProperties;
    }

    public void setMaxProperties(final Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    public Schema maxProperties(final Integer maxProperties) {
        this.maxProperties = maxProperties;
        return this;
    }

    /**
     * returns the minProperties property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer minProperties
     **/

    public Integer getMinProperties() {
        return this.minProperties;
    }

    public void setMinProperties(final Integer minProperties) {
        this.minProperties = minProperties;
    }

    public Schema minProperties(final Integer minProperties) {
        this.minProperties = minProperties;
        return this;
    }

    /**
     * returns the required property from a Schema instance.
     *
     * @return List&lt;String&gt; required
     **/

    public List<String> getRequired() {
        return this.required;
    }

    public void setRequired(final List<String> required) {
        List<String> list = new ArrayList<>();
        if (required != null) {
            for (final String req : required) {
                if (this.properties == null || this.properties.containsKey(req)) {
                    list.add(req);
                }
            }
        }
        Collections.sort(list);
        if (list.isEmpty()) {
            list = null;
        }
        this.required = list;
    }

    public Schema required(final List<String> required) {
        this.required = required;
        return this;
    }

    public Schema addRequiredItem(final String requiredItem) {
        if (this.required == null) {
            this.required = new ArrayList<>();
        }
        this.required.add(requiredItem);
        Collections.sort(this.required);
        return this;
    }

    /**
     * returns the type property from a Schema instance.
     *
     * @return String type
     **/

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Schema type(final String type) {
        this.type = type;
        return this;
    }

    /**
     * returns the not property from a Schema instance.
     *
     * @return Schema not
     **/

    public Schema getNot() {
        return this.not;
    }

    public void setNot(final Schema not) {
        this.not = not;
    }

    public Schema not(final Schema not) {
        this.not = not;
        return this;
    }

    /**
     * returns the properties property from a Schema instance.
     *
     * @return Map&lt;String, Schema&gt; properties
     **/

    public Map<String, Schema> getProperties() {
        return this.properties;
    }

    public void setProperties(final Map<String, Schema> properties) {
        this.properties = properties;
    }

    public Schema properties(final Map<String, Schema> properties) {
        this.properties = properties;
        return this;
    }

    public Schema addProperties(final String key, final Schema propertiesItem) {
        if (this.properties == null) {
            this.properties = new LinkedHashMap<>();
        }
        this.properties.put(key, propertiesItem);
        return this;
    }

    /**
     * returns the additionalProperties property from a Schema instance. Can be either a Boolean or a Schema
     *
     * @return Object additionalProperties
     **/

    public Object getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(final Object additionalProperties) {
        if (additionalProperties != null && !(additionalProperties instanceof Boolean) && !(additionalProperties instanceof Schema)) {
            throw new IllegalArgumentException("additionalProperties must be either a Boolean or a Schema instance");
        }
        this.additionalProperties = additionalProperties;
    }

    public Schema additionalProperties(final Object additionalProperties) {
        setAdditionalProperties(additionalProperties);
        return this;
    }

    /**
     * returns the description property from a Schema instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Schema description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the format property from a Schema instance.
     *
     * @return String format
     **/

    public String getFormat() {
        return this.format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public Schema format(final String format) {
        this.format = format;
        return this;
    }

    /**
     * returns the $ref property from a Schema instance.
     *
     * @return String $ref
     **/
    public String get$ref() {
        return this.$ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
            $ref = "#/components/schemas/" + $ref;
        }
        this.$ref = $ref;
    }

    public Schema $ref(final String $ref) {

        set$ref($ref);
        return this;
    }

    /**
     * returns the readOnly property from a Schema instance.
     *
     * @return Boolean readOnly
     **/

    public Boolean getReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(final Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Schema readOnly(final Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * returns the writeOnly property from a Schema instance.
     *
     * @return Boolean writeOnly
     **/

    public Boolean getWriteOnly() {
        return this.writeOnly;
    }

    public void setWriteOnly(final Boolean writeOnly) {
        this.writeOnly = writeOnly;
    }

    public Schema writeOnly(final Boolean writeOnly) {
        this.writeOnly = writeOnly;
        return this;
    }

    /**
     * returns the example property from a Schema instance.
     *
     * @return String example
     **/

    public Object getExample() {
        return this.example;
    }

    public void setExample(final Object example) {
        this.example = cast(example);
    }

    public Schema example(final Object example) {
        setExample(example);
        return this;
    }

    /**
     * returns the externalDocs property from a Schema instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return this.externalDocs;
    }

    public void setExternalDocs(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public Schema externalDocs(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
        return this;
    }

    /**
     * returns the deprecated property from a Schema instance.
     *
     * @return Boolean deprecated
     **/

    public Boolean getDeprecated() {
        return this.deprecated;
    }

    public void setDeprecated(final Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Schema deprecated(final Boolean deprecated) {
        this.deprecated = deprecated;
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
        final Schema schema = (Schema) o;
        return Objects.equals(this.title, schema.title) &&
                Objects.equals(this.multipleOf, schema.multipleOf) &&
                Objects.equals(this.maximum, schema.maximum) &&
                Objects.equals(this.exclusiveMaximum, schema.exclusiveMaximum) &&
                Objects.equals(this.minimum, schema.minimum) &&
                Objects.equals(this.exclusiveMinimum, schema.exclusiveMinimum) &&
                Objects.equals(this.maxLength, schema.maxLength) &&
                Objects.equals(this.minLength, schema.minLength) &&
                Objects.equals(this.pattern, schema.pattern) &&
                Objects.equals(this.maxItems, schema.maxItems) &&
                Objects.equals(this.minItems, schema.minItems) &&
                Objects.equals(this.uniqueItems, schema.uniqueItems) &&
                Objects.equals(this.maxProperties, schema.maxProperties) &&
                Objects.equals(this.minProperties, schema.minProperties) &&
                Objects.equals(this.required, schema.required) &&
                Objects.equals(this.type, schema.type) &&
                Objects.equals(this.not, schema.not) &&
                Objects.equals(this.properties, schema.properties) &&
                Objects.equals(this.additionalProperties, schema.additionalProperties) &&
                Objects.equals(this.description, schema.description) &&
                Objects.equals(this.format, schema.format) &&
                Objects.equals(this.$ref, schema.$ref) &&
                Objects.equals(this.readOnly, schema.readOnly) &&
                Objects.equals(this.writeOnly, schema.writeOnly) &&
                Objects.equals(this.example, schema.example) &&
                Objects.equals(this.externalDocs, schema.externalDocs) &&
                Objects.equals(this.deprecated, schema.deprecated) &&
                Objects.equals(this.extensions, schema.extensions) &&
                Objects.equals(this.discriminator, schema.discriminator) &&
                Objects.equals(this._enum, schema._enum) &&
                Objects.equals(this._default, schema._default);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.multipleOf, this.maximum, this.exclusiveMaximum, this.minimum, this.exclusiveMinimum, this.maxLength, this.minLength, this.pattern, this.maxItems,
                this.minItems, this.uniqueItems, this.maxProperties, this.minProperties, this.required, this.type, this.not, this.properties, this.additionalProperties, this.description, this.format, this.$ref,
                this.readOnly, this.writeOnly, this.example, this.externalDocs, this.deprecated, this.extensions, this.discriminator, this._enum, this._default);
    }

    /**
     * add an extention to the extention list of Schema instance.
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
     * Set the extensions property to an Schema instance.
     *
     * @param Map<String, Object> extensions
     */
    public void setExtensions(final java.util.Map<String, Object> extensions) {
    	this.extensions = extensions;
    }

    /**
     * Set the extensions property to an Schema instance.
     *
     * @param Map<String, Object> extensions
     * @return Schema instance
     */
    public Schema extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Schema {\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    format: ").append(toIndentedString(this.format)).append("\n");
        sb.append("    $ref: ").append(toIndentedString(this.$ref)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    title: ").append(toIndentedString(this.title)).append("\n");
        sb.append("    multipleOf: ").append(toIndentedString(this.multipleOf)).append("\n");
        sb.append("    maximum: ").append(toIndentedString(this.maximum)).append("\n");
        sb.append("    exclusiveMaximum: ").append(toIndentedString(this.exclusiveMaximum)).append("\n");
        sb.append("    minimum: ").append(toIndentedString(this.minimum)).append("\n");
        sb.append("    exclusiveMinimum: ").append(toIndentedString(this.exclusiveMinimum)).append("\n");
        sb.append("    maxLength: ").append(toIndentedString(this.maxLength)).append("\n");
        sb.append("    minLength: ").append(toIndentedString(this.minLength)).append("\n");
        sb.append("    pattern: ").append(toIndentedString(this.pattern)).append("\n");
        sb.append("    maxItems: ").append(toIndentedString(this.maxItems)).append("\n");
        sb.append("    minItems: ").append(toIndentedString(this.minItems)).append("\n");
        sb.append("    uniqueItems: ").append(toIndentedString(this.uniqueItems)).append("\n");
        sb.append("    maxProperties: ").append(toIndentedString(this.maxProperties)).append("\n");
        sb.append("    minProperties: ").append(toIndentedString(this.minProperties)).append("\n");
        sb.append("    required: ").append(toIndentedString(this.required)).append("\n");
        sb.append("    not: ").append(toIndentedString(this.not)).append("\n");
        sb.append("    properties: ").append(toIndentedString(this.properties)).append("\n");
        sb.append("    additionalProperties: ").append(toIndentedString(this.additionalProperties)).append("\n");
        sb.append("    readOnly: ").append(toIndentedString(this.readOnly)).append("\n");
        sb.append("    writeOnly: ").append(toIndentedString(this.writeOnly)).append("\n");
        sb.append("    example: ").append(toIndentedString(this.example)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(this.externalDocs)).append("\n");
        sb.append("    deprecated: ").append(toIndentedString(this.deprecated)).append("\n");
        sb.append("    discriminator: ").append(toIndentedString(this.discriminator)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    protected String toIndentedString(final java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

