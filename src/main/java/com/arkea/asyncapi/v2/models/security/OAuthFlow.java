package com.arkea.asyncapi.v2.models.security;

import java.util.Objects;

/**
 * OAuthFlow
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#oauthFlowObject"
 */

public class OAuthFlow {

    private String authorizationUrl = null;

    private String tokenUrl = null;

    private String refreshUrl = null;

    private Scopes scopes = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the authorizationUrl property from a OAuthFlow instance.
     *
     * @return String authorizationUrl
     **/

    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    public void setAuthorizationUrl(final String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public OAuthFlow authorizationUrl(final String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
        return this;
    }

    /**
     * returns the tokenUrl property from a OAuthFlow instance.
     *
     * @return String tokenUrl
     **/

    public String getTokenUrl() {
        return this.tokenUrl;
    }

    public void setTokenUrl(final String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public OAuthFlow tokenUrl(final String tokenUrl) {
        this.tokenUrl = tokenUrl;
        return this;
    }

    /**
     * returns the refreshUrl property from a OAuthFlow instance.
     *
     * @return String refreshUrl
     **/

    public String getRefreshUrl() {
        return this.refreshUrl;
    }

    public void setRefreshUrl(final String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    public OAuthFlow refreshUrl(final String refreshUrl) {
        this.refreshUrl = refreshUrl;
        return this;
    }

    /**
     * returns the scopes property from a OAuthFlow instance.
     *
     * @return Scopes scopes
     **/

    public Scopes getScopes() {
        return this.scopes;
    }

    public void setScopes(final Scopes scopes) {
        this.scopes = scopes;
    }

    public OAuthFlow scopes(final Scopes scopes) {
        this.scopes = scopes;
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
        final OAuthFlow oauthFlow = (OAuthFlow) o;
        return Objects.equals(this.authorizationUrl, oauthFlow.authorizationUrl) &&
                        Objects.equals(this.tokenUrl, oauthFlow.tokenUrl) &&
                        Objects.equals(this.refreshUrl, oauthFlow.refreshUrl) &&
                        Objects.equals(this.scopes, oauthFlow.scopes) &&
                        Objects.equals(this.extensions, oauthFlow.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.authorizationUrl, this.tokenUrl, this.refreshUrl, this.scopes, this.extensions);
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

    public OAuthFlow extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class OAuthFlow {\n");

        sb.append("    authorizationUrl: ").append(toIndentedString(this.authorizationUrl)).append("\n");
        sb.append("    tokenUrl: ").append(toIndentedString(this.tokenUrl)).append("\n");
        sb.append("    refreshUrl: ").append(toIndentedString(this.refreshUrl)).append("\n");
        sb.append("    scopes: ").append(toIndentedString(this.scopes)).append("\n");
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
