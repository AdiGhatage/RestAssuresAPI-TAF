package com.java;

import com.files.Payloads;

import io.restassured.path.json.JsonPath;

public class CompleteJSonParse {

	public static void main(String[] args) {

		JsonPath jsPath = new JsonPath(Payloads.courses());
		
		int intCount = jsPath.getInt("courses.size()");
		System.out.println("Number of courses : "+intCount);
		
		
	}

}
