package com.trend.frs.lucifer.endpoints;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.trend.frs.lucifer.services.EmployeeService;

@Path("/rs21/employee")
public class JaxRs21Endpoint {
	private static final Logger LOG = LogManager.getLogger(JaxRs20Endpoint.class);

	private static ExecutorService executor = Executors.newFixedThreadPool(32);

	@Inject
	private EmployeeService employeeSvc;
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public CompletionStage<String> searchByName(@PathParam("name") final String name) {
		
		final CompletableFuture<Boolean> validateUserTask = new CompletableFuture<>();

		CompletableFuture<String> searchDriverTask = validateUserTask.thenComposeAsync(t -> {
			return CompletableFuture.supplyAsync(() -> searchDriver(), executor);
		}, executor);

		final CompletableFuture<String> notifyUserTask = searchDriverTask.thenApplyAsync(driver -> notifyUser(driver),
				executor);

		executor.execute(() -> {
			validateUserTask.complete(exist(name));
		});

		return notifyUserTask;
	}

	private Boolean exist(String name) {
		LOG.debug("searchDriverTask handled by thread: {} ", Thread.currentThread().getName());
		LOG.debug("searching employee: {}", name);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			LOG.warn("request was interrupted.", ex);
		}
		return (employeeSvc.search(name) != null);
	}

	private String searchDriver() {
		LOG.debug("searchDriverTask handled by thread: {} ", Thread.currentThread().getName());

		try {
			Thread.sleep(2500);
		} catch (InterruptedException ex) {
			LOG.warn("request was interrupted.", ex);
		}
		return "johndoe";
	}

	private String notifyUser(String driver) {
		LOG.debug("searchDriverTask handled by thread: {}", Thread.currentThread().getName());

		return "Your driver is " + driver + " and the OTP is " + (new Random().nextInt(999) + 1000);
	}

}
