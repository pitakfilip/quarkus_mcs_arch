package cz.muni.soa.infrastructure.discovery;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ApplicationScoped
public class ConsulRegistration {

    @ConfigProperty(name = "quarkus.application.name", defaultValue = "null")
    String serviceName;

    @ConfigProperty(name = "quarkus.http.port", defaultValue = "null")
    String servicePort;

    String serviceId;


    public void init(@Observes StartupEvent ev, Vertx vertx) {
        ConsulClient consul = ConsulClient.create(vertx, new ConsulClientOptions().setHost("localhost").setPort(8500));
        String host = getHostAddress();
        serviceId =  serviceName + ":" + servicePort;

        consul.registerService(new ServiceOptions()
                .setAddress(host)
                .setPort(Integer.parseInt(servicePort))
                .setName(serviceName)
                .setId(serviceId)
                .setCheckOptions(new CheckOptions()
                        .setName("Service Healthcheck")
                        .setServiceId(serviceId)
                        .setStatus(CheckStatus.PASSING) // Set initial status state
                        .setHttp("http://"+ host + ":" + servicePort + "/q/health")
                        .setInterval("1m")
                        .setDeregisterAfter("1m")
                )
        );
    }

    private static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "127.0.0.1";
        }
    }
}
