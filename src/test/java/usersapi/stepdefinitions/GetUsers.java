package usersapi.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import skillsapi.stepdefinitions.BaseSteps;

public class GetUsers extends BaseSteps {
	
	@Given("User leaves the username and password empty")
	public void user_leaves_the_username_and_password_empty() {
		createRequest("","");
	    
	}

	@When("The user sends the get request")
	public void the_user_sends_the_get_request() {
		call(userBasePath,Method.GET);
	    
	}

	@Then("The user should see the error mesage with the status {int} unauthorized")
	public void the_user_should_see_the_error_mesage_with_the_status_unauthorized(int statusCode) {
		validateStatus(statusCode);
	   
	}

	@Given("User enters the correct username and password")
	public void user_enters_the_correct_username_and_password() {
		createRequest(username, password);
	    
	}

	@Then("The user should see the Status: {int} OK")
	public void the_user_should_see_the_status_ok(Integer statusCode) {
	    validateStatus(statusCode);
	}

	@Then("Check Response status line for Users")
	public void check_response_status_line_for_users() {
		validateStatusLine("HTTP/1.1 200 ");
	    
	}

	@Then("Check Response body for Users")
	public void check_response_body_for_users() {
		System.out.println("------------------------------------------------");
		int sizeofskillname= response.path("skill_name.size()");
		System.out.println("Total records of skill_name :" + " "+sizeofskillname);
		int sizeofskillid= response.path("skill_id.size()");
		System.out.println("Total records of skill_id :" +" "+sizeofskillid);
		response.getBody();
		System.out.println("Successfully Fetched all UserSkills Data");
		System.out.println("------------------------------------------------");
	   
	}

	@Then("Validate Schema for Users")
	public void validate_schema_for_users() {
		validateSchema(baseUrl);
	   
	}

}
