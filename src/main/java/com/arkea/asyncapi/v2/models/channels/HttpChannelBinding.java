package com.arkea.asyncapi.v2.models.channels;

import com.arkea.asyncapi.v2.models.media.Schema;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/http/README.md#channel"
 *
 */
public class HttpChannelBinding extends ChannelBindings {

    /** Required. Type of operation. Its value MUST be either request or response. */
    private final String type = null;

    /** When type is request, this is the HTTP method, otherwise it MUST be ignored. Its value MUST be one of GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS, CONNECT, and TRACE. */
    private final String method = null;

    /** A Schema object containing the definitions for each query parameter. This schema MUST be of type object and have a properties key. */
    private final Schema query = null;

    /** The version of this binding. If omitted, "latest" MUST be assumed. */
    private final String bindingVersion = null;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.bindingVersion == null ? 0 : this.bindingVersion.hashCode());
        result = prime * result + (this.method == null ? 0 : this.method.hashCode());
        result = prime * result + (this.query == null ? 0 : this.query.hashCode());
        result = prime * result + (this.type == null ? 0 : this.type.hashCode());
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
        final HttpChannelBinding other = (HttpChannelBinding) obj;
        if (this.bindingVersion == null) {
            if (other.bindingVersion != null) {
                return false;
            }
        } else if (!this.bindingVersion.equals(other.bindingVersion)) {
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
        if (this.type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!this.type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HttpChannelBinding [type=" + this.type + ", method=" + this.method + ", query=" + this.query + ", bindingVersion=" + this.bindingVersion + "]";
    }

}
