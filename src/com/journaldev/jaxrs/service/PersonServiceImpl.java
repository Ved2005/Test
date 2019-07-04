
package com.journaldev.jaxrs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.journaldev.jaxrs.model.Link;
import com.journaldev.jaxrs.model.Person;
import com.journaldev.jaxrs.model.Response;
import com.journaldev.jaxrs.model.User;
import com.sun.jersey.api.client.WebResource.Builder;

@Path("/person")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class PersonServiceImpl implements PersonService {

	private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

	@Override
	@POST
	@Path("/add")
	public Response addPerson(Person p , @Context UriInfo uriInfo) {
		Response response = new Response();
		if (persons.get(p.getId()) != null) {
			response.setStatus(false);
			response.setMessage("Person Already Exists");
			return response;
		}
		persons.put(p.getId(), p);
		populateUser(p,response,uriInfo);
		response.setStatus(true);
		response.setMessage("Person created successfully");
		
		return response;
	}

	private void populateUser(Person p, Response response, UriInfo uriInfo) {
		
		User newUser=new User(1,"Vedp","Engg");
		p.setUser(newUser);
		String url=uriInfo.getBaseUriBuilder()
				.path(PersonServiceImpl.class)
				.path(PersonServiceImpl.class,"getPerson")			
				
				.build(p.getId()).toString();
		response.setLink(new Link("user",url ));
	}

	@Override
	@GET
	@Path("/{id}/delete")
	public Response deletePerson(@PathParam("id") int personID) {
		Response response = new Response();
		if (persons.get(personID) == null) {
			response.setStatus(false);
			response.setMessage("Person Doesn't Exists");
			return response;
		}
		persons.remove(personID);
		response.setStatus(true);
		response.setMessage("Person deleted successfully");
		return response;
	}

	@Override
	@GET
	@Path("/{id}/get")
	public Person getPerson(@PathParam("id") int id) {
		return persons.get(id);
	}

	@GET
	@Path("/{id}/getDummy")
	public Person getDummyPerson(@PathParam("id") int id) {
		Person p = new Person();
		p.setAge(99);
		p.setName("Dummy");
		p.setId(id);
		return p;
	}

	@Override
	@GET
	@Path("/getAll")
	public Person[] getAllPersons() {
		Set<Integer> ids = persons.keySet();
		Person[] p = new Person[ids.size()];
		int i = 0;
		for (Integer id : ids) {
			p[i] = persons.get(id);
			i++;
		}
		return p;
	}

}
