package com.arkea.asyncapi.v2.parser.util;

import static com.arkea.asyncapi.v2.parser.util.RefUtils.extractSimpleName;

import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arkea.asyncapi.v2.models.media.StringSchema;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arkea.asyncapi.v2.models.AsyncAPI;
import com.arkea.asyncapi.v2.models.Components;
import com.arkea.asyncapi.v2.models.ExternalDocumentation;
import com.arkea.asyncapi.v2.models.channels.Channel;
import com.arkea.asyncapi.v2.models.channels.ChannelBindings;
import com.arkea.asyncapi.v2.models.info.Contact;
import com.arkea.asyncapi.v2.models.info.Identifier;
import com.arkea.asyncapi.v2.models.info.Info;
import com.arkea.asyncapi.v2.models.info.License;
import com.arkea.asyncapi.v2.models.media.ArraySchema;
import com.arkea.asyncapi.v2.models.media.ByteArraySchema;
import com.arkea.asyncapi.v2.models.media.ComposedSchema;
import com.arkea.asyncapi.v2.models.media.DateSchema;
import com.arkea.asyncapi.v2.models.media.DateTimeSchema;
import com.arkea.asyncapi.v2.models.media.MapSchema;
import com.arkea.asyncapi.v2.models.media.ObjectSchema;
import com.arkea.asyncapi.v2.models.media.Schema;
import com.arkea.asyncapi.v2.models.messages.CorrelationID;
import com.arkea.asyncapi.v2.models.messages.HttpMessageBinding;
import com.arkea.asyncapi.v2.models.messages.KafkaMessageBinding;
import com.arkea.asyncapi.v2.models.messages.Message;
import com.arkea.asyncapi.v2.models.messages.MessageBinding;
import com.arkea.asyncapi.v2.models.messages.MessageTrait;
import com.arkea.asyncapi.v2.models.operations.HttpOperationBinding;
import com.arkea.asyncapi.v2.models.operations.KafkaOperationBinding;
import com.arkea.asyncapi.v2.models.operations.Operation;
import com.arkea.asyncapi.v2.models.operations.OperationBinding;
import com.arkea.asyncapi.v2.models.operations.OperationTrait;
import com.arkea.asyncapi.v2.models.parameters.Parameter;
import com.arkea.asyncapi.v2.models.security.OAuthFlow;
import com.arkea.asyncapi.v2.models.security.OAuthFlows;
import com.arkea.asyncapi.v2.models.security.Scopes;
import com.arkea.asyncapi.v2.models.security.SecurityRequirement;
import com.arkea.asyncapi.v2.models.security.SecurityScheme;
import com.arkea.asyncapi.v2.models.servers.MqttServerBinding;
import com.arkea.asyncapi.v2.models.servers.Server;
import com.arkea.asyncapi.v2.models.servers.ServerBinding;
import com.arkea.asyncapi.v2.models.servers.ServerVariable;
import com.arkea.asyncapi.v2.models.tags.Tag;
import com.arkea.asyncapi.v2.parser.models.AsyncParseResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

// CNO
// Might be decomposed but looks like exactly io.swagger.v3.parser.util.OpenAPIDeserializer

public class AsyncAPIDeserializer {

    final static Logger logger = LoggerFactory.getLogger(AsyncAPIDeserializer.class);

    protected static Set<String> ROOT_KEYS = new LinkedHashSet<>(Arrays.asList("asyncapi", "id", "info", "servers", "defaultContentType", "channels", "components", "tags", "externalDocs", "extensions"));

    protected static Set<String> INFO_KEYS = new LinkedHashSet<>(Arrays.asList("title", "version", "description", "termsOfService", "contact", "license", "extensions"));

    protected static Set<String> CONTACT_KEYS = new LinkedHashSet<>(Arrays.asList("name", "url", "email", "extensions"));

    protected static Set<String> LICENSE_KEYS = new LinkedHashSet<>(Arrays.asList("name", "url", "extensions"));

    protected static Set<String> TAG_KEYS = new LinkedHashSet<>(Arrays.asList("name", "description", "externalDocs", "extensions"));

    protected static Set<String> CHANNEL_KEYS = new LinkedHashSet<>(Arrays.asList("description", "subscribe", "publish", "parameters", "bindings", "extensions"));

    protected static Set<String> SERVER_KEYS = new LinkedHashSet<>(Arrays.asList("url", "protocol", "protocolVersion", "description", "variables", "security"));

    protected static Set<String> SERVER_BINDING_MQTT_KEYS = new LinkedHashSet<>(Arrays.asList("clientId",
                    "cleanSession", "lastWill", "lastWill.topic", "lastWill.qos", "lastWill.retain", "keepAlive", "bindingVersion", "extensions"));

    protected static Set<String> SERVER_VARIABLE_KEYS = new LinkedHashSet<>(Arrays.asList("enum", "default", "description", "extensions"));

    protected static Set<String> MESSAGE_KEYS = new LinkedHashSet<>(Arrays.asList("$ref", "headers", "payload", "correlationId", "schemaFormat", "contentType",
                    "name", "title", "summary", "description", "tags", "externalDocs", "bindings", "traits", "extensions"));

    protected static Set<String> MESSAGE_TRAIT_KEYS = new LinkedHashSet<>(Arrays.asList("$ref", "headers", "correlationId", "schemaFormat", "contentType",
                    "name", "title", "summary", "description", "tags", "externalDocs", "bindings", "extensions"));

    protected static Set<String> MESSAGE_BINDING_KEYS = new LinkedHashSet<>(Arrays.asList("http", "ws", "kafka", "amqp", "amqp1", "mqtt", "mqtt5", "nats", "jms", "sns", "sqs", "stomp", "redis", "mercure", "extensions"));

    protected static Set<String> OPERATION_KEYS = new LinkedHashSet<>(Arrays.asList("operationId", "summary", "description", "tags", "externalDocs", "bindings", "traits", "message", "extensions"));

    protected static Set<String> OPERATION_TRAIT_KEYS = new LinkedHashSet<>(Arrays.asList("$ref", "operationId", "summary", "description", "tags", "externalDocs", "bindings", "extensions"));

    protected static Set<String> OPERATION_BINDING_KEYS = new LinkedHashSet<>(Arrays.asList("http", "ws", "kafka", "amqp", "amqp1", "mqtt", "mqtt5", "nats", "jms", "sns", "sqs", "stomp", "redis", "mercure", "extensions"));

    protected static Set<String> OPERATION_BINDING_HTTP_KEYS = new LinkedHashSet<>(Arrays.asList("type", "method", "query", "bindingVersion", "extensions"));

    protected static Set<String> OPERATION_BINDING_KAFKA_KEYS = new LinkedHashSet<>(Arrays.asList("groupId", "clientId", "bindingVersion", "extensions"));

    protected static Set<String> PARAMETER_KEYS = new LinkedHashSet<>(Arrays.asList("$ref", "description", "schema", "location", "extensions"));

    protected static Set<String> CORRELATION_ID_KEYS = new LinkedHashSet<>(Arrays.asList("description", "location", "extensions"));

    protected static Set<String> SECURITY_SCHEME_KEYS = new LinkedHashSet<>(Arrays.asList("type", "description", "name", "in", "scheme", "bearerFormat", "flows", "openIdConnectUrl", "extensions"));

    protected static Set<String> EXTERNAL_DOCS_KEYS = new LinkedHashSet<>(Arrays.asList("description", "url", "extensions"));

    protected static Set<String> COMPONENTS_KEYS = new LinkedHashSet<>(Arrays.asList("schemas", "messages", "securitySchemes", "parameters", "correlationIds", "operationTraits", "messageTraits", "serverBindings", "channelBindings", "operationBindings", "messageBindings", "extensions"));

    protected static Set<String> SCHEMA_KEYS = new LinkedHashSet<>(Arrays.asList("$ref", "default", "title", "type", "required", "multipleOf", "maximum", "exclusiveMaximum", "minimum", "exclusiveMinimum", "maxLength", "minLength", "pattern", "maxItems", "minItems", "uniqueItems", "maxProperties",
                    "minProperties", "enum", "example", "readOnly", "writeOnly",
                    "allOf", "oneOf", "anyOf", "items", "nullable",
                    "properties", "additionalProperties", "not", "description", "format", "discriminator", "externalDocs", "deprecated", "extensions"));

    // protected static Set<String> EXAMPLE_KEYS = new LinkedHashSet<>(Arrays.asList("$ref", "summary", "description", "value", "externalValue"));

    protected static Set<String> OAUTHFLOWS_KEYS = new LinkedHashSet<>(Arrays.asList("implicit", "password", "clientCredentials", "authorizationCode", "extensions"));

    protected static Set<String> OAUTHFLOW_KEYS = new LinkedHashSet<>(Arrays.asList("authorizationUrl", "tokenUrl", "refreshUrl", "scopes", "extensions"));

    protected static List<String> OPERATION_BINDING_HTTP_METHODE_VALUES = Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD", "OPTIONS", "CONNECT", "TRACE");

    private static final String REQUEST = "request";

    private static final String RESPONSE = "response";

    private static final String LATEST = "latest";

    private static final Pattern RFC3339_DATE_TIME_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?((Z)|([+-]\\d{2}:\\d{2}))$");

    private static final Pattern RFC3339_DATE_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})$");

    private static final String REFERENCE_SEPARATOR = "#/";

    private static final String INVALID_TYPE = "Invalid type for : ";

    private Components components;

    private final Set<String> operationIDs = new HashSet<>();

    public AsyncParseResult deserialize(final JsonNode rootNode) {
        return deserialize(rootNode, null);
    }

    public AsyncParseResult deserialize(final JsonNode rootNode, final String path) {
        final AsyncParseResult result = new AsyncParseResult();
        try {

            final ParseResult rootParse = new ParseResult();
            final AsyncAPI api = parseRoot(rootNode, rootParse);
            result.setAsyncAPI(api);
            result.setMessages(rootParse.getParseMessages());

        } catch (final Exception e) {
            result.setMessages(Arrays.asList(e.getMessage()));
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * Return AsyncAPI formated model from the root node.
     *
     * @param node the root
     * @param result
     * @param path
     * @return
     *
     */
    public AsyncAPI parseRoot(final JsonNode node, final ParseResult result) {
        final String location = "";
        final AsyncAPI asyncAPI = new AsyncAPI();
        if (node.getNodeType().equals(JsonNodeType.OBJECT)) {
            final ObjectNode rootNode = (ObjectNode) node;

            // required
            String value = getString("asyncapi", rootNode, true, location, result);
            // we don't even try if the version isn't there
            if (value == null || !value.startsWith("2.0")) {
                return null;
            }
            asyncAPI.setAsyncapi(value);

            value = getString("defaultContentType", rootNode, false, location, result);
            if (value != null) {
                asyncAPI.setDefaultContentType(value);
            }

            value = getString("id", rootNode, false, location, result);
            if (value != null) {
                final Identifier id = new Identifier().id(value);
                asyncAPI.setId(id);
            }

            // required
            ObjectNode obj = getObject("info", rootNode, true, location, result);
            if (obj != null) {
                final Info info = getInfo(obj, "info", result);
                asyncAPI.setInfo(info);
            }

            obj = getObject("servers", rootNode, false, location, result);
            if (obj != null) {
                asyncAPI.setServers(getServersMap(obj, "servers", result));
            }

            // required
            obj = getObject("channels", rootNode, true, location, result);
            if (obj != null) {
                final Map<String, Channel> channels = getChannels(obj, "channels", result, false);
                asyncAPI.setChannels(channels);
            }

            obj = getObject("components", rootNode, false, location, result);
            if (obj != null) {
                final Components components = getComponents(obj, "components", result);
                asyncAPI.setComponents(components);
                this.components = components;
            }

            final ArrayNode array = getArray("tags", rootNode, false, location, result);
            if (array != null && array.size() > 0) {
                asyncAPI.setTags(getTagList(array, "tags", result));
            }

            obj = getObject("externalDocs", rootNode, false, location, result);
            if (obj != null) {
                final ExternalDocumentation externalDocs = getExternalDocs(obj, "externalDocs", result);
                asyncAPI.setExternalDocs(externalDocs);
            }

            final Map<String, Object> extensions = getExtensions(rootNode);
            if (extensions != null && extensions.size() > 0) {
                asyncAPI.setExtensions(extensions);
            }

            final Set<String> keys = getKeys(rootNode);
            for (final String key : keys) {
                if (!ROOT_KEYS.contains(key) && !key.startsWith("x-")) {
                    result.extra(location, key, node.get(key));
                }
            }

        } else {
            result.invalidType(location, "asyncapi", "object", node);
            result.invalid();
            logger.warn(INVALID_TYPE + "asyncapi");
            return null;
        }

        return asyncAPI;
    }

    public String mungedRef(final String refString) {
        // Ref: IETF RFC 3966, Section 5.2.2
        if (!refString.contains(":") && // No scheme
                        !refString.startsWith("#") && // Path is not empty
                        !refString.startsWith("/") && // Path is not absolute
                        refString.indexOf(".") > 0) { // Path does not start with dot but contains "." (file extension)
            return "./" + refString;
        }
        return null;
    }

    public Map<String, Object> getExtensions(final ObjectNode node) {

        final Map<String, Object> extensions = new LinkedHashMap<>();

        final Set<String> keys = getKeys(node);
        for (final String key : keys) {
            if (key.startsWith("x-")) {
                extensions.put(key, Json.mapper().convertValue(node.get(key), Object.class));
            }
        }
        return extensions;

    }

    public Components getComponents(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }
        final Components components = new Components();

        // private Map<String, Schema<?>> schemas = null;
        ObjectNode node = getObject("schemas", obj, false, location, result);
        if (node != null) {
            components.setSchemas(getSchemas(node, String.format("%s.%s", location, "schemas"), result, true));
        }

        // private Map<String, Message> messages = null;
        node = getObject("messages", obj, false, location, result);
        if (node != null) {
            components.setMessages(getMessages(node, String.format("%s.%s", location, "messages"), result, true));
        }

        // private Map<String, SecurityScheme> securitySchemes = null;
        node = getObject("securitySchemes", obj, false, location, result);
        if (node != null) {
            components.setSecuritySchemes(getSecuritySchemes(node, String.format("%s.%s", location, "securitySchemes"), result, true));
        }

        // In progress
        // private Map<String, Parameter> parameters = null;
        node = getObject("parameters", obj, false, location, result);
        if (node != null) {
            components.setParameters(getParameters(node, String.format("%s.%s", location, "parameters"), result, true));
        }

        // private Map<String, CorrelationID> correlationIds = null;
        node = getObject("correlationId", obj, false, location, result);
        if (node != null) {
            components.setCorrelationIds(getCorrelationIds(node, String.format("%s.%s", location, "correlationId"), result, true));
        }

        // private Map<String, OperationTrait> operationTraits = null; //Map[string, Operation Trait Object] An object to hold reusable Operation Trait Objects.
        node = getObject("operationTraits", obj, false, location, result);
        if (node != null) {
            components.setOperationTraits(getOperationTraits(node, String.format("%s.%s", location, "operationTraits"), result, true));
        }

        // private Map<String, MessageTrait> messageTraits = null;
        node = getObject("messageTraits", obj, false, location, result);
        if (node != null) {
            components.setMessageTraits(getMessageTraits(node, String.format("%s.%s", location, "messageTraits"), result, true));
        }

        // private Map<String, ServerBindings> serverBindings = null; // Map[string, Server Binding Object] An object to hold reusable Server Binding Objects.
        node = getObject("serverBindings", obj, false, location, result);
        if (node != null) {
            components.setServerBindings(getServerBindings(node, String.format("%s.%s", location, "serverBindings"), result, true));
        }

        // private Map<String, ChannelBindings> channelBindings = null;// channelBindings Map[string, Channel Binding Object] An object to hold reusable Channel Binding Objects.
        node = getObject("channelBindings", obj, false, location, result);
        if (node != null) {
            components.setChannelBindings(getChannelBindings(node, String.format("%s.%s", location, "channelBindings"), result, true));
        }

        // private Map<String, OperationBindings> operationBindings = null;// An object to hold reusable Operation Binding Objects.
        node = getObject("operationBindings", obj, false, location, result);
        if (node != null) {
            components.setOperationBindings(getOperationBindings(node, String.format("%s.%s", location, "operationBindings"), result, true));
        }

        // private Map<String, MessageBindings>messageBindings = null; // Map[string, Message Binding Object]
        node = getObject("messageBindings", obj, false, location, result);
        if (node != null) {
            components.setMessageBindings(getMessageBindings(node, String.format("%s.%s", location, "messageBindings"), result, true));
        }

        // private java.util.Map<String, Object> extensions = null;
        components.setExtensions(new LinkedHashMap<>());
        final Map<String, Object> extensions = getExtensions(obj);
        if (extensions != null && extensions.size() > 0) {
            components.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!COMPONENTS_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, obj.get(key));
            }
        }

        return components;
    }

    // OK
    public List<Tag> getTagList(final ArrayNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }
        final List<Tag> tags = new ArrayList<>();
        final Set<String> tagsTracker = new HashSet<>();
        for (final JsonNode item : obj) {
            if (item.getNodeType().equals(JsonNodeType.OBJECT)) {
                final Tag tag = getTag((ObjectNode) item, location, result);
                if (tag != null) {
                    tags.add(tag);

                    if (tagsTracker.contains(tag.getName())) {
                        result.uniqueTags(location, tag.getName());
                    }

                    tagsTracker.add(tag.getName());
                }
            }
        }
        return tags;
    }

    // OK
    public Tag getTag(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }

        final Tag tag = new Tag();
        String value = getString("name", obj, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            tag.setName(value);
        }

        value = getString("description", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            tag.setDescription(value);
        }

        final ObjectNode docs = getObject("externalDocs", obj, false, location, result);
        final ExternalDocumentation externalDocs = getExternalDocs(docs, String.format("%s.%s", location, "externalDocs"), result);
        if (externalDocs != null) {
            tag.setExternalDocs(externalDocs);
        }

        final Map<String, Object> extensions = getExtensions(obj);
        if (extensions != null && extensions.size() > 0) {
            tag.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!TAG_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, obj.get(key));
            }
        }

        return tag;
    }

    public Map<String, Server> getServersMap(final ObjectNode obj, final String location, final ParseResult result) {

        final Map<String, Server> servers = new LinkedHashMap<String, Server>();
        if (obj == null) {
            return null;

        }

        final Iterator<Map.Entry<String, JsonNode>> iter = obj.fields();
        while (iter.hasNext()) {
            final Map.Entry<String, JsonNode> entry = iter.next();

            if (entry.getValue().getNodeType().equals(JsonNodeType.OBJECT)) {
                final Server server = getServer((ObjectNode) entry.getValue(), location, result);
                if (server != null) {
                    servers.put(entry.getKey(), server);
                } else {
                    final Server defaultServer = new Server();
                    defaultServer.setUrl("/");
                    servers.put("null", defaultServer);
                }
            }
        }
        return servers;
    }

    public Server getServer(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }

        final Server server = new Server();

        // private String url = null;
        String value = getString("url", obj, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            server.setUrl(value);
        }

        // private String protocol = null;
        value = getString("protocol", obj, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            server.setProtocol(value);
        }
        // private String protocolVersion = null;
        value = getString("protocolVersion", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            server.setProtocolVersion(value);
        }

        // private String description = null;
        value = getString("description", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            server.setDescription(value);
        }

        // private ServerVariables variables = null;
        if (obj.get("variables") != null) {
            final ObjectNode variables = getObject("variables", obj, false, location, result);
            final Map<String, ServerVariable> serverVariables = getServerVariables(variables, String.format("%s.%s", location, "variables"), result);
            if (serverVariables != null && serverVariables.size() > 0) {
                server.setVariables(serverVariables);
            }
        }

        // private SecurityRequirement security = null;
        final ArrayNode docs = getArray("security", obj, false, location, result);
        final SecurityRequirement<String> security = getSecurityRequirementsList(docs, String.format("%s.%s", location, "security"), result);
        if (security != null) {
            server.setSecurity(security);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!SERVER_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, obj.get(key));
            }
        }

        return server;
    }

    boolean isValidURL(final String urlString) {
        try {
            final URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (final Exception exception) {
            logger.error("Invalid URL : " + urlString, exception.getMessage());
            return false;
        }
    }

    public Map<String, ServerVariable> getServerVariables(final ObjectNode obj, final String location, final ParseResult result) {
        final Map<String, ServerVariable> serverVariables = new LinkedHashMap<String, ServerVariable>();
        if (obj == null) {
            return null;
        }

        final Set<String> serverKeys = getKeys(obj);
        for (final String serverName : serverKeys) {
            final JsonNode serverValue = obj.get(serverName);
            final ObjectNode server = (ObjectNode) serverValue;
            final ServerVariable serverVariable = getServerVariable(server, String.format("%s.%s", location, serverName), result);
            serverVariables.put(serverName, serverVariable);
        }

        return serverVariables;
    }

    // OK
    private Map<String, ServerBinding> getServerBindings(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, ServerBinding> serverBindings = new LinkedHashMap<>();

        final Set<String> serverBindingKeys = getKeys(obj);
        for (final String serverBindingName : serverBindingKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[A-Za-z0-9_\\-]+$", // "^[a-zA-Z0-9\\.\\-_]+$",
                                serverBindingName)) {
                    result.warning(location, "Server Binding name " + serverBindingName + " doesn't adhere to regular expression ^[A-Za-z0-9_\\\\-]+$");
                }
            }
            final JsonNode serverBindingValue = obj.get(serverBindingName);
            if (!serverBindingValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, serverBindingName, "object", serverBindingValue);
            } else {
                final ObjectNode serverBindingNode = (ObjectNode) serverBindingValue;
                final ServerBinding serverBindingObj = getServerBinding(serverBindingNode, String.format("%s.%s", location, serverBindingName), result, serverBindingName);
                if (serverBindingObj != null) {
                    serverBindings.put(serverBindingName, serverBindingObj);
                }
            }
        }

        return serverBindings;
    }

    private ServerBinding getServerBinding(final ObjectNode node, final String location, final ParseResult result, final String serverBindingName) {
        if (node == null) {
            return null;
        }

        ServerBinding serverBindings = new ServerBinding();
        switch (ServerBinding.TypeEnum.getType(serverBindingName)) {
            case MQTT:
                serverBindings = getMqttServerBinding(node, String.format("%s.%s", location, serverBindingName), result);
                break;

            // TODO voir les differents formats qu'on va utiliser puis completer getServerBinding
            default:
                break;
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            serverBindings.setExtensions(extensions);
        }

        return serverBindings;
    }

    private ServerBinding getMqttServerBinding(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final MqttServerBinding serverBinding = new MqttServerBinding();
        // TODO faire getMqttServerBinding si ajout

        /** The version of this binding. If omitted, "latest" MUST be assumed. */
        final String value = getString("bindingVersion", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            serverBinding.setBindingVersion(value);
        } else {
            serverBinding.setBindingVersion(LATEST);
        }

        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!SERVER_BINDING_MQTT_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }
        return serverBinding;

    }

    public ServerVariable getServerVariable(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }

        final ServerVariable serverVariable = new ServerVariable();

        final ArrayNode arrayNode = getArray("enum", obj, false, location, result);
        if (arrayNode != null) {
            final List<String> _enum = new ArrayList<>();
            for (final JsonNode n : arrayNode) {
                if (n.isValueNode()) {
                    _enum.add(n.asText());
                    serverVariable.setEnum(_enum);
                } else {
                    result.invalidType(location, "enum", "value", n);
                }
            }
        }
        String value = getString("default", obj, true, String.format("%s.%s", location, "default"), result);
        if (StringUtils.isNotBlank(value)) {
            serverVariable.setDefault(value);
        }

        value = getString("description", obj, false, String.format("%s.%s", location, "description"), result);
        if (StringUtils.isNotBlank(value)) {
            serverVariable.setDescription(value);
        }

        final Map<String, Object> extensions = getExtensions(obj);
        if (extensions != null && extensions.size() > 0) {
            serverVariable.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!SERVER_VARIABLE_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, obj.get(key));
            }
        }

        return serverVariable;
    }

    // OK
    public Map<String, Channel> getChannels(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, Channel> channels = new LinkedHashMap<>();

        final Iterator<Map.Entry<String, JsonNode>> iter = obj.fields();
        while (iter.hasNext()) {
            final Map.Entry<String, JsonNode> entry = iter.next();

            if (entry.getValue().getNodeType().equals(JsonNodeType.OBJECT)) {
                final Channel channelObj = getChannel((ObjectNode) entry.getValue(), String.format("%s.%s", location, entry.getKey()), result);
                if (channelObj != null) {
                    channels.put(entry.getKey(), channelObj);
                }
            }
        }
        return channels;
    }

    // OK sauf ref
    public Channel getChannel(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final Channel channel = new Channel();

        final String value = getString("description", node, false, String.format("%s.%s", location, "description"), result);
        if (StringUtils.isNotBlank(value)) {
            channel.setDescription(value);
        }

        final ObjectNode subscribeNode = getObject("subscribe", node, false, String.format("%s.%s", location, "subscribe"), result);
        if (subscribeNode != null) {
            channel.setSubscribe(getOperation(subscribeNode, String.format("%s.%s", location, "subscribe"), result));
        }

        ObjectNode objectNode = getObject("publish", node, false, String.format("%s.%s", location, "publish"), result);
        if (objectNode != null) {
            channel.setPublish(getOperation(objectNode, String.format("%s.%s", location, "publish"), result));
        }

        objectNode = getObject("bindings", node, false, String.format("%s.%s", location, "bindings"), result);
        final Map<String, ChannelBindings> bindings = getChannelBindings(objectNode, String.format("%s.%s", location, "bindings"), result, false);
        if (bindings != null && bindings.size() > 0) {
            channel.setBindings(bindings);
        }

        // private Map<String,Parameter> parameters = null;
        objectNode = getObject("parameters", node, false, String.format("%s.%s", location, "parameters"), result);
        final Map<String, Parameter> parameters = getParameters(objectNode, String.format("%s.%s", location, "parameters"), result, false);
        if (parameters != null && parameters.size() > 0) {
            channel.setParameters(parameters);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            channel.setExtensions(extensions);
        }

        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!CHANNEL_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return channel;

    }

    // OK
    private Map<String, ChannelBindings> getChannelBindings(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, ChannelBindings> channelBindings = new LinkedHashMap<>();

        final Set<String> channelBindingKeys = getKeys(obj);
        for (final String channelBindingName : channelBindingKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[A-Za-z0-9_\\-]+$", // "^[a-zA-Z0-9\\.\\-_]+$",
                                channelBindingName)) {
                    result.warning(location, "Channel Binding name " + channelBindingName + " doesn't adhere to regular expression ^[A-Za-z0-9_\\\\-]+$");
                }
            }
            final JsonNode channelBindingValue = obj.get(channelBindingName);
            if (!channelBindingValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, channelBindingName, "object", channelBindingValue);
            } else {
                final ObjectNode channelBindingNode = (ObjectNode) channelBindingValue;
                final ChannelBindings channelBindingObj = getChannelBinding(channelBindingNode, String.format("%s.%s", location, channelBindingName), result);
                if (channelBindingObj != null) {
                    channelBindings.put(channelBindingName, channelBindingObj);
                }
            }
        }

        return channelBindings;
    }

    // TODO voir les differents formats qu'on va utiliser puis completer
    private ChannelBindings getChannelBinding(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final ChannelBindings channelBinding = new ChannelBindings();
        // ObjectNode itemsNode = getObject("description", node, false, location, result);

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            channelBinding.setExtensions(extensions);
        }

        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!SCHEMA_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return channelBinding;
    }

    // OK
    public ExternalDocumentation getExternalDocs(final ObjectNode node, final String location, final ParseResult result) {
        ExternalDocumentation externalDocs = null;

        if (node != null) {
            externalDocs = new ExternalDocumentation();

            String value = getString("description", node, false, location, result);
            if (StringUtils.isNotBlank(value)) {
                externalDocs.description(value);
            }

            value = getString("url", node, true, location, result);
            if (StringUtils.isNotBlank(value)) {
                externalDocs.url(value);
            }

            final Map<String, Object> extensions = getExtensions(node);
            if (extensions != null && extensions.size() > 0) {
                externalDocs.extensions(extensions);
            }

            final Set<String> keys = getKeys(node);
            for (final String key : keys) {
                if (!EXTERNAL_DOCS_KEYS.contains(key) && !key.startsWith("x-")) {
                    result.extra(location, key, node.get(key));
                }
            }
        }

        return externalDocs;
    }

    public String getString(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result, final Set<String> uniqueValues) {
        String value = null;
        final JsonNode v = node.get(key);
        if (node == null || v == null) {
            if (required) {
                result.missing(location, key);
                result.invalid();
            }
        } else if (!v.isValueNode()) {
            result.invalidType(location, key, "string", node);
        } else {
            value = v.asText();
            if (uniqueValues != null && !uniqueValues.add(value)) {
                result.unique(location, "operationId");
                result.invalid();
            }
        }
        return value;
    }

    public String getString(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result) {
        return getString(key, node, required, location, result, null);
    }

    public Set<String> getKeys(final ObjectNode node) {
        final Set<String> keys = new LinkedHashSet<>();
        if (node == null) {
            return keys;
        }

        final Iterator<String> it = node.fieldNames();
        while (it.hasNext()) {
            keys.add(it.next());
        }

        return keys;
    }

    public ObjectNode getObject(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result) {
        final JsonNode value = node.get(key);
        ObjectNode object = null;
        if (value == null) {
            if (required) {
                result.missing(location, key);
                result.invalid();
            }
        } else if (!value.getNodeType().equals(JsonNodeType.OBJECT) && !value.getNodeType().equals(JsonNodeType.STRING)) {
            result.invalidType(location, key, "object", value);
            if (required) {
                result.invalid();
            }
        } else {
            object = (ObjectNode) value;
        }
        return object;
    }

    public Info getInfo(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }
        final Info info = new Info();

        String value = getString("title", node, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            info.setTitle(value);
        }

        value = getString("version", node, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            info.setVersion(value);
        }

        value = getString("description", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            info.setDescription(value);
        }

        value = getString("termsOfService", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            info.setTermsOfService(value);
        }

        ObjectNode obj = getObject("contact", node, false, "contact", result);
        final Contact contact = getContact(obj, String.format("%s.%s", location, "contact"), result);
        if (obj != null) {
            info.setContact(contact);
        }

        obj = getObject("license", node, false, location, result);
        final License license = getLicense(obj, String.format("%s.%s", location, "license"), result);
        if (obj != null) {
            info.setLicense(license);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            info.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(node);
        for (final String key : keys) {
            if (!INFO_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return info;
    }

    // OK
    public License getLicense(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final License license = new License();

        String value = getString("name", node, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            license.setName(value);
        }

        value = getString("url", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            try {
                new URL(value);
            } catch (final Exception e) {
                logger.warn(e.getMessage());
                result.warning(location, value);
            }
            license.setUrl(value);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            license.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(node);
        for (final String key : keys) {
            if (!LICENSE_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return license;
    }

    // OK
    public Contact getContact(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final Contact contact = new Contact();

        String value = getString("name", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            contact.name(value);
        }

        value = getString("url", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            try {
                new URL(value);
            } catch (final Exception e) {
                logger.warn(e.getMessage());
                result.warning(location, value);
            }
            contact.url(value);
        }

        value = getString("email", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            contact.email(value);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            contact.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(node);
        for (final String key : keys) {
            if (!CONTACT_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return contact;
    }

    public ArrayNode getArray(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result) {
        final JsonNode value = node.get(key);
        ArrayNode arrayNode = null;
        if (value == null) {
            if (required) {
                result.missing(location, key);
                result.invalid();
            }
        } else if (!value.getNodeType().equals(JsonNodeType.ARRAY)) {
            result.invalidType(location, key, "array", value);
        } else {
            arrayNode = (ArrayNode) value;
        }
        return arrayNode;
    }

    public Boolean getBoolean(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result) {
        Boolean value = null;
        final JsonNode v = node.get(key);
        if (node == null || v == null) {
            if (required) {
                result.missing(location, key);
                result.invalid();
            }
        } else {
            if (v.getNodeType().equals(JsonNodeType.BOOLEAN)) {
                value = v.asBoolean();
            } else if (v.getNodeType().equals(JsonNodeType.STRING)) {
                final String stringValue = v.textValue();
                return Boolean.parseBoolean(stringValue);
            }
        }
        return value;
    }

    public BigDecimal getBigDecimal(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result) {
        BigDecimal value = null;
        final JsonNode v = node.get(key);
        if (node == null || v == null) {
            if (required) {
                result.missing(location, key);
                result.invalid();
            }
        } else if (v.getNodeType().equals(JsonNodeType.NUMBER)) {
            value = new BigDecimal(v.asText());
        } else if (!v.isValueNode()) {
            result.invalidType(location, key, "double", node);
        }
        return value;
    }

    public Integer getInteger(final String key, final ObjectNode node, final boolean required, final String location, final ParseResult result) {
        Integer value = null;
        final JsonNode v = node.get(key);
        if (node == null || v == null) {
            if (required) {
                result.missing(location, key);
                result.invalid();
            }
        } else if (v.getNodeType().equals(JsonNodeType.NUMBER)) {
            if (v.isInt()) {
                value = v.intValue();
            }
        } else if (!v.isValueNode()) {
            result.invalidType(location, key, "integer", node);
        }
        return value;
    }

    // In progress
    public Map<String, Parameter> getParameters(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, Parameter> parameters = new LinkedHashMap<>();
        final Set<String> filter = new HashSet<>();
        Parameter parameter = null;

        final Set<String> parameterKeys = getKeys(obj);
        for (final String parameterName : parameterKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[a-zA-Z0-9\\.\\-_]+$",
                                parameterName)) {
                    result.warning(location, "Parameter name " + parameterName + " doesn't adhere to regular expression ^[a-zA-Z0-9\\.\\-_]+$");
                }
            }

            final JsonNode parameterValue = obj.get(parameterName);
            if (parameterValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                final ObjectNode parameterObj = (ObjectNode) parameterValue;
                if (parameterObj != null) {
                    parameter = getParameter(parameterObj, String.format("%s.%s", location, parameterName), result);
                    if (parameter != null) {
                        // if(PATH_PARAMETER.equalsIgnoreCase(parameter.getIn()) && Boolean.FALSE.equals(parameter.getRequired())){
                        // result.warning(location, "For path parameter "+ parameterName + " the required value should be true");
                        // }
                        parameters.put(parameterName, parameter);
                    }
                }
            }

        }
        return parameters;
    }

    public List<Parameter> getParameterList(final ArrayNode obj, final String location, final ParseResult result) {
        final List<Parameter> parameters = new ArrayList<>();
        if (obj == null) {
            return parameters;
        }
        for (final JsonNode item : obj) {
            if (item.getNodeType().equals(JsonNodeType.OBJECT)) {
                final Parameter parameter = getParameter((ObjectNode) item, location, result);
                if (parameter != null) {
                    parameters.add(parameter);
                }
            }
        }
        final Set<String> filter = new HashSet<>();

        parameters.stream().map(this::getParameterDefinition).forEach(param -> {
            final String ref = param.get$ref();

            // TODO les parametres sont differents dans AsyncAPI
            // if(!filter.add(param.getName()+"#"+param.getIn())) {
            // if(ref != null) {
            // if (ref.startsWith(REFERENCE_SEPARATOR)) {// validate if it's inline param also
            // result.warning(location, "There are duplicate parameter values");
            // }
            // }else{
            // result.warning(location, "There are duplicate parameter values");
            // }
            // }
        });
        return parameters;
    }

    // TODO InProgress
    private Parameter getParameterDefinition(final Parameter parameter) {
        if (parameter.get$ref() == null) {
            return parameter;
        }
        final Object parameterSchemaName = extractSimpleName(parameter.get$ref()).getLeft();
        return Optional.ofNullable(this.components)
                        .map(Components::getParameters)
                        .map(parameters -> parameters.get(parameterSchemaName))
                        .orElse(parameter);

    }

    // OK
    public Parameter getParameter(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }

        Parameter parameter = new Parameter();

        // private String $ref = null;
        final JsonNode ref = obj.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {
                parameter = new Parameter();
                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    parameter.set$ref(mungedRef);
                } else {
                    parameter.set$ref(ref.textValue());
                }
                return parameter;
            } else {
                result.invalidType(location, "$ref", "string", obj);
                return null;
            }
        }

        // private String description = null;
        String value = getString("description", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            parameter.setDescription(value);
        }

        // TODO j'ai pas bien compris pour le location du parameter Object
        /** A runtime expression that specifies the location of the parameter value. Even when a definition for the target field exists, it MUST NOT be used to validate this parameter but, instead, the schema property MUST be used. */
        value = getString("location", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            parameter.setLocation(value);
        }

        // private Schema<?> schema = null;
        final ObjectNode node = getObject("schema", obj, false, location, result);
        if (node != null) {
            parameter.setSchema(getSchema(node, String.format("%s.%s", location, "schema"), result));
        }

        // private java.util.Map<String, Object> extensions = null;
        final Map<String, Object> extensions = getExtensions(obj);
        if (extensions != null && extensions.size() > 0) {
            parameter.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!PARAMETER_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }
        return parameter;
    }

    public Object getAnyExample(final String nodeKey, final ObjectNode node, final String location, final ParseResult result) {
        final JsonNode example = node.get(nodeKey);
        if (example != null) {
            if (example.getNodeType().equals(JsonNodeType.STRING)) {
                return getString(nodeKey, node, false, location, result);
            }
            if (example.getNodeType().equals(JsonNodeType.NUMBER)) {
                final Integer integerExample = getInteger(nodeKey, node, false, location, result);
                if (integerExample != null) {
                    return integerExample;
                } else {
                    final BigDecimal bigDecimalExample = getBigDecimal(nodeKey, node, false, location, result);
                    if (bigDecimalExample != null) {
                        return bigDecimalExample;
                    }
                }
            } else if (example.getNodeType().equals(JsonNodeType.OBJECT)) {
                final ObjectNode objectValue = getObject(nodeKey, node, false, location, result);
                if (objectValue != null) {
                    return objectValue;
                }
            } else if (example.getNodeType().equals(JsonNodeType.ARRAY)) {
                final ArrayNode arrayValue = getArray(nodeKey, node, false, location, result);
                if (arrayValue != null) {
                    return arrayValue;
                }
            } else if (example.getNodeType().equals(JsonNodeType.BOOLEAN)) {
                final Boolean bool = getBoolean(nodeKey, node, false, location, result);
                if (bool != null) {
                    return bool;
                }
            }
        }
        return null;
    }

    public Map<String, SecurityScheme> getSecuritySchemes(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, SecurityScheme> securitySchemes = new LinkedHashMap<>();

        final Set<String> securitySchemeKeys = getKeys(obj);
        for (final String securitySchemeName : securitySchemeKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[a-zA-Z0-9\\.\\-_]+$",
                                securitySchemeName)) {
                    result.warning(location, "SecurityScheme name " + securitySchemeName + " doesn't adhere to regular expression ^[a-zA-Z0-9\\.\\-_]+$");
                }
            }
            final JsonNode securitySchemeValue = obj.get(securitySchemeName);
            if (!securitySchemeValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, securitySchemeName, "object", securitySchemeValue);
            } else {
                final ObjectNode securityScheme = (ObjectNode) securitySchemeValue;
                final SecurityScheme securitySchemeObj = getSecurityScheme(securityScheme, String.format("%s.%s", location, securitySchemeName), result);
                if (securityScheme != null) {
                    securitySchemes.put(securitySchemeName, securitySchemeObj);
                }
            }
        }
        return securitySchemes;
    }

    public SecurityScheme getSecurityScheme(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final SecurityScheme securityScheme = new SecurityScheme();

        final JsonNode ref = node.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {
                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    securityScheme.set$ref(mungedRef);
                } else {
                    securityScheme.set$ref(ref.textValue());
                }
                return securityScheme;
            } else {
                result.invalidType(location, "$ref", "string", node);
                return null;
            }
        }

        boolean descriptionRequired, bearerFormatRequired, nameRequired, inRequired, schemeRequired, flowsRequired, openIdConnectRequired;
        descriptionRequired = bearerFormatRequired = nameRequired = inRequired = schemeRequired = flowsRequired = openIdConnectRequired = false;

        String value = getString("type", node, true, location, result);
        if (StringUtils.isNotBlank(value)) {
            if (SecurityScheme.Type.APIKEY.toString().equals(value)) {
                securityScheme.setType(SecurityScheme.Type.APIKEY);
                inRequired = true;
            } else if (SecurityScheme.Type.HTTPAPIKEY.toString().equals(value)) {
                securityScheme.setType(SecurityScheme.Type.HTTPAPIKEY);
                nameRequired = inRequired = schemeRequired = true;
            } else if (SecurityScheme.Type.HTTP.toString().equals(value)) {
                securityScheme.setType(SecurityScheme.Type.HTTP);
                schemeRequired = true;
            } else if (SecurityScheme.Type.OAUTH2.toString().equals(value)) {
                securityScheme.setType(SecurityScheme.Type.OAUTH2);
                flowsRequired = true;
            } else if (SecurityScheme.Type.OPENIDCONNECT.toString().equals(value)) {
                securityScheme.setType(SecurityScheme.Type.OPENIDCONNECT);
                openIdConnectRequired = true;
            } else {
                result.invalidType(location + ".type", "type", "http|apiKey|oauth2|openIdConnect ", node);
            }
        }
        value = getString("description", node, descriptionRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            securityScheme.setDescription(value);
        }

        value = getString("name", node, nameRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            securityScheme.setName(value);
        }

        final String securitySchemeIn = getString("in", node, inRequired, location, result);
        final Optional<SecurityScheme.In> matchingIn = Arrays.stream(SecurityScheme.In.values())
                        .filter(in -> in.toString().equals(securitySchemeIn))
                        .findFirst();

        securityScheme.setIn(matchingIn.orElse(null));

        value = getString("scheme", node, schemeRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            securityScheme.setScheme(value);
        }

        value = getString("bearerFormat", node, bearerFormatRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            securityScheme.setBearerFormat(value);
        }

        final ObjectNode flowsObject = getObject("flows", node, flowsRequired, location, result);
        if (flowsObject != null) {
            securityScheme.setFlows(getOAuthFlows(flowsObject, location, result));
        }

        value = getString("openIdConnectUrl", node, openIdConnectRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            securityScheme.setOpenIdConnectUrl(value);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            securityScheme.setExtensions(extensions);
        }

        final Set<String> securitySchemeKeys = getKeys(node);
        for (final String key : securitySchemeKeys) {
            if (!SECURITY_SCHEME_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return securityScheme;
    }

    // OK
    public Map<String, Message> getMessages(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, Message> messages = new LinkedHashMap<String, Message>();

        final Set<String> messageKeys = getKeys(obj);
        for (final String messageName : messageKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[a-zA-Z0-9\\.\\-_]+$",
                                messageName)) {
                    result.warning(location, "Message name " + messageName + " doesn't adhere to regular expression ^[a-zA-Z0-9\\.\\-_]+$");
                }
            }
            final JsonNode messageValue = obj.get(messageName);
            if (!messageValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, messageName, "object", messageValue);
            } else {
                final ObjectNode message = (ObjectNode) messageValue;
                final Message messageObj = getMessage(message, String.format("%s.%s", location, messageName), result);
                if (messageObj != null) {
                    messages.put(messageName, messageObj);
                }
            }
        }

        return messages;
    }

    // OK sauf payload
    public Message getMessage(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final Message message = new Message();
        final JsonNode ref = node.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {
                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    message.set$ref(mungedRef);
                } else {
                    message.set$ref(ref.textValue());
                }
                return message;
            } else {
                result.invalidType(location, "$ref", "string", node);
                return null;
            }
        }

        String value = getString("schemaFormat", node, false, String.format("%s.%s", location, "schemaFormat"), result);
        if (StringUtils.isNotBlank(value)) {
            message.setSchemaFormat(value);
        }

        value = getString("contentType", node, false, String.format("%s.%s", location, "contentType"), result);
        if (StringUtils.isNotBlank(value)) {
            message.setContentType(value);
        }

        value = getString("name", node, false, String.format("%s.%s", location, "name"), result);
        if (StringUtils.isNotBlank(value)) {
            message.setName(value);
        }

        value = getString("title", node, false, String.format("%s.%s", location, "title"), result);
        if (StringUtils.isNotBlank(value)) {
            message.setTitle(value);
        }

        value = getString("summary", node, false, String.format("%s.%s", location, "summary"), result);
        if (StringUtils.isNotBlank(value)) {
            message.setSummary(value);
        }

        value = getString("description", node, false, String.format("%s.%s", location, "description"), result);
        if (StringUtils.isNotBlank(value)) {
            message.setDescription(value);
        }

        final ObjectNode headersObject = getObject("headers", node, false, String.format("%s.%s", location, "headers"), result);
        if (headersObject != null) {
            message.setHeaders(getSchema(headersObject, location, result));
        }

        final ObjectNode payloadObject = getObject("payload", node, false, String.format("%s.%s", location, "payload"), result);
        if (payloadObject != null) {

            // TODO !!!!!!!!!!!!!!! attention payload formate en schema pour le moment !!!!!!!!!!!!!!!
            message.setPayload(getSchema(payloadObject, String.format("%s.%s", location, "payload"), result));
        }

        final ObjectNode correlationIdObject = getObject("correlationId", node, false, String.format("%s.%s", location, "correlationId"), result);
        if (correlationIdObject != null) {
            message.setCorrelationId(getCorrelationId(correlationIdObject, String.format("%s.%s", location, "correlationId"), result));
        }

        final ArrayNode array = getArray("tags", node, false, location, result);
        final List<Tag> tags = getTagList(array, String.format("%s.%s", location, "tags"), result);
        if (tags != null) {
            message.setTags(tags);
        }

        final ObjectNode externalDocs = getObject("externalDocs", node, false, location, result);
        if (externalDocs != null) {
            final ExternalDocumentation docs = getExternalDocs(externalDocs, location, result);
            if (docs != null) {
                message.setExternalDocs(docs);
            }
        }

        final ObjectNode bindingsObject = getObject("bindings", node, false, location, result);
        if (bindingsObject != null) {
            message.setBindings(getMessageBindings(bindingsObject, location, result, false));
        }

        // TODO traiter les exemple dans Message
        // examples [Map[string, any]] An array with examples of valid message objects.

        final ArrayNode traitsObject = getArray("traits", node, false, location, result);
        if (traitsObject != null) {
            message.setTraits(getMessageTraitList(traitsObject, location, result));
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            message.setExtensions(extensions);
        }

        final Set<String> messageKeys = getKeys(node);
        for (final String key : messageKeys) {
            if (!MESSAGE_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return message;
    }

    // OK
    public Map<String, MessageTrait> getMessageTraits(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, MessageTrait> messageTraits = new LinkedHashMap<String, MessageTrait>();

        final Set<String> messageTraitKeys = getKeys(obj);
        for (final String messageTraitName : messageTraitKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[a-zA-Z0-9\\.\\-_]+$",
                                messageTraitName)) {
                    result.warning(location, "Message Trait key " + messageTraitName + " doesn't adhere to regular expression ^[a-zA-Z0-9\\.\\-_]+$");
                }
            }
            final JsonNode messageTraitValue = obj.get(messageTraitName);
            if (!messageTraitValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, messageTraitName, "object", messageTraitValue);
            } else {
                final ObjectNode messageTrait = (ObjectNode) messageTraitValue;
                final MessageTrait messageTraitObj = getMessageTrait(messageTrait, String.format("%s.%s", location, messageTraitName), result);
                if (messageTraitObj != null) {
                    messageTraits.put(messageTraitName, messageTraitObj);
                }
            }
        }

        return messageTraits;
    }

    // OK sauf payload
    public MessageTrait getMessageTrait(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final MessageTrait messageTrait = new MessageTrait();
        final JsonNode ref = node.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {
                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    messageTrait.set$ref(mungedRef);
                } else {
                    messageTrait.set$ref(ref.textValue());
                }
                return messageTrait;
            } else {
                result.invalidType(location, "$ref", "string", node);
                return null;
            }
        }

        String value = getString("schemaFormat", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageTrait.setSchemaFormat(value);
        }

        value = getString("contentType", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageTrait.setContentType(value);
        }

        value = getString("name", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageTrait.setName(value);
        }

        value = getString("title", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageTrait.setTitle(value);
        }

        value = getString("summary", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageTrait.setSummary(value);
        }

        value = getString("description", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageTrait.setDescription(value);
        }

        final ObjectNode headersObject = getObject("headers", node, false, location, result);
        if (headersObject != null) {
            messageTrait.setHeaders(getSchema(headersObject, location, result));
        }

        final ObjectNode correlationIdObject = getObject("correlationId", node, false, location, result);
        if (correlationIdObject != null) {
            messageTrait.setCorrelationId(getCorrelationId(correlationIdObject, location, result));
        }

        final ArrayNode array = getArray("tags", node, false, location, result);
        final List<Tag> tags = getTagList(array, String.format("%s.%s", location, "tags"), result);
        if (tags != null) {
            messageTrait.setTags(tags);
        }

        final ObjectNode externalDocs = getObject("externalDocs", node, false, location, result);
        if (externalDocs != null) {
            final ExternalDocumentation docs = getExternalDocs(externalDocs, location, result);
            if (docs != null) {
                messageTrait.setExternalDocs(docs);
            }
        }

        final ObjectNode bindingsObject = getObject("bindings", node, false, location, result);
        if (bindingsObject != null) {
            messageTrait.setBindings(getMessageBindings(bindingsObject, location, result, false));
        }

        // TODO traiter les exemple dans MessageTrait
        // examples [Map[string, any]] An array with examples of valid messageTrait objects.

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            messageTrait.setExtensions(extensions);
        }

        final Set<String> messageTraitKeys = getKeys(node);
        for (final String key : messageTraitKeys) {
            if (!MESSAGE_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return messageTrait;
    }

    // OK
    private Map<String, MessageBinding> getMessageBindings(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, MessageBinding> messageBindings = new LinkedHashMap<>();

        final Set<String> messageBindingKeys = getKeys(obj);
        for (final String messageBindingName : messageBindingKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[A-Za-z0-9_\\-]+$", // "^[a-zA-Z0-9\\.\\-_]+$",
                                messageBindingName)) {
                    result.warning(location, "Message Binding name " + messageBindingName + " doesn't adhere to regular expression ^[A-Za-z0-9_\\\\-]+$");
                }
            }
            final JsonNode messageBindingValue = obj.get(messageBindingName);
            if (!messageBindingValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, messageBindingName, "object", messageBindingValue);
            } else {
                final ObjectNode messageBindingNode = (ObjectNode) messageBindingValue;
                final MessageBinding operationBindingObj = getMessageBinding(messageBindingNode, String.format("%s.%s", location, messageBindingName), result, messageBindingName);
                if (operationBindingObj != null) {
                    messageBindings.put(messageBindingName, operationBindingObj);
                }
            }
        }

        return messageBindings;
    }

    // TODO voir les differents formats qu'on va utiliser puis completer
    private MessageBinding getMessageBinding(final ObjectNode node, final String location, final ParseResult result, final String messageBindingName) {
        if (node == null) {
            return null;
        }

        MessageBinding messageBindings = new MessageBinding();
        switch (MessageBinding.TypeEnum.getType(messageBindingName)) {
            case HTTP:
                messageBindings = getHttpMessageBinding(node, String.format("%s.%s", location, messageBindingName), result);
                break;
            case KAFKA:
                messageBindings = getKafkaMessageBinding(node, String.format("%s.%s", location, messageBindingName), result);
                break;

            // TODO voir les differents formats qu'on va utiliser puis completer getOperationBinding
            default:
                break;
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            messageBindings.setExtensions(extensions);
        }

        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!MESSAGE_BINDING_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return messageBindings;
    }

    private MessageBinding getHttpMessageBinding(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final HttpMessageBinding messageBinding = new HttpMessageBinding();
        // TODO tester http binding

        /** A Schema object containing the definitions for HTTP-specific headers. This schema MUST be of type object and have a properties key. */
        final ObjectNode obj = getObject("headers", node, false, location, result);
        if (obj != null) {
            final Schema headers = getSchema(obj, String.format("%s.%s", location, "headers"), result);
            if (headers.getType().equalsIgnoreCase("object") && headers.getProperties() != null && headers.getProperties().size() > 0) {
                messageBinding.setHeaders(headers);
            }
        }

        /** The version of this binding. If omitted, "latest" MUST be assumed. */
        final String value = getString("bindingVersion", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageBinding.setBindingVersion(value);
        } else {
            messageBinding.setBindingVersion(LATEST);
        }

        return messageBinding;

    }

    private MessageBinding getKafkaMessageBinding(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final KafkaMessageBinding messageBinding = new KafkaMessageBinding();
        // TODO tester kafka binding

        /** The message key. */
        final ObjectNode obj = getObject("key", node, false, location, result);
        if (obj != null) {
            messageBinding.setKey(getSchema(obj, String.format("%s.%s", location, "groupId"), result));
        }

        /** The version of this binding. If omitted, "latest" MUST be assumed. */
        final String value = getString("bindingVersion", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            messageBinding.setBindingVersion(value);
        } else {
            messageBinding.setBindingVersion(LATEST);
        }

        return messageBinding;

    }

    // A finir
    public List<MessageTrait> getMessageTraitList(final ArrayNode nodes, final String location, final ParseResult result) {
        if (nodes == null) {
            return null;
        }

        final List<MessageTrait> messageTraits = new ArrayList<>();

        for (final JsonNode node : nodes) {
            if (node.getNodeType().equals(JsonNodeType.OBJECT)) {
                final MessageTrait messageTrait = new MessageTrait();
                final Set<String> keys = getKeys((ObjectNode) node);
                if (keys.size() == 0) {
                    messageTraits.add(messageTrait);
                } else {

                    // TODO revoir le parsing de MessageTrait
                    // for (String key : keys) {
                    // if (key != null) {
                    // JsonNode value = node.get(key);
                    // if (key != null && JsonNodeType.ARRAY.equals(value.getNodeType())) {
                    // ArrayNode arrayNode = (ArrayNode) value;
                    // List<String> scopes = Stream
                    // .generate(arrayNode.elements()::next)
                    // .map((n) -> n.asText())
                    // .limit(arrayNode.size())
                    // .collect(Collectors.toList());
                    // operationTrait.addList(key, scopes);
                    // }
                    // }
                    // }
                    // if (operationTrait.size() > 0) {
                    // operationTraits.add(operationTrait);
                    // }
                }
            }
        }

        return messageTraits;

    }

    // OK
    public Map<String, CorrelationID> getCorrelationIds(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, CorrelationID> correlationIds = new LinkedHashMap<>();

        final Iterator<Map.Entry<String, JsonNode>> iter = obj.fields();
        while (iter.hasNext()) {
            final Map.Entry<String, JsonNode> entry = iter.next();
            if (underComponents) {
                if (!Pattern.matches("^[a-zA-Z0-9\\.\\-_]+$",
                                entry.getKey())) {
                    result.warning(location, "CorrelationId key " + entry.getKey() + " doesn't adhere to regular expression ^[a-zA-Z0-9\\.\\-_]+$");
                }
            }
            if (entry.getValue().getNodeType().equals(JsonNodeType.OBJECT)) {
                final CorrelationID correlationIdObj = getCorrelationId((ObjectNode) entry.getValue(), String.format("%s.%s", location, entry.getKey()), result);
                if (correlationIdObj != null) {
                    correlationIds.put(entry.getKey(), correlationIdObj);
                }
            }
        }
        return correlationIds;
    }

    // OK
    private CorrelationID getCorrelationId(final ObjectNode node, final String location, final ParseResult result) {

        if (node == null) {
            return null;
        }

        final CorrelationID correlationID = new CorrelationID();

        final JsonNode ref = node.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {
                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    correlationID.set$ref(mungedRef);
                } else {
                    correlationID.set$ref(ref.textValue());
                }
                return correlationID;
            } else {
                result.invalidType(location, "$ref", "string", node);
                return null;
            }
        }

        String value = getString("description", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            correlationID.setDescription(value);
        }

        value = getString("location", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            correlationID.setLocation(value);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            correlationID.setExtensions(extensions);
        }

        final Set<String> securitySchemeKeys = getKeys(node);
        for (final String key : securitySchemeKeys) {
            if (!CORRELATION_ID_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return correlationID;
    }

    public OAuthFlows getOAuthFlows(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final OAuthFlows oAuthFlows = new OAuthFlows();

        ObjectNode objectNode = getObject("implicit", node, false, location, result);
        if (objectNode != null) {
            oAuthFlows.setImplicit(getOAuthFlow("implicit", objectNode, location, result));
        }

        objectNode = getObject("password", node, false, location, result);
        if (objectNode != null) {
            oAuthFlows.setPassword(getOAuthFlow("password", objectNode, location, result));
        }

        objectNode = getObject("clientCredentials", node, false, location, result);
        if (objectNode != null) {
            oAuthFlows.setClientCredentials(getOAuthFlow("clientCredentials", objectNode, location, result));
        }

        objectNode = getObject("authorizationCode", node, false, location, result);
        if (objectNode != null) {
            oAuthFlows.setAuthorizationCode(getOAuthFlow("authorizationCode", objectNode, location, result));
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            oAuthFlows.setExtensions(extensions);
        }

        final Set<String> oAuthFlowKeys = getKeys(node);
        for (final String key : oAuthFlowKeys) {
            if (!OAUTHFLOWS_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return oAuthFlows;
    }

    public OAuthFlow getOAuthFlow(final String oAuthFlowType, final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final OAuthFlow oAuthFlow = new OAuthFlow();

        boolean authorizationUrlRequired, tokenUrlRequired, refreshUrlRequired, scopesRequired;
        authorizationUrlRequired = tokenUrlRequired = refreshUrlRequired = false;
        scopesRequired = true;
        switch (oAuthFlowType) {
            case "implicit":
                authorizationUrlRequired = true;
                break;
            case "password":
                tokenUrlRequired = true;
                break;
            case "clientCredentials":
                tokenUrlRequired = true;
                break;
            case "authorizationCode":
                authorizationUrlRequired = tokenUrlRequired = true;
                break;
        }

        String value = getString("authorizationUrl", node, authorizationUrlRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            oAuthFlow.setAuthorizationUrl(value);
        }

        value = getString("tokenUrl", node, tokenUrlRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            oAuthFlow.setTokenUrl(value);
        }

        value = getString("refreshUrl", node, refreshUrlRequired, location, result);
        if (StringUtils.isNotBlank(value)) {
            oAuthFlow.setRefreshUrl(value);
        }

        final ObjectNode scopesObject = getObject("scopes", node, scopesRequired, location, result);

        final Scopes scope = new Scopes();
        final Set<String> keys = getKeys(scopesObject);
        for (final String name : keys) {
            final JsonNode scopeValue = scopesObject.get(name);
            if (scopesObject != null) {
                scope.addString(name, scopeValue.asText());
            }
        }
        oAuthFlow.setScopes(scope);

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            oAuthFlow.setExtensions(extensions);
        }

        final Set<String> oAuthFlowKeys = getKeys(node);
        for (final String key : oAuthFlowKeys) {
            if (!OAUTHFLOW_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return oAuthFlow;
    }

    public Map<String, Schema<?>> getSchemas(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, Schema<?>> schemas = new LinkedHashMap<>();

        final Set<String> schemaKeys = getKeys(obj);
        for (final String schemaName : schemaKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[a-zA-Z0-9\\.\\-_]+$",
                                schemaName)) {
                    result.warning(location, "Schema name " + schemaName + " doesn't adhere to regular expression ^[a-zA-Z0-9\\.\\-_]+$");
                }
            }
            final JsonNode schemaValue = obj.get(schemaName);
            if (!schemaValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, schemaName, "object", schemaValue);
            } else {
                final ObjectNode schema = (ObjectNode) schemaValue;
                final Schema schemaObj = getSchema(schema, String.format("%s.%s", location, schemaName), result);
                if (schemaObj != null) {
                    schemas.put(schemaName, schemaObj);
                }
            }
        }

        return schemas;
    }

    public Schema getSchema(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        // const
        // if / then / else

        // patternProperties
        // additionalItems
        // items
        // propertyNames
        // contains
        // allOf
        // oneOf
        // anyOf

        Schema schema = null;
        final ArrayNode oneOfArray = getArray("oneOf", node, false, location, result);
        final ArrayNode allOfArray = getArray("allOf", node, false, location, result);
        final ArrayNode anyOfArray = getArray("anyOf", node, false, location, result);
        final ObjectNode itemsNode = getObject("items", node, false, location, result);

        if (allOfArray != null || anyOfArray != null || oneOfArray != null) {
            final ComposedSchema composedSchema = new ComposedSchema();

            if (allOfArray != null) {

                for (final JsonNode n : allOfArray) {
                    if (n.isObject()) {
                        schema = getSchema((ObjectNode) n, location, result);
                        composedSchema.addAllOfItem(schema);
                    }
                }
                schema = composedSchema;
            }
            if (anyOfArray != null) {

                for (final JsonNode n : anyOfArray) {
                    if (n.isObject()) {
                        schema = getSchema((ObjectNode) n, location, result);
                        composedSchema.addAnyOfItem(schema);
                    }
                }
                schema = composedSchema;
            }
            if (oneOfArray != null) {

                for (final JsonNode n : oneOfArray) {
                    if (n.isObject()) {
                        schema = getSchema((ObjectNode) n, location, result);
                        composedSchema.addOneOfItem(schema);
                    }
                }
                schema = composedSchema;
            }
        }

        if (itemsNode != null) {
            final ArraySchema items = new ArraySchema();
            if (itemsNode.getNodeType().equals(JsonNodeType.OBJECT)) {
                items.setItems(getSchema(itemsNode, location, result));
            } else if (itemsNode.getNodeType().equals(JsonNodeType.ARRAY)) {
                for (final JsonNode n : itemsNode) {
                    if (n.isValueNode()) {
                        items.setItems(getSchema(itemsNode, location, result));
                    }
                }
            }
            schema = items;
        }

        final Boolean additionalPropertiesBoolean = getBoolean("additionalProperties", node, false, location, result);

        final ObjectNode additionalPropertiesObject = additionalPropertiesBoolean == null ? getObject("additionalProperties", node, false, location, result) : null;

        final Object additionalProperties = additionalPropertiesObject != null ? getSchema(additionalPropertiesObject, location, result) : additionalPropertiesBoolean;

        if (additionalProperties != null) {
            if (schema == null) {
                schema = additionalProperties.equals(Boolean.FALSE) ? new ObjectSchema() : new MapSchema();
            }
            schema.setAdditionalProperties(additionalProperties);
        }

        if (schema == null) {
            schema = SchemaTypeUtil.createSchemaByType(node);
        }

        // private String $ref = null;
        final JsonNode ref = node.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {

                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    schema.set$ref(mungedRef);
                } else {
                    schema.set$ref(ref.asText());
                }
                return schema;
            } else {
                result.invalidType(location, "$ref", "string", node);
                logger.error(INVALID_TYPE + "$ref in Schema");
                return null;
            }
        }

        String value = getString("title", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            schema.setTitle(value);
        }

        value = getString("discriminator", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            schema.setDiscriminator(value);
        }

        BigDecimal bigDecimal = getBigDecimal("multipleOf", node, false, location, result);
        if (bigDecimal != null) {
            schema.multipleOf(bigDecimal);
        }

        if (bigDecimal != null) {
            schema.maximum(bigDecimal);
        }

        Boolean bool = getBoolean("exclusiveMaximum", node, false, location, result);
        if (bool != null) {
            schema.exclusiveMaximum(bool);
        }

        bigDecimal = getBigDecimal("minimum", node, false, location, result);
        if (bigDecimal != null) {
            schema.minimum(bigDecimal);
        }

        bigDecimal = getBigDecimal("maximum", node, false, location, result);
        if (bigDecimal != null) {
            schema.maximum(bigDecimal);
        }

        bool = getBoolean("exclusiveMinimum", node, false, location, result);
        if (bool != null) {
            schema.exclusiveMinimum(bool);
        }

        Integer integer = getInteger("minLength", node, false, location, result);
        if (integer != null) {
            schema.minLength(integer);
        }

        integer = getInteger("maxLength", node, false, location, result);
        if (integer != null) {
            schema.maxLength(integer);
        }

        // This string SHOULD be a valid regular expression, according to the ECMA 262
        // regular expression dialect
        final String pattern = getString("pattern", node, false, location, result);
        if (StringUtils.isNotBlank(pattern)) {
            schema.pattern(pattern);
        }

        integer = getInteger("maxItems", node, false, location, result);
        if (integer != null) {
            schema.maxItems(integer);
        }
        integer = getInteger("minItems", node, false, location, result);
        if (integer != null) {
            schema.minItems(integer);
        }
        bool = getBoolean("uniqueItems", node, false, location, result);
        if (bool != null) {
            schema.uniqueItems(bool);
        }

        integer = getInteger("maxProperties", node, false, location, result);
        if (integer != null) {
            schema.maxProperties(integer);
        }

        integer = getInteger("minProperties", node, false, location, result);
        if (integer != null) {
            schema.minProperties(integer);
        }
        final ArrayNode required = getArray("required", node, false, location, result);
        if (required != null) {
            final List<String> requiredList = new ArrayList<>();
            for (final JsonNode n : required) {
                if (n.getNodeType().equals(JsonNodeType.STRING)) {
                    requiredList.add(((TextNode) n).textValue());
                } else {
                    result.invalidType(location, "required", "string", n);
                }
            }
            if (requiredList.size() > 0) {
                schema.required(requiredList);
            }
        }

        final ArrayNode enumArray = getArray("enum", node, false, location, result);
        if (enumArray != null) {
            for (final JsonNode n : enumArray) {
                if (n.isNumber()) {
                    schema.addEnumItemObject(n.numberValue());
                } else if (n.isBoolean()) {
                    schema.addEnumItemObject(n.booleanValue());
                } else if (n.isValueNode()) {
                    try {
                        schema.addEnumItemObject(getDecodedObject(schema, n.asText(null)));
                    } catch (final ParseException e) {
                        result.invalidType(location, String.format("enum=`%s`", e.getMessage()), schema.getFormat(), n);
                        logger.error(INVALID_TYPE + "enum in Schema");
                    }
                } else {
                    result.invalidType(location, "enum", "value", n);
                    logger.error(INVALID_TYPE + "enum in Schema");
                }
            }
        }

        value = getString("type", node, false, location, result);
        if (StringUtils.isBlank(schema.getType())) {
            if (StringUtils.isNotBlank(value)) {
                schema.type(value);
            } else {
                // may have an enum where type can be inferred
                final JsonNode enumNode = node.get("enum");
                if (enumNode != null && enumNode.isArray()) {
                    final String type = inferTypeFromArray((ArrayNode) enumNode);
                    schema.type(type);
                }
            }
            if ("array".equals(schema.getType()) && !(schema instanceof ArraySchema && ((ArraySchema) schema).getItems() != null)) {
                result.missing(location, "items");
            }
        }

        final ObjectNode notObj = getObject("not", node, false, location, result);
        if (notObj != null) {
            final Schema not = getSchema(notObj, location, result);
            if (not != null) {
                schema.not(not);
            }
        }

        // private Map<String, Schema> properties = null;
        final Map<String, Schema> properties = new LinkedHashMap<>();
        final ObjectNode propertiesObj = getObject("properties", node, false, location, result);
        Schema property = null;

        final Set<String> keys = getKeys(propertiesObj);
        for (final String name : keys) {
            final JsonNode propertyValue = propertiesObj.get(name);
            if (!propertyValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, "properties", "object", propertyValue);
            } else {
                if (propertiesObj != null) {
                    property = getSchema((ObjectNode) propertyValue, location, result);
                    if (property != null) {
                        properties.put(name, property);
                    }
                }
            }
        }
        if (propertiesObj != null) {
            schema.setProperties(properties);
        }

        value = getString("description", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            schema.description(value);
        }

        value = getString("format", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            schema.format(value);
        }

        // sets default value according to the schema type
        if (node.get("default") != null) {
            if (!StringUtils.isBlank(schema.getType())) {
                if (schema.getType().equals("array")) {
                    final ArrayNode array = getArray("default", node, false, location, result);
                    if (array != null) {
                        schema.setDefault(array);
                    }
                } else if (schema.getType().equals("string")) {
                    value = getString("default", node, false, location, result);
                    if (value != null) {
                        try {
                            schema.setDefault(getDecodedObject(schema, value));
                        } catch (final ParseException e) {
                            result.invalidType(location, String.format("default=`%s`", e.getMessage()), schema.getFormat(), node);
                            logger.error(INVALID_TYPE + "default in Schema");
                        }
                    }
                } else if (schema.getType().equals("boolean")) {
                    bool = getBoolean("default", node, false, location, result);
                    if (bool != null) {
                        schema.setDefault(bool);
                    }
                } else if (schema.getType().equals("object")) {
                    final Object object = getObject("default", node, false, location, result);
                    if (object != null) {
                        schema.setDefault(object);
                    }
                } else if (schema.getType().equals("integer")) {
                    final Integer number = getInteger("default", node, false, location, result);
                    if (number != null) {
                        schema.setDefault(number);
                    }
                } else if (schema.getType().equals("number")) {
                    final BigDecimal number = getBigDecimal("default", node, false, location, result);
                    if (number != null) {
                        schema.setDefault(number);
                    }
                }
            }
        }

        bool = getBoolean("readOnly", node, false, location, result);
        if (bool != null) {
            schema.setReadOnly(bool);
        }

        bool = getBoolean("writeOnly", node, false, location, result);
        if (bool != null) {
            schema.setWriteOnly(bool);
        }

        bool = Optional.ofNullable(getBoolean("writeOnly", node, false, location, result)).orElse(false) && Optional.ofNullable(getBoolean("readOnly", node, false, location, result)).orElse(false);
        if (bool == true) {
            result.warning(location, " writeOnly and readOnly are both present");
            logger.warn(INVALID_TYPE + "writeOnly and readOnly are both present in Schema");

        }

        final ObjectNode externalDocs = getObject("externalDocs", node, false, location, result);
        if (externalDocs != null) {
            final ExternalDocumentation docs = getExternalDocs(externalDocs, location, result);
            if (docs != null) {
                schema.externalDocs(docs);
            }
        }

        final Object example = getAnyExample("example", node, location, result);
        if (example != null) {
            schema.setExample(example);
        }

        bool = getBoolean("deprecated", node, false, location, result);
        if (bool != null) {
            schema.deprecated(bool);
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            schema.extensions(extensions);
        }

        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!SCHEMA_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }

        return schema;

    }

    /**
     * Decodes the given string and returns an object applicable to the given schema.
     * Throws a ParseException if no applicable object can be recognized.
     */
    private Object getDecodedObject(final Schema schema, final String objectString) throws ParseException {
        final Object object = objectString == null ? null :

                        schema.getClass().equals(DateSchema.class) ? toDate(objectString) :

                                        schema.getClass().equals(DateTimeSchema.class) ? toDateTime(objectString) :

                                                        schema.getClass().equals(ByteArraySchema.class) ? toBytes(objectString) :

                                                                        objectString;

        if (object == null && objectString != null) {
            throw new ParseException(objectString, 0);
        }

        return object;
    }

    /**
     * Returns the Date represented by the given RFC3339 date-time string.
     * Returns null if this string can't be parsed as Date.
     */
    private OffsetDateTime toDateTime(final String dateString) {

        OffsetDateTime dateTime = null;
        try {
            dateTime = OffsetDateTime.parse(dateString);
        } catch (final Exception ignore) {
            logger.info("Exception during parsing dateTime", ignore);

        }

        return dateTime;
    }

    /**
     * Returns the Date represented by the given RFC3339 full-date string.
     * Returns null if this string can't be parsed as Date.
     */
    private Date toDate(final String dateString) {
        final Matcher matcher = RFC3339_DATE_PATTERN.matcher(dateString);

        Date date = null;
        if (matcher.matches()) {
            final String year = matcher.group(1);
            final String month = matcher.group(2);
            final String day = matcher.group(3);

            try {
                date = new Calendar.Builder()
                                .setDate(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day))
                                .build()
                                .getTime();
            } catch (final Exception ignore) {
                logger.info("Exception during parsing date", ignore);
            }
        }

        return date;
    }

    /**
     * Returns the byte array represented by the given base64-encoded string.
     * Returns null if this string is not a valid base64 encoding.
     */
    private byte[] toBytes(final String byteString) {
        byte[] bytes;

        try {
            bytes = Base64.getDecoder().decode(byteString);
        } catch (final Exception e) {
            logger.error("Exception during parsing Bytes", e);
            bytes = null;
        }

        return bytes;
    }

    // OK
    public Operation getOperation(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }

        final Operation operation = new Operation();

        String value = getString("operationId", obj, false, String.format("%s.%s", location, "operationId"), result, this.operationIDs);
        if (StringUtils.isNotBlank(value)) {
            operation.setOperationId(value);
        }

        value = getString("summary", obj, false, String.format("%s.%s", location, "summary"), result);
        if (StringUtils.isNotBlank(value)) {
            operation.setSummary(value);
        }

        value = getString("description", obj, false, String.format("%s.%s", location, "description"), result);
        if (StringUtils.isNotBlank(value)) {
            operation.setDescription(value);
        }

        ArrayNode array = getArray("tags", obj, false, location, result);
        final List<Tag> tags = getTagList(array, String.format("%s.%s", location, "tags"), result);
        if (tags != null) {
            operation.setTags(tags);
        }

        ObjectNode nodeObj = getObject("externalDocs", obj, false, location, result);
        final ExternalDocumentation docs = getExternalDocs(nodeObj, String.format("%s.%s", location, "externalDocs"), result);
        if (docs != null) {
            operation.setExternalDocs(docs);
        }

        // TODO verifier les operation binding si ils sont bien utilises car je doute
        nodeObj = getObject("bindings", obj, false, location, result);
        final Map<String, OperationBinding> bindings = getOperationBindings(nodeObj, String.format("%s.%s", location, "bindings"), result, false);
        if (bindings != null && bindings.size() > 0) {
            operation.setBindings(bindings);
        }

        array = getArray("traits", obj, false, location, result);
        if (array != null && array.size() > 0) {
            operation.setTraits(getOperationTraitsList(array, "tags", result));
        }

        final ObjectNode requestObjectNode = getObject("message", obj, false, location, result);
        if (requestObjectNode != null) {
            // If oneof

            final ArrayNode oneOfArray = getArray("oneOf", requestObjectNode, false, location, result);
            if (oneOfArray != null) {
                // ObjectMapper mapper = new ObjectMapper();
                ObjectNode itObj = null;
                for (final JsonNode n : oneOfArray) {
                    if (n.isObject()) {
                        itObj = n.deepCopy();
                        // itObj= (ObjectNode) mapper.readTree(n.asText());
                        operation.setMessage(getMessage(itObj, String.format("%s.%s", location, "message"), result));
                    }
                }
            }

            else {
                operation.setMessage(getMessage(requestObjectNode, String.format("%s.%s", location, "message"), result));
            }
        }

        final Map<String, Object> extensions = getExtensions(obj);
        if (extensions != null && extensions.size() > 0) {
            operation.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!OPERATION_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, obj.get(key));
            }
        }

        return operation;
    }

    private Map<String, OperationTrait> getOperationTraits(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, OperationTrait> operationTraits = new LinkedHashMap<>();

        final Set<String> operationTraitKeys = getKeys(obj);
        for (final String operationTraitName : operationTraitKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[A-Za-z0-9_\\-]+$", // "^[a-zA-Z0-9\\.\\-_]+$",
                                operationTraitName)) {
                    result.warning(location, "Operation Trait name " + operationTraitName + " doesn't adhere to regular expression ^[A-Za-z0-9_\\\\-]+$");
                }
            }
            final JsonNode operationTraitValue = obj.get(operationTraitName);
            if (!operationTraitValue.getNodeType().equals(JsonNodeType.OBJECT)) {
                result.invalidType(location, operationTraitName, "object", operationTraitValue);
            } else {
                final ObjectNode operationTraitNode = (ObjectNode) operationTraitValue;
                final OperationTrait operationTraitObj = getOperationTrait(operationTraitNode, String.format("%s.%s", location, operationTraitName), result);
                if (operationTraitObj != null) {
                    operationTraits.put(operationTraitName, operationTraitObj);
                }
            }
        }

        return operationTraits;
    }

    public OperationTrait getOperationTrait(final ObjectNode obj, final String location, final ParseResult result) {
        if (obj == null) {
            return null;
        }

        final OperationTrait operationTrait = new OperationTrait();

        final JsonNode ref = obj.get("$ref");
        if (ref != null) {
            if (ref.getNodeType().equals(JsonNodeType.STRING)) {

                final String mungedRef = mungedRef(ref.textValue());
                if (mungedRef != null) {
                    operationTrait.set$ref(mungedRef);
                } else {
                    operationTrait.set$ref(ref.asText());
                }
                return operationTrait;
            } else {
                result.invalidType(location, "$ref", "string", obj);
                return null;
            }
        }

        String value = getString("operationId", obj, false, location, result, this.operationIDs);
        if (StringUtils.isNotBlank(value)) {
            operationTrait.setOperationId(value);
        }

        value = getString("summary", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            operationTrait.setSummary(value);
        }

        value = getString("description", obj, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            operationTrait.setDescription(value);
        }

        final ArrayNode array = getArray("tags", obj, false, location, result);
        final List<Tag> tags = getTagList(array, String.format("%s.%s", location, "tags"), result);
        if (tags != null) {
            operationTrait.setTags(tags);
        }

        ObjectNode objNode = getObject("externalDocs", obj, false, location, result);
        final ExternalDocumentation docs = getExternalDocs(objNode, String.format("%s.%s", location, "externalDocs"), result);
        if (docs != null) {
            operationTrait.setExternalDocs(docs);
        }

        // TODO verifier les operation binding si ils sont bien utilises car je doute
        objNode = getObject("bindings", obj, false, location, result);
        final Map<String, OperationBinding> bindings = getOperationBindings(objNode, String.format("%s.%s", location, "bindings"), result, false);
        if (bindings != null && bindings.size() > 0) {
            operationTrait.setBindings(bindings);
        }

        final Map<String, Object> extensions = getExtensions(obj);
        if (extensions != null && extensions.size() > 0) {
            operationTrait.setExtensions(extensions);
        }

        final Set<String> keys = getKeys(obj);
        for (final String key : keys) {
            if (!OPERATION_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, obj.get(key));
            }
        }

        return operationTrait;
    }

    // OK
    private Map<String, OperationBinding> getOperationBindings(final ObjectNode obj, final String location, final ParseResult result, final boolean underComponents) {
        if (obj == null) {
            return null;
        }
        final Map<String, OperationBinding> operationBindings = new LinkedHashMap<>();

        final Set<String> operationBindingKeys = getKeys(obj);
        for (final String operationBindingName : operationBindingKeys) {
            if (underComponents) {
                if (!Pattern.matches("^[A-Za-z0-9_\\-]+$", // "^[a-zA-Z0-9\\.\\-_]+$",
                                operationBindingName)) {
                    result.warning(location, "Operation Binding name " + operationBindingName + " doesn't adhere to regular expression ^[A-Za-z0-9_\\\\-]+$");
                }
            }
            final JsonNode operationBindingValue = obj.get(operationBindingName);
            final ObjectNode operationBindingNode = (ObjectNode) operationBindingValue;
            final OperationBinding operationBindingObj = getOperationBinding(operationBindingNode, String.format("%s.%s", location, operationBindingName), result, operationBindingName);
            if (operationBindingObj != null) {
                operationBindings.put(operationBindingName, operationBindingObj);
            }
        }

        return operationBindings;
    }

    private OperationBinding getOperationBinding(final ObjectNode node, final String location, final ParseResult result, final String operationBindingName) {
        if (node == null) {
            return null;
        }

        OperationBinding operationBindings = new OperationBinding();

        switch (OperationBinding.TypeEnum.getType(operationBindingName)) {
            case HTTP:
                operationBindings = getHttpOperationBinding(node, String.format("%s.%s", location, operationBindingName), result);
                break;
            case KAFKA:
                operationBindings = getKafkaOperationBinding(node, String.format("%s.%s", location, operationBindingName), result);
                break;

            // TODO voir les differents formats qu'on va utiliser puis completer getOperationBinding
            default:
                break;
        }

        final Map<String, Object> extensions = getExtensions(node);
        if (extensions != null && extensions.size() > 0) {
            operationBindings.setExtensions(extensions);
        }

        return operationBindings;
    }

    private OperationBinding getHttpOperationBinding(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final HttpOperationBinding operationBinding = new HttpOperationBinding();
        // TODO tester http binding
        /** Required. Type of operation. Its value MUST be either request or response. */
        final String type = getString("type", node, true, location, result);
        if (StringUtils.isNotBlank(type) && (StringUtils.equalsIgnoreCase(type, REQUEST) || StringUtils.equalsIgnoreCase(type, RESPONSE))) {
            operationBinding.setType(type);
        } else {
            result.invalidType(location, type, "string : request or response", node);
        }

        /** When type is request, this is the HTTP method, otherwise it MUST be ignored. Its value MUST be one of GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS, CONNECT, and TRACE.*/
        String value = getString("method", node, false, location, result);

        // TODO confirmer que le contains marche sur la liste avec des strings
        if (StringUtils.isNotBlank(value) && StringUtils.equalsIgnoreCase(type, REQUEST) && OPERATION_BINDING_HTTP_METHODE_VALUES.contains(value)) {
            operationBinding.setMethod(value);
        }

        /** The version of this binding. If omitted, "latest" MUST be assumed. */
        value = getString("bindingVersion", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            operationBinding.setBindingVersion(value);
        } else {
            operationBinding.setBindingVersion(LATEST);
        }

        /** A Schema object containing the definitions for each query parameter. This schema MUST be of type object and have a properties key. */
        final ObjectNode obj = getObject("query", node, false, location, result);
        if (obj != null) {
            final Schema query = getSchema(obj, String.format("%s.%s", location, "query"), result);
            if (query.getType().equalsIgnoreCase("object") && query.getProperties() != null && query.getProperties().size() > 0) {
                operationBinding.setQuery(query);
            }
        }
        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!OPERATION_BINDING_HTTP_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }
        return operationBinding;

    }

    private OperationBinding getKafkaOperationBinding(final ObjectNode node, final String location, final ParseResult result) {
        if (node == null) {
            return null;
        }

        final KafkaOperationBinding operationBinding = new KafkaOperationBinding();

        /** Id of the consumer group. */
        final JsonNode groupNode = node.get("groupId");
        Optional<Schema<?>> groupSchema;
        if(JsonNodeType.STRING.equals(Optional.ofNullable(groupNode).map(JsonNode::getNodeType).orElse(JsonNodeType.MISSING))) {
            groupSchema = Optional.ofNullable(getString("groupId", node, false, location, result))
                    .map(str -> new StringSchema().addEnumItem(str));
        } else {
            groupSchema = Optional.ofNullable(getObject("groupId", node, false, location, result))
                    .map(obj -> getSchema(obj, String.format("%s.%s", location, "groupId"), result));
        }
        groupSchema.ifPresent(operationBinding::setGroupId);


        /** Id of the consumer group. */
        final JsonNode clientNode = node.get("clientId");
        Optional<Schema<?>> clientSchema;
        if(JsonNodeType.STRING.equals(Optional.ofNullable(groupNode).map(JsonNode::getNodeType).orElse(JsonNodeType.MISSING))) {
            clientSchema = Optional.ofNullable(getString("clientId", node, false, location, result))
                    .map(str -> new StringSchema().addEnumItem(str));
        } else {
            clientSchema = Optional.ofNullable(getObject("clientId", node, false, location, result))
                    .map(obj -> getSchema(obj, String.format("%s.%s", location, "clientId"), result));
        }
        clientSchema.ifPresent(operationBinding::setClientId);

        /** The version of this binding. If omitted, "latest" MUST be assumed. */
        final String value = getString("bindingVersion", node, false, location, result);
        if (StringUtils.isNotBlank(value)) {
            operationBinding.setBindingVersion(value);
        } else {
            operationBinding.setBindingVersion(LATEST);
        }
        final Set<String> schemaKeys = getKeys(node);
        for (final String key : schemaKeys) {
            if (!OPERATION_BINDING_KAFKA_KEYS.contains(key) && !key.startsWith("x-")) {
                result.extra(location, key, node.get(key));
            }
        }
        return operationBinding;

    }

    public List<OperationTrait> getOperationTraitsList(final ArrayNode nodes, final String location, final ParseResult result) {
        if (nodes == null) {
            return null;
        }

        final List<OperationTrait> operationTraits = new ArrayList<>();

        for (final JsonNode node : nodes) {
            if (node.getNodeType().equals(JsonNodeType.OBJECT)) {
                final OperationTrait trait = getOperationTrait((ObjectNode) node, String.format("%s.%s", location, "operationTrait"), result);
                operationTraits.add(trait);
            }
        }

        return operationTraits;

    }

    // TODO je ne comprend pas le mapping par rapport a la spec pour SecurityRequirement
    // security scheme which is declared in the Security Schemes => mais dans les exemples ca correspond pas

    public SecurityRequirement<String> getSecurityRequirementsList(final ArrayNode nodes, final String location, final ParseResult result) {
        if (nodes == null) {
            return null;
        }

        final SecurityRequirement<String> securityRequirement = new SecurityRequirement<>();

        for (final JsonNode node : nodes) {
            if (node.getNodeType().equals(JsonNodeType.OBJECT)) {
                final Set<String> keys = getKeys((ObjectNode) node);
                if (keys.size() == 0) {
                } else {
                    for (final String key : keys) {
                        if (key != null) {
                            final JsonNode value = node.get(key);
                            if (key != null && JsonNodeType.ARRAY.equals(value.getNodeType())) {
                                final ArrayNode arrayNode = (ArrayNode) value;
                                final List<String> scopes = Stream
                                                .generate(arrayNode.elements()::next)
                                                .map((n) -> n.asText())
                                                .limit(arrayNode.size())
                                                .collect(Collectors.toList());
                                securityRequirement.addList(scopes);
                            }
                        }
                    }
                }
            }
        }

        return securityRequirement;

    }

    public String inferTypeFromArray(final ArrayNode an) {
        if (an.size() == 0) {
            return "string";
        }
        String type = null;
        for (int i = 0; i < an.size(); i++) {
            final JsonNode element = an.get(0);
            if (element.isBoolean()) {
                if (type == null) {
                    type = "boolean";
                } else if (!"boolean".equals(type)) {
                    type = "string";
                }
            } else if (element.isNumber()) {
                if (type == null) {
                    type = "number";
                } else if (!"number".equals(type)) {
                    type = "string";
                }
            } else {
                type = "string";
            }
        }

        return type;
    }

    protected static class ParseResult {

        private boolean valid = true;

        private final Map<Location, JsonNode> extra = new LinkedHashMap<>();

        private final Map<Location, JsonNode> unsupported = new LinkedHashMap<>();

        private final Map<Location, String> invalidType = new LinkedHashMap<>();

        private final List<Location> missing = new ArrayList<>();

        private final List<Location> warnings = new ArrayList<>();

        private final List<Location> unique = new ArrayList<>();

        private final List<Location> uniqueTags = new ArrayList<>();

        public ParseResult() {
        }

        public void unsupported(final String location, final String key, final JsonNode value) {
            this.unsupported.put(new Location(location, key), value);
        }

        public void extra(final String location, final String key, final JsonNode value) {
            this.extra.put(new Location(location, key), value);
        }

        public void missing(final String location, final String key) {
            this.missing.add(new Location(location, key));
        }

        public void warning(final String location, final String key) {
            this.warnings.add(new Location(location, key));
        }

        public void unique(final String location, final String key) {
            this.unique.add(new Location(location, key));

        }

        public void uniqueTags(final String location, final String key) {
            this.uniqueTags.add(new Location(location, key));
        }

        public void invalidType(final String location, final String key, final String expectedType, final JsonNode value) {
            this.invalidType.put(new Location(location, key), expectedType);
        }

        public void invalid() {
            this.valid = false;
        }

        public boolean isValid() {
            return this.valid;
        }

        public List<String> getParseMessages() {
            final List<String> messages = new ArrayList<String>();
            for (final Location l : this.extra.keySet()) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key + " is unexpected";
                messages.add(message);
            }
            for (final Location l : this.invalidType.keySet()) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key + " is not of type `" + this.invalidType.get(l) + "`";
                messages.add(message);
            }
            for (final Location l : this.missing) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key + " is missing";
                messages.add(message);
            }
            for (final Location l : this.warnings) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key;
                messages.add(message);
            }
            for (final Location l : this.unsupported.keySet()) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key + " is unsupported";
                messages.add(message);
            }
            for (final Location l : this.unique) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key + " is repeated";
                messages.add(message);
            }
            for (final Location l : this.uniqueTags) {
                final String location = l.location.equals("") ? "" : l.location + ".";
                final String message = "attribute " + location + l.key + " is repeated";
                messages.add(message);
            }
            return messages;
        }
    }

    protected static class Location {

        private final String location;

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Location)) {
                return false;
            }

            final Location location1 = (Location) o;

            if (this.location != null ? !this.location.equals(location1.location) : location1.location != null) {
                return false;
            }
            return !(this.key != null ? !this.key.equals(location1.key) : location1.key != null);

        }

        @Override
        public int hashCode() {
            int result = this.location != null ? this.location.hashCode() : 0;
            result = 31 * result + (this.key != null ? this.key.hashCode() : 0);
            return result;
        }

        private final String key;

        public Location(final String location, final String key) {
            this.location = location;
            this.key = key;
        }
    }

}
