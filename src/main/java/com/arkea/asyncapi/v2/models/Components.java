package com.arkea.asyncapi.v2.models;

import java.util.LinkedHashMap;
import java.util.Map;

import com.arkea.asyncapi.v2.models.channels.ChannelBindings;
import com.arkea.asyncapi.v2.models.media.Schema;
import com.arkea.asyncapi.v2.models.messages.CorrelationID;
import com.arkea.asyncapi.v2.models.messages.Message;
import com.arkea.asyncapi.v2.models.messages.MessageBinding;
import com.arkea.asyncapi.v2.models.messages.MessageTrait;
import com.arkea.asyncapi.v2.models.operations.HttpOperationBinding;
import com.arkea.asyncapi.v2.models.operations.OperationBinding;
import com.arkea.asyncapi.v2.models.operations.OperationTrait;
import com.arkea.asyncapi.v2.models.parameters.Parameter;
import com.arkea.asyncapi.v2.models.security.SecurityScheme;
import com.arkea.asyncapi.v2.models.servers.ServerBinding;

/**
 * Components
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#componentsObject"
 *
 *      Holds a set of reusable objects for different aspects of the AsyncAPI
 *      specification. All objects defined within the components object will
 *      have no effect on the API unless they are explicitly referenced from
 *      properties outside the components object.
 */

public class Components {

	/** An object to hold reusable Schema Objects. */
	private Map<String, Schema<?>> schemas = null;
	/** An object to hold reusable Message Objects. */
	private Map<String, Message> messages = null;
	/** An object to hold reusable Security Scheme Objects. */
	private Map<String, SecurityScheme> securitySchemes = null;
	/** An object to hold reusable Parameter Objects. */
	private Map<String, Parameter> parameters = null;
	/** An object to hold reusable Correlation ID Objects. */
	private Map<String, CorrelationID> correlationIds = null;
	/** An object to hold reusable Operation Trait Objects. */
	private Map<String, OperationTrait> operationTraits = null;
	/** An object to hold reusable  Message Trait Objects. */
	private Map<String, MessageTrait> messageTraits = null;
	/** An object to hold reusable Server Binding Objects. */
	private Map<String, ServerBinding> serverBindings = null;
	/** An object to hold reusable Channel Binding Objects. */
	private Map<String, ChannelBindings> channelBindings = null;
	/** An object to hold reusable Operation Binding Objects. */
	private Map<String, OperationBinding> operationBindings = null;
	/** An object to hold reusable Message Binding Objects. */
	private Map<String, MessageBinding> messageBindings = null;

	/**
	 * Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-,
	 * for example, x-internal-id. The value can be null, a primitive, an array or
	 * an object. Can have any valid JSON format value.
	 */
	private Map<String, Object> extensions = null;

	public Components() {
		super();
		this.operationBindings = new LinkedHashMap<String, OperationBinding>();
		this.operationBindings.put("test", new HttpOperationBinding());
	}

	/**
	 * returns the schemas property from a Components instance.
	 *
	 * @return Map&lt;String, Schema&gt; schemas
	 **/

	public Map<String, Schema<?>> getSchemas() {
		return this.schemas;
	}

	public void setSchemas(final Map<String, Schema<?>> schemas) {
		this.schemas = schemas;
	}

	public Components schemas(final Map<String, Schema<?>> schemas) {
		this.schemas = schemas;
		return this;
	}

	public Components addSchemas(final String key, final Schema<?> schemasItem) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
		}
		this.schemas.put(key, schemasItem);
		return this;
	}

	public Map<String, Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Map<String, Message> messages) {
		this.messages = messages;
	}

	public Map<String, CorrelationID> getCorrelationIds() {
		return this.correlationIds;
	}

	public void setCorrelationIds(final Map<String, CorrelationID> correlationIds) {
		this.correlationIds = correlationIds;
	}

	public Map<String, OperationTrait> getOperationTraits() {
		return this.operationTraits;
	}

	public void setOperationTraits(final Map<String, OperationTrait> operationTraits) {
		this.operationTraits = operationTraits;
	}

	public Map<String, MessageTrait> getMessageTraits() {
		return this.messageTraits;
	}

	public void setMessageTraits(final Map<String, MessageTrait> messageTraits) {
		this.messageTraits = messageTraits;
	}

	public Map<String, ServerBinding> getServerBindings() {
		return this.serverBindings;
	}

	public void setServerBindings(final Map<String, ServerBinding> serverBindings) {
		this.serverBindings = serverBindings;
	}

	public Map<String, ChannelBindings> getChannelBindings() {
		return this.channelBindings;
	}

	public void setChannelBindings(final Map<String, ChannelBindings> channelBindings) {
		this.channelBindings = channelBindings;
	}

	public Map<String, OperationBinding> getOperationBindings() {
		return this.operationBindings;
	}

	public void setOperationBindings(final Map<String, OperationBinding> operationBindings) {
		this.operationBindings = operationBindings;
	}

	public Map<String, MessageBinding> getMessageBindings() {
		return this.messageBindings;
	}

	public void setMessageBindings(final Map<String, MessageBinding> messageBindings) {
		this.messageBindings = messageBindings;
	}

	/**
	 * returns the parameters property from a Components instance.
	 *
	 * @return Map&lt;String, Parameter&gt; parameters
	 **/

	public Map<String, Parameter> getParameters() {
		return this.parameters;
	}

	public void setParameters(final Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}

	public Components parameters(final Map<String, Parameter> parameters) {
		this.parameters = parameters;
		return this;
	}

	public Components addParameters(final String key, final Parameter parametersItem) {
		if (this.parameters == null) {
			this.parameters = new LinkedHashMap<>();
		}
		this.parameters.put(key, parametersItem);
		return this;
	}

	/**
	 * returns the securitySchemes property from a Components instance.
	 *
	 * @return Map&lt;String, SecurityScheme&gt; securitySchemes
	 **/

	public Map<String, SecurityScheme> getSecuritySchemes() {
		return this.securitySchemes;
	}

	public void setSecuritySchemes(final Map<String, SecurityScheme> securitySchemes) {
		this.securitySchemes = securitySchemes;
	}

	public Components securitySchemes(final Map<String, SecurityScheme> securitySchemes) {
		this.securitySchemes = securitySchemes;
		return this;
	}

	public Components addSecuritySchemes(final String key, final SecurityScheme securitySchemesItem) {
		if (this.securitySchemes == null) {
			this.securitySchemes = new LinkedHashMap<>();
		}
		this.securitySchemes.put(key, securitySchemesItem);
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

	public Components extensions(final java.util.Map<String, Object> extensions) {
		this.extensions = extensions;
		return this;
	}

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("class Components {\n");
//
//        sb.append("    schemas: ").append(toIndentedString(schemas)).append("\n");
//        sb.append("    responses: ").append(toIndentedString(responses)).append("\n");
//        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
//        sb.append("    examples: ").append(toIndentedString(examples)).append("\n");
//        sb.append("    requestBodies: ").append(toIndentedString(requestBodies)).append("\n");
//        sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
//        sb.append("    securitySchemes: ").append(toIndentedString(securitySchemes)).append("\n");
//        sb.append("    links: ").append(toIndentedString(links)).append("\n");
//        sb.append("    callbacks: ").append(toIndentedString(callbacks)).append("\n");
//        sb.append("}");
//        return sb.toString();
//    }

	@Override
	public String toString() {
		return "Components [schemas=" + this.schemas + ", messages=" + this.messages + ", securitySchemes=" + this.securitySchemes + ", parameters=" + this.parameters + ", correlationIds=" + this.correlationIds + ", operationTraits=" + this.operationTraits + ", messageTraits=" + this.messageTraits + ", serverBindings="
				+ this.serverBindings + ", channelBindings=" + this.channelBindings + ", operationBindings=" + this.operationBindings + ", messageBindings=" + this.messageBindings + ", extensions=" + this.extensions + "]";
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
