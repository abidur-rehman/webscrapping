package webscrapping.com.service.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import webscrapping.com.model.Driver;
import webscrapping.com.model.Result;
import webscrapping.com.model.Team;
import webscrapping.com.service.ResultServiceImpl;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTests {
	
    @InjectMocks
    private ResultServiceImpl resultService;
    
    @Test
    public void orderedDriversListTest() {
    	List<Driver> unOrderedDriversList = getDriversTestData();
    	
    	List<Driver> orderedDriversList = resultService.getOrderedDriversList(unOrderedDriversList);
        
    	assertNotNull(orderedDriversList);
        
    	assertEquals(10, orderedDriversList.size());
    	
        assertEquals(300, orderedDriversList.get(0).getPoints().intValue());
        assertEquals(200, orderedDriversList.get(1).getPoints().intValue());
        assertEquals(100, orderedDriversList.get(2).getPoints().intValue());
        assertEquals(50, orderedDriversList.get(3).getPoints().intValue());
    }
    
    @Test
    public void orderedTeamsListTest() {
    	List<Team> unOrderedTeamsList = getTeamsTestData();
    	
    	List<Team> orderedTeamsList = resultService.getOrderedTeamsList(unOrderedTeamsList);
        
    	assertNotNull(orderedTeamsList);
        
    	assertEquals(5, orderedTeamsList.size());
        
    	assertEquals(500, orderedTeamsList.get(0).getPoints().intValue());
        assertEquals(400, orderedTeamsList.get(1).getPoints().intValue());
        assertEquals(300, orderedTeamsList.get(2).getPoints().intValue());
        assertEquals(200, orderedTeamsList.get(3).getPoints().intValue());
    }
    
    @Test
    public void createResultTest() {
    	List<Team> unOrderedTeamsList = getTeamsTestData();
    	List<Driver> unOrderedDriversList = getDriversTestData();
    	Result result = resultService.createResult(unOrderedTeamsList, unOrderedDriversList);
    	
    	assertNotNull(result);
    	
    	assertEquals(5, result.getTeamsList().size());
    	
    	assertEquals(500, result.getTeamsList().get(0).getPoints().intValue());
        assertEquals(400, result.getTeamsList().get(1).getPoints().intValue());
        
        
    	assertEquals(10, result.getDriversList().size());
    	
        assertEquals(300, result.getDriversList().get(0).getPoints().intValue());
        assertEquals(200, result.getDriversList().get(1).getPoints().intValue());
    	
    }
    
    @Test
    public void prepareJsonResultTest() throws JsonParseException, JsonMappingException, IOException {
    	List<Team> unOrderedTeamsList = getTeamsTestData();
    	List<Driver> unOrderedDriversList = getDriversTestData();
    	Result input = resultService.createResult(unOrderedTeamsList, unOrderedDriversList);
    	
    	String jsonResult = resultService.prepareJsonResult(input);
    	
    	assertNotNull(jsonResult);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Result result = mapper.readValue(jsonResult, Result.class);
    	
    	assertNotNull(result);
    	assertEquals(5, result.getTeamsList().size());
    	
    	assertEquals(500, result.getTeamsList().get(0).getPoints().intValue());
        assertEquals(400, result.getTeamsList().get(1).getPoints().intValue());
        
        
    	assertEquals(10, result.getDriversList().size());
    	
        assertEquals(300, result.getDriversList().get(0).getPoints().intValue());
        assertEquals(200, result.getDriversList().get(1).getPoints().intValue());
    }


	private List<Team> getTeamsTestData() {
		List<Team> unOrderedTeamsList = new ArrayList<Team>();
		
		Team one = Team.createTeam("one",  500);
		Team two = Team.createTeam("two",  400);
		Team three = Team.createTeam("three",  300);
		Team four = Team.createTeam("four",  200);
		Team five = Team.createTeam("five",  100);
		Team six = Team.createTeam("six",  50);
		Team seven = Team.createTeam("seven",  40);
		
		unOrderedTeamsList.add(seven);
		unOrderedTeamsList.add(five);
		unOrderedTeamsList.add(one);
		unOrderedTeamsList.add(four);
		unOrderedTeamsList.add(three);
		unOrderedTeamsList.add(two);
		unOrderedTeamsList.add(six);
		
		return unOrderedTeamsList;
	}

	private List<Driver> getDriversTestData() {
		List<Driver> unOrderedDriversList = new ArrayList<Driver>();
		
		Driver one = Driver.createDriver("one", 300);
		Driver two = Driver.createDriver("two", 200);
		Driver three = Driver.createDriver("three", 100);
		Driver four = Driver.createDriver("four", 50);
		Driver five = Driver.createDriver("five", 40);
		Driver six = Driver.createDriver("six", 30);
		Driver seven = Driver.createDriver("seven", 30);
		Driver eight = Driver.createDriver("eight", 30);
		Driver nine = Driver.createDriver("nine", 20);
		Driver ten = Driver.createDriver("ten", 10);
		Driver eleven = Driver.createDriver("eleven", 5);
		
		unOrderedDriversList.add(three);
		unOrderedDriversList.add(five);
		unOrderedDriversList.add(one);
		unOrderedDriversList.add(six);
		unOrderedDriversList.add(four);
		unOrderedDriversList.add(two);
		unOrderedDriversList.add(seven);
		unOrderedDriversList.add(ten);
		unOrderedDriversList.add(eight);
		unOrderedDriversList.add(eleven);
		unOrderedDriversList.add(nine);
		
		return unOrderedDriversList;
	} 

}
