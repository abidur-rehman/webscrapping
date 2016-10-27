package webscrapping.com.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Team {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Team.class);
	private String name;
	private Integer points;
	
	public static Team createTeam(String name, Integer points) {
		LOGGER.info("Creating team " + name + " with points "+points);
		Team team = new Team();
		team.name = name;
		team.points = points;
		return team;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	@Override
	public String toString() {
		return "Team {name: "+ this.getName()+", points: "+ this.getPoints()+"}";
	}

}
