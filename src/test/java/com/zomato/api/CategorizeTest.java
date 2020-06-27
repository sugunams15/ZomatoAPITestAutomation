package com.zomato.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zomato.base.ZomatoURLs;
import com.zomato.lib.CategoriesLib;
import static org.hamcrest.MatcherAssert.assertThat;

public class CategorizeTest {
	CategoriesLib categorizeLib;
	String user_key;

	
	@BeforeMethod
	public void init(){
		categorizeLib = new CategoriesLib();
		user_key = ZomatoURLs.API_KEY.value();
	}
	
	@Test
	public void categorizeSuccessResponse() {
		String response= categorizeLib.getCategories(user_key);
		String[] jsoList = response.split("},");
		assertThat("There should be 13 categories", 
				jsoList.length==13);
	}
	
	@Test
	public void categorizeInvalidUserkey() {
		String response= categorizeLib.getCategories(user_key+"09");
		assertThat("Should get Invalid API message", 
				response.equals("Invalid API Key"));
	}

}
