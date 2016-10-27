package webscrapping.com.service;

import java.util.List;

import webscrapping.com.model.Driver;

public interface DriverService {
	public void init() throws InterruptedException;
	public List<Driver> buildDriversList(); 
}
