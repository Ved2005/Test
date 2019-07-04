package com.journaldev.jaxrs.service;

import javax.ws.rs.core.UriInfo;

import com.journaldev.jaxrs.model.Person;
import com.journaldev.jaxrs.model.Response;

public interface PersonService { 
	public Response deletePerson(int id);

	public Person getPerson(int id);

	public Person[] getAllPersons();

	public Response addPerson(Person p, UriInfo uriInfo);

}
