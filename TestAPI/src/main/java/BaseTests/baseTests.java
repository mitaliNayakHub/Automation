package BaseTests;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONException;

public class baseTests {
	protected static String countryName = null;
	public static String baseUri = "http://services.groupkt.com/country/get";
	protected static int code = 0;
	
	
//  This method returns the method content
	public static void requestSpec(String country) {
		RestAssured.baseURI = baseUri;
		RequestSpecification httpRequest = RestAssured.given();
		 String add = "/iso2/" + country;
		Response response = httpRequest.get(add);
		String responseBody = response.asString();
		System.out.println("Response Body is =>  " + responseBody);

	}

	//This method is to verify the returned status code 
	public static void verifyStatusCode(int i, String value) {
		String country = "/iso2code/" + value;
		RequestSpecification httpRequest = RestAssured.given();
		int statusCode = httpRequest.get(country).getStatusCode();
		System.out.println("returned status code is " + statusCode);
		System.out.println("Response Body is =>  " + httpRequest.get(country).asString());
		Assert.assertEquals(statusCode, i, "expected status code returned");
	}

	//This method is to create a new entity in the list
	public static void postRequest(String requestBody) {
		Response response = null;
		try {
			response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/create");
			if (response.getStatusCode() == 201 || response.getStatusCode() == 202) {
				System.out.println("The state is added successfully");
			} else if (response.getStatusCode() == 403)
			{
				System.out.println("This action is forbidden");
			}
			else {
				System.out.println("Unsuccesful request");
				System.out.println(response.statusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// This method checks if the response contains some expected value
	public static void containsName(String code) throws JSONException {
		String response = RestAssured.given().get(baseUri + "/all").asString();
		System.out.println(response);
		if (response.contains("Country found matching code [" +code+"].")) {
//		if (response.contains("\"City\":" + name + "\"")) {
			System.out.println("The city exists in the list");
		} else {
			System.out.println("The city doesn't exists in the list");
		}

	}
}
