package skillsapi.stepdefinitions;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseSteps {

	public final String username ="APIPROCESSING";
	public final String password ="2xx@Success";
	public Response response;
	public static String jsonAsString;
	public static RequestSpecification request;
	public static String baseUrl = "https://springboot-lms-userskill.herokuapp.com";
	public static String userBasePath = "/Users";
	public static String skillBasePath = "/Skills";
	public static String userSkillBasePath = "/UserSkills";
	public static String userSkillMapBasePath = "/UserSkillsMap";

	public RequestSpecification httpRequest;


	public void createRequest(String userName, String password) {
		httpRequest = given().
				baseUri(baseUrl).
				auth().basic(userName, password).header("content-Type",
				"application/json");
	}

	public void call(String path, Method method) {
		response = httpRequest.request(method, path);
	}

	public void validateStatus(int status) {
		validateStatus(response.getStatusCode(), status);
	}
	
	public void validateStatus(int actual, int expected) {
		System.out.println("------------------------------------------------");
		System.out.println("Validating status code");
		assertEquals(expected, actual);
		System.out.println("Status Code :" + actual);
		System.out.println("------------------------------------------------");
		
	}

	public void validateStatusLine(String statusLine) {
		validateStatusLine(response.getStatusLine(), statusLine);
	}
	
	public void validateStatusLine(String actual, String expected) {
		System.out.println("------------------------------------------------");
		System.out.println("Validating status Line");
		assertEquals(actual, expected);
		System.out.println("Status Line :" +actual);
		System.out.println("------------------------------------------------");
	}

	public void validateContentType(String contentType) {
		assertEquals(response.getContentType(), contentType);
	}

	public void validateSchema(String schemaPath) {
		System.out.println("------------------------------------------------");
		System.out.println("Validating the schema");
		response.
			then().
			assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
		System.out.println("------------------------------------------------");
		
	}

	public void logResponse() {
		response.then().log().body();
	}

	

}
