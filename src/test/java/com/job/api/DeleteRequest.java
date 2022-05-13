package com.job.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utilities.ExcelUtils;

import io.restassured.http.Method;

public class DeleteRequest extends BaseTest {
	@Test(dataProvider = "JobsApiData")
	public void test_DeleteRecord(String jid) throws InterruptedException {

		createRequest();
		
		// Creating Json object to send data along with delete request

		JSONObject requestparams = new JSONObject();

		requestparams.put("Job Id", jid);
		
		httpRequest.body(requestparams.toString());

		call(BaseTest.jobPath,Method.DELETE);

		response.then().log().all();

		// ** status code validation **//

				int statusCode = response.getStatusCode();
				if (statusCode == 200) {
					log.info("Validating the StatusCode" + statusCode);
					Assert.assertEquals(statusCode, 200);
					Assert.assertEquals(statusCode, 200, "Job is successfully Deleted");
				} else if (statusCode == 404) {
					Assert.assertEquals(statusCode, 404);
					Assert.assertEquals(statusCode, 404, "Job" + jid + "" + "Job Not Found");
				}
	}

	@DataProvider(name = "JobsApiData")
	public String[][] postApi() throws IOException {
		return ExcelUtils.getData("JobApi.xlsx", "DELETE");
	}


	public void schema_validation() {
		response.then().body(matchesJsonSchemaInClasspath("jsonSchema/jobApiSchema.json"));
		System.out.println("Schema successfully validated in PUT Method");	

	}
}



