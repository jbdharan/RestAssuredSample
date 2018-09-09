package com.country.framework;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class config {
	public void configure(){
		RestAssured.baseURI ="http://restcountries.eu/rest/v1/all";
		
	}
	
public RequestSpecification getRequestSpecification(){
	return RestAssured.given().contentType(ContentType.JSON);
}
}
