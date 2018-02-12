package microservices.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class UserDetailsApplication {

    private static Log LOGGER = LogFactory.getLog(UserDetailsApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Initializing user detail service....");
        SpringApplication.run(UserDetailsApplication.class, args);
    }

    @RequestMapping(value = "/user/{cid}/userdetails", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomerContactDetails(@PathVariable Integer cid) throws InterruptedException {

        LOGGER.info("Getting User details for user:" + cid);
        Hashtable<Integer, String> customers = new Hashtable<Integer, String>();
        customers.put(100, "Ibra");
        customers.put(101, "Marc");
        customers.put(102, "Chris");

        String result = customers.get(cid);
        LOGGER.info("retrn User details for result for user:" + cid);
        return result;
    }
}
