package com.countryTest;

import com.country.framework.config;
import common.Endpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;




public class country {

	@Test
	public void testStatusCode(){
	given().
	get(Endpoint.GET_COUNTRY).then().statusCode(200);
	}
	
/*	@Test
	public void testIncorrectStatusCode(){
	given().
	get(Endpoint.GET_COUNTRY).then().statusCode(500);
	}*/
	
	@Test
	public void ListAllCountry(){
		/*given().get(Endpoint.GET_COUNTRY).then().statusCode(200);	*/
		RequestSpecification requestSpecification = new config().getRequestSpecification();
		String json = given().get(Endpoint.GET_COUNTRY).then().extract().asString();
		JsonPath jp = new JsonPath(json);
		List<String> list = jp.get("name");
		System.out.println("Country's are -------"+list.get(0));
	}
	
	@Test
	public void validateCountry(){
	    RequestSpecification requestSpecification = new config().getRequestSpecification();
		requestSpecification.queryParam("alpha2Code", "AF");
		given().get(Endpoint.GET_COUNTRY).then().statusCode(200);
		Assert.assertEquals("list.get[0]", "Afghanistan");
	}
	@Test
    public void validateCapital(){
		RequestSpecification requestSpecification = new config().getRequestSpecification();
		/*requestSpecification.queryParam("alpha2Code", "AF");
		String json = given().get(Endpoint.GET_COUNTRY).then().statusCode(200).log().all();*/
		
		String json = given().get(Endpoint.GET_COUNTRY).then().extract().asString();
		Response response = given().spec(requestSpecification).get(Endpoint.GET_COUNTRY);
		JsonPath jp = new JsonPath(json);
		List<String> list = jp.get("name");
		System.out.println("Country's are -------"+list);
		
		//java obj
		CountryDetails countrydetails = response.as(CountryDetails.class);
		Assert.assertEquals(countrydetails.getcountryName(),"Afghanistan" );
	}   
	
	
}
