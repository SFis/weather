package com.example.data;

import org.json.simple.parser.*;

import com.example.Location;
import com.example.WeatherServlet;

import java.security.Timestamp;

import org.json.simple.*;

public class LocationJsonParser implements IDataSource {

	public LocationJsonParser() {

	}
		
	@Override
	public Location getLocation(int id) {
		Request r = new Request(WeatherServlet.APIKEY, id);		
		return parseRequest(r);
	}
	
	@Override
	public Location getLocation(String location) {
		Request r = new Request(WeatherServlet.APIKEY, location);
		return parseRequest(r);
	}
	
	private Location parseRequest(Request r) {
		Location loc = new Location();
		JSONParser parser = new JSONParser();				
		try {
			JSONObject obj = (JSONObject) parser.parse(r.execute());
			loc.locationId = Integer.valueOf(obj.get("id").toString());
			loc.name = obj.get("name").toString();
			
			JSONArray weatherArr = new JSONArray();
			weatherArr = (JSONArray) obj.get("weather");
			JSONObject weatherEntry = (JSONObject) parser.parse(
					weatherArr.get(0).toString());
			loc.weather = weatherEntry.get("main").toString();
			
			JSONObject mainObj = new JSONObject();
			mainObj = (JSONObject)obj.get("main");
			loc.temp = Float.valueOf(mainObj.get("temp").toString());
			
			JSONObject sysObj = new JSONObject();
			sysObj = (JSONObject)obj.get("sys");
			loc.sunrise = new java.sql.Timestamp(
					Long.valueOf(sysObj.get("sunrise").toString())*1000);
			
			JSONObject coordObj = new JSONObject();
			coordObj = (JSONObject)obj.get("coord");
			loc.lat = Float.valueOf(coordObj.get("lat").toString());
			loc.lon = Float.valueOf(coordObj.get("lon").toString());
			
			
		} catch (ParseException e) {
			System.out.println("Error while parsing data.");
			e.printStackTrace();
		}
		return loc;
	}

}
