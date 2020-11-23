package com.arkea.asyncapi.v2.models.operations;

/**
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp/README.md#operation"
 *
 */
public class AmqpOperationBinding extends OperationBinding {

    // TODO AmqpOperationBinding definition

    // expiration integer Publish, Subscribe TTL (Time-To-Live) for the message. It MUST be greater than or equal to zero.
    // userId string Publish, Subscribe Identifies the user who has sent the message.
    // cc [string] Publish, Subscribe The routing keys the message should be routed to at the time of publishing.
    // priority integer Publish, Subscribe A priority for the message.
    // deliveryMode integer Publish, Subscribe Delivery mode of the message. Its value MUST be either 1 (transient) or 2 (persistent).
    // mandatory boolean Publish Whether the message is mandatory or not.
    // bcc [string] Publish Like cc but consumers will not receive this information.
    // replyTo string Publish, Subscribe Name of the queue where the consumer should send the response.
    // timestamp boolean Publish, Subscribe Whether the message should include a timestamp or not.
    // ack boolean Subscribe Whether the consumer should ack the message or not.
    // bindingVersion string Publish, Subscribe The version of this binding. If omitted, "latest" MUST be assumed.
    // This object MUST contain only the properties defined above.

}
