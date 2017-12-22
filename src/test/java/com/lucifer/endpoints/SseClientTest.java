package com.trend.frs.lucifer.endpoints;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import org.junit.Before;
import org.junit.Test;

public class SseClientTest {

	private WebTarget target;

	@Before
	public void initClient() {
		Client client = ClientBuilder.newBuilder().register(String.class).build();
		target = client.target("http://localhost:8080/sse/stream"); // 1
	}

	@Test
	public void test() {

		SseEventSource eventSource = SseEventSource.target(target).build(); // 2
		System.out.println("SSE Event source created........");

		eventSource.register(inboundEvent -> {
			System.out.println("Events received in thread " + Thread.currentThread().getName());
			System.out.println("SSE event recieved ----- " + inboundEvent.getName() + ":" + inboundEvent.readData());
		}, t -> { // 4
			t.printStackTrace();
		}, () -> { // 5
			System.out.println("process complete");
		});

		eventSource.open(); // 6
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		eventSource.close(); // 7
	}

}
