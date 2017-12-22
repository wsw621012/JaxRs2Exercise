package com.trend.frs.lucifer.endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.trend.frs.lucifer.models.Employee;
import com.trend.frs.lucifer.services.EmployeeService;

@Path("/employee")
public class EmployeeEndpoint {

	@Inject
	private EmployeeService employeeSvc;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(employeeSvc.all().toArray()).build();
	}
	
	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("name") final String name) {
		Employee e = employeeSvc.search(name);
		if (e != null) {
			return Response.ok(e).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
