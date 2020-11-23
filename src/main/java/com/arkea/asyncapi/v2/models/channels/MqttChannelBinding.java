package com.arkea.asyncapi.v2.models.channels;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#channel"
 *
 */
public class MqttChannelBinding extends ChannelBindings {

    /** Publish, Subscribe	Defines how hard the broker/client will try to ensure that a message is received. Its value MUST be either 0, 1 or 2. */
    private Integer qos = null;

    /** Publish, Subscribe	Whether the broker should retain the message or not. */
    private Boolean retain = null;

    /** The version of this binding. If omitted, "latest" MUST be assumed. */
    private String bindingVersion = null;

    public Integer getQos() {
        return this.qos;
    }

    public void setQos(final Integer qos) {
        this.qos = qos;
    }

    public Boolean getRetain() {
        return this.retain;
    }

    public void setRetain(final Boolean retain) {
        this.retain = retain;
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
        result = prime * result + (this.qos == null ? 0 : this.qos.hashCode());
        result = prime * result + (this.retain == null ? 0 : this.retain.hashCode());
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
        final MqttChannelBinding other = (MqttChannelBinding) obj;
        if (this.bindingVersion == null) {
            if (other.bindingVersion != null) {
                return false;
            }
        } else if (!this.bindingVersion.equals(other.bindingVersion)) {
            return false;
        }
        if (this.qos == null) {
            if (other.qos != null) {
                return false;
            }
        } else if (!this.qos.equals(other.qos)) {
            return false;
        }
        if (this.retain == null) {
            if (other.retain != null) {
                return false;
            }
        } else if (!this.retain.equals(other.retain)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MqttChannelBinding [qos=" + this.qos + ", retain=" + this.retain + ", bindingVersion=" + this.bindingVersion + "]";
    }

}
