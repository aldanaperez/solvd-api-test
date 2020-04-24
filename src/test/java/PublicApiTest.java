import static org.testng.Assert.*;

import java.io.FileReader;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.*;


public class PublicApiTest {
	
	private final static JSONParser parser = new JSONParser();
	private final static String BASE_URL = "http://restapi.demoqa.com";
	private final static String POST_RESOURCE = BASE_URL + "/customer/register";
	private final static String GET_RESOURCE = BASE_URL + "/utilities/weather/city/";
	private static final String PATH_TO_DEFAULT_JSON = "api/register_person_rq.json";
	

	@Test
	public void testGetWeatherFromACity() throws Exception{
		
		RestAssured.baseURI = GET_RESOURCE;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "Tandil");
		assertEquals(response.getStatusCode(), 200);
		assertFalse(response.getStatusCode() == 404);
		assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		assertEquals(response.jsonPath().get("City"), "Tandil");
		assertNotNull(response.jsonPath().get("Temperature"));

	}
	
	@Test
	public void testCreateNewCustomer() throws Exception{
		
		RestAssured.baseURI = POST_RESOURCE;
		RequestSpecification httpRequest = RestAssured.given();

		//Request payload sending along with post request
		JSONObject requestParams = (JSONObject) parser
				.parse(new FileReader(ClassLoader.getSystemResource(PATH_TO_DEFAULT_JSON).getFile()));
		requestParams.put("UserName", RandomStringUtils.randomAlphanumeric(10));
		requestParams.put("Password", RandomStringUtils.randomAlphanumeric(10));
		requestParams.put("Email", RandomStringUtils.randomAlphanumeric(10) + "@gmail.com");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.request(Method.POST);
		assertEquals(response.getStatusCode(), 201);
		assertEquals(response.jsonPath().get("SuccessCode"), "OPERATION_SUCCESS");
		assertEquals(response.jsonPath().get("Message"), "Operation completed successfully");
	}

	
}
