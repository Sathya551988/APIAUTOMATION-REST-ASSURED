package skillsapi.stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.utilities.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class PostSkills extends BaseSteps {
	List<Response> responseList = new ArrayList<Response>();
	String[][] data;
	
	@Given("CreateSkills Read Data from file for post")
	public void create_skills_read_data_from_file_for_post() {
	    data=ExcelUtils.getData("SkillsApi.xlsx", "POST");
	}

	@When("The user create Skills with POST API")
	public void the_user_create_skills_with_post_api() {
	    
		for (String[] row : data) {
			createRequest(username, password);
			JSONObject params = new JSONObject();
			params.put("skill_name",row[0]);
			httpRequest.body(params.toString());
			Response response = httpRequest.request(Method.POST,BaseSteps.skillBasePath);
			response.then().log().all();
			responseList.add(response);
			
		}
	}

	@Then("CreateSkills Check Response with status code")
	public void create_skills_check_response_with_status_code() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			String statusCode = row[1];
			validateStatus(response.getStatusCode(), Integer.parseInt(statusCode));
		}
	}

	@Then("CreateSkills Check Response status line")
	public void create_skills_check_response_status_line() {
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			validateStatusLine(response.getStatusLine(), row[2]);
		}
	}

	@Then("CreatSkills Validate Post Schema")
	public void creat_skills_validate_post_schema() {		
		for(int i=0; i<responseList.size(); i++) {
			Response response = responseList.get(i);
			String[] row = data[i];
			response
			.then()
			.assertThat().
			body(matchesJsonSchemaInClasspath(row[3]));
		}
	}
	
	@Then("CreatSkills response Validation")
	public void creat_skills_validate_response() {
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

	@Then("CreatSkills validate DB")
	public void creat_skills_validate_db() {
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
	
	@Given("Create SkillsUser leaves the username and password empty")
	public void skills_user_leaves_the_username_and_password_empty() {
	    createRequest("", "");
	}

	@When("Create SkillsUser sends the Post Request")
	public void skills_user_sends_the_get_request() {
	    call(skillBasePath, Method.POST);
	}

	@Then("Create SkillsUser should see the error message with the Status {int} unauthorized")
	public void skills_user_should_see_the_error_message_with_the_status_unauthorized(int statusCode) {
	   validateStatus(statusCode);
	}

}

