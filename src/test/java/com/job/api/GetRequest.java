package com.job.api;

import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.File;



public class GetRequest  extends BaseTest {
	@Test
	public void GetAll() throws InterruptedException {
		log.info("********Testing for GetAllJobApi ********");
		
		createRequest();
		
		log.info("Http Request  " + httpRequest);
		
		call(BaseTest.jobPath,Method.GET);
		
		response.then().log().all();
		
		// ** status code validation **//

		int statusCode= response.getStatusCode();
		log.info("Validating the StatusCode"+ statusCode);
		Assert.assertEquals(statusCode,200);
	
		// ** status Line validation **//
		 
		String statusLine = response.getStatusLine();
		 log.info("Validating the StatusLine"+statusLine);
		 Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		 
		 schemaValitation();
		 		 

	}

}
