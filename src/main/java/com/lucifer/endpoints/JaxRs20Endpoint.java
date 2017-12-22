package com.trend.frs.lucifer.endpoints;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trend.frs.lucifer.models.Employee;
import com.trend.frs.lucifer.services.EmployeeService;

@Path("/rs20/employee")
public class JaxRs20Endpoint {

	private static final Logger LOG = LogManager.getLogger(JaxRs20Endpoint.class);

	private static ExecutorService executor = Executors.newFixedThreadPool(32);

	@Inject
	private EmployeeService employeeSvc;

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public void timeRequest(@PathParam("name") final String name, @Suspended AsyncResponse asyncResponse) {

		asyncResponse.setTimeout(5, TimeUnit.SECONDS);
		asyncResponse.setTimeoutHandler(resp -> {
			resp.resume(Response.status(Status.REQUEST_TIMEOUT).build());
		});

		executor.execute(() -> {
			Employee e = employeeSvc.search(name);
			if (e != null) {
				asyncResponse.resume(Response.ok(e).build());
			} else {
				asyncResponse.resume(Response.status(Status.NOT_FOUND).build());
			}
		});
	}

	@GET
	@Path("/callback/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public void callbackRequest(@PathParam("name") final String name, @Suspended AsyncResponse asyncResponse) {
		asyncResponse.register((CompletionCallback) (t -> {
			if (t != null) {
				LOG.error("something wrong on complete.", t);
			}
		}));

		asyncResponse.register((ConnectionCallback) (resp -> {
			resp.cancel();
			LOG.error("Connection lost or closed by the client!");
		}));

		executor.execute(() -> {
			Employee e = employeeSvc.search(name);
			if (e != null) {
				asyncResponse.resume(Response.ok(e).build());
			} else {
				asyncResponse.resume(Response.status(Status.NOT_FOUND).build());
			}
		});
	}
}
