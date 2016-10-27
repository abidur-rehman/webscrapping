# webscrapping
Spring boot application reading data from formula one race results for the 2015 season result and displaying them in json format.

## Steps to start the Application
1. Download the application and change to the root directory of the application.
2. The application requires <b>phantomjs</b> driver to read webpage data, therefore <b>phantomjs</b> must be available under the files directory of the project. For your convenience files directory already includes <b>phantomjs</b> for mac and windows. Mac users should unzip the mac version and change its access rights, so that target <b>webscrapping.jar</b> can execute it later on. Mac command to change the rights:-
<b>chmod 777 files/phantomjs</b>

2. Run the maven command to build the applicaiton:-
<b>mvn package</b>

3. Run the command to start the application:-
<b>java -jar target/webscrapping-1.0.jar</b>

4. Wait till the result is created and displayed on the console. Result can also be viewed in the browser using
http://localhost:8080/

5. The Result.json shows the result.
