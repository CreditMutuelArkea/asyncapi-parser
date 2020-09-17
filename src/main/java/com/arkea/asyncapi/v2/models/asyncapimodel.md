# AsyncAPI Parser

# Disclaimer

Part of this content has been based on [OpenAPI/Swagger-parser project](https://github.com/swagger-api/swagger-parser) to be as close as possible.

#### Async Api Version : 2.0.0

## Introduction

The AsyncAPI Model is an implementation of the [AsyncAPI Specification](https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md).  

Many protocols are fully implemented but not all.

This parser project was initially  done for Arkéa project. 

It was divided in two different parts : model and parser. You can find below simplified parts of [AsyncAPI Specification](https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md) used in Arkéa project.

The main function to use is :

com.arkea.asyncapi.v2.parser.AsyncAPIV2Parser.readContents(FileAsString)

You can use Yaml or Json file in inputStream.

The result return the parsed result (based on com.arkea.asyncapi.v2.models.AsyncAPI) and messages (an array of string). Messages is empty if no errors happens during the parsing process.   

Following section is directly taken from  [AsyncAPI Specification](https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md) and is simplified in order to start easily.

## Table of Contents
- [Definitions](#definitions)
  - [Application](#definitionsApplication)
  - [Producer](#definitionsProducer)
  - [Consumer](#definitionsConsumer)
  - [Message](#definitionsMessage)
  - [Channel](#definitionsChannel)
- [Specification](#specification)
	- [Format](#format)
	- [Schema](#schema)
	    - [AsyncAPI Object](#A2SObject)
      - [AsyncAPI Version String](#A2SVersionString)
      - [Info Object](#infoObject)
      - [Channels Object](#channelsObject)
      - [Channel Item Object](#channelItemObject)
      - [Operation Object](#operationObject)
      - [Message Object](#messageObject)
      - [Parameters Object](#parametersObject)
      - [Parameter Object](#parameterObject)
      - [Reference Object](#referenceObject)
      - [Components Object](componentsObject)
      - [Schema Object](schemaObject)
      - [Correlation ID Object](#correlationIdObject)
      - [Specification Extensions](#specificationExtensions)

<!-- /TOC -->

## <a name="definitions"/>Definitions

#### <a name="definitionsApplication"></a>Application
An application is any kind of computer program or a group of them. It MUST be a [producer](#definitionsProducer), a [consumer](#definitionsConsumer) or both. An application MAY be a microservice, IoT device (sensor), mainframe process, etc. An application MUST also use a protocol supported by the server in order to connect and exchange [messages](#definitionsMessage).

#### <a name="definitionsProducer"></a>Producer
A producer is a type of application, connected to a server, that is creating [messages](#definitionsMessage) and addressing them to [channels](#definitionsChannel). A producer MAY be publishing to multiple channels depending on the server, protocol, and use-case pattern. 

#### <a name="definitionsConsumer"></a>Consumer
A consumer is a type of application, that is consuming [messages](#definitionsMessage) from [channels](#definitionsChannel). A consumer MAY be consuming from multiple channels depending on the server, protocol, and the use-case pattern. 

#### <a name="definitionsMessage"></a>Message
A message is the mechanism by which information is exchanged via a channel between servers and applications. A message MUST contain a payload and MAY also contain headers. The payload contains the data, defined by the application, which MUST be serialized into a format (JSON, XML, Avro, binary, etc.). Since a message is a generic mechanism, it can support multiple interaction patterns such as event, command, request, or response. 

#### <a name="definitionsChannel"></a>Channel
A channel is an addressable component, made available by the server, for the organization of [messages](#definitionsMessage). [Producer](#definitionsProducer) applications send messages to channels and [consumer](#definitionsConsumer) applications consume messages from channels. Servers MAY support many channel instances, allowing messages with different content to be addressed to different channels. Depending on the server implementation, the channel MAY be included in the message via protocol-defined headers.

## <a name="specification"></a>Specification

### <a name="format"></a>Format

JSON and YAML files are accepted.

### <a name="schema"></a>Schema

#### <a name="A2SObject"></a>AsyncAPI Object

This is the root document object for the API specification.
It combines resource listing and API declaration together into one document.

##### Fixed Fields

Field Name | Type | Description
---|:---:|---
asyncapi | AsyncAPI Version String | **Required.** Specifies the AsyncAPI Specification version being used. It can be used by tooling Specifications and clients to interpret the version. The structure shall be `major`.`minor`.`patch`, where `patch` versions _must_ be compatible with the existing `major`.`minor` tooling. Typically patch versions will be introduced to address errors in the documentation, and tooling should typically be compatible with the corresponding `major`.`minor` (1.0.*). Patch versions will correspond to patches of this document.
id | Identifier | Identifier of the [application](#definitionsApplication) the AsyncAPI document is defining.
info | [Info Object](#infoObject) | **Required.** Provides metadata about the API. The metadata can be used by the clients if needed.
 servers                    | Servers Object | Provides connection details of servers.
channels | [Channels Object](#channelsObject) | **Required** The available channels and messages for the API.
 components                 | [Components Object](#componentsObject) | An element to hold various schemas for the specification.
<a name="A2STags"></a>tags | Tags Object | A list of tags used by the specification with additional metadata. Each tag name in the list MUST be unique.
 externalDocs               | External Documentation Object | Additional external documentation.


This object can be extended with [Specification Extensions](#specificationExtensions).

#### <a name="A2SVersionString"></a>AsyncAPI Version String

For the moment the only version accepted for the AsyncAPI is 2.0.X .

#### <a name="A2SIdString"></a>Identifier

This field represents a unique universal identifier of the [application](#definitionsApplication) the AsyncAPI document is defining. It must conform to the URI format, according to [RFC3986](http://tools.ietf.org/html/rfc3986).

It is RECOMMENDED to use a [URN](https://tools.ietf.org/html/rfc8141) to globally and uniquely identify the application during long periods of time, even after it becomes unavailable or ceases to exist.

###### Examples

```json
{
  "id": "urn:com:smartylighting:streetlights:server"
}
```

```yaml
id: 'urn:com:smartylighting:streetlights:server'
```

```json
{
  "id": "https://github.com/smartylighting/streetlights-server"
}
```

```yaml
id: 'https://github.com/smartylighting/streetlights-server'
```

#### <a name="infoObject"></a>Info Object

The object provides metadata about the API.
The metadata can be used by the clients if needed.

see [Info Object](https://github.com/asyncapi/asyncapi/blob/master/versions/2.0.0/asyncapi.md#infoObject) for more details.


#### <a name="channelsObject"></a>Channels Object

Holds the relative paths to the individual channel and their operations. Channel paths are relative to servers.

Channels are also known as "topics", "routing keys", "event types" or "paths".

##### Patterned Fields

Field Pattern | Type | Description
---|:---:|---
<a name="channelsObjectChannel"></a>{channel} | [Channel Item Object](#channelItemObject) | A relative path to an individual channel. The field name MUST be in the form of a [RFC 6570 URI template](https://tools.ietf.org/html/rfc6570). Query parameters and fragments SHALL NOT be used, instead use bindings to define them. 

##### Channels Object Example

```json
{
  "user/signedup": {
    "subscribe": {
      "$ref": "#/components/messages/userSignedUp"
    }
  }
}
```

```yaml
user/signedup:
  subscribe:
    $ref: "#/components/messages/userSignedUp"
```




#### <a name="channelItemObject"></a>Channel Item Object

Describes the operations available on a single channel.

##### Fixed Fields

Field Name | Type | Description
---|:---:|---
$ref | `string` | Allows for an external definition of this channel item. The referenced structure MUST be in the format of a [Channel Item Object](#channelItemObject). If there are conflicts between the referenced definition and this Channel Item's definition, the behavior is *undefined*.
description | `string` | An optional description of this channel item. [CommonMark syntax](http://spec.commonmark.org/) can be used for rich text representation.
subscribe | [Operation Object](#operationObject) | A definition of the SUBSCRIBE operation.
 publish     | [Operation Object](#operationObject) | A definition of the PUBLISH operation.
parameters | [Parameters Object](#parametersObject) | A map of the parameters included in the channel name. It SHOULD be present only when using channels with expressions (as defined by [RFC 6570 section 2.2](https://tools.ietf.org/html/rfc6570#section-2.2)).
bindings | Channel Bindings Object | A map where the keys describe the name of the protocol and the values describe protocol-specific definitions for the channel.

This object can be extended with [Specification Extensions](#specificationExtensions).

##### Channel Item Object Example

```json
{
  "description": "This channel is used to exchange messages about users signing up",
  "subscribe": {
    "summary": "A user signed up.",
    "message": {
      "description": "A longer description of the message",
      "payload": {
        "type": "object",
        "properties": {
          "user": {
            "$ref": "#/components/schemas/user"
          },
          "signup": {
            "$ref": "#/components/schemas/signup"
          }
        }
      }
    }
  },
  "bindings": {
    "amqp": {
      "is": "queue",
      "queue": {
        "exclusive": true
      }
    }
  }
}
```

```yaml
description: This channel is used to exchange messages about users signing up
subscribe:
  summary: A user signed up.
  message:
    description: A longer description of the message
    payload:
      type: object
      properties:
        user:
          $ref: "#/components/schemas/user"
        signup:
bindings:
  amqp:
    is: queue
    queue:
      exclusive: true
```

Using `oneOf` to specify multiple messages per operation:

```json
{
  "subscribe": {
    "message": {
      "oneOf": [
        { "$ref": "#/components/messages/signup" },
        { "$ref": "#/components/messages/login" }
      ]
    }
  }
}
```

```yaml
subscribe:
  message:
    oneOf:
      - $ref: '#/components/messages/signup'
      - $ref: '#/components/messages/login'
```







#### <a name="operationObject"></a>Operation Object

Describes a publish or a subscribe operation. This provides a place to document how and why messages are sent and received. For example, an operation might describe a chat application use case where a user sends a text message to a group.

##### Fixed Fields

Field Name | Type | Description
---|:---:|---
operationId | `string` | Unique string used to identify the operation. The id MUST be unique among all operations described in the API. The operationId value is **case-sensitive**. Tools and libraries MAY use the operationId to uniquely identify an operation, therefore, it is RECOMMENDED to follow common programming naming conventions.
summary | `string` | A short summary of what the operation is about.
description | `string` | A verbose explanation of the operation. [CommonMark syntax](http://spec.commonmark.org/) can be used for rich text representation.
tags | [Tag Object] | A list of tags for API documentation control. Tags can be used for logical grouping of operations.
externalDocs | External Documentation Object | Additional external documentation for this operation.
bindings | Operation Bindings Object | A map where the keys describe the name of the protocol and the values describe protocol-specific definitions for the operation.
traits | [Operation Trait Object &#124; Reference Object ] | A list of traits to apply to the operation object. Traits MUST be merged into the operation object using the [JSON Merge Patch](https://tools.ietf.org/html/rfc7386) algorithm in the same order they are defined here. 
message | [[Message Object](#messageObject) &#124; [Reference Object](#referenceObject)] | A definition of the message that will be published or received on this channel. `oneOf` is allowed here to specify multiple messages, however, **a message MUST be valid only against one of the referenced message objects.**

This object can be extended with [Specification Extensions](#specificationExtensions).

##### Operation Object Example

```json
{
  "operationId": "registerUser",
  "summary": "Action to sign a user up.",
  "description": "A longer description",
  "tags": [
    { "name": "user" },
    { "name": "signup" },
    { "name": "register" }
  ],
  "message": {
    "headers": {
      "type": "object",
      "properties": {
        "applicationInstanceId": {
          "description": "Unique identifier for a given instance of the publishing application",
          "type": "string"
        }
      }
    },
    "payload": {
      "type": "object",
      "properties": {
        "user": {
          "$ref": "#/components/schemas/userCreate"
        },
        "signup": {
          "$ref": "#/components/schemas/signup"
        }
      }
    }
  },
  "bindings": {
    "amqp": {
      "ack": false
    },
  },
  "traits": [
    { "$ref": "#/components/operationTraits/kafka" }
  ]
}
```

```yaml
operationId: registerUser
summary: Action to sign a user up.
description: A longer description
tags:
  - name: user
  - name: signup
  - name: register
message:
  headers:
    type: object
    properties:
      applicationInstanceId:
        description: Unique identifier for a given instance of the publishing application
        type: string
  payload:
    type: object
    properties:
      user:
        $ref: "#/components/schemas/userCreate"
      signup:
        $ref: "#/components/schemas/signup"
bindings:
  amqp:
    ack: false
traits:
  - $ref: "#/components/operationTraits/kafka"
```

#### <a name="messageObject"></a>Message Object

Describes a message received on a given channel and operation.

##### Fixed Fields

| Field Name                                         |                             Type                             | Description                                                  |
| -------------------------------------------------- | :----------------------------------------------------------: | ------------------------------------------------------------ |
| headers                                            | [Schema Object](#schemaObject) &#124; [Reference Object](#referenceObject) | Schema definition of the application headers. Schema MUST be of type "object". It **MUST NOT** define the protocol headers. |
| payload                                            |                            `any`                             | Definition of the message payload. It can be of any type but defaults to [Schema object](#schemaObject). |
| correlationId                                      | [Correlation ID Object &#124; [Reference Object](#referenceObject)] | Definition of the correlation ID used for message tracing or matching. |
| schemaFormat                                       |                           `string`                           | A string containing the name of the schema format used to define the message payload. If omitted, implementations should parse the payload as a [Schema object](#schemaObject). Check out the [supported schema formats table](#messageObjectSchemaFormatTable) for more information. Custom values are allowed but their implementation is OPTIONAL. A custom value MUST NOT refer to one of the schema formats listed in the [table](#messageObjectSchemaFormatTable). |
| contentType                                        |                           `string`                           | The content type to use when encoding/decoding a message's payload. The value MUST be a specific media type (e.g. `application/json`). When omitted, the value MUST be the one specified on the defaultContentType field. |
| <a name="messageObjectName"></a>name               |                           `string`                           | A machine-friendly name for the message.                     |
| <a name="messageObjectTitle"></a>title             |                           `string`                           | A human-friendly title for the message.                      |
| <a name="messageObjectSummary"></a>summary         |                           `string`                           | A short summary of what the message is about.                |
| <a name="messageObjectDescription"></a>description |                           `string`                           | A verbose explanation of the message. [CommonMark syntax](http://spec.commonmark.org/) can be used for rich text representation. |
| tags                                               |                         Tags Object                          | A list of tags for API documentation control. Tags can be used for logical grouping of messages. |
| externalDocs                                       |                External Documentation Object                 | Additional external documentation for this message.          |
| bindings                                           |                   Message Bindings Object                    | A map where the keys describe the name of the protocol and the values describe protocol-specific definitions for the message. |
| ~~examples~~                                       |                  ~~[Map[`string`, `any`]]~~                  | ~~An array with examples of valid message objects.~~         |
| traits                                             | [Message Trait Object &#124; [Reference Object](#referenceObject)] | A list of traits to apply to the message object. Traits MUST be merged into the message object using the [JSON Merge Patch](https://tools.ietf.org/html/rfc7386) algorithm in the same order they are defined here. The resulting object MUST be a valid [Message Object](#messageObject). |

This object can be extended with [Specification Extensions](#specificationExtensions).

##### <a name="messageObjectSchemaFormatTable"></a>Schema formats table

The following table contains a set of values that every implementation MUST support.

| Name                                                         |                        Allowed values                        | Notes                                                      |
| ------------------------------------------------------------ | :----------------------------------------------------------: | ---------------------------------------------------------- |
| [AsyncAPI 2.0.0 Schema Object](#schemaObject)                | `application/vnd.aai.asyncapi;version=2.0.0`, `application/vnd.aai.asyncapi+json;version=2.0.0`, `application/vnd.aai.asyncapi+yaml;version=2.0.0` | This is the default when a `schemaFormat` is not provided. |
| [JSON Schema Draft 07](http://json-schema.org/specification-links.html#draft-7) | `application/schema+json;version=draft-07`, `application/schema+yaml;version=draft-07` |                                                            |

The following table contains a set of values that every implementation is RECOMMENDED to support.

| Name                                                         |                        Allowed values                        | Notes |
| ------------------------------------------------------------ | :----------------------------------------------------------: | ----- |
| [Avro 1.9.0 schema](https://avro.apache.org/docs/1.9.0/spec.html#schemas) | `application/vnd.apache.avro;version=1.9.0`, `application/vnd.apache.avro+json;version=1.9.0`, `application/vnd.apache.avro+yaml;version=1.9.0` |       |
| [OpenAPI 3.0.0 Schema Object](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#schemaObject) | `application/vnd.oai.openapi;version=3.0.0`, `application/vnd.oai.openapi+json;version=3.0.0`, `application/vnd.oai.openapi+yaml;version=3.0.0` |       |
| [RAML 1.0 data type](https://github.com/raml-org/raml-spec/blob/master/versions/raml-10/raml-10.md/) |             `application/raml+yaml;version=1.0`              |       |


##### Message Object Example

```json
{
  "name": "UserSignup",
  "title": "User signup",
  "summary": "Action to sign a user up.",
  "description": "A longer description",
  "contentType": "application/json",
  "tags": [
    { "name": "user" },
    { "name": "signup" },
    { "name": "register" }
  ],
  "headers": {
    "type": "object",
    "properties": {
      "correlationId": {
        "description": "Correlation ID set by application",
        "type": "string"
      },
      "applicationInstanceId": {
        "description": "Unique identifier for a given instance of the publishing application",
        "type": "string"
      }
    }
  },
  "payload": {
    "type": "object",
    "properties": {
      "user": {
        "$ref": "#/components/schemas/userCreate"
      },
      "signup": {
        "$ref": "#/components/schemas/signup"
      }
    }
  },
  "correlationId": {
    "description": "Default Correlation ID",
    "location": "$message.header#/correlationId"
  },
  "traits": [
    { "$ref": "#/components/messageTraits/commonHeaders" }
  ]
}
```

```yaml
name: UserSignup
title: User signup
summary: Action to sign a user up.
description: A longer description
contentType: application/json
tags:
  - name: user
  - name: signup
  - name: register
headers:
  type: object
  properties:
    correlationId:
      description: Correlation ID set by application
      type: string
    applicationInstanceId:
      description: Unique identifier for a given instance of the publishing application
      type: string
payload:
  type: object
  properties:
    user:
      $ref: "#/components/schemas/userCreate"
    signup:
      $ref: "#/components/schemas/signup"
correlationId:
  description: Default Correlation ID
  location: $message.header#/correlationId
traits:
  - $ref: "#/components/messageTraits/commonHeaders"
```

Example using Avro to define the payload:

```json
{
  "name": "UserSignup",
  "title": "User signup",
  "summary": "Action to sign a user up.",
  "description": "A longer description",
  "tags": [
    { "name": "user" },
    { "name": "signup" },
    { "name": "register" }
  ],
  "schemaFormat": "application/vnd.apache.avro+json;version=1.9.0",
  "payload": {
    "$ref": "path/to/user-create.avsc#/UserCreate"
  }
}
```

```yaml
name: UserSignup
title: User signup
summary: Action to sign a user up.
description: A longer description
tags:
  - name: user
  - name: signup
  - name: register
schemaFormat: 'application/vnd.apache.avro+yaml;version=1.9.0'
payload:
  $ref: 'path/to/user-create.avsc/#UserCreate'
```




#### <a name="parametersObject"></a>Parameters Object

Describes a map of parameters included in a channel name.

This map MUST contain all the parameters used in the parent channel name.

##### Patterned Fields

Field Pattern | Type | Description
---|:---:|---
`^[A-Za-z0-9_\-]+$` | [Parameter Object](#parameterObject) &#124; [Reference Object](#referenceObject) | The key represents the name of the parameter. It MUST match the parameter name used in the parent channel name.

##### Parameters Object Example

```json
{
  "user/{userId}/signup": {
    "parameters": {
      "userId": {
        "description": "Id of the user.",
        "schema": {
          "type": "string"
        }
      }
    },
    "subscribe": {
      "$ref": "#/components/messages/userSignedUp"
    }
  }
}
```

```yaml
user/{userId}/signup:
  parameters:
    userId:
      description: Id of the user.
      schema:
        type: string
  subscribe:
    $ref: "#/components/messages/userSignedUp"
```





#### <a name="parameterObject"></a>Parameter Object

Describes a parameter included in a channel name.

##### Fixed Fields

Field Name | Type | Description
---|:---:|---
description | `string` | A verbose explanation of the parameter. [CommonMark syntax](http://spec.commonmark.org/) can be used for rich text representation.
schema | [Schema Object](#schemaObject) | Definition of the parameter.
location | `string` | A [runtime expression](#runtimeExpression) that specifies the location of the parameter value. Even when a definition for the target field exists, it MUST NOT be used to validate this parameter but, instead, the `schema` property MUST be used.

This object can be extended with [Specification Extensions](#specificationExtensions).

##### Parameter Object Example

```json
{
  "user/{userId}/signup": {
    "parameters": {
      "userId": {
        "description": "Id of the user.",
        "schema": {
          "type": "string"
        },
        "location": "$message.payload#/user/id"
      }
    },
    "subscribe": {
      "$ref": "#/components/messages/userSignedUp"
    }
  }
}
```

```yaml
user/{userId}/signup:
  parameters:
    userId:
      description: Id of the user.
      schema:
        type: string
      location: $message.payload#/user/id
  subscribe:
    $ref: "#/components/messages/userSignedUp"
```



#### <a name="referenceObject"></a>Reference Object

A simple object to allow referencing other components in the specification, internally and externally.


The Reference Object is defined by [JSON Reference](https://tools.ietf.org/html/draft-pbryan-zyp-json-ref-03) and follows the same structure, behavior and rules. A JSON Reference SHALL only be used to refer to a schema that is formatted in either JSON or YAML. In the case of a YAML-formatted Schema, the JSON Reference SHALL be applied to the JSON representation of that schema. The JSON representation SHALL be made by applying the conversion described [here](#format).

For this specification, reference resolution is done as defined by the JSON Reference specification and not by the JSON Schema specification.

##### Fixed Fields
Field Name | Type | Description
---|:---:|---
$ref | `string` | **Required.** The reference string.

This object cannot be extended with additional properties and any properties added SHALL be ignored.

##### Reference Object Example

```json
{
  "$ref": "#/components/schemas/Pet"
}
```

```yaml
  $ref: '#/components/schemas/Pet'
```

#### <a name="componentsObject"></a>Components Object

Holds a set of reusable objects for different aspects of the AsyncAPI specification.
All objects defined within the components object will have no effect on the API unless they are explicitly referenced from properties outside the components object.

##### Fixed Fields

Field Name | Type | Description
---|:---|---
schemas | Map[`string`, [Schema Object](#schemaObject) \| [Reference Object](#referenceObject)] | An object to hold reusable [Schema Objects](#schemaObject).
messages | Map[`string`, [Message Object](#messageObject) \| [Reference Object](#referenceObject)] | An object to hold reusable [Message Objects](#messageObject).
securitySchemes| Map[`string`, Security Scheme Object \| [Reference Object](#referenceObject)] | An object to hold reusable Security Scheme Objects 
parameters | Map[`string`, [Parameter Object](#parameterObject) \| [Reference Object](#referenceObject)] | An object to hold reusable [Parameter Objects](#parameterObject).
correlationIds | Map[`string`, Correlation ID Object] | An object to hold reusable Correlation ID Objects. 
operationTraits | Map[`string`, Operation Trait Object]  | An object to hold reusable Operation Trait Objects. 
messageTraits | Map[`string`, Message Trait Object]  | An object to hold reusable Message Trait Objects. 
serverBindings | Map[`string`, Server Binding Object]  | An object to hold reusable Server Binding Objects. 
channelBindings | Map[`string`, Channel Binding Object]  | An object to hold reusable Channel Binding Objects. 
operationBindings | Map[`string`, Operation Binding Object]                      | An object to hold reusable Operation Binding Objects. 
messageBindings | Map[`string`, Message Binding Object] | An object to hold reusable Message Binding Objects. 

This object can be extended with [Specification Extensions](#specificationExtensions).

All the fixed fields declared above are objects that MUST use keys that match the regular expression: `^[a-zA-Z0-9\.\-_]+$`.

Field Name Examples:

```
User
User_1
User_Name
user-name
my.org.User
```

##### Components Object Example

```json
"components": {
  "schemas": {
    "Category": {
      "type": "object",
      "properties": {
        "id": {
          "type": "integer",
          "format": "int64"
        },
        "name": {
          "type": "string"
        }
      }
    },
    "Tag": {
      "type": "object",
      "properties": {
        "id": {
          "type": "integer",
          "format": "int64"
        },
        "name": {
          "type": "string"
        }
      }
    }
  },
  "messages": {
    "userSignUp": {
      "summary": "Action to sign a user up.",
      "description": "Multiline description of what this action does.\nHere you have another line.\n",
      "tags": [
        {
          "name": "user"
        },
        {
          "name": "signup"
        }
      ],
      "headers": {
        "type": "object",
        "properties": {
          "applicationInstanceId": {
            "description": "Unique identifier for a given instance of the publishing application",
            "type": "string"
          }
        }
      },
      "payload": {
        "type": "object",
        "properties": {
          "user": {
            "$ref": "#/components/schemas/userCreate"
          },
          "signup": {
            "$ref": "#/components/schemas/signup"
          }
        }
      }
    }
  },
  "parameters": {
    "userId": {
      "name": "userId",
      "description": "Id of the user.",
      "schema": {
        "type": "string"
      }
    }
  },
  "correlationIds": {
    "default": {
      "description": "Default Correlation ID",
      "location": "$message.header#/correlationId"
    }
  },
  "messageTraits": {
    "commonHeaders": {
      "headers": {
        "type": "object",
        "properties": {
          "my-app-header": {
            "type": "integer",
            "minimum": 0,
            "maximum": 100
          }
        }
      }
    }
  }
}
```

```yaml
components:
  schemas:
    Category:
      type: object
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
    Tag:
      type: object
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
  messages:
    userSignUp:
      summary: Action to sign a user up.
      description: |
        Multiline description of what this action does.
        Here you have another line.
      tags:
        - name: user
        - name: signup
      headers:
        type: object
        properties:
          applicationInstanceId:
            description: Unique identifier for a given instance of the publishing application
            type: string
      payload:
        type: object
        properties:
          user:
            $ref: "#/components/schemas/userCreate"
          signup:
            $ref: "#/components/schemas/signup"
  parameters:
    userId:
    - name: userId
      description: Id of the user.
      schema:
        type: string
  correlationIds:
    default:
      description: Default Correlation ID
      location: $message.header#/correlationId
  messageTraits:
    commonHeaders:
      headers:
        type: object
        properties:
          my-app-header:
            type: integer
            minimum: 0
            maximum: 100
```

#### <a name="schemaObject"></a>Schema Object

The Schema Object allows the definition of input and output data types.
These types can be objects, but also primitives and arrays.
This object is a superset of the [JSON Schema Specification Draft 07](http://json-schema.org/).

Further information about the properties can be found in [JSON Schema Core](https://tools.ietf.org/html/draft-handrews-json-schema-01) and [JSON Schema Validation](https://tools.ietf.org/html/draft-handrews-json-schema-validation-01).
Unless stated otherwise, the property definitions follow the JSON Schema specification as referenced here.

##### Properties

The AsyncAPI Schema Object is a JSON Schema vocabulary which extends JSON Schema Core and Validation vocabularies. As such, any keyword available for those vocabularies is by definition available in AsyncAPI, and will work the exact same way, including but not limited to:

- title
- type
- required
- multipleOf
- maximum
- exclusiveMaximum
- minimum
- exclusiveMinimum
- maxLength
- minLength
- pattern (This string SHOULD be a valid regular expression, according to the [ECMA 262 regular expression](https://www.ecma-international.org/ecma-262/5.1/#sec-7.8.5) dialect)
- maxItems
- minItems
- uniqueItems
- maxProperties
- minProperties
- enum
- const
- examples
- if / then / else
- readOnly
- writeOnly
- properties
- patternProperties
- additionalProperties
- additionalItems
- items
- propertyNames
- contains
- allOf
- oneOf
- anyOf
- not

The following properties are taken from the JSON Schema definition but their definitions were adjusted to the AsyncAPI Specification.

- description - [CommonMark syntax](http://spec.commonmark.org/) can be used for rich text representation.
- format - See [Data Type Formats](#dataTypeFormat) for further details. While relying on JSON Schema's defined formats, the AsyncAPI Specification offers a few additional predefined formats.
- default - The default value represents what would be assumed by the consumer of the input as the value of the schema if one is not provided. Unlike JSON Schema, the value MUST conform to the defined type for the Schema Object defined at the same level. For example, of `type` is `string`, then `default` can be `"foo"` but cannot be `1`.

Alternatively, any time a Schema Object can be used, a [Reference Object](#referenceObject) can be used in its place. This allows referencing definitions in place of defining them inline.

In addition to the JSON Schema fields, the following AsyncAPI vocabulary fields MAY be used for further schema documentation:

##### Fixed Fields
Field Name | Type | Description
---|:---:|---
discriminator | `string` | Adds support for polymorphism. The discriminator is the schema property name that is used to differentiate between other schema that inherit this schema. The property name used MUST be defined at this schema and it MUST be in the `required` property list. When used, the value MUST be the name of this schema or any schema that inherits it. See [Composition and Inheritance](#schemaComposition) for more details.
externalDocs | External Documentation Object | Additional external documentation for this schema.
deprecated | `boolean` | Specifies that a schema is deprecated and SHOULD be transitioned out of usage. Default value is `false`.

This object can be extended with [Specification Extensions](#specificationExtensions).

###### <a name="schemaComposition"></a>Composition and Inheritance (Polymorphism)

The AsyncAPI Specification allows combining and extending model definitions using the `allOf` property of JSON Schema, in effect offering model composition.
`allOf` takes in an array of object definitions that are validated *independently* but together compose a single object.

While composition offers model extensibility, it does not imply a hierarchy between the models.
To support polymorphism, AsyncAPI Specification adds the support of the `discriminator` field.
When used, the `discriminator` will be the name of the property used to decide which schema definition is used to validate the structure of the model.
As such, the `discriminator` field MUST be a required field.
There are are two ways to define the value of a discriminator for an inheriting instance.

- Use the schema's name.
- Override the schema's name by overriding the property with a new value. If exists, this takes precedence over the schema's name.

As such, inline schema definitions, which do not have a given id, *cannot* be used in polymorphism.

##### Schema Object Examples

###### Primitive Sample

```json
{
  "type": "string",
  "format": "email"
}
```

```yaml
type: string
format: email
```

###### Simple Model

```json
{
  "type": "object",
  "required": [
    "name"
  ],
  "properties": {
    "name": {
      "type": "string"
    },
    "address": {
      "$ref": "#/components/schemas/Address"
    },
    "age": {
      "type": "integer",
      "format": "int32",
      "minimum": 0
    }
  }
}
```

```yaml
type: object
required:
- name
properties:
  name:
    type: string
  address:
    $ref: '#/components/schemas/Address'
  age:
    type: integer
    format: int32
    minimum: 0
```

###### Model with Map/Dictionary Properties

For a simple string to string mapping:

```json
{
  "type": "object",
  "additionalProperties": {
    "type": "string"
  }
}
```

```yaml
type: object
additionalProperties:
  type: string
```

For a string to model mapping:

```json
{
  "type": "object",
  "additionalProperties": {
    "$ref": "#/components/schemas/ComplexModel"
  }
}
```

```yaml
type: object
additionalProperties:
  $ref: '#/components/schemas/ComplexModel'
```

###### Model with Example

```json
{
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "format": "int64"
    },
    "name": {
      "type": "string"
    }
  },
  "required": [
    "name"
  ],
  "example": {
    "name": "Puma",
    "id": 1
  }
}
```

```yaml
type: object
properties:
  id:
    type: integer
    format: int64
  name:
    type: string
required:
- name
example:
  name: Puma
  id: 1
```

###### Models with Composition

```json
{
  "schemas": {
    "ErrorModel": {
      "type": "object",
      "required": [
        "message",
        "code"
      ],
      "properties": {
        "message": {
          "type": "string"
        },
        "code": {
          "type": "integer",
          "minimum": 100,
          "maximum": 600
        }
      }
    },
    "ExtendedErrorModel": {
      "allOf": [
        {
          "$ref": "#/components/schemas/ErrorModel"
        },
        {
          "type": "object",
          "required": [
            "rootCause"
          ],
          "properties": {
            "rootCause": {
              "type": "string"
            }
          }
        }
      ]
    }
  }
}
```

```yaml
schemas:
  ErrorModel:
    type: object
    required:
    - message
    - code
    properties:
      message:
        type: string
      code:
        type: integer
        minimum: 100
        maximum: 600
  ExtendedErrorModel:
    allOf:
    - $ref: '#/components/schemas/ErrorModel'
    - type: object
      required:
      - rootCause
      properties:
        rootCause:
          type: string
```

###### Models with Polymorphism Support

```json
{
  "schemas": {
    "Pet": {
      "type": "object",
      "discriminator": "petType",
      "properties": {
        "name": {
          "type": "string"
        },
        "petType": {
          "type": "string"
        }
      },
      "required": [
        "name",
        "petType"
      ]
    },
    "Cat": {
      "description": "A representation of a cat. Note that `Cat` will be used as the discriminator value.",
      "allOf": [
        {
          "$ref": "#/components/schemas/Pet"
        },
        {
          "type": "object",
          "properties": {
            "huntingSkill": {
              "type": "string",
              "description": "The measured skill for hunting",
              "enum": [
                "clueless",
                "lazy",
                "adventurous",
                "aggressive"
              ]
            }
          },
          "required": [
            "huntingSkill"
          ]
        }
      ]
    },
    "Dog": {
      "description": "A representation of a dog. Note that `Dog` will be used as the discriminator value.",
      "allOf": [
        {
          "$ref": "#/components/schemas/Pet"
        },
        {
          "type": "object",
          "properties": {
            "packSize": {
              "type": "integer",
              "format": "int32",
              "description": "the size of the pack the dog is from",
              "minimum": 0
            }
          },
          "required": [
            "packSize"
          ]
        }
      ]
    },
    "StickInsect": {
      "description": "A representation of an Australian walking stick. Note that `StickBug` will be used as the discriminator value.",
      "allOf": [
        {
          "$ref": "#/components/schemas/Pet"
        },
        {
          "type": "object",
          "properties": {
            "petType": {
              "const": "StickBug"
            },
            "color": {
              "type": "string",
            }
          },
          "required": [
            "color"
          ]
        }
      ]
    }
  }
}
```

```yaml
schemas:
  Pet:
    type: object
    discriminator: petType
    properties:
      name:
        type: string
      petType:
        type: string
    required:
    - name
    - petType
  ## applies to instances with `petType: "Cat"`
  ## because that is the schema name
  Cat:
    description: A representation of a cat
    allOf:
    - $ref: '#/components/schemas/Pet'
    - type: object
      properties:
        huntingSkill:
          type: string
          description: The measured skill for hunting
          enum:
          - clueless
          - lazy
          - adventurous
          - aggressive
      required:
      - huntingSkill
  ## applies to instances with `petType: "Dog"`
  ## because that is the schema name
  Dog:
    description: A representation of a dog
    allOf:
    - $ref: '#/components/schemas/Pet'
    - type: object
      properties:
        packSize:
          type: integer
          format: int32
          description: the size of the pack the dog is from
          minimum: 0
      required:
      - packSize
  ## applies to instances with `petType: "StickBug"`
  ## because that is the required value of the discriminator field,
  ## overriding the schema name
  StickInsect:
    description: A representation of an Australian walking stick
    allOf:
    - $ref: '#/components/schemas/Pet'
    - type: object
      properties:
        petType:
          const: StickBug
        color:
          type: string
      required:
      - color
```



### <a name="correlationIdObject"></a>Correlation ID Object

An object that specifies an identifier at design time that can used for message tracing and correlation. 

For specifying and computing the location of a Correlation ID, a [runtime expression](#runtimeExpression) is used.

##### Fixed Fields

Field Name | Type | Description
---|:---|---
description | `string` | An optional description of the identifier. [CommonMark syntax](http://spec.commonmark.org/) can be used for rich text representation.
location | `string` | **REQUIRED.** A [runtime expression](#runtimeExpression) that specifies the location of the correlation ID.

This object can be extended with [Specification Extensions](#specificationExtensions).

##### Examples

```json
{
  "description": "Default Correlation ID",
  "location": "$message.header#/correlationId"
}
```

```yaml
description: Default Correlation ID
location: $message.header#/correlationId
```



### <a name="specificationExtensions"></a>Specification Extensions

While the AsyncAPI Specification tries to accommodate most use cases, additional data can be added to extend the specification at certain points.

The extensions properties are implemented as patterned fields that are always prefixed by `"x-"`.

Field Pattern | Type | Description
---|:---:|---
<a name="infoExtensions"></a>`^x-[\w\d\-\_]+$` | Any | Allows extensions to the AsyncAPI Schema. The field name MUST begin with `x-`, for example, `x-internal-id`. The value can be `null`, a primitive, an array or an object. Can have any valid JSON format value.

The extensions may or may not be supported by the available tooling, but those may be extended as well to add requested support (if tools are internal or open-sourced).
