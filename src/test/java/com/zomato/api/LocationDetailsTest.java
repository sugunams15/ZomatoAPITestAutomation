package com.zomato.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zomato.base.HTTPStatus;
import com.zomato.base.ZomatoURLs;
import com.zomato.lib.LocationDetailsLib;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LocationDetailsTest {
	
	LocationDetailsLib locationDetailsLib;
	String user_key;

	
	@BeforeMethod
	public void init(){
		locationDetailsLib = new LocationDetailsLib();
		user_key = ZomatoURLs.API_KEY.value();
	}
	
	
	@Test
	public void locationDetailsSuccessResponse() {
		
		Response response = locationDetailsLib.getLocationDetails(user_key, "4", "city");
		List<String> cuisinesListExpected = new ArrayList<String>();
		cuisinesListExpected.add("North Indian");
		cuisinesListExpected.add("Chinese");
		cuisinesListExpected.add("Fast Food");
		cuisinesListExpected.add("Beverages");
		cuisinesListExpected.add("South Indian");
		
		assertThat("Response should be successful", 
				response.getStatusCode() == HTTPStatus.STATUS_OK.getValue());
		
		List<String> cuisinesListActual = response.jsonPath().getList("top_cuisines");
		HashMap<Object, Object> locationDetails = (HashMap<Object, Object>) response.jsonPath().getMap("location");
		List<String> bestRatedRestaurants = response.jsonPath().getList("best_rated_restaurant");
		
		assertThat("The cuisine list is not correct", 
				cuisinesListExpected.equals(cuisinesListActual));
		
		assertThat("Number of restuarent is lessthan 10", 
				bestRatedRestaurants.size() == 10);
		
		assertThat("entity_type should be city", 
				locationDetails.get("entity_type").equals("city"));
		
		assertThat("verify city_id: Failed", response.jsonPath().get("location.city_id").toString(), 
				is(equalTo("4")));
		
		assertThat("city_name should be Bengaluru", 
				locationDetails.get("city_name").equals("Bengaluru"));
		
		assertThat("country_name should be India", 
				locationDetails.get("country_name").equals("India"));
		
		
	}
	
	@Test
	public void locationDetailsWithoutEntityId() {
		Response response = locationDetailsLib.getLocationDetails(user_key, "", "city");
		
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_FORBIDDEN.getValue());
		
		assertThat("Should get Invalid API message", 
				response.jsonPath().get("message").equals("Invalid location parameters"));
		
	}

	@Test
	public void locationDetailsWithoutEntityType() {
		Response response = locationDetailsLib.getLocationDetails(user_key, "4", "");
		
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_FORBIDDEN.getValue());
		
		assertThat("Should get Invalid API message", 
				response.jsonPath().get("message").equals("Invalid location parameters"));
		
	}
	
	@Test
	public void locationInvalidUserkey() {
		Response response = locationDetailsLib.getLocationDetails(user_key+"123", "4","city");
		
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_FORBIDDEN.getValue());
		
		assertThat("Should get Invalid API message", 
				response.jsonPath().get("message").equals("Invalid API Key"));
	}
}
