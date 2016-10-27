package webscrapping.com.service;

import java.util.List;

import webscrapping.com.model.Driver;
import webscrapping.com.model.Result;
import webscrapping.com.model.Team;

public interface ResultService {

	void buildResult(List<Team> teamsList, List<Driver> driversList);
	String getJsonResult();
	Result getResult();

}
