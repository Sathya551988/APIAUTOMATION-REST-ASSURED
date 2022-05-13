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

public class PutSkills extends BaseSteps {
	List<Response> responseList = new ArrayList<Response>();
    String[][] data=null;
	
    @Given("UpdataSkills Read Data from file for put")
	public void updata_skills_read_data_from_file_for_put() {
    	data=ExcelUtils.getData("SkillsApi.xlsx", "PUT");
	}

	@When("User sent Put request for update Skills endpoint")
	public void user_sent_put_request_for_update_skills_endpoint() {
		for (String[] row : data) {
			createRequest(username, password);
			JSONObject params = new JSONObject();
			params.put("skill_name",row[0]);
			httpRequest.body(params.toString());
			Response response = httpRequest.request(Method.PUT,BaseSteps.skillBasePath+ "/" + row[1]);
			response.then().log().all();
			responseList.add(response);
			
		}
	}

	@Then("UpdateSkills Check Response with status code")
	public void update_skills_check_response_with_status_code() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			String statusCode = row[2];
			validateStatus(response.getStatusCode(), Integer.parseInt(statusCode));
		}
	   
	}

	@Then("UpdateSkills Check Response status line")
	public void update_skills_check_response_status_line() {
		
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			validateStatusLine(response.getStatusLine(), row[3]);
		}
	}
	    
	

	@Then("UpdateSkills Validate Put Schema")
	public void update_skills_validate_put_schema() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			response
			.then()
			.assertThat().
			body(matchesJsonSchemaInClasspath(row[4]));
		}
	    
	}

	@Then("UpdateSkills response body Validation")
	public void update_skills_response_body_validation() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
	    	if(response.getStatusCode() != 400) {
	    		
	    		System.out.println("------------------------------------------------");
	    		System.out.println("Validating response Body skill name");
	    		/* Validating the Name */
				String name = response.jsonPath().getString("skill_name");
				assertEquals(name, row[0]);
				System.out.println("Skillname : " + name);
	    		System.out.println("------------------------------------------------");
				
				
	    	}
		}
	}

	@Then("UpdateSkills validate DB")
	public void update_skills_validate_db() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
	    	if(response.getStatusCode() != 400) {
	    		String skillID = response.jsonPath().getString("skill_id");
	    		/* Create request for GetUserById*/
		    	createRequest(username, password );
				call(BaseSteps.skillBasePath+"/"+skillID, Method.GET);
				/* Logging the response*/
				response.then().log().all();
				/* Validating the Name */
				String name = response.jsonPath().getString("skill_name");
				assertEquals(name, row[0]);
				
	    	}

		 }
	   
	}

	@Given("Update Skills user leaves the username and password empty")
	public void update_skills_user_leaves_the_username_and_password_empty() {
		createRequest("", "");
	}

	@When("Update Skills user sends the Post Request")
	public void update_skills_user_sends_the_post_request() {
		call(skillBasePath+"/891", Method.PUT);
	}

	@Then("Update Skills user should see the error message with the Status {int} unauthorized")
	public void update_skills_user_should_see_the_error_message_with_the_status_unauthorized(int statusCode) {
		validateStatus(statusCode);
	}


}