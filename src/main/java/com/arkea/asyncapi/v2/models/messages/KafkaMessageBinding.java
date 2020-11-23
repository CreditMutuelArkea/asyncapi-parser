package com.arkea.asyncapi.v2.models.messages;

import com.arkea.asyncapi.v2.models.media.Schema;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/kafka/README.md#message"
 *
 */
public class KafkaMessageBinding extends MessageBinding {

    /** The message key. */
    private Schema key = null;

    /** The version of this binding. If omitted, "latest" MUST be assumed. */
    private String bindingVersion = null;

    public Schema getKey() {
        return this.key;
    }

    public void setKey(final Schema key) {
        this.key = key;
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
        result = prime * result + (this.key == null ? 0 : this.key.hashCode());
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
        final KafkaMessageBinding other = (KafkaMessageBinding) obj;
        if (this.bindingVersion == null) {
            if (other.bindingVersion != null) {
                return false;
            }
        } else if (!this.bindingVersion.equals(other.bindingVersion)) {
            return false;
        }
        if (this.key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!this.key.equals(other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KafkaMessageBinding [key=" + this.key + ", bindingVersion=" + this.bindingVersion + "]";
    }

}
