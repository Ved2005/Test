package com.journaldev.jaxrs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {
	String rel;
	String url;
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Link(){}
	
	public Link(String rel, String url) {
		super();
		this.rel = rel;
		this.url = url;
	}
	
	//test

}
