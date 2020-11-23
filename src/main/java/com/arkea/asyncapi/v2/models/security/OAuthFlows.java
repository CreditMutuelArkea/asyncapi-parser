package com.arkea.asyncapi.v2.models.security;

import java.util.Objects;

/**
 * OAuthFlows
 *
 * @see "https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#oauthFlowsObject"
 */

public class OAuthFlows {

    private OAuthFlow implicit = null;

    private OAuthFlow password = null;

    private OAuthFlow clientCredentials = null;

    private OAuthFlow authorizationCode = null;

    /** Allows extensions to the AsyncAPI Schema. The field name MUST begin with x-, for example, x-internal-id.
     *  The value can be null, a primitive, an array or an object. Can have any valid JSON format value. */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the implicit property from a OAuthFlows instance.
     *
     * @return OAuthFlow implicit
     **/

    public OAuthFlow getImplicit() {
        return this.implicit;
    }

    public void setImplicit(final OAuthFlow implicit) {
        this.implicit = implicit;
    }

    public OAuthFlows implicit(final OAuthFlow implicit) {
        this.implicit = implicit;
        return this;
    }

    /**
     * returns the password property from a OAuthFlows instance.
     *
     * @return OAuthFlow password
     **/

    public OAuthFlow getPassword() {
        return this.password;
    }

    public void setPassword(final OAuthFlow password) {
        this.password = password;
    }

    public OAuthFlows password(final OAuthFlow password) {
        this.password = password;
        return this;
    }

    /**
     * returns the clientCredentials property from a OAuthFlows instance.
     *
     * @return OAuthFlow clientCredentials
     **/

    public OAuthFlow getClientCredentials() {
        return this.clientCredentials;
    }

    public void setClientCredentials(final OAuthFlow clientCredentials) {
        this.clientCredentials = clientCredentials;
    }

    public OAuthFlows clientCredentials(final OAuthFlow clientCredentials) {
        this.clientCredentials = clientCredentials;
        return this;
    }

    /**
     * returns the authorizationCode property from a OAuthFlows instance.
     *
     * @return OAuthFlow authorizationCode
     **/

    public OAuthFlow getAuthorizationCode() {
        return this.authorizationCode;
    }

    public void setAuthorizationCode(final OAuthFlow authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public OAuthFlows authorizationCode(final OAuthFlow authorizationCode) {
        this.authorizationCode = authorizationCode;
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
        final OAuthFlows oauthFlows = (OAuthFlows) o;
        return Objects.equals(this.implicit, oauthFlows.implicit) &&
                        Objects.equals(this.password, oauthFlows.password) &&
                        Objects.equals(this.clientCredentials, oauthFlows.clientCredentials) &&
                        Objects.equals(this.authorizationCode, oauthFlows.authorizationCode) &&
                        Objects.equals(this.extensions, oauthFlows.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.implicit, this.password, this.clientCredentials, this.authorizationCode, this.extensions);
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

    public OAuthFlows extensions(final java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class OAuthFlows {\n");

        sb.append("    implicit: ").append(toIndentedString(this.implicit)).append("\n");
        sb.append("    password: ").append(toIndentedString(this.password)).append("\n");
        sb.append("    clientCredentials: ").append(toIndentedString(this.clientCredentials)).append("\n");
        sb.append("    authorizationCode: ").append(toIndentedString(this.authorizationCode)).append("\n");
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
