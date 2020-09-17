package com.arkea.asyncapi.v2.models.info;

public class Identifier {

	/**
	 * This field represents a unique universal identifier of the application the AsyncAPI document is defining. It must conform to the URI format, according to RFC3986.
		It is RECOMMENDED to use a URN to globally and uniquely identify the application during long periods of time, even after it becomes unavailable or ceases to exist.

		Examples
		{
		  "id": "urn:com:smartylighting:streetlights:server"
		}
		id: 'urn:com:smartylighting:streetlights:server'
		{
		  "id": "https://github.com/smartylighting/streetlights-server"
		}
		id: 'https://github.com/smartylighting/streetlights-server' */

	private String id = null;



	public String getId() {
		return this.id;
	}
    /**
     * Set the id property from an Identifier instance.
     *
     * @return Identifier instance
     **/

	public Identifier id(final String id) {
		this.id = id;
		return this;
	}

}
