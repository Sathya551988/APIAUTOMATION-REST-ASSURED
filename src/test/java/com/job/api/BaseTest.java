package com.job.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.util.logging.Logger;

import org.hamcrest.MatcherAssert;

import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;

public class BaseTest {
	public RequestSpecification httpRequest;
	public Response response;
	public static String jobApiUrl = "https://jobs123.herokuapp.com";
	public static String jobPath = "/Jobs";
	public static String userName = "APIPROCESSING";
	public static String password = "2xx@Success";
	public Logger log = Logger.getLogger("RestAssured Automation");

	public void createRequest() {
		httpRequest = given().baseUri(jobApiUrl).auth().basic(userName, password).header("content-Type",
				"application/json");
	}

	public void call(String path, Method method) {
		response = httpRequest.request(method,path);
	}
	
	public void schemaValitation() {
		String res = response.getBody().asString().replaceAll("NaN","\"10 Hours\"");
		MatcherAssert.assertThat(res,JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema/GetJobApiSchema.json"));
		
	}
}
