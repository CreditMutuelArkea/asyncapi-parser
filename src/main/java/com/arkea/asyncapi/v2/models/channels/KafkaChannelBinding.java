package com.arkea.asyncapi.v2.models.channels;

import com.arkea.asyncapi.v2.models.media.Schema;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/kafka/README.md#channel"
 *
 */
public class KafkaChannelBinding extends ChannelBindings {

    /** Id of the consumer group. */
    private Schema groupId = null;

    /** Id of the consumer inside a consumer group. */
    private Schema clientId = null;

    /** The version of this binding. If omitted, "latest" MUST be assumed. */
    private String bindingVersion = null;

    public Schema getGroupId() {
        return this.groupId;
    }

    public void setGroupId(final Schema groupId) {
        this.groupId = groupId;
    }

    public Schema getClientId() {
        return this.clientId;
    }

    public void setClientId(final Schema clientId) {
        this.clientId = clientId;
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
        result = prime * result + (this.clientId == null ? 0 : this.clientId.hashCode());
        result = prime * result + (this.groupId == null ? 0 : this.groupId.hashCode());
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
        final KafkaChannelBinding other = (KafkaChannelBinding) obj;
        if (this.bindingVersion == null) {
            if (other.bindingVersion != null) {
                return false;
            }
        } else if (!this.bindingVersion.equals(other.bindingVersion)) {
            return false;
        }
        if (this.clientId == null) {
            if (other.clientId != null) {
                return false;
            }
        } else if (!this.clientId.equals(other.clientId)) {
            return false;
        }
        if (this.groupId == null) {
            if (other.groupId != null) {
                return false;
            }
        } else if (!this.groupId.equals(other.groupId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KafkaChannelBinding [groupId=" + this.groupId + ", clientId=" + this.clientId + ", bindingVersion=" + this.bindingVersion + "]";
    }

}
