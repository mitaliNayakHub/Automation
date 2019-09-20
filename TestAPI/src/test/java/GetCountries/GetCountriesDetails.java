package GetCountries;

import org.json.JSONException;
import org.testng.annotations.Test;
import BaseTests.baseTests;

public class GetCountriesDetails {

	String add= "/all";

	//Test to check the response for country US
	@Test 
	public void validateUS() {
		System.out.println(baseTests.baseUri + add);
		baseTests.requestSpec(add);
		baseTests.verifyStatusCode(200, "US");
	}

	//Test to check the response for country DE
	@Test
	public void validateDE() {
		baseTests.requestSpec(add);
		baseTests.verifyStatusCode(200, "DE");
	}

	//Test to check the response for country GB
	@Test
	public void validateGB() {
		baseTests.requestSpec(add);
		baseTests.verifyStatusCode(200, "GB");
	}

	//Test to check the response for non existing countries
	@Test
	public void getnonExistingCountries() {
		baseTests.requestSpec(add);
		baseTests.verifyStatusCode(400, "asd");
	}

	//Test to add a new entity in the list
	@Test
	public void addCountry() {
		String request = "{\"name\":\"Test Country\",\"alpha2_code\":\"TC\",\"alpha3_code\":\"TCY\"}";
		baseTests.postRequest(request);
		baseTests.requestSpec("TC");
		baseTests.verifyStatusCode(200, "TC");
	}

	//Get list of all the requests and get the result for a provided country code
	@Test
	public void getAll() throws JSONException {
		System.out.println(baseTests.baseUri + add);
		baseTests.containsName("US");
		baseTests.containsName("GB");
		baseTests.containsName("DE");

	}
}
