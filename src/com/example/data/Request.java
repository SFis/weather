package com.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Request {
	
//	private String apiKey = "";
	private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
	private URLConnection request = null;
	private String url = "";
	
	public Request(String apiKey, String location) {
		url = baseUrl + "?q=" + location + "&APPID=" + apiKey;
	}
	
	public Request(String apiKey, int id) {
		url = baseUrl + "?id=" + id + "&APPID=" + apiKey;
	}
	
	public String execute() {
		String response = "";
		
		try {
			request = new URL(url).openConnection();
			InputStream is = request.getInputStream();
			response = new BufferedReader(new InputStreamReader(is))
					  .lines().collect(Collectors.joining("\n"));
			
		} catch (MalformedURLException e) {
			System.out.println("URL invalid.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO error.");
			e.printStackTrace();
		}
		return response;
	}
	

}
