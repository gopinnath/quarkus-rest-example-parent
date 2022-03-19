package org.gopinnath.quarkus.client.proxy.example;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.logging.Logger;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class MyRemoteHeaderFactory implements ClientHeadersFactory {

    private static final Logger LOGGER = Logger.getLogger(MyRemoteHeaderFactory.class);

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> inbound,
                                                 MultivaluedMap<String, String> outbound) {
        MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();
        inbound.forEach((key, values) -> readAndReplaceConfigValue(key, values, headers));
        outbound.forEach((key, values) -> readAndReplaceConfigValue(key, values, headers));
        return headers;
    }

    private void readAndReplaceConfigValue(String key, List<String> values,
                                           MultivaluedMap<String, String> headers) {
        values.forEach(value -> {
            if(value.startsWith("${") && value.endsWith("}")) {
                String propertyKey = value.substring(2, value.length() - 1);
                LOGGER.info("Replacing " + propertyKey);
                headers.add(key, ConfigProvider.getConfig().getValue(propertyKey,String.class));
            } else {
                headers.add(key, value);
            }
        });
    }
}
