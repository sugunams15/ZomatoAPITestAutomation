package com.zomato.lib;

import java.util.HashMap;

import com.zomato.base.ZomatoURLs;
import com.zomato.utils.RestUtility;

import io.restassured.response.Response;

public class RestaurantLib {
	
	RestUtility restUtility;
		
		public RestaurantLib() {
			restUtility = new RestUtility(ZomatoURLs.BASE_URL.value());
		}
		
		public Response getRestaurantDetails(String user_key, String res_id) {
			HashMap<String, String> headers = new HashMap<String, String>();
			HashMap<String, String> queryParams = new HashMap<String, String>();
			headers.put("Accept", "application/json");
			headers.put("user-key", user_key);
			
			queryParams.put("res_id", res_id);
			
			Response response = restUtility.getCall(headers, queryParams, null, ZomatoURLs.RESTAURENT_URL.value());
			return response;
		}

}
