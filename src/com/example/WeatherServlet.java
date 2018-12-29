package com.example;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.data.JsonGenerator;
import com.example.data.LocationJsonParser;

/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String APIKEY = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherServlet() {
        super();
    }

	/**
	 * TODO Complete the following method
	 * * You can use any Open Source library/code that will help you - 
	 *   * But the library then needs to be included (typically as jar file) in WebContent/WEB-INF/lib
	 *   * The code needs to work without us doing any extra work
	 * * You can take as long as you like 
	 *   * But we do not expect you to put in more than a few hours of work
	 *   * In practice you should be able to commit your solution within one week of cloning this repository
	 * * We expect readable coding 
	 *   * Methods and perhaps even classes are your friends!
	 *   * Comments are expected where it is not obvious what is happening
	 *   Clone the project into your own github repository. Once you are finished email us the name and branch of
	 *   your solution on github. We will clone and test it. Please refrain from changing your code once you have
	 *   contacted us.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Please read the following Data using the openweathermap API:
		// api.openweathermap.org/data/2.5/ - Documentation: https://openweathermap.org/api
		// You will need an API key - please request it from your interviewer
		
		// The weather for your hometown 
		
		// The weather for the town you were born
		// (If that happens to be the same town, then the town/location where you spend your last holiday)
		
		// Then compare the two 
		//   - difference in temperature
		//   - absolute difference in minutes between sunrises
		//   - distance (in km) <-- as a bonus
		// Also note where (if anywhere) it is currently raining.
		
		// Output the result as JSON Object with the attributes
		//     myHomeTown: '<name>'
		//     myHomeTownLocation: '<String with latitude longitude>'
		//     myOtherTown: '<name>'
		//     myOtherTownLocation: '<String with latitude longitude>'
		//     itIsRainingIn: '<Name of Town or "nowhere">'
		//	   sunriseTimeDifference: '<integer difference in minutes between sunrises>'
		//     tempDifference: <integer difference in temperature>
		//     distance: <integer distance in km (optional)>
		
		// if anything fails return a 500 error and a json object with the attributes 
		//     message: '<Possibly the message given by the failed api call>'
		//     error: true
		
		
		//API Key provided by interviewer
		APIKEY = "d4e426deed9e49ba83e8507a80271557";
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		
		// Login
		if (APIKEY.isEmpty())
			response.sendRedirect("index.jsp");
		else {
			
			Location lWernigerode = new Location();
			Location lBallenstedt = new Location();
			
			
			LocationJsonParser parser = new LocationJsonParser();
			JsonGenerator generator = new JsonGenerator();
				
			try {
			
			lWernigerode = parser.getLocation("Wernigerode,de");
			lBallenstedt = parser.getLocation(2953060);
			response.getWriter().append(
					"location:\t" + lWernigerode.name +"\t\t"+ lBallenstedt.name +"\n"+  
					"weather:\t" + lWernigerode.weather +"\t\t\t"+ lBallenstedt.weather +"\n"+ 
					
					"temperature:\t" + Math.round(lWernigerode.temp -273.15f) +" \u00b0C\t\t\t"+ 
					Math.round(lBallenstedt.temp -273.15f) +" \u00b0C\n"+ 
					"diff:\t\t\t\t" + Calculations.diff(lWernigerode.temp, lBallenstedt.temp) +" \u00b0C\n"+
					
					"sunrise:\t" + (df.format(lWernigerode.sunrise)) +"\t\t"+ 
					(df.format(lBallenstedt.sunrise)) +"\n"+ 
					"diff:\t\t\t\t" + Calculations.compareToMinutes(
							lWernigerode.sunrise, lBallenstedt.sunrise) +" min\n"+
					"lat/lon:\t" + lWernigerode.lat +"\u00b0 / "+ lWernigerode.lon + 
					"\u00b0\t\t"+ lBallenstedt.lat +" / "+ lBallenstedt.lon +"\u00b0\n"+
					"diff:\t\t\t\t" + Calculations.computeDistance(lWernigerode.lat, lWernigerode.lon, 
							lBallenstedt.lat, lBallenstedt.lon) + "km\n"
					);
			
			if (lWernigerode.weather.contentEquals("Rain"))
				response.getWriter().append("\nIt's raining in Wernigerode.\n");
			if (lBallenstedt.weather.contentEquals("Rain"))
				response.getWriter().append("\nIt's raining in Ballenstedt.\n");
			
			} catch (Exception e) {
				response.sendError(500);
			}
			
			//Now output the data as a new JSON
			generator.genJsonFromLocations(lWernigerode, lBallenstedt);
			response.getWriter().append("\nJSON file stored in: " + System.getProperty("user.dir") + "/data.json\n");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		APIKEY = request.getParameter("ukey");
		if (!APIKEY.isEmpty())
			response.sendRedirect("WeatherServlet");
		else 
			response.sendRedirect("index.jsp");
		doGet(request, response);
	}

}
