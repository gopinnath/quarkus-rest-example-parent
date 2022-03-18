package org.gopinnath.quarkus.client.proxy.example;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOGGER = Logger.getLogger(GreetingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@HeaderParam("key") String key, @HeaderParam("api-key") String apiKey) {
        LOGGER.info("Inside hello method...");
        LOGGER.info("Key received is " + key);
        LOGGER.info("Api Key received is " + apiKey);
        return "Hello From Example Service";
    }
}