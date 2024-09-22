package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class DataDrivenTests {

	@DataProvider
	public Object[][] getUserData() {
		//to Provide a data for different user we have provide data in 2D Object array 
	return new Object[][] {
		{"987654", "Sohan89", "Sohan", "Kumar", "sohan987@gmail.com", "Test@123", "8765646134"},
		{"987655", "Mohan90", "Mohan", "Kumar", "mohan654@gmail.com", "Test@456", "6578646134"},
			
	};
	}
	
	@Test(priority =1, dataProvider = "getUserData")
	public void testCreateUser(String userID, String userName, String firstN, String lastN, String UserEmail, String pwd, String pn) 
	{	
	  User userPayload = new User();
	  
	  userPayload.setId(Integer.parseInt(userID));
	  userPayload.setUsername(userName);
	  userPayload.setFirstName(firstN);
	  userPayload.setLastName(lastN);
	  userPayload.setEmail(UserEmail);
	  userPayload.setPassword(pwd);
	  userPayload.setPhone(pn);
	  
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	  
		
	}
	
	@DataProvider
	public Object[][] getUserName() {
		//to Provide a data for different user we have provide data in 2D Object array 
	return new Object[][] {
		{"Sohan89"},
		{"Mohan90"},
			
	};
	}
	
	@Test(priority=2, dataProvider= "getUserName")
	public void testGetUser(String userName) {
		
		Response response = UserEndPoints.readUser(userName);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3, dataProvider= "getUserName")
	public void testDeleteUser(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
