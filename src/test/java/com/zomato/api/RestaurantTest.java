package com.zomato.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zomato.base.HTTPStatus;
import com.zomato.base.ZomatoURLs;
import com.zomato.lib.LocationDetailsLib;
import com.zomato.lib.RestaurantLib;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.logging.Logger;

public class RestaurantTest {
	RestaurantLib restaurantLib;
	LocationDetailsLib locationDetailsLib;
	String user_key;
	Logger logger;

	
	@BeforeMethod
	public void init(){
		restaurantLib = new RestaurantLib();
		locationDetailsLib = new LocationDetailsLib();
		user_key = ZomatoURLs.API_KEY.value();
		logger = Logger.getLogger(RestaurantTest.class.getName());
	}
	
	/*
	 * Testcase to verify whether the restaurants API returns valid restaurant details, 
	 * by passing valid user_key and res_id obtained fromloaction_details API
	 */
	@Test
	public void restaurantSuccessResponse() {
		
		String restId;
		logger.info("get restaurant id from locationdetails API");
		Response response1= locationDetailsLib.getLocationDetails(user_key, "4", "city");
		restId = response1.jsonPath().get("best_rated_restaurant.restaurant[0].id").toString();
		
		logger.info("get restaurant name from locationdetails API");
		String restName = response1.jsonPath().get("best_rated_restaurant.restaurant[0].name");
		
		logger.info("get restaurant details by passing restaurent id");
		Response response = restaurantLib.getRestaurantDetails(user_key, restId);
		
		logger.info("verify the status and the result is for the given restaurent id");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_OK.getValue());
		
		assertThat("Restaurant id is wrong", 
				response.jsonPath().get("id").equals(restId));
		
		assertThat("Restaurant id is wrong", 
				response.jsonPath().get("id").equals(restId));
		
		assertThat("Restaurant name is wrong", 
				response.jsonPath().get("name").equals(restName));
		
		assertThat("city should be bengaluru", 
				response.jsonPath().get("location.city").equals("Bangalore"));
	}
	
	/*
	 * Testcase to verify whether the restaurants API returns error by passing valid user_key and without res_id
	 */
	
	@Test
	public void restaurantResponseWithoutResID() {
		Response response = restaurantLib.getRestaurantDetails(user_key, "");
		
		logger.info("verify the status is not found");
		assertThat("Did not get Success Response", 
				response.getStatusCode()==HTTPStatus.STATUS_NOT_FOUND.getValue());
	}

}
