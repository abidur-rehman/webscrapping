package webscrapping.com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Team> teamsList = new ArrayList<Team>();
	private List<Driver> driversList = new ArrayList<Driver>();

	
	public static Result createResult(List<Team> teamsList, List<Driver> driversList) {
		Result result = new Result();
		result.teamsList = teamsList;
		result.driversList = driversList;
		return result;
	}
	
	public void setTeamsList(List<Team> teamsList) {
		this.teamsList = teamsList;
	}
	
	public List<Team> getTeamsList() {
		return teamsList;
	}
	
	public void setDriversList(List<Driver> driversList) {
		this.driversList = driversList;
	}
	
	public List<Driver> getDriversList() {
		return driversList;
	}

}
