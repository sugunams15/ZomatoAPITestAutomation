package com.zomato.lib;

import java.util.HashMap;

import com.zomato.base.ZomatoURLs;
import com.zomato.utils.RestUtility;

import io.restassured.response.Response;

public class CitiesLib {
RestUtility restUtility;
	
	public CitiesLib() {
		restUtility = new RestUtility(ZomatoURLs.BASE_URL.value());
	}
	
	public Response getCities(String user_key, String city_name, String lat, String lon, String city_ids, String count) {
		HashMap<String, String> headers = new HashMap<String, String>();
		HashMap<String, String> queryParams = new HashMap<String, String>();
		headers.put("user-key", user_key);
		
		queryParams.put("q", city_name);
		queryParams.put("lat", lat);
		queryParams.put("lon", lon);
		queryParams.put("city_ids", city_ids);
		queryParams.put("count", count);
		
		Response response = restUtility.getCall(headers, queryParams, null, ZomatoURLs.CITIES_URL.value());
		return response;
	}

}
