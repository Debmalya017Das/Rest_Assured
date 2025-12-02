package com.rest.assured;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class RestTest {

	public static Response doGetReq(String end) {
		return RestAssured.given().headers("Content-Type", ContentType.JSON, "Accept" ,ContentType.JSON).when()
				.get(end).then().contentType(ContentType.JSON).extract().response();
	}
	
	@Test
	public void getNodeValueFromNestedRespJSON() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://reqres.in/api/";
		Response response = doGetReq("users?page=2");
		
		JsonPath j = new JsonPath(response.asString());
		
		String fName = j.getString("data[1].first_name");
		System.out.println("\n The First Name is "+ fName);
		Assert.assertEquals(fName, "Lindsay");
	
	}
	@Test
	public void storeResponseIntoJsonFile() {
		Response response = doGetReq("https://jsonplaceholder.typicode.com/users/1");
		
		try {
			FileWriter fw = new FileWriter(
					System.getProperty("user.dir")+"/Resources/jsonfiles/schema.json");
					fw.write(response.asPrettyString());
					fw.flush();
					fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getNumofRootElements() {
		Response response  = doGetReq("https://jsonplaceholder.typicode.com/users/");
		List<Map<String, String>> companies = response.jsonPath().getList("company");
		
		System.out.println("\n" + companies.get(0).get("name")+ "\n");
	}
	
	
	//faileddd 
	@Test
	public void createjsonuser() {
		RestAssured.useRelaxedHTTPSValidation();
		File payload = new File("Resources/jsonfiles/xyz.json");
		RestAssured.given().baseUri("https://reqres.in").contentType(ContentType.JSON).body(payload).when().post("/api/users")
		.then().assertThat().statusCode(201).log().all().body("name", Matchers.equalTo("Json_Test")).body("job", Matchers.equalTo("Leader"));
	}
	
	@Test
	public void createxml() {
		
		File xml = new File("Resources/jsonfiles/abx.xml");
		
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com/auth")
		.contentType(ContentType.XML).body(xml).when().post().then().assertThat().statusCode(200).log().all();
		
	}
	
//	couldnot do the multipart form and other form data one because of the basic auth creds and base token shiit
}

