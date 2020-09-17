package com.arkea.asyncapi.v2.models.messages;

import java.util.List;
import java.util.Map;

import com.arkea.asyncapi.v2.models.ExternalDocumentation;
import com.arkea.asyncapi.v2.models.media.Schema;
import com.arkea.asyncapi.v2.models.tags.Tag;

/**
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#messageObject"
 *
 */
public class Message {

	 /** Definition of the application headers. Schema MUST be of type "object". It MUST NOT define the protocol headers. */
	private Schema<?> headers	= null;
	/** Any	Definition of the message payload. It can be of any type but defaults to Schema object. */
	private Object payload = null;
	 /** Definition of the correlation ID used for message tracing or matching. */
	private CorrelationID correlationId	 = null;
	/** A string containing the name of the schema format used to define the message payload.
	 * If omitted, implementations should parse the payload as a Schema object.
	 * Check out the supported schema formats table for more information.
	 * Custom values are allowed but their implementation is OPTIONAL.
	 * A custom value MUST NOT refer to one of the schema formats listed in the table. */
	private String schemaFormat = null;
	/**	The content type to use when encoding/decoding a message's payload. The value MUST be a specific media type (e.g. application/json). When omitted, the value MUST be the one specified on the defaultContentType field. */
	private String contentType	 = null;
	/** A machine-friendly name for the message. */
	private String name	= null;
	/** A human-friendly title for the message. */
	private String title = null;
	/** A short summary of what the message is about. */
	private String summary = null;
	/** A verbose explanation of the message. CommonMark syntax can be used for rich text representation. */
	private String description = null;
	/** A list of tags for API documentation control. Tags can be used for logical grouping of messages. */
	private List<Tag> tags = null;
	/** Additional external documentation for this message. */
	private ExternalDocumentation externalDocs = null;
	/**	A map where the keys describe the name of the protocol and the values describe protocol-specific definitions for the message. */
	private Map<String, MessageBinding> bindings	= null;

	//TODO traiter les exemple dans Message
	//	examples	[Map[string, any]]	An array with examples of valid message objects.

	/** A list of traits to apply to the message object.
	 *  Traits MUST be merged into the message object using the JSON Merge Patch algorithm in the same order they are defined here.
	 *   The resulting object MUST be a valid Message Object. */
	private List<MessageTrait> traits = null;

	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;
    /** Allows for an external definition of this channel item. */
	private String $ref = null;



	public Schema<?> getHeaders() {
		return this.headers;
	}

	public void setHeaders(final Schema<?> headers) {
		this.headers = headers;
	}

	public Object getPayload() {
		return this.payload;
	}

	public void setPayload(final Object payload) {
		this.payload = payload;
	}

	public CorrelationID getCorrelationId() {
		return this.correlationId;
	}

	public void setCorrelationId(final CorrelationID correlationId) {
		this.correlationId = correlationId;
	}

	public String getSchemaFormat() {
		return this.schemaFormat;
	}

	public void setSchemaFormat(final String schemaFormat) {
		this.schemaFormat = schemaFormat;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(final List<Tag> tags) {
		this.tags = tags;
	}

	public ExternalDocumentation getExternalDocs() {
		return this.externalDocs;
	}

	public void setExternalDocs(final ExternalDocumentation externalDocs) {
		this.externalDocs = externalDocs;
	}


	public Map<String, MessageBinding> getBindings() {
		return this.bindings;
	}

	public void setBindings(final Map<String, MessageBinding> bindings) {
		this.bindings = bindings;
	}

	public List<MessageTrait> getTraits() {
		return this.traits;
	}

	public void setTraits(final List<MessageTrait> traits) {
		this.traits = traits;
	}

	public java.util.Map<String, Object> getExtensions() {
		return this.extensions;
	}

	public void setExtensions(final java.util.Map<String, Object> extensions) {
		this.extensions = extensions;
	}

	/**
     * returns the $ref property from a Message instance.
     *
     * @return String $ref
     **/
    public String get$ref() {
        return this.$ref;
    }

    //TODO verifier si c'est la bonne ref
    public void set$ref(String $ref) {
        if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
            $ref = "#/components/messages/" + $ref;
        }
        this.$ref = $ref;
    }

    public Message $ref(final String $ref) {

        set$ref($ref);
        return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.$ref == null ? 0 : this.$ref.hashCode());
		result = prime * result + (this.bindings == null ? 0 : this.bindings.hashCode());
		result = prime * result + (this.contentType == null ? 0 : this.contentType.hashCode());
		result = prime * result + (this.correlationId == null ? 0 : this.correlationId.hashCode());
		result = prime * result + (this.description == null ? 0 : this.description.hashCode());
		result = prime * result + (this.extensions == null ? 0 : this.extensions.hashCode());
		result = prime * result + (this.externalDocs == null ? 0 : this.externalDocs.hashCode());
		result = prime * result + (this.headers == null ? 0 : this.headers.hashCode());
		result = prime * result + (this.name == null ? 0 : this.name.hashCode());
		result = prime * result + (this.payload == null ? 0 : this.payload.hashCode());
		result = prime * result + (this.schemaFormat == null ? 0 : this.schemaFormat.hashCode());
		result = prime * result + (this.summary == null ? 0 : this.summary.hashCode());
		result = prime * result + (this.tags == null ? 0 : this.tags.hashCode());
		result = prime * result + (this.title == null ? 0 : this.title.hashCode());
		result = prime * result + (this.traits == null ? 0 : this.traits.hashCode());
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
		final Message other = (Message) obj;
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
		if (this.contentType == null) {
			if (other.contentType != null) {
				return false;
			}
		} else if (!this.contentType.equals(other.contentType)) {
			return false;
		}
		if (this.correlationId == null) {
			if (other.correlationId != null) {
				return false;
			}
		} else if (!this.correlationId.equals(other.correlationId)) {
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
		if (this.externalDocs == null) {
			if (other.externalDocs != null) {
				return false;
			}
		} else if (!this.externalDocs.equals(other.externalDocs)) {
			return false;
		}
		if (this.headers == null) {
			if (other.headers != null) {
				return false;
			}
		} else if (!this.headers.equals(other.headers)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.payload == null) {
			if (other.payload != null) {
				return false;
			}
		} else if (!this.payload.equals(other.payload)) {
			return false;
		}
		if (this.schemaFormat == null) {
			if (other.schemaFormat != null) {
				return false;
			}
		} else if (!this.schemaFormat.equals(other.schemaFormat)) {
			return false;
		}
		if (this.summary == null) {
			if (other.summary != null) {
				return false;
			}
		} else if (!this.summary.equals(other.summary)) {
			return false;
		}
		if (this.tags == null) {
			if (other.tags != null) {
				return false;
			}
		} else if (!this.tags.equals(other.tags)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		if (this.traits == null) {
			if (other.traits != null) {
				return false;
			}
		} else if (!this.traits.equals(other.traits)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Message [headers=" + this.headers + ", payload=" + this.payload + ", correlationId=" + this.correlationId + ", schemaFormat=" + this.schemaFormat + ", contentType=" + this.contentType + ", name=" + this.name + ", title=" + this.title + ", summary=" + this.summary + ", description=" + this.description + ", tags=" + this.tags
				+ ", externalDocs=" + this.externalDocs + ", bindings=" + this.bindings + ", traits=" + this.traits + ", extensions=" + this.extensions + ", $ref=" + this.$ref + "]";
	}



}
