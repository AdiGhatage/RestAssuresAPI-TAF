package com.java;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

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
		String strPlace = jsPath.getString("place_id");
		System.out.println("Place id is:  "+strPlace);
		
		//update place
		String strAddress = "Summer walk, Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+strPlace+"\",\r\n" + 
				"\"address\":\""+strAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").when().put("maps/api/place/update/json").then().assertThat()
		.log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String strGetResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", strPlace)
		.when().get("maps/api/place/get/json").then().assertThat()
		.log().all().statusCode(200).extract().response().asString();
		JsonPath jsString = new JsonPath(strGetResponse);
		String strNewAddress = jsString.getString("address");
		System.out.println("Address is: "+strNewAddress);
		Assert.assertEquals(strAddress, strNewAddress);
		
		
	}

}
