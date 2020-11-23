package com.arkea.asyncapi.v2.models.channels;

import com.arkea.asyncapi.v2.models.media.Schema;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/websockets/README.md#channel"
 *
 */
public class WebsocketsChannelBinding extends ChannelBindings {

    /** The HTTP method to use when establishing the connection. Its value MUST be either GET or POST. */
    private String method = null;

    /** A Schema object containing the definitions for each query parameter.
     *  This schema MUST be of type object and have a properties key. */
    private Schema query = null;

    /** A Schema object containing the definitions of the HTTP headers to use when establishing the connection.
     *  This schema MUST be of type object and have a properties key. */
    private Schema headers = null;

    /** The version of this binding. If omitted, "latest" MUST be assumed. */
    private String bindingVersion = null;

    public String getMethod() {
        return this.method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public Schema getQuery() {
        return this.query;
    }

    public void setQuery(final Schema query) {
        this.query = query;
    }

    public Schema getHeaders() {
        return this.headers;
    }

    public void setHeaders(final Schema headers) {
        this.headers = headers;
    }

    public String getBindingVersion() {
        return this.bindingVersion;
    }

    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.bindingVersion == null ? 0 : this.bindingVersion.hashCode());
        result = prime * result + (this.headers == null ? 0 : this.headers.hashCode());
        result = prime * result + (this.method == null ? 0 : this.method.hashCode());
        result = prime * result + (this.query == null ? 0 : this.query.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WebsocketsChannelBinding other = (WebsocketsChannelBinding) obj;
        if (this.bindingVersion == null) {
            if (other.bindingVersion != null) {
                return false;
            }
        } else if (!this.bindingVersion.equals(other.bindingVersion)) {
            return false;
        }
        if (this.headers == null) {
            if (other.headers != null) {
                return false;
            }
        } else if (!this.headers.equals(other.headers)) {
            return false;
        }
        if (this.method == null) {
            if (other.method != null) {
                return false;
            }
        } else if (!this.method.equals(other.method)) {
            return false;
        }
        if (this.query == null) {
            if (other.query != null) {
                return false;
            }
        } else if (!this.query.equals(other.query)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebsocketsChannelBinding [method=" + this.method + ", query=" + this.query + ", headers=" + this.headers + ", bindingVersion=" + this.bindingVersion + "]";
    }

}
