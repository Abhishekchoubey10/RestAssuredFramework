package api.endpoints;

/*
 * Swagger URI -- https://petstore.swagger.io
 * 
 * Create User(POST) -- https://petstore.swagger.io/v2/user
 * Get User(GET) -- https://petstore.swagger.io/v2/user/{username}
 * Update User(PUT) -- https://petstore.swagger.io/v2/user/[username]
 * Delete User(DELETE) -- https://petstore.swagger.io/v2/user/{username}
 * 
 */

public class Routes {

	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String userCreateUrl = baseUrl+"/user";
	public static String userGetUrl = baseUrl+"/user/{username}";
	public static String userUpdateUrl = baseUrl+"/user/{username}";
	public static String userDeleteUrl = baseUrl+"/user/{username}";
	
}
