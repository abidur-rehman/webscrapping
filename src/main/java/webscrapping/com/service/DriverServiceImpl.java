package webscrapping.com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import webscrapping.com.model.Driver;
import webscrapping.com.model.Team;


@Component
public class DriverServiceImpl implements DriverService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
	
	private static final String URL = "http://www.f1-fansite.com/f1-results/2015-f1-results-standings/";
	
	private List<Driver> driversList = new ArrayList<Driver>();
	private List<Team> teamsList = new ArrayList<Team>();
	private WebDriver webDriver;
	
	@Autowired
	private ResultService resultService;

	public void init() throws InterruptedException {
		
		webDriver = loadDriver();
		
		webDriver.get(URL);
		webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		LOGGER.info("building lists");
		this.driversList.addAll(buildDriversList());
		this.teamsList.addAll(buildTeamsList());
		
		resultService.buildResult(teamsList, driversList);
		
		webDriver.quit();
	}


	public WebDriver loadDriver() {
		
		String fileName = getFileName();
		LOGGER.info("fileName is =====> " + fileName);
		String filePath = System.getProperty("user.dir") + "/files/"+fileName;
		
        PhantomJSDriverService service = new PhantomJSDriverService.Builder()
        		.usingPhantomJSExecutable(new File(filePath))
                .usingAnyFreePort()
                .build();
		
		WebDriver webDriver = new PhantomJSDriver(service, new DesiredCapabilities());
		return webDriver;
	}
	
	public List<Team> buildTeamsList() {

		LOGGER.info("building team list");
		WebElement teamsTable = webDriver.findElement(By.className("msr_season_team_results"));

		List<Team> teamsList = new ArrayList<Team>();
		List<WebElement> rows = getTableRows(teamsTable);
		for (WebElement row : rows) {
			try {
				WebElement td2 = row.findElement(By.cssSelector("td[class='msr_team']"));
				String teamName = td2.getText();
				
				WebElement totalPoints = row.findElement(By.cssSelector("td[class='msr_total']"));
				String teamPoints = totalPoints.getText();
				
				Team team  = Team.createTeam(teamName, Integer.parseInt(teamPoints));
				teamsList.add(team);
				
			} catch (NoSuchElementException e) {
				continue;
			}
		}
		return teamsList;
	}

	private List<WebElement> getTableRows(WebElement teamsTable) {
		List<WebElement> rows = teamsTable.findElements(By.cssSelector(("tbody tr")));
		return rows;
	}

	public List<Driver> buildDriversList() {
		WebElement driverTable = webDriver.findElement(By.className("msr_season_driver_results"));

		List<WebElement> rows = getTableRows(driverTable);
		
		List<Driver> driversList = new ArrayList<Driver>();
		for (WebElement row : rows) {
			WebElement td2 = row.findElement(By.cssSelector(("td:nth-child(2)")));
			String driverName = td2.getText();
			
			WebElement totalPoints = row.findElement(By.cssSelector(("td:nth-child(22)")));
			String driverPoints = totalPoints.getText();
			
			Driver driver  = Driver.createDriver(driverName, Integer.parseInt(driverPoints));
			driversList.add(driver);
		}

		return driversList;
	}
	
	private String getFileName() {
		String fileName = null;
		String osName = System.getProperty("os.name");
		String osNameMatch = osName.toLowerCase();
		if (osNameMatch.contains("windows")) {
			fileName = "phantomjs.exe";
		} else if (osNameMatch.contains("mac os") || osNameMatch.contains("macos") || osNameMatch.contains("darwin")) {
			fileName = "phantomjs";
		}
		
		return fileName;
	}

}
