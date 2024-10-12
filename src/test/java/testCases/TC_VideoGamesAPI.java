package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class TC_VideoGamesAPI {
	
	@Test(priority = 1)
	public void testget_AllVideoGames()
	{
		given()
		.when()
			.get("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200);
	}
	
	@Test(priority = 2)
	public void testadd_VideoGame()
	{
		HashMap data=new HashMap();
		data.put("id", "77777777");
		data.put("name", "gaandPhaad2");
		data.put("releaseDate", "2024-10-12T10:59:55.995");
		data.put("reviewScore", "1000000");
		data.put("category", "everything");
		data.put("rating", "1000000");
		
		
		Response res=
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		System.out.println("Response--->"+res.asString());
		String jsonResponse=res.asString();
		Assert.assertEquals(jsonResponse.contains("Record Added Successfully"), true);
		System.out.println("\n");
		
	}
	@Test(priority = 3)
	public void test_getVideoGameBy_ID()
	{
		given()
			.contentType("application/json")
		.when()
			.get("http://localhost:8080/app/videogames/77777777")
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("77777777"))
			.body("videoGame.name", equalTo("gaandPhaad2"));
		
	}
	@Test(priority = 4)
	public void test_put_VideoGame()
	{
		HashMap data=new HashMap();
		data.put("id", "77777777");
		data.put("name", "gaandPhaad3");
		data.put("releaseDate", "2024-10-12T10:59:55.995");
		data.put("reviewScore", "1000000");
		data.put("category", "everything");
		data.put("rating", "1000000");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("http://localhost:8080/app/videogames/77777777")
		.then()
			.statusCode(200)
			.log().body();
			
	}
	@Test(priority=5)
	public void test_DeleteVideoGame()
	{
		Response res =
				given()
				.when()
					.delete("http://localhost:8080/app/videogames/77777777")
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
		
	}
	
	

}
