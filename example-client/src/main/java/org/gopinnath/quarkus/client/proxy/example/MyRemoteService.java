package org.gopinnath.quarkus.client.proxy.example;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.inject.Default;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@RegisterRestClient(baseUri = "http://localhost:8000")
@RegisterClientHeaders(MyRemoteHeaderFactory.class)
@ClientHeaderParam(name = "Key", value = "{getApiKey}")
@ClientHeaderParam(name = "api-key", value = "{getConfigValue}")
@ClientHeaderParam(name = "dynamic-api-key", value = "${api-key}")
public interface MyRemoteService {

    default String getApiKey() {
        return ConfigProvider.getConfig().getValue("api-key",String.class);
    }

    default String getConfigValue(String key) {
        return ConfigProvider.getConfig().getValue(key,String.class);
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String helloWithKeyHeader();
}
