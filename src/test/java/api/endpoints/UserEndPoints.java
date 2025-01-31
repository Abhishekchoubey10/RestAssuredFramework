package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//Created to perform Create Read Update and Delete requests to the User API.

public class UserEndPoints {

	public static Response createUser(User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

		.when().post(Routes.userCreateUrl);

		return response;

	}
	
	public static Response readUser(String userName) {

		Response response = (Response) given().pathParam("username", userName)

		.when().get(Routes.userGetUrl);

		return response;

	}
	
	public static Response updateUser(String userName, User payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName).body(payload)

		.when().put(Routes.userUpdateUrl);

		return response;

	}
	
	public static Response deleteUser(String userName) {

		Response response = (Response) given().pathParam("username", userName)

		.when().delete(Routes.userDeleteUrl);

		return response;

	}

}
