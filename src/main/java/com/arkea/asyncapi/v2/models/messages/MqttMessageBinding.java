package com.arkea.asyncapi.v2.models.messages;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#message"
 *
 */
public class MqttMessageBinding extends MessageBinding{

	/** The version of this binding. If omitted, "latest" MUST be assumed. */
	 private String bindingVersion =null;

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
		final MqttMessageBinding other = (MqttMessageBinding) obj;
		if (this.bindingVersion == null) {
			if (other.bindingVersion != null) {
				return false;
			}
		} else if (!this.bindingVersion.equals(other.bindingVersion)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MqttMessageBinding [bindingVersion=" + this.bindingVersion + "]";
	}


}
