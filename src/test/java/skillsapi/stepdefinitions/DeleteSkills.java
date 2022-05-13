package skillsapi.stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.utilities.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class DeleteSkills extends BaseSteps{
	
	List<Response> responseList = new ArrayList<Response>();
    String[][] data=null;
	
	@Given("Read Data from file for DELETESkills")
	public void read_data_from_file_for_delete_skills() {
		data=ExcelUtils.getData("SkillsApi.xlsx", "DELETE");
	}

	@When("User sent DELETE request to delete the User")
	public void user_sent_delete_request_to_delete_the_user() {
		for (String[] row : data) {
			createRequest(username, password);
			Response response = httpRequest.request(Method.DELETE,BaseSteps.skillBasePath+ "/" + row[0]);
			response.then().log().all();
			responseList.add(response);
		}
	}

	@Then("DeleteSkills Check Response with status")
	public void delete_skills_check_response_with_status() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			String statusCode = row[1];
			validateStatus(response.getStatusCode(), Integer.parseInt(statusCode));
		}
	}

	@Then("DeleteSkills Check Response status line")
	public void delete_skills_check_response_status_line() {
		
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			validateStatusLine(response.getStatusLine(), row[2]);
		}
	}

	@Then("DeleteSkill Validate delete Schema")
	public void delete_skill_validate_delete_schema() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			response
			.then()
			.assertThat().
			body(matchesJsonSchemaInClasspath(row[3]));
		}
	}

	@Then("DeleteSkill validate DB")
	public void delete_skill_validate_db() {
		for(int i=0; i<responseList.size(); i++) {
			Response res = responseList.get(i);
			String[] row = data[i];
	    	if(res.getStatusCode() != 404) {
	    		String skillID = res.jsonPath().getString("Skill_Id");
	    		/* Create request for GetUserById*/
		    	createRequest(username, password );
				call(BaseSteps.skillBasePath+"/"+skillID, Method.GET);
				/* Logging the response*/
				response.then().log().all();
				/* Validating user not exists */
				assertEquals(response.getStatusCode(), 404);
				
	    	}

		 }
	}

	@Given("Delete Skills user leaves the username and password empty")
	public void delete_skills_user_leaves_the_username_and_password_empty() {
		createRequest("", "");
	}

	@When("Delete Skills user sends the Delete Request")
	public void delete_skills_user_sends_the_delete_request() {
		call(skillBasePath+"/891", Method.DELETE);
	}

	@Then("Delete Skills user should see the error message with the Status {int} unauthorized")
	public void delete_skills_user_should_see_the_error_message_with_the_status_unauthorized(int statusCode) {
		validateStatus(statusCode);
	}

}
