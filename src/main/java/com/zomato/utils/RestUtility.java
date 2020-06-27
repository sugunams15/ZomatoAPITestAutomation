package com.zomato.utils;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class RestUtility {
	private static RestUtility restUtility;

	public RestUtility(final String url) {
		RestAssured.baseURI = url;
	}

	public Response getCall(final HashMap<String, String> headers, final HashMap<String, ?> queryParams,
			final HashMap<String, ?> pathParams, final String url, final Object body) {
		RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.JSON);
		if (headers != null) {
			requestSpecification.headers(headers);
		}
		if (queryParams != null) {
			requestSpecification.queryParams(queryParams);
		}
		if (pathParams != null) {
			requestSpecification.pathParams(pathParams);
		}
		if (body != null) {
			requestSpecification.body(body);
		}
		String uniqueId = UUID.randomUUID().toString();
		requestSpecification.queryParam("indentifier", uniqueId);

		Response response = requestSpecification.when().get(url).thenReturn();
		return response;
	}

	public Response getCall(final HashMap<String, String> headers, final HashMap<String, ?> queryParams,
			final HashMap<String, ?> pathParams, final String url) {
		RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.JSON);
		if (headers != null) {
			requestSpecification.headers(headers);
		}
		if (queryParams != null) {
			requestSpecification.queryParams(queryParams);
		}
		if (pathParams != null) {
			requestSpecification.pathParams(pathParams);
		}
		String uniqueId = UUID.randomUUID().toString();
		requestSpecification.queryParam("indentifier", uniqueId);

		Response response = requestSpecification.when().get(url).thenReturn();

		return response;
	}

	public Response postCall(final HashMap<String, String> headers, final Object body,
			final HashMap<String, ?> queryParams, final String pathParams, final String url) {
		RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.JSON);
		String url2 = url;
		if (headers != null) {
			requestSpecification.headers(headers);
		}
		if (body != null) {
			requestSpecification.body(body);
		}
		if (pathParams != null) {
			url2 = StringUtils.join(url2, pathParams);
		}
		if (queryParams != null) {
			requestSpecification.queryParams(queryParams);
		}
		String uniqueId = UUID.randomUUID().toString();
		requestSpecification.queryParam("indentifier", uniqueId);

		Response response = requestSpecification.when().post(url2).thenReturn();

		return response;
	}

	
	public Response putCall(final HashMap<String, String> headers, final Object body,
			final HashMap<String, ?> queryParams, final String pathParams, final String url) {
		RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.JSON);
		String url2 = url;
		if (headers != null) {
			requestSpecification.headers(headers);
		}
		if (body != null) {
			requestSpecification.body(body);
		}
		if (pathParams != null) {
			url2 = StringUtils.join(url2, pathParams);
		}
		if (queryParams != null) {
			requestSpecification.queryParams(queryParams);
		}
		String uniqueId = UUID.randomUUID().toString();
		requestSpecification.queryParam("indentifier", uniqueId);

		Response response = requestSpecification.when().put(url2).thenReturn();

		return response;
	}

	public Response postCall(final HashMap<String, String> headers, final Object body, final String pathParams,
			final String url) {
		return postCall(headers, body, null, pathParams, url);
	}

}
