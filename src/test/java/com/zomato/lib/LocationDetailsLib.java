package com.zomato.lib;

import java.util.HashMap;

import com.zomato.base.ZomatoURLs;
import com.zomato.utils.RestUtility;

import io.restassured.response.Response;

public class LocationDetailsLib {
	
RestUtility restUtility;
	
	public LocationDetailsLib() {
		restUtility = new RestUtility(ZomatoURLs.BASE_URL.value());
	}
	
	public Response getLocationDetails(String user_key, String entity_id, String entity_type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		HashMap<String, String> queryParams = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("user-key", user_key);
		
		queryParams.put("entity_id", entity_id);
		queryParams.put("entity_type", entity_type);
		
		Response response = restUtility.getCall(headers, queryParams, null, ZomatoURLs.LOCATION_DETAILS_URL.value());
		return response;
	}
	
	

}
