package microservices.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {

    private static Log LOGGER = LogFactory.getLog(EurekaServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("initializing discovery service...");
        SpringApplication.run(EurekaServiceApplication.class, args);
    }
}
