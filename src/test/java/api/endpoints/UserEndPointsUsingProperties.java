package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//Created to perform Create Read Update and Delete requests to the User API.

public class UserEndPointsUsingProperties {
	
	//Method Created for getting URl's from properties file
	public static ResourceBundle getURL() {
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("userCreateUrl");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

		.when().post(post_url);

		return response;

	}
	
	public static Response readUser(String userName) {
		
		String read_url = getURL().getString("userGetUrl");

		Response response = (Response) given().pathParam("username", userName)

		.when().get(read_url);

		return response;

	}
	
	public static Response updateUser(String userName, User payload) {
		
		String update_url = getURL().getString("userUpdateUrl");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName).body(payload)

		.when().put(update_url);

		return response;

	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getURL().getString("userDeleteUrl");

		Response response = (Response) given().pathParam("username", userName)

		.when().delete(delete_url);

		return response;

	}

}
