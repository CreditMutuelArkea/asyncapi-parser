package com.arkea.asyncapi.v2.models.servers;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#server"
 *
 */
public class MqttServerBinding extends ServerBinding{
//TODO faire MqttServerBinding si besoin

//	clientId	string	The client identifier.
//	cleanSession	boolean	Whether to create a persisten connection or not. When false, the connection will be persistent.
//	lastWill	object	Last Will and Testament configuration.
//	lastWill.topic	string	The topic where the Last Will and Testament message will be sent.
//	lastWill.qos	integer	Defines how hard the broker/client will try to ensure that the Last Will and Testament message is received. Its value MUST be either 0, 1 or 2.
//	lastWill.retain	boolean	Whether the broker should retain the Last Will and Testament message or not.
//	keepAlive	integer	Interval in seconds of the longest period of time the broker and the client can endure without sending a message.

	//	bindingVersion	string	The version of this binding. If omitted, "latest" MUST be assumed.
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
		final MqttServerBinding other = (MqttServerBinding) obj;
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
		return "MqttServerBinding [bindingVersion=" + this.bindingVersion + "]";
	}


}
