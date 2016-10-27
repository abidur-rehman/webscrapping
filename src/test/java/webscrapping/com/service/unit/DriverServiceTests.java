package webscrapping.com.service.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.runners.MockitoJUnitRunner;
import webscrapping.com.model.Driver;
import webscrapping.com.model.Team;
import webscrapping.com.service.DriverServiceImpl;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTests {

    @Mock
    private WebDriver webDriver;
    
    @Mock
    private WebElement webElement;
    
    @Spy
    private ArrayList<WebElement> webElements;
    
    @InjectMocks
    private DriverServiceImpl driverService;
    
    @Test
    public void driversListTest() {

    	WebElement selectedMockElement = mock(WebElement.class);
    	webElements.add(selectedMockElement);
    	
    	WebElement driverElement = mock(WebElement.class);
    	String driverName = "LEWIS HAMILTON";
    	
    	WebElement pointsElement = mock(WebElement.class);
    	String driverPoints = "381";
    	
    	when(webDriver.findElement(By.className("msr_season_driver_results"))).thenReturn(webElement);
    	when(webElement.findElements(By.cssSelector("tbody tr"))).thenReturn(webElements);
    	when(selectedMockElement.findElement(By.cssSelector("td:nth-child(2)"))).thenReturn(driverElement);
    	when(driverElement.getText()).thenReturn(driverName);
    	
    	when(selectedMockElement.findElement(By.cssSelector("td:nth-child(22)"))).thenReturn(pointsElement);
    	when(pointsElement.getText()).thenReturn(driverPoints);

        List<Driver> result = driverService.buildDriversList();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driverName, result.get(0).getName());
        Integer driverPointsInt = Integer.parseInt(driverPoints);
        assertEquals(driverPointsInt, result.get(0).getPoints());
    }
    
    @Test
    public void teamsListTest() {

    	WebElement selectedMockElement = mock(WebElement.class);
    	webElements.add(selectedMockElement);
    	
    	WebElement driverElement = mock(WebElement.class);
    	String teamName = "FERRARI";
    	
    	WebElement pointsElement = mock(WebElement.class);
    	String teamPoints = "428";

    	when(webDriver.findElement(By.className("msr_season_team_results"))).thenReturn(webElement);
    	when(webElement.findElements(By.cssSelector("tbody tr"))).thenReturn(webElements);
    	when(selectedMockElement.findElement(By.cssSelector("td[class='msr_team']"))).thenReturn(driverElement);
    	when(driverElement.getText()).thenReturn(teamName);
    	
    	when(selectedMockElement.findElement(By.cssSelector("td[class='msr_total']"))).thenReturn(pointsElement);
    	when(pointsElement.getText()).thenReturn(teamPoints);

        List<Team> result = driverService.buildTeamsList();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(teamName, result.get(0).getName());
        Integer teamPointsInt = Integer.parseInt(teamPoints);
        assertEquals(teamPointsInt, result.get(0).getPoints());
    }
    
}
