package webscrapping.com.service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import webscrapping.com.model.Driver;
import webscrapping.com.model.Result;
import webscrapping.com.model.Team;

@Component
public class ResultServiceImpl implements ResultService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResultServiceImpl.class);
	private String jsonResult;
	private Result result;

	@Override
	public void buildResult(List<Team> teamsList, List<Driver> driversList) {
		result = createResult(teamsList, driversList);
	}
	
	public Result createResult(List<Team> teamsList, List<Driver> driversList) {
		List<Team> orderedTeamList = getOrderedTeamsList(teamsList);
		
		List<Driver> orderedDriversList = getOrderedDriversList(driversList);
		Result result = Result.createResult(orderedTeamList, orderedDriversList);
		return result;
	}

	public List<Driver> getOrderedDriversList(List<Driver> driversList) {
		List<Driver> top10Drivers = driversList.stream().sorted(
				((t1, t2) -> t2.getPoints().compareTo(t1.getPoints())))
				.limit(10)
				.collect(Collectors.toList());
		return top10Drivers;
	}

	public List<Team> getOrderedTeamsList(List<Team> teamsList) {
		List<Team> top5Teams = teamsList.stream().sorted(
				((t1, t2) -> t2.getPoints().compareTo(t1.getPoints())))
				.limit(5)
				.collect(Collectors.toList());
		return top5Teams;
	}
	
	public String prepareJsonResult(Result input) {

		String result = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			result = mapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			LOGGER.info("Exception "+ e.getMessage());
		}
		return result;
	}
	
	
	@Override
	public String getJsonResult() {
		jsonResult = prepareJsonResult(result);
		return jsonResult;
	}
	
	public Result getResult() {
		return result;
	}

}
