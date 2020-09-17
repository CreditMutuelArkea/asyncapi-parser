package com.arkea.asyncapi.v2.models.channels;

public class ChannelBindings {
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
		final ChannelBindings other = (ChannelBindings) obj;
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
		return "ChannelBindings [extensions=" + this.extensions + "]";
	}

}
