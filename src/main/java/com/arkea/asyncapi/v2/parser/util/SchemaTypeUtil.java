package com.arkea.asyncapi.v2.parser.util;

import org.apache.commons.lang3.StringUtils;

import com.arkea.asyncapi.v2.models.media.BinarySchema;
import com.arkea.asyncapi.v2.models.media.BooleanSchema;
import com.arkea.asyncapi.v2.models.media.ByteArraySchema;
import com.arkea.asyncapi.v2.models.media.DateSchema;
import com.arkea.asyncapi.v2.models.media.DateTimeSchema;
import com.arkea.asyncapi.v2.models.media.EmailSchema;
import com.arkea.asyncapi.v2.models.media.IntegerSchema;
import com.arkea.asyncapi.v2.models.media.NumberSchema;
import com.arkea.asyncapi.v2.models.media.ObjectSchema;
import com.arkea.asyncapi.v2.models.media.PasswordSchema;
import com.arkea.asyncapi.v2.models.media.Schema;
import com.arkea.asyncapi.v2.models.media.StringSchema;
import com.arkea.asyncapi.v2.models.media.UUIDSchema;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SchemaTypeUtil {

    private static final String TYPE = "type";

    private static final String FORMAT = "format";

    public static final String INTEGER_TYPE = "integer";

    public static final String NUMBER_TYPE = "number";

    public static final String STRING_TYPE = "string";

    public static final String BOOLEAN_TYPE = "boolean";

    public static final String OBJECT_TYPE = "object";

    public static final String INTEGER32_FORMAT = "int32";

    public static final String INTEGER64_FORMAT = "int64";

    public static final String FLOAT_FORMAT = "float";

    public static final String DOUBLE_FORMAT = "double";

    public static final String BYTE_FORMAT = "byte";

    public static final String BINARY_FORMAT = "binary";

    public static final String DATE_FORMAT = "date";

    public static final String DATE_TIME_FORMAT = "date-time";

    public static final String PASSWORD_FORMAT = "password";

    public static final String EMAIL_FORMAT = "email";

    public static final String UUID_FORMAT = "uuid";

    public static Schema createSchemaByType(final ObjectNode node) {
        if (node == null) {
            return new Schema();
        }
        final String type = getNodeValue(node, TYPE);
        if (StringUtils.isBlank(type)) {
            return new Schema();
        }
        final String format = getNodeValue(node, FORMAT);

        return createSchema(type, format);
    }

    public static Schema createSchema(final String type, final String format) {

        if (INTEGER_TYPE.equals(type)) {
            if (StringUtils.isBlank(format)) {
                return new IntegerSchema().format(null);
            } else {
                return new IntegerSchema().format(format);
            }
        } else if (NUMBER_TYPE.equals(type)) {
            if (StringUtils.isBlank(format)) {
                return new NumberSchema();
            } else {
                return new NumberSchema().format(format);
            }
        } else if (BOOLEAN_TYPE.equals(type)) {
            if (StringUtils.isBlank(format)) {
                return new BooleanSchema();
            } else {
                return new BooleanSchema().format(format);
            }
        } else if (STRING_TYPE.equals(type)) {
            if (BYTE_FORMAT.equals(format)) {
                return new ByteArraySchema();
            } else if (BINARY_FORMAT.equals(format)) {
                return new BinarySchema();
            } else if (DATE_FORMAT.equals(format)) {
                return new DateSchema();
            } else if (DATE_TIME_FORMAT.equals(format)) {
                return new DateTimeSchema();
            } else if (PASSWORD_FORMAT.equals(format)) {
                return new PasswordSchema();
            } else if (EMAIL_FORMAT.equals(format)) {
                return new EmailSchema();
            } else if (UUID_FORMAT.equals(format)) {
                return new UUIDSchema();
            } else {
                if (StringUtils.isBlank(format)) {
                    return new StringSchema().format(null);
                } else {
                    return new StringSchema().format(format);
                }
            }
        } else if (OBJECT_TYPE.equals(type)) {
            return new ObjectSchema();
        } else {
            return new Schema();
        }
    }

    private static String getNodeValue(final ObjectNode node, final String field) {
        final JsonNode jsonNode = node.get(field);
        if (jsonNode == null) {
            return null;
        }
        return jsonNode.textValue();
    }

}
