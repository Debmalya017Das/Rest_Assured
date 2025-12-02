package com.rest.assured;

import static org.testng.Assert.assertEquals;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class RA_handson {
	
	
	@Test(priority = 1 )
	public void test() {
				
		Response resp = RestAssured.given().baseUri("https://ergast.com/api/f1").get();
		System.out.println("sts code: "+ resp.statusCode());
		
		assertEquals(resp.statusCode(), 200);
		
	}
	
	@Test
	public void printbookingid() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		String body = "{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		
		Response resp = RestAssured.given().get();
		System.out.println(resp.asPrettyString());
		
		 Response posted = RestAssured.given().header("content-type","application/json").body(body).post();
		 System.out.println("Post success with stscode: "+posted.statusCode());
		 System.out.println("Booking id: "+ posted.jsonPath().getInt("bookingid"));
		 assertEquals(posted.statusCode(),200);
		 
		 
		
	}
	@Test
	 public void printalldets() {
		
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		
		String payload= "{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";

		
		Response resp = RestAssured.given().header("content-Type", "application/json").body(payload).post();
		assertEquals(resp.statusCode(),200);
		
		System.out.println("firstname: "+resp.jsonPath().getString("booking.firstname"));
		System.out.println("price: "+resp.jsonPath().getInt("booking.totalprice"));
		System.out.println("depositpaid: "+resp.jsonPath().getBoolean("booking.depositpaid"));
		System.out.println("checkin: "+resp.jsonPath().getString("booking.bookingdates.checkin"));
		 
		System.out.println("additional: "+resp.jsonPath().getString("booking.additionalneeds"));
	 }
	
	@Test
	public void auth1() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://postman-echo.com";
		RestAssured.basePath = "/basic-auth";
		
		Response response = RestAssured.given().auth().preemptive().basic("postman", "password").get();
		assertEquals(response.statusCode(),200);
		System.out.println("Resp: "+ response.prettyPrint());
				
	
	}
	@Test
	public void respcode() {
		 RestAssured.baseURI = "https://the-internet.herokuapp.com";
		 RestAssured.basePath = "/status_codes/200";
		 
		 Response response = RestAssured.given().get();
		 System.out.println(response.prettyPrint());
		 
		
		 
	}
	@Test
	public void respcode301() {
		 RestAssured.baseURI = "https://the-internet.herokuapp.com";
		 RestAssured.basePath = "/status_codes/301";
		 
		 Response response = RestAssured.given().get();
		 System.out.println(response.prettyPrint());
		 
		
		 
	}
	@Test
	public void respcode404() {
		 RestAssured.baseURI = "https://the-internet.herokuapp.com";
		 RestAssured.basePath = "/status_codes/404";
		 
		 Response response = RestAssured.given().get();
		 System.out.println(response.prettyPrint());		 
		
		 
	}
//--------------Didnot work------------------//
	@Test
	public void parseJSON() {

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		
		Response response = RestAssured.given().get();
		
		
//		JsonElement root = JsonParser.parseString(response.asString());
		
		System.out.println();			
	
	}
	
	
	
	
	@Test
	public void parseXML() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		int id =119;
		
		Response response = RestAssured.given().header("content-type", "application/XML").accept("application/XML").get("/"+id);
		
		System.out.println(response.prettyPrint());	
		
		
	}
	@Test
	public void queryparam() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api/users";
		Response response = RestAssured.given().queryParams("page", 2).get();
		
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void doubleparam() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api/users";
		Response response = RestAssured.given().queryParams("page", 2).queryParams("id", 5).get();
		
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void pathparam() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//		RestAssured.basePath = "/posts";
		
		Response response = RestAssured.given().get("/posts?id=5");

		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void endpointbase() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//		RestAssured.basePath = "/posts";
		
		Response response = RestAssured.given().get("/posts/5");

		System.out.println(response.prettyPrint());
		
	}
	


	@Test
	public void successresp() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://api.restful-api.dev/objects";
		String payload = "{\n" +
			    "  \"name\": \"Dell I5\",\n" +
			    "  \"data\": {\n" +
			    "    \"year\": 2023,\n" +
			    "    \"price\": 20000,\n" +
			    "    \"CPU model\": \"Intel Core i9\",\n" +
			    "    \"Hard disk size\": \"2 TB\"\n" +
			    "  }\n" +
			    "}";

		Response response =RestAssured.given().header("content-type","application/json").body(payload).post();
		
		assertEquals(response.statusCode(),200);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		System.out.println(RestAssured.given().get().prettyPrint());
	}
	
//	upto chapter 6 -- qn -2
	
	@Test
	public void updateprice() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://api.restful-api.dev";
		RestAssured.basePath = "/objects/ff80818191c724f20191c768f632008d";
		
		String payload = "{"
		        + "\"name\": \"Apple MacBook Pro 16\","
		        + "\"data\": {"
		        +     "\"year\": 2019,"
		        +     "\"price\": 4000.99,"
		        +     "\"CPU model\": \"Intel Core i9\","
		        +     "\"Hard disk size\": \"1 TB\","
		        +     "\"color\": \"silver\""
		        + "}"
		        + "}";

		Response response = RestAssured.given().header("content-type","application/json").body(payload).put();	
		assertEquals(response.statusCode(),200);
	}
	
	@Test
	public void updateexisiting() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://api.restful-api.dev";
		RestAssured.basePath = "/objects/ff80818191c724f20191c768f632008d";
		
		String payload = "{\r\n"
				+ "   \"name\": \"Apple MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2019,\r\n"
				+ "      \"price\": 4000.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\",\r\n"
				+ "      \"color\": \"silver\"\r\n"
				+ "   }\r\n"
				+ "}";
		Response response = RestAssured.given().header("content-type","application/json").body(payload).put();
		
		System.out.println(response.jsonPath().getDouble("data.price"));
		
	}
	
	//----- chapter 6 site is not working so starting chapter 7
	
	@Test
	public void responsestscode() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		
		String payload = "{\r\n"
				+ "    \"firstname\": \"Jim\",\r\n"
				+ "    \"lastname\": \"Brown\",\r\n"
				+ "    \"totalprice\": 111,\r\n"
				+ "    \"depositpaid\": true,\r\n"
				+ "    \"bookingdates\": {\r\n"
				+ "        \"checkin\": \"2018-01-01\",\r\n"
				+ "        \"checkout\": \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\": \"Breakfast\"\r\n"
				+ "}";
		
		String payload2 = "{\r\n"
				+ "    \"firstname\": \"Jim\",\r\n"
				+ "    \"lastname\": \"Brown\",\r\n"
				+ "    \"totalprice\": 111,\r\n"
				+ "    \"depositpaid\": true,\r\n"
				+ "    \"bookingdates\": {\r\n"
				+ "        \"checkin\": \"2018-01-01\",\r\n"
				+ "        \"checkout\": \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\": \"Breakfast\"\r\n"
				+ "}";
		
		Response response = RestAssured.given().header("content-type","application/json").body(payload).post();
		assertEquals(response.statusCode(),200);
		
		RestAssured.given()
	    .header("Content-Type", "application/json")
	    .body(payload2)
	    .when()
	    .post()
	    .then()
	    .assertThat()
	    .statusCode(200);
		
		
	}
	
	@Test
	public void qn4() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		
		String payload = "{\r\n"
				+ "    \"firstname\": \"Jim\",\r\n"
				+ "    \"lastname\": \"Brown\",\r\n"
				+ "    \"totalprice\": 111,\r\n"
				+ "    \"depositpaid\": true,\r\n"
				+ "    \"bookingdates\": {\r\n"
				+ "        \"checkin\": \"2018-01-01\",\r\n"
				+ "        \"checkout\": \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\": \"Breakfast\"\r\n"
				+ "}";
		Response response = RestAssured.given().header("content-type","application/json").body(payload).post();
		String stsline = response.statusLine();
		System.out.println(stsline);
	}
	
	@Test
	public void header() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		
		String payload = "{\r\n"
				+ "    \"firstname\": \"Jim\",\r\n"
				+ "    \"lastname\": \"Brown\",\r\n"
				+ "    \"totalprice\": 111,\r\n"
				+ "    \"depositpaid\": true,\r\n"
				+ "    \"bookingdates\": {\r\n"
				+ "        \"checkin\": \"2018-01-01\",\r\n"
				+ "        \"checkout\": \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\": \"Breakfast\"\r\n"
				+ "}";
		Response response = RestAssured.given().header("content-type","application/json").body(payload).post();
		
		//single header
		System.out.println(response.getHeader("content-type"));
		
		
		Headers allheads = response.getHeaders();
		for(Header head: allheads) {
			System.out.println(head.getName() +" : "+ head.getValue());		
		}
		
		//validating headers
		
//		RestAssured.given().contentType("application/json").body(payload).when().post().then().header("content-type", equalTo("application/json"));
		
		
	} 
	
	
	
	//--------------ch8-----------//
	@Test
	public void jsonschema() {
		RestAssured.baseURI = " http://universities.hipolabs.com";
				
		Response response = RestAssured.given().get("/search?country=United+States");
		
		System.out.println(response.prettyPrint());		
		
	}

}
