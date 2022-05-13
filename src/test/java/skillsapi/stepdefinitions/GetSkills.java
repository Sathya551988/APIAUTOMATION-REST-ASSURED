package skillsapi.stepdefinitions;

import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GetSkills extends BaseSteps {
	
	


	@Given("SkillsUser leaves the username and password empty")
	public void skills_user_leaves_the_username_and_password_empty() {
	    createRequest("", "");
	}

	@When("SkillsUser sends the Get Request")
	public void skills_user_sends_the_get_request() {
	    call(skillBasePath, Method.GET);
	}

	@Then("SkillsUser should see the error message with the Status {int} unauthorized")
	public void skills_user_should_see_the_error_message_with_the_status_unauthorized(int statusCode) {
	   validateStatus(statusCode);
	}

	@Given("SkillsUser enters correct username and password")
	public void skills_user_enters_correct_username_and_password() {
		createRequest(username, password);
	}

	@Then("SkillsUser should see the Status:{int} OK")
	public void skills_user_should_see_the_status_ok(int statusCode) {
		//response.then().log().all();
		validateStatus(statusCode);
		
	}
	@Then("Check Response status line for skills")
	public void check_response_status_line_for_skills() {
		validateStatusLine("HTTP/1.1 200 ");
	}

	@Then("Check Response body for skills")
	public void check_response_body_for_skills() {
		System.out.println("------------------------------------------------");
		int sizeofskillname= response.path("skill_name.size()");
		System.out.println("Total records of skill_name :" + " "+sizeofskillname);
		int sizeofskillid= response.path("skill_id.size()");
		System.out.println("Total records of skill_id :" +" "+sizeofskillid);
		response.getBody();
		System.out.println("Successfully Fetched all UserSkills Data");
		System.out.println("------------------------------------------------");
	}

	@Then("Validate skills Schema")
	public void validate_skills_schema() {
		validateSchema("jsonSchema/getSkillJsonSchema.json");
		
	}

}
