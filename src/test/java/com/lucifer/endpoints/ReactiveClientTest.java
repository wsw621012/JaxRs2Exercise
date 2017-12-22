package com.trend.frs.lucifer.endpoints;

import static org.junit.Assert.*;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class ReactiveClientTest {

	private WebTarget tut;

    @Before
    public void initClient() {
        this.tut = ClientBuilder.newClient().target("http://localhost:8080/rs21/employee/John");
    }
    
	@Test
	public void reactive() throws InterruptedException {
        CompletionStage<Response> stage = this.tut.request().rx().get();
        stage.thenApply(req -> req.readEntity(String.class)).thenAccept(System.out::println);
        Thread.sleep(5000);
    }

}
