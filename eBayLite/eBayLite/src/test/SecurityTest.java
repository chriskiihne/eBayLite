package test;

import static org.junit.Assert.*;


import org.junit.Test;

import util.Security;
import  org.junit.Assert;

public class SecurityTest {

	@Test
	public void testUserExist() {
		
		// TODO implement
		
		//Security sec=new  Security();
		Assert.assertTrue(Security.doesUserExist("test1"));
		Assert.assertFalse(Security.doesUserExist("TEST2"));
		Assert.assertFalse(Security.doesUserExist("test9"));
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetPassword() {
		
		// TODO implement
		
		//Security sec=new  Security();
	
		String actualResult1 = Security.getPassword("test3");
		Assert.assertEquals("test3", actualResult1);
		
		String actualResult2 = Security.getPassword("test7");
		Assert.assertEquals("test7", actualResult2);
	//	fail("Not yet implemented");
		
	}
	
	
	@Test
	public void testAuthenticate() {
		
		// TODO implement
		
		//Security sec=new  Security();
	
		Assert.assertTrue(Security.authenticate("test1","test1"));	
		Assert.assertFalse(Security.authenticate("test3","test2"));	
		Assert.assertFalse(Security.authenticate("test6","test6"));	
		Assert.assertFalse(Security.authenticate("test7","test3"));
	//	fail("Not yet implemented");
		
	}
	
	
	@Test
	public void testAddUser() {
		
		// TODO implement
		
		//Security sec=new  Security();
	
		Assert.assertTrue(Security.addUser("test2", "test2"));	
		Assert.assertFalse(Security.addUser("test3", "test1"));	
		Assert.assertFalse(Security.addUser("test6","test6"));	
		Assert.assertFalse(Security.addUser("test7","test3"));
	//	fail("Not yet implemented");
		
	}
	

}
