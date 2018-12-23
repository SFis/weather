package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		response.getWriter().append("This should be my output");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
