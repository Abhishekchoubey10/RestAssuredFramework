package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsUsingProperties;
import api.payload.User;
import io.restassured.response.Response;

public class UserPropertiesTest {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5, 10));
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority =1)
	public void testCreateUser() {
		
		logger.info("*********Creating User*********");
		Response response = UserEndPointsUsingProperties.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Created*********");
	}
	
	@Test(priority =2)
	public void testGetUser() {
		
		logger.info("*********Get User*********");
		Response response = UserEndPointsUsingProperties.readUser(this.userPayload.getUsername());
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Found*********");
		
	}
	
	@Test(priority =3)
	public void testUpdateUser() {
		
		logger.info("*********Updating User*********");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndPointsUsingProperties.updateUser(this.userPayload.getUsername(), userPayload);
	    response.then().log().all();
			
	    Assert.assertEquals(response.getStatusCode(), 200);
	    
	    //Check Data After Update 
		Response responseAfterUpdate = UserEndPointsUsingProperties.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********User Updated*********");
	    
	}
	
	@Test(priority =4)
	public void testDeleteUser() {
		
		logger.info("*********Deleting User*********");
		Response response = UserEndPointsUsingProperties.deleteUser(this.userPayload.getUsername());
	    response.then().log().all();
		
	    Assert.assertEquals(response.getStatusCode(), 200);
	    logger.info("*********User Deleted*********");
	}
	
}
