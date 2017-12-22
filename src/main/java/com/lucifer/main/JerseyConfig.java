package com.trend.frs.lucifer.main;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		registerEndpoints();
		configureSwagger();
	}
	
	private void registerEndpoints() {
		packages("com.trend.frs.lucifer.endpoints");
        //register(EmployeeEndpoint.class);
        //register(AsyncRs20Endpoint.class);
    }
	
	private void configureSwagger() {
//        register(ApiListingResource.class);
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("0.0.1");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/api");
//        beanConfig.setResourcePackage("com.trend.frs.lucifer.resources");
//        beanConfig.setPrettyPrint(true);
//        beanConfig.setScan(true);
    }

}
