package com.arkea.asyncapi.v2.models.messages;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp/README.md#message"
 *
 */
public class AmqpMessageBinding extends MessageBinding {

    /** A MIME encoding for the message content. */
    private String contentEncoding = null;

    /** Application-specific message type. */
    private String messageType = null;

    /** The version of this binding. If omitted, "latest" MUST be assumed. */
    private String bindingVersion = null;

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(final String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(final String messageType) {
        this.messageType = messageType;
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
        result = prime * result + (this.contentEncoding == null ? 0 : this.contentEncoding.hashCode());
        result = prime * result + (this.messageType == null ? 0 : this.messageType.hashCode());
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
        final AmqpMessageBinding other = (AmqpMessageBinding) obj;
        if (this.bindingVersion == null) {
            if (other.bindingVersion != null) {
                return false;
            }
        } else if (!this.bindingVersion.equals(other.bindingVersion)) {
            return false;
        }
        if (this.contentEncoding == null) {
            if (other.contentEncoding != null) {
                return false;
            }
        } else if (!this.contentEncoding.equals(other.contentEncoding)) {
            return false;
        }
        if (this.messageType == null) {
            if (other.messageType != null) {
                return false;
            }
        } else if (!this.messageType.equals(other.messageType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AmqpMessageBinding [contentEncoding=" + this.contentEncoding + ", messageType=" + this.messageType + ", bindingVersion=" + this.bindingVersion + "]";
    }

}
