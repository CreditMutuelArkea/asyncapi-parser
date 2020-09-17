package com.arkea.asyncapi.v2.models.channels;

import java.util.Map;

import com.arkea.asyncapi.v2.models.operations.Operation;
import com.arkea.asyncapi.v2.models.parameters.Parameter;

/**
 * Server
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#channelItemObject"
 */

public class Channel {

	/**
	 * An optional description of this channel item. CommonMark syntax can be used
	 * for rich text representation.
	 */
	private String description = null;
	/** A definition of the SUBSCRIBE operation. */
	private Operation subscribe = null;
	/** A definition of the PUBLISH operation. */
	private Operation publish = null;
	/**
	 * A map of the parameters included in the channel name. It SHOULD be present
	 * only when using channels with expressions (as defined by RFC 6570 section
	 * 2.2).
	 */
	private Map<String, Parameter> parameters = null;
	/**
	 * A map where the keys describe the name of the protocol and the values
	 * describe protocol-specific definitions for the channel.
	 */
	private Map<String, ChannelBindings> bindings = null;
	/**
	 * Allows for an external definition of this channel item. The referenced
	 * structure MUST be in the format of a Channel Item Object. If there are
	 * conflicts between the referenced definition and this Channel Item's
	 * definition, the behavior is undefined.
	 */
	private String $ref = null;
	/**
	 * Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-,
	 * for example, x-internal-id. The value can be null, a primitive, an array or
	 * an object. Can have any valid JSON format value.
	 */
	private java.util.Map<String, Object> extensions = null;

	/**
	 * Return the description of this channel
	 *
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the description property of this channel instance.
	 *
	 * @param String description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Return the subscribe parameter of this channel
	 *
	 * @return Operation subscribe
	 */
	public Operation getSubscribe() {
		return this.subscribe;
	}

	/**
	 * Set the subscribe property of this channel instance.
	 *
	 * @param Operation subscribe
	 */
	public void setSubscribe(final Operation subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * Return the publish parameter from this channel
	 *
	 * @return Operation publish
	 */
	public Operation getPublish() {
		return this.publish;
	}

	/**
	 * Return parameters from this channel
	 *
	 * @return Map<String, Parameter> parameters
	 */
	public Map<String, Parameter> getParameters() {
		return this.parameters;
	}

	/**
	 * Set parameters of this channel instance.
	 *
	 * @param Map<String, Parameter> parameters
	 */
	public void setParameters(final Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Set the publish property of this channel instance.
	 *
	 * @param Operation publish
	 */
	public void setPublish(final Operation publish) {
		this.publish = publish;
	}

	/**
	 * Return the bindings parameter from this channel
	 *
	 * @return Map<String, ChannelBindings> bindings
	 */
	public Map<String, ChannelBindings> getBindings() {
		return this.bindings;
	}

	/**
	 * Set the bindings property of this channel instance.
	 *
	 * @param Map<String, ChannelBindings> bindings
	 */
	public void setBindings(final Map<String, ChannelBindings> bindings) {
		this.bindings = bindings;
	}

	/**
	 * Return the channel instance by ref
	 *
	 * @param $ref
	 * @return
	 */
	public Channel $ref(final String $ref) {

		set$ref($ref);
		return this;
	}


	public void set$ref(String $ref) {
		if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
			$ref = "#/components/channels/channel" + $ref;
		}
		this.$ref = $ref;
	}

	/**
	 * Returns the extensions property from this channel instance.
	 *
	 * @return Map<String, Object> extensions
	 */
	public java.util.Map<String, Object> getExtensions() {
		return this.extensions;
	}

	/**
	 * Set the extensions property of this channel instance.
	 *
	 * @param extensions
	 * @return ExternalDocumentation this
	 */
	public void setExtensions(final java.util.Map<String, Object> extensions) {
		this.extensions = extensions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.$ref == null ? 0 : this.$ref.hashCode());
		result = prime * result + (this.bindings == null ? 0 : this.bindings.hashCode());
		result = prime * result + (this.description == null ? 0 : this.description.hashCode());
		result = prime * result + (this.extensions == null ? 0 : this.extensions.hashCode());
		result = prime * result + (this.publish == null ? 0 : this.publish.hashCode());
		result = prime * result + (this.parameters == null ? 0 : this.parameters.hashCode());
		result = prime * result + (this.subscribe == null ? 0 : this.subscribe.hashCode());
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
		final Channel other = (Channel) obj;
		if (this.$ref == null) {
			if (other.$ref != null) {
				return false;
			}
		} else if (!this.$ref.equals(other.$ref)) {
			return false;
		}
		if (this.bindings == null) {
			if (other.bindings != null) {
				return false;
			}
		} else if (!this.bindings.equals(other.bindings)) {
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
		if (this.publish == null) {
			if (other.publish != null) {
				return false;
			}
		} else if (!this.publish.equals(other.publish)) {
			return false;
		}
		if (this.parameters == null) {
			if (other.parameters != null) {
				return false;
			}
		} else if (!this.parameters.equals(other.parameters)) {
			return false;
		}
		if (this.subscribe == null) {
			if (other.subscribe != null) {
				return false;
			}
		} else if (!this.subscribe.equals(other.subscribe)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Channel [description=" + this.description + ", subscribe=" + this.subscribe + ", publish=" + this.publish + ", parameters=" + this.parameters + ", bindings=" + this.bindings + ", $ref=" + this.$ref + ", extensions=" + this.extensions + "]";
	}

}
