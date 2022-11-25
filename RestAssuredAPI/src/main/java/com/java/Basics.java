package com.java;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.files.Payloads;

public class Basics {
	
	//validate if add place is working
	//given - all input details
	//when - submit the api
	//then - validate the response
	
	public static void main(String[] args)
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String strResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.addPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response is: "+strResponse);
		JsonPath jsPath = new JsonPath(strResponse);
		
		
	}

}
