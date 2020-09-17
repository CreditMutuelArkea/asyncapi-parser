package com.arkea.asyncapi.v2.models.servers;

/**
 * ServerBindings
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#serverBindingsObject"
 *
 */
public class ServerBinding  {

	  /**
     * Gets or Sets Type
     */
    public enum TypeEnum {
    	HTTP("http"),
    	WEBSOCKETS("ws"),
    	KAFKA("kafka"),
    	AMQP("amqp"),
    	AMQP1("amqp1"),
    	MQTT("mqtt"),
    	MQTT5("mqtt5"),
    	NATS("nats"),
    	JMS("jms"),
    	SNS("sns"),
    	SQS("sqs"),
    	STOMP("stomp"),
    	REDIS("redis"),
    	MERCURE("mercure"),
    	UNDEFINE("undefine");
        private final String value;

        TypeEnum(final String value) {
            this.value = value;
        }

        public static TypeEnum getType(final String value) {
        	try {
				 return TypeEnum.valueOf(value.toUpperCase());
			} catch (final IllegalArgumentException e) {
				return TypeEnum.UNDEFINE;
			}

        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

	public java.util.Map<String, Object> getExtensions() {
		return this.extensions;
	}

	public void setExtensions(final java.util.Map<String, Object> extensions) {
		this.extensions = extensions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.extensions == null ? 0 : this.extensions.hashCode());
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
		final ServerBinding other = (ServerBinding) obj;
		if (this.extensions == null) {
			if (other.extensions != null) {
				return false;
			}
		} else if (!this.extensions.equals(other.extensions)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ServerBindings [extensions=" + this.extensions + "]";
	}



}
