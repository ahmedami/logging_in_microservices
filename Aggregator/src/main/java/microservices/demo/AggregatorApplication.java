package microservices.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class AggregatorApplication {

    private static Log LOGGER = LogFactory.getLog(AggregatorApplication.class);

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate webTemplate;

    public static void main(String[] args) {
        LOGGER.info("Initializing aggregation service ...");
        SpringApplication.run(AggregatorApplication.class, args);
    }


    @RequestMapping(value = "/user/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomer(@PathVariable Integer cid) {

        LOGGER.info("Getting Aggregated information for user: "+ cid);
        LOGGER.info("calling user-details service for user: "+ cid);
        ResponseEntity<String> contact = webTemplate.getForEntity("http://user-details/demo/user/" + cid + "/userdetails", String.class);
        LOGGER.info("calling vehicle-details service for user: "+ cid);
        ResponseEntity<String> vehicle = webTemplate.getForEntity("http://vehicle-service/demo/user/" + cid + "/vehicledetails", String.class);
        LOGGER.info("return aggregated result for user: "+ cid);
        return contact.getBody() + " | " + vehicle.getBody();

    }
}
