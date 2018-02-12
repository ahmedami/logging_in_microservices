package microservices.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class VehicleServiceApplication {

    private static Log LOGGER = LogFactory.getLog(VehicleServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Initializing vehicle service...");
        SpringApplication.run(VehicleServiceApplication.class, args);
    }

    @RequestMapping(value = "/user/{cid}/vehicledetails", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomerVehicleDetails(@PathVariable Integer cid) {
        LOGGER.info(" Getting Vehicle details for useer : " + cid);
        Hashtable<Integer, String> vehicles = new Hashtable<Integer, String>();
        vehicles.put(100, "Tesla Model 3.");
        vehicles.put(101, "Mercedes-Benz Project ONE.");
        vehicles.put(102, "BMW 2018.");

        String result = vehicles.get(cid);
        LOGGER.info(" return Vehicle details for useer : " + cid);
        return result;
    }
}
