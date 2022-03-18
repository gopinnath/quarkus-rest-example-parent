package org.gopinnath.quarkus.client.proxy.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @RestClient
    private MyRemoteService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return service.helloWithKeyHeader();
    }
}