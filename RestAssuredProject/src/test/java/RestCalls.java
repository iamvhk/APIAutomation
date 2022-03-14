import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.json.simple.JSONObject;


public class RestCalls {
	
	
	
	@Test
	void test_1_get() {
		
//		given().
//			
//		when().
//			get("https://reqres.in/api/users?page=2"). 
//		then().
//			assertThat().
//			statusCode(200).
//		and().
//			body("data.id",hasSize(6));
		
		Response response=get("https://reqres.in/api/users?page=2");
		
		Assert.assertEquals(200,response.getStatusCode());
		Set<String> store=new LinkedHashSet<String>();
		
		
			store.add(response.jsonPath().getString("data[0]"));
			store.add(response.jsonPath().getString("data[1]"));
			store.add(response.jsonPath().getString("data[2]"));
			store.add(response.jsonPath().getString("data[3]"));
			store.add(response.jsonPath().getString("data[4]"));
			store.add(response.jsonPath().getString("data[5]"));
		
			System.out.println(store);
		
		
	}
	
	@Test
	void test_2_delete() {
		
		when().
			delete("https://reqres.in/api/users/2").
		then().
			statusCode(204).
			log().all();
	}
	
	@Test
	void test_3_update() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Hari");
		request.put("job", "Pilot");
		
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("https://reqres.in/api/users/2").
		then().
			statusCode(200).
			log().all();
			
		
		
	}
	
	

}
