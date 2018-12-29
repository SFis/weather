package com.example.data;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.example.Calculations;
import com.example.Location;

public class JsonGenerator implements IDataOutput {

	@Override
	public void genJsonFromLocations(Location lA, Location lB) throws IOException {

		FileWriter file = new FileWriter("data.json");
		
		try {
			

			JSONObject myHomeTown = new JSONObject();
			myHomeTown.put("myHomeTown", lA.name);
			file.write(myHomeTown.toJSONString());
			JSONObject myHomeTownLocation = new JSONObject();
			myHomeTownLocation.put("myHomeTownLocation", "Lat: " + lA.lat + " | Lon: " + lA.lon);
			file.write(myHomeTownLocation.toJSONString());
			
			JSONObject myOtherTown = new JSONObject();
			myOtherTown.put("myOtherTown", lB.name);
			file.write(myOtherTown.toJSONString());
			JSONObject myOtherTownLocation = new JSONObject();
			myOtherTownLocation.put("myOtherTownLocation", "Lat: " + lB.lat + " | Lon: " + lB.lon);
			file.write(myOtherTownLocation.toJSONString());
			
			String rain = "nowhere";
			if (lA.weather.equals("Rain") && lB.weather.equals("Rain"))
				rain = "both";
			else if (lA.weather.equals("Rain"))
				rain = lA.name;
			else if (lB.weather.equals("Rain"))
				rain = lB.name;
			JSONObject itIsRainingIn = new JSONObject();
			itIsRainingIn.put("itIsRainingIn", rain);
			file.write(itIsRainingIn.toJSONString());

			JSONObject sunriseTimeDifference = new JSONObject();
			sunriseTimeDifference.put("sunriseTimeDifference",
					Calculations.compareToMinutes(lA.sunrise, lB.sunrise));
			file.write(sunriseTimeDifference.toJSONString());

			JSONObject tempDifference = new JSONObject();
			tempDifference.put("tempDifference", Calculations.diff(lA.temp, lB.temp));
			file.write(tempDifference.toJSONString());

			JSONObject distance = new JSONObject();
			distance.put("distance", Calculations.computeDistance(lA.lat, lA.lon, lB.lat, lB.lon));
			file.write(distance.toJSONString());
			
			
			
		} catch (Exception e) {
			JSONObject message = new JSONObject();
			message.put("message", e.getStackTrace());
			file.write(message.toJSONString());
			
			JSONObject error = new JSONObject();
			error.put("error", true);
			file.write(error.toJSONString());
		} 
		
		file.flush();
		
	}

}
