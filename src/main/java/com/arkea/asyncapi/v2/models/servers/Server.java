package com.arkea.asyncapi.v2.models.servers;

import java.util.Map;
import java.util.Objects;

import com.arkea.asyncapi.v2.models.security.SecurityRequirement;


/**
 * Server
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#serverObject"
 */

public class Server {

	/** REQUIRED. A URL to the target host. This URL supports Server Variables and MAY be relative,
	 *  to indicate that the host location is relative to the location where the AsyncAPI document is being served.
	 *   Variable substitutions will be made when a variable is named in {brackets}. */
    private String url = null;
    /** REQUIRED. The protocol this URL supports for connection. Supported protocol include, but are not limited to:
     *  amqp, amqps, http, https, jms, kafka, kafka-secure, mqtt, secure-mqtt, stomp, stomps, ws, wss, mercure. */
    private String protocol = null;
    /** The version of the protocol used for connection. For instance: AMQP 0.9.1, HTTP 2.0, Kafka 1.0.0, etc. */
    private String protocolVersion = null;
    /** An optional string describing the host designated by the URL. CommonMark syntax MAY be used for rich text representation. */
    private String description = null;
    /** A map between a variable name and its value. The value is used for substitution in the server's URL template. */
    private Map<String, ServerVariable> variables = null;
    /** A declaration of which security mechanisms can be used with this server.
     *  The list of values includes alternative security requirement objects that can be used.
     *   Only one of the security requirement objects need to be satisfied to authorize a connection or operation. */
    private SecurityRequirement<String> security = null;
	/** A map where the keys describe the name of the protocol and the values describe protocol-specific definitions for the server. */
    private Map<String,ServerBinding> bindings = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private Map<String, Object> extensions = null;

    public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	public String getProtocolVersion() {
		return this.protocolVersion;
	}

	public void setProtocolVersion(final String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public SecurityRequirement<String> getSecurity() {
		return this.security;
	}

	public void setSecurity(final SecurityRequirement<String> security) {
		this.security = security;
	}

	public Map<String,ServerBinding> getBindings() {
		return this.bindings;
	}

	public void setBindings(final Map<String,ServerBinding> bindings) {
		this.bindings = bindings;
	}



    /**
     * returns the url property from a Server instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Server url(final String url) {
        this.url = url;
        return this;
    }

    /**
     * returns the description property from a Server instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Server description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the variables property from a Server instance.
     *
     * @return Map<String, ServerVariable> variables
     **/

    public Map<String, ServerVariable> getVariables() {
        return this.variables;
    }

    public void setVariables(final Map<String, ServerVariable> variables) {
        this.variables = variables;
    }

    public Server variables(final Map<String, ServerVariable> variables) {
        this.variables = variables;
        return this;
    }

    public Map<String, Object> getExtensions() {
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

    public Server extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

  @Override
  public int hashCode() {
      return Objects.hash(this.url, this.protocol, this.protocolVersion, this.description, this.variables, this.extensions, this.security);
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
		final Server other = (Server) obj;
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
		if (this.protocol == null) {
			if (other.protocol != null) {
				return false;
			}
		} else if (!this.protocol.equals(other.protocol)) {
			return false;
		}
		if (this.protocolVersion == null) {
			if (other.protocolVersion != null) {
				return false;
			}
		} else if (!this.protocolVersion.equals(other.protocolVersion)) {
			return false;
		}
		if (this.security == null) {
			if (other.security != null) {
				return false;
			}
		} else if (!this.security.equals(other.security)) {
			return false;
		}
		if (this.url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!this.url.equals(other.url)) {
			return false;
		}
		if (this.variables == null) {
			if (other.variables != null) {
				return false;
			}
		} else if (!this.variables.equals(other.variables)) {
			return false;
		}
		return true;
	}


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Server {\n");

        sb.append("    url: ").append(toIndentedString(this.url)).append("\n");
        sb.append("    protocol: ").append(toIndentedString(this.protocol)).append("\n");
        sb.append("    protocolVersion: ").append(toIndentedString(this.protocolVersion)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    variables: ").append(toIndentedString(this.variables)).append("\n");
        sb.append("    security: ").append(toIndentedString(this.security)).append("\n");
        sb.append("    bindings: ").append(toIndentedString(this.bindings)).append("\n");
        sb.append("    extensions: ").append(toIndentedString(this.extensions)).append("\n");
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

