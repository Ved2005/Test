
package com.journaldev.jaxrs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	private boolean status;
	private String message;
	private Link link;

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
