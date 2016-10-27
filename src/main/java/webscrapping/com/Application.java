package webscrapping.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webscrapping.com.service.DriverService;
import webscrapping.com.service.ResultService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private ResultService resultService;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
	@Override
	public void run(String... args) throws Exception {
		driverService.init();
		String result = resultService.getJsonResult();
		LOGGER.info(result);
	}
	
}
