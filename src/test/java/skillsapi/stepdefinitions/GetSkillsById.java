package skillsapi.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;

public class GetSkillsById extends BaseSteps {
	
	@Given("Skills user enters username and password")
	public void skills_user_enters_username_and_password() {
		
	    createRequest(username, password);
	    
	}

	@When("Skills user sends the Get Request for the Single skill by {string}")
	public void skills_user_sends_the_get_request_for_the_single_skill_by(String skillId) {
		
		call(BaseSteps.skillBasePath+"/"+skillId,Method.GET);
	    
	}

	@Then("Skills user should see the Status:{int} OK for single id")
	public void skills_user_should_see_the_status_ok_for_single_id(Integer statusCode) {
		
		validateStatus(statusCode);
		
	    
	}
	@Then("Check Response status line for get skills")
	public void check_response_status_line_for_get_skills() {
		
		validateStatusLine("HTTP/1.1 200 ");
	    
		}

	@Then("Check Response body for get skills")
	public void check_response_body_for_get_skills() {
		System.out.println("------------------------------------------------");
		String sizeofskillname= response.path("skill_name");
		System.out.println("skill_name :" + " "+sizeofskillname);
		int sizeofskillid= response.path("skill_id");
		System.out.println(" skill_id :" +" "+sizeofskillid);
		response.getBody();
		System.out.println("Successfully Fetched all UserSkills Datas");
		System.out.println("------------------------------------------------");
	    
	}

	@Then("Validate get skills Schema")
	public void validate_get_skills_schema() {
		validateSchema("jsonSchema/getSkillbyidSchema.json");
	    
	}



}
