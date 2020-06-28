package com.zomato.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.logging.Logger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zomato.base.HTTPStatus;
import com.zomato.base.ZomatoURLs;
import com.zomato.lib.CitiesLib;

import io.restassured.response.Response;

public class CitiesTest {
	CitiesLib citiesLib;
	String user_key;
	Logger logger;

	
	@BeforeMethod
	public void init(){
		citiesLib = new CitiesLib();
		user_key = ZomatoURLs.API_KEY.value();
		logger = Logger.getLogger(RestaurantTest.class.getName());
	}
	
	/*
	 * Testcase to verify whether the cities API returns valid city details by passing valid user_key and city_name
	 */
	
	@Test
	public void citiesSuccessResponseWithCityName() {
		
		Response response = citiesLib.getCities(user_key, "Bengaluru", "", "", "", "1");
		
		logger.info("verify the status and the result is for the given restaurent id");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		assertThat("city id should be 4", 
				response.jsonPath().getInt("location_suggestions.id[0]"),
				is(equalTo(4)));
		assertThat("city name should be Bengaluru", 
				response.jsonPath().get("location_suggestions.name[0]").equals("Bengaluru"));
		assertThat("country_id should be 1", 
				response.jsonPath().getInt("location_suggestions.country_id[0]"),
				is(equalTo(1)));
		assertThat("country_name should be India", 
				response.jsonPath().get("location_suggestions.country_name[0]").equals("India"));
		
		
	}
	
	/*
	 * Testcase to verify whether the cities API returns valid city details by passing valid user_key and city_id
	 */
	
	@Test
	public void citiesSuccessResponseWithCityId() {
		
		Response response = citiesLib.getCities(user_key, "", "", "", "5", "1");
		
		logger.info("verify the status and the result is for the given restaurent id");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		assertThat("city id should be 5", 
				response.jsonPath().getInt("location_suggestions.id[0]"),
				is(equalTo(5)));
		assertThat("city name should be Pune", 
				response.jsonPath().get("location_suggestions.name[0]").equals("Pune"));
		assertThat("country_id should be 1", 
				response.jsonPath().getInt("location_suggestions.country_id[0]"),
				is(equalTo(1)));
		assertThat("country_name should be India", 
				response.jsonPath().get("location_suggestions.country_name[0]").equals("India"));
		
		
	}
	
	/*
	 * Testcase to verify whether the cities API returns valid city details by passing valid user_key and city coordinates
	 */
	
	@Test
	public void citiesSuccessResponseWithCityCords() {
		
		Response response = citiesLib.getCities(user_key, "", "12.958336", "78.721879", "", "1");
		
		logger.info("verify the status and the result is for the given restaurent id");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		assertThat("city id should be 11562", 
				response.jsonPath().get("location_suggestions.id[0]").toString(),
				is(equalTo("11562")));
		assertThat("city name should be Ambur", 
				response.jsonPath().get("location_suggestions.name[0]").equals("Ambur"));
		assertThat("country_id should be 1", 
				response.jsonPath().getInt("location_suggestions.country_id[0]"),
				is(equalTo(1)));
		assertThat("country_name should be India", 
				response.jsonPath().get("location_suggestions.country_name[0]").equals("India"));
		
		
	}
	
	/*
	 * Testcase to verify whether the cities API returns valid empty list by passing valid user_key and other values as empty
	 */
	
	@Test
	public void citiesSuccessResponseWithoutParams() {
		
		Response response = citiesLib.getCities(user_key, "", "", "", "", "");
		
		logger.info("verify the status and the result is for the given restaurent id");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		assertThat("city id should be 11562", 
				response.jsonPath().getList("location_suggestions").isEmpty());
		
		
	}
	
	/*
	 * Testcase to verify whether the cities API returns error by passing valid user_key and invalid Coordinates
	 */
	
	@Test
	public void citiesSuccessResponseWithInvalidCords() {
		
		Response response = citiesLib.getCities(user_key, "", "122.958336", "778.721879", "", "");
		
		logger.info("verify the status and the result is for the given restaurent id");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_INTERNAL_SERVER_ERROR.getValue());
		
		
		
	}


}
