
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import groovyjarjarasm.asm.commons.Method;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class SimpleGetTest {
 
	@Test
	public void GetWeatherDetails()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
 
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
 
		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		//Response response = httpRequest.request(Method.GET, "/Bangalorre");
		Response response = httpRequest.get("/Bangalore");
 
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		System.out.println("Response Body is =>  " + responseBody);
 
	}
	
	
@Test
public void DisplayAllNodesInWeatherAPI()
{
	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	RequestSpecification httpRequest = RestAssured.given();
	Response response = httpRequest.get("/Hyderabad");
 
	// First get the JsonPath object instance from the Response interface
	JsonPath jsonPathEvaluator = response.jsonPath();
 
	// Let us print the city variable to see what we got
	System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
 
	// Print the temperature node
	System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
 
	// Print the humidity node
	System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
 
	// Print weather description
	System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
 
	// Print Wind Speed
	System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));
 
	// Print Wind Direction Degree
	System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
}


@Test
public void RegistrationSuccessful()
{		
	RestAssured.baseURI ="http://restapi.demoqa.com/customer";
	RequestSpecification request = RestAssured.given();
	
	//RequestSpecification request = RestAssured.given().auth().basic("", "");
	//RequestSpecification request = RestAssured.given().param("Username", "").param("Pass", "");
 
	JSONObject requestParams = new JSONObject();
	requestParams.put("FirstName", "Vird4eender"); // Cast
	requestParams.put("LastName", "Sindedgh");
	requestParams.put("UserName", "sdimdpldeeuser2dd2011");
	requestParams.put("Password", "pasdsdeword1");
 
	requestParams.put("Email",  "sampled2eede26d9@gmail.com");
	
	request.header("Content-Type", "application/json");
	
	request.body(requestParams.toJSONString());
	Response response = request.post("/register");
 
	//String responseBody = response.getBody().asString();
	
	//JsonPath jsonPathEvaluator = response.jsonPath();
	System.out.println("City received from Response " + response.jsonPath().get("SuccessCode"));
	
	//System.out.println("Response Body is =>  " + responseBody);
	//String successCode = response.jsonPath().get("SuccessCode");
	//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
}
 
}