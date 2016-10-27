package webscrapping.com.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);
	private String name;
	private Integer points;
	
	public static Driver createDriver(String name, Integer points) {
		LOGGER.info("Creating driver " + name + " with points "+points);
		Driver driver = new Driver();
		driver.name = name;
		driver.points = points;
		return driver;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	@Override
	public String toString() {
		return "Driver {name: "+ this.getName()+", points: "+ this.getPoints()+"}";
	}
}
