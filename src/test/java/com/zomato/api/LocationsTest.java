package com.zomato.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zomato.base.HTTPStatus;
import com.zomato.base.ZomatoURLs;
import com.zomato.lib.LocationsLib;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;


public class LocationsTest {
	LocationsLib locationsLib;
	String user_key;

	
	@BeforeMethod
	public void init(){
		locationsLib = new LocationsLib();
		user_key = ZomatoURLs.API_KEY.value();
	}
	
	@Test
	public void locationsSuccessResponseWithCityandCount() {
		Response response = locationsLib.getLocations(user_key, "Bengaluru","","","2");
		Map<String, String> locationMap = new HashMap<String, String>();
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		List<Object> locationsList =  (List<Object>) response.jsonPath().getList("location_suggestions");
		locationMap = response.jsonPath().getMap("location_suggestions[0]");
		
		assertThat("Locations count is not correct", 
				locationsList.size()<=2);
		
		assertThat("entity_type should be city", 
				locationMap.get("entity_type").equals("city"));
		
		assertThat("verify city_id: Failed", response.jsonPath().get("location_suggestions.city_id[0]").toString(), 
				is(equalTo("4")));
		
		assertThat("city_name should be Bengaluru", 
				locationMap.get("city_name").equals("Bengaluru"));
		
		assertThat("country_name should be India", 
				locationMap.get("country_name").equals("India"));
		
		
	}
	
	@Test
	public void locationsSuccessResponseWithLatandLon() {
		Response response = locationsLib.getLocations(user_key, "","12.958335","77.721879","");
		Map<String, String> locationMap = new HashMap<String, String>();
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		List<Object> locationsList =  (List<Object>) response.jsonPath().getList("location_suggestions");
		locationMap = response.jsonPath().getMap("location_suggestions[0]");
		
		assertThat("Locations count is not correct", 
				locationsList.size()<=1);
		
		assertThat("entity_type should be city", 
				locationMap.get("entity_type").equals("subzone"));
		
		assertThat("verify city_id: Failed", response.jsonPath().get("location_suggestions.city_id[0]").toString(), 
				is(equalTo("4")));
		
		assertThat("verify latitude: Failed", response.jsonPath().get("location_suggestions.latitude[0]").toString(), 
				is(equalTo("12.966236")));
		
		assertThat("verify longitude: Failed", response.jsonPath().get("location_suggestions.longitude[0]").toString(), 
				is(equalTo("77.72157")));
		
		assertThat("city_name should be Bengaluru", 
				locationMap.get("city_name").equals("Bengaluru"));
		
		assertThat("country_name should be India", 
				locationMap.get("country_name").equals("India"));
		
		
	}
	
	@Test
	public void categorizeInvalidUserkey() {
		Response response = locationsLib.getLocations("", "","12.958335","77.721879","");
		
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_FORBIDDEN.getValue());
		
		assertThat("Should get Invalid API message", 
				response.jsonPath().get("message").equals("Invalid API Key"));
	}
	
	@Test
	public void locationsSuccessResponseWithIncreasedCount() {
		Response response = locationsLib.getLocations(user_key, "","12.958335","77.721879","5");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		List<Object> locationsList =  (List<Object>) response.jsonPath().getList("location_suggestions");
		
		assertThat("Locations count is not correct", 
				locationsList.size()==5);
	}

}
