package com.zomato.lib;

import java.util.HashMap;

import org.testng.annotations.Optional;

import com.zomato.base.ZomatoURLs;
import com.zomato.utils.RestUtility;

import io.restassured.response.Response;

public class LocationsLib {
RestUtility restUtility;
	
	public LocationsLib() {
		restUtility = new RestUtility(ZomatoURLs.BASE_URL.value());
	}
	
	public Response getLocations(String user_key, String city_name, String lat,  String lon, String count) {
		HashMap<String, String> headers = new HashMap<String, String>();
		HashMap<String, String> queryParams = new HashMap<String, String>();
		headers.put("user-key", user_key);
		
		queryParams.put("query", city_name);
		queryParams.put("lat", lat);
		queryParams.put("lon", lon);
		queryParams.put("count", count);
		
		Response response = restUtility.getCall(headers, queryParams, null, ZomatoURLs.LOACTIONS_URL.value());
		return response;
		
		
	}

}
