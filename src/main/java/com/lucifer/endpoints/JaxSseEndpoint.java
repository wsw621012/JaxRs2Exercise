package com.trend.frs.lucifer.endpoints;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.trend.frs.lucifer.models.Customer;
import com.trend.frs.lucifer.models.Employee;
import com.trend.frs.lucifer.models.Student;

@Path("/sse")
public class JaxSseEndpoint {

	private SseBroadcaster channel; // get handle to SseBroadcaster

	@GET // 1
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void emit(@Context SseEventSink eventSink, @Context Sse util) { // 2
		OutboundSseEvent sseEvent = util.newEvent("abhirockzz", new Date().toString()); // 3
		eventSink.send(sseEvent); // 4
		eventSink.close(); // 5
	}

	@Path("subscribe")
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void subscribe(@Context SseEventSink eventSink, @Context Sse util) {
		eventSink.send(util.newEvent("Subscription accepted. ID - " + UUID.randomUUID().toString()));
		channel.register(eventSink);
	}

	
	@Path("/stream")
	@GET
	@Produces("text/event-stream")
	public void fetch(@Context Sse sse, @Context SseEventSink eSink) {

	        OutboundSseEvent stringEvent = sse.newEventBuilder()
	                .name("stringEvent")
	                .data(new Date().toString()).build();
	        eSink.send(stringEvent);

	        OutboundSseEvent primitiveTypeEvent = sse.newEventBuilder()
	                .name("primitiveTypeEvent")
	                .data(System.currentTimeMillis()).build();
	        eSink.send(primitiveTypeEvent);

	        OutboundSseEvent jsonbType = sse.newEventBuilder()
	                .name("jsonbType")
	                .data(new Employee("test", "test@test", 42))
	                .mediaType(MediaType.APPLICATION_JSON_TYPE)
	                .build();
	        eSink.send(jsonbType);

	        OutboundSseEvent jaxbType = sse.newEventBuilder()
	                .name("jaxbType")
	                .data(new Customer("king", "testcut@test"))
	                .mediaType(MediaType.APPLICATION_XML_TYPE)
	                .build();
	        eSink.send(jaxbType);

	        OutboundSseEvent customObjWithMBW = sse.newEventBuilder()
	                .name("customObjectWithMessageBodyWriter")
	                .data(new Student("student", "60727"))
	                .mediaType(MediaType.APPLICATION_JSON_TYPE)
	                .build();
	        eSink.send(customObjWithMBW);

	        System.out.println("events sent");
	        eSink.close();
	        System.out.println("sink closed");
	}
}
