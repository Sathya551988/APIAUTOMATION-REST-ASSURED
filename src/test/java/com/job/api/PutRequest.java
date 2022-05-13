package com.job.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.utilities.ExcelUtils;
import io.restassured.http.Method;

public class PutRequest extends BaseTest {
	@Test(dataProvider = "JobsApiData")
	public void UpdateRecord(String jtitle, String name, String jloc, String jtype, String jposttime, String jdesc,
			String jid) throws InterruptedException {

		createRequest();

		// Request payload sending along with put request

		JSONObject requestparams = new JSONObject();

		requestparams.put("Job Title", jtitle);
		requestparams.put("Job Location", jloc);
		requestparams.put("Job Type", jtype);
		requestparams.put("Job Posted time", jposttime);
		requestparams.put("Job Company Name", name);
		requestparams.put("Job Id", jid);

		httpRequest.body(requestparams.toString());

		call(BaseTest.jobPath, Method.PUT);
		response.then().log().all();
		// ** status code validation **//

		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Validating the StatusCode" + statusCode);
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(statusCode, 200, "Job is successfully created");
			
			
			String statusLine1 = response.getStatusLine();
			log.info("Validating the StatusLine" + statusLine1);
			Assert.assertEquals(statusLine1, "HTTP/1.1 200 OK");

			// ** response body validation **//

			String responseBody = response.getBody().asString();

			Assert.assertEquals(responseBody.contains(jtitle), true);
			Assert.assertEquals(responseBody.contains(jloc), true);
			Assert.assertEquals(responseBody.contains(jtype), true);
			Assert.assertEquals(responseBody.contains(jposttime), true);
			Assert.assertEquals(responseBody.contains(jid), true);
			schemaValitation();
		} else if (statusCode == 404) {
			Assert.assertEquals(statusCode, 404);
			Assert.assertEquals(statusCode, 404, "Job" + jid + "" + "Job Not Found");
			
			String statusLine1 = response.getStatusLine();
			log.info("Validating the StatusLine" + statusLine1);
			Assert.assertEquals(statusLine1, "HTTP/1.1 404 NOT FOUND");
		}

		// ** status Line validation **//

	

	}

	@DataProvider(name = "JobsApiData")
	public String[][] postApi() throws IOException {
		return ExcelUtils.getData("JobApi.xlsx", "PUT");
	}
}
