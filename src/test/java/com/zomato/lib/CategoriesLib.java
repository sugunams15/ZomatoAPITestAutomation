package com.zomato.lib;

import java.util.HashMap;
import java.util.List;

import com.zomato.base.HTTPStatus;
import com.zomato.base.ZomatoURLs;
import com.zomato.utils.RestUtility;

import io.restassured.response.Response;
public class CategoriesLib {
	RestUtility restUtility;
	
	public CategoriesLib() {
		restUtility = new RestUtility(ZomatoURLs.BASE_URL.value());
	}
	
	public String getCategories(String user_key) {
		String url = ZomatoURLs.CATEGORIES_URL.value();
		List<String> jsonResponse; 
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("user-key", user_key);
		
		Response response = restUtility.getCall(headers, null, null, url);
		if(response.statusCode()==HTTPStatus.STATUS_OK.getValue()) {
			jsonResponse  = response.jsonPath().getList("categories");
			return jsonResponse.toString(); 
		}
		else {
			return response.jsonPath().getString("message");
		}
	}

}
