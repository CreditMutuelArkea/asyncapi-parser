package com.arkea.asyncapi.v2.models.messages;

import com.arkea.asyncapi.v2.models.media.Schema;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/http/README.md#message"
 *
 */
public class HttpMessageBinding extends MessageBinding{

	/** A Schema object containing the definitions for HTTP-specific headers.
	 *  This schema MUST be of type object and have a properties key. */
	private Schema headers = null;
	/** The version of this binding. If omitted, "latest" MUST be assumed. */
	private String bindingVersion = null;

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
		final HttpMessageBinding other = (HttpMessageBinding) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "HttpMessageBinding [headers=" + this.headers + ", bindingVersion=" + this.bindingVersion + "]";
	}


}
