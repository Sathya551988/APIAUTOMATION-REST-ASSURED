package com.job.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utilities.ExcelUtils;

import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;

public class PostRequest extends BaseTest {
	@Test(dataProvider = "JobsApiData")
	public void createRecord(String jtitle, String jcompname, String jloc, String jtype, String jposttime, String jdesc,
			String jid) throws InterruptedException {

		createRequest();

		JSONObject requestparams = new JSONObject();

		requestparams.put("Job Title", jtitle);
		requestparams.put("Job Company Name", jcompname);
		requestparams.put("Job Location", jloc);
		requestparams.put("Job Type", jtype);
		requestparams.put("Job Posted time", jposttime);
		requestparams.put("Job Description", jdesc);
		requestparams.put("Job Id", jid);

		httpRequest.body(requestparams.toString());

		call(BaseTest.jobPath, Method.POST);
		response.then().log().all();

		// ** status code validation **//

		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();
		if (statusCode == 200) {
			log.info("Validating the StatusCode" + statusCode);
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(statusCode, 200, "Job is successfully created");
			
			log.info("Validating the StatusLine" + statusLine);
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			
			// ** response body validation **//

			String responseBody = response.getBody().asString();

			Assert.assertEquals(responseBody.contains(jtitle), true);
			Assert.assertEquals(responseBody.contains(jcompname), true);
			Assert.assertEquals(responseBody.contains(jloc), true);
			Assert.assertEquals(responseBody.contains(jtype), true);
			Assert.assertEquals(responseBody.contains(jposttime), true);
			Assert.assertEquals(responseBody.contains(jdesc), true);
			Assert.assertEquals(responseBody.contains(jid), true);
			
			schemaValitation();
			
			
		} else if (statusCode == 409) {
			Assert.assertEquals(statusCode, 409);
			Assert.assertEquals(statusCode, 409, "Job" + jid + "" + "Job Already Exists");
			
			log.info("Validating the StatusLine" + statusLine);
			Assert.assertEquals(statusLine, "HTTP/1.1 409 CONFLICT");
		}


		
		

	}

	@DataProvider(name = "JobsApiData")
	public String[][] postApi() throws IOException {
		return ExcelUtils.getData("JobApi.xlsx", "POST");
	}

}