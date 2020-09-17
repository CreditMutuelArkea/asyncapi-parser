package com.arkea.asyncapi.v2.models.security;

import org.apache.commons.lang3.EnumUtils;

/**
 * SecurityScheme
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#securitySchemeObject"
 */

public class SecurityScheme {

	/**
	 * Gets or Sets in
	 * The location of the API key
	 */
	public enum In {
		// Valid values are "user" and "password" for apiKey and "query", "header" or "cookie" for httpApiKey
		USER("user"),
		PASSWORD("password"),
		QUERY("query"),
		HEARDER("header"),
		COOKIE("cookie");


		private final String value;

		In(final String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(this.value);
		}

		public static Boolean isIn(final String word) {
			return EnumUtils.isValidEnum(In.class, word);
		}
	}

    /**
     * Gets or Sets type
     * The type of the security scheme
     */
    public enum Type {

        APIKEY("apiKey"),
        HTTP("http"),
        OAUTH2("oauth2"),
        OPENIDCONNECT("openIdConnect"),
        USERPASSWORD("userPassword"),
        X509("X509"),
        SYMMETRICENCRYPTION("symmetricEncryption"),
    	ASYMMETRICENCRYPTION("asymmetricEncryption"),
    	HTTPAPIKEY("httpApiKey");


        private final String value;

        Type(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }


    private String $ref = null;
    private Type type = null;
    private String description = null;
    private String name = null;
    private In in = null;
    private String scheme = null;
    private String bearerFormat = null;
    private OAuthFlows flows = null;
    private String openIdConnectUrl = null;
	/** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
	 *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;





	public SecurityScheme $ref(final String $ref) {

		set$ref($ref);
		return this;
	}

	/**
	 * returns the $ref property from a MessageTrait instance.
	 *
	 * @return String $ref
	 **/
	public String get$ref() {
		return this.$ref;
	}

	// TODO verifier si c'est la bonne ref
	public void set$ref(String $ref) {
		if ($ref != null && $ref.indexOf('.') == -1 && $ref.indexOf('/') == -1) {
			$ref = "#/components/securityschemes/" + $ref;
		}
		this.$ref = $ref;
	}



    /**
     * returns the type property from a SecurityScheme instance.
     *
     * @return Type type
     **/

    public Type getType() {
        return this.type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public SecurityScheme type(final Type type) {
        this.type = type;
        return this;
    }

    /**
     * returns the description property from a SecurityScheme instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public SecurityScheme description(final String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the name property from a SecurityScheme instance.
     *
     * @return String name
     **/

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public SecurityScheme name(final String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the in property from a SecurityScheme instance.
     *
     * @return In in
     **/

    public In getIn() {
        return this.in;
    }

    public void setIn(final In in) {
        this.in = in;
    }

    public SecurityScheme in(final In in) {
        this.in = in;
        return this;
    }

    /**
     * returns the scheme property from a SecurityScheme instance.
     *
     * @return String scheme
     **/

    public String getScheme() {
        return this.scheme;
    }

    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }

    public SecurityScheme scheme(final String scheme) {
        this.scheme = scheme;
        return this;
    }

    /**
     * returns the bearerFormat property from a SecurityScheme instance.
     *
     * @return String bearerFormat
     **/

    public String getBearerFormat() {
        return this.bearerFormat;
    }

    public void setBearerFormat(final String bearerFormat) {
        this.bearerFormat = bearerFormat;
    }

    public SecurityScheme bearerFormat(final String bearerFormat) {
        this.bearerFormat = bearerFormat;
        return this;
    }

    /**
     * returns the flows property from a SecurityScheme instance.
     *
     * @return OAuthFlows flows
     **/

    public OAuthFlows getFlows() {
        return this.flows;
    }

    public void setFlows(final OAuthFlows flows) {
        this.flows = flows;
    }

    public SecurityScheme flows(final OAuthFlows flows) {
        this.flows = flows;
        return this;
    }

    /**
     * returns the openIdConnectUrl property from a SecurityScheme instance.
     *
     * @return String openIdConnectUrl
     **/

    public String getOpenIdConnectUrl() {
        return this.openIdConnectUrl;
    }

    public void setOpenIdConnectUrl(final String openIdConnectUrl) {
        this.openIdConnectUrl = openIdConnectUrl;
    }

    public SecurityScheme openIdConnectUrl(final String openIdConnectUrl) {
        this.openIdConnectUrl = openIdConnectUrl;
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

    public SecurityScheme extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecurityScheme)) {
            return false;
        }

        final SecurityScheme that = (SecurityScheme) o;

        if (this.type != that.type) {
            return false;
        }
        if (this.description != null ? !this.description.equals(that.description) : that.description != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(that.name) : that.name != null) {
            return false;
        }
        if (this.in != that.in) {
            return false;
        }
        if (this.scheme != null ? !this.scheme.equals(that.scheme) : that.scheme != null) {
            return false;
        }
        if (this.bearerFormat != null ? !this.bearerFormat.equals(that.bearerFormat) : that.bearerFormat != null) {
            return false;
        }
        if (this.flows != null ? !this.flows.equals(that.flows) : that.flows != null) {
            return false;
        }
        if (this.openIdConnectUrl != null ? !this.openIdConnectUrl.equals(that.openIdConnectUrl) : that.openIdConnectUrl != null) {
            return false;
        }
        return this.extensions != null ? this.extensions.equals(that.extensions) : that.extensions == null;
    }

    @Override
    public int hashCode() {
        int result = this.type != null ? this.type.hashCode() : 0;
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.in != null ? this.in.hashCode() : 0);
        result = 31 * result + (this.scheme != null ? this.scheme.hashCode() : 0);
        result = 31 * result + (this.bearerFormat != null ? this.bearerFormat.hashCode() : 0);
        result = 31 * result + (this.flows != null ? this.flows.hashCode() : 0);
        result = 31 * result + (this.openIdConnectUrl != null ? this.openIdConnectUrl.hashCode() : 0);
        result = 31 * result + (this.extensions != null ? this.extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class SecurityScheme {\n");

        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    in: ").append(toIndentedString(this.in)).append("\n");
        sb.append("    scheme: ").append(toIndentedString(this.scheme)).append("\n");
        sb.append("    bearerFormat: ").append(toIndentedString(this.bearerFormat)).append("\n");
        sb.append("    flows: ").append(toIndentedString(this.flows)).append("\n");
        sb.append("    openIdConnectUrl: ").append(toIndentedString(this.openIdConnectUrl)).append("\n");
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

