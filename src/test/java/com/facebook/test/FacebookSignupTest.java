package com.facebook.test;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.facebook.base.Base;
import com.facebook.pages.FacebookSignupPages;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.Person.Sex;

public class FacebookSignupTest extends Base{
	//JFairy library is used to generate random test data
	FacebookSignupPages facebookSignupPages;
	Fairy fairy = Fairy.create();
	Person person = fairy.person();
	
	//To perform test set up activities 
	@BeforeMethod
	public void setUp(){
		initialization();
		facebookSignupPages=new FacebookSignupPages();
	}
	
	//Happy path test cases
	//validate page title
	@Test(priority=1)
	public void validatePageTitle() {
		Assert.assertEquals(facebookSignupPages.getPageTitle(), "Facebook – log in or sign up");
	}
	
	//Validate Firstname field
	@Test(priority=2)
	public void validateFirstNameField() {
		String fname=person.getFirstName();
		facebookSignupPages.enterFirstName(fname);
		Assert.assertEquals(facebookSignupPages.readFirstName(), fname);
	}
	
	//Validate Lastname field
	@Test(priority=3)
	public void validateLastNameField() {
		String surname=person.getLastName();
		facebookSignupPages.enterSurName(surname);
		Assert.assertEquals(facebookSignupPages.readSurName(),surname);
	}
	
	//validate email field
	@Test(priority=4)
	public void validateEmailFieldValidation() {
		String email=person.getEmail();
		facebookSignupPages.enterEmail(email);
		Assert.assertEquals(facebookSignupPages.readEmail(), email);
		
	}
	
	//validate confirm email field
	@Test(priority=5)
	public void validateConfirmemailField() {
		String confemail=person.getEmail();
		facebookSignupPages.enterEmail(confemail);
		facebookSignupPages.enterconfEmail(confemail);
		Assert.assertEquals(facebookSignupPages.readconfEmail(),confemail);
		
	}
	
	//validate password field
	@Test(priority=6)
	public void validatePassowordField() {
		String password=person.getPassword();
		facebookSignupPages.enterPassword(password);
		Assert.assertEquals(facebookSignupPages.readPassword(),password);
		
	}
	
	//validate sign up with valid details
	@Test(priority=7)
	public void validateSignUpWithValidDetailsE2E() {
		facebookSignupPages.enterFirstName(person.getFirstName());
		facebookSignupPages.enterSurName(person.getLastName());
		String email=person.getEmail();
		facebookSignupPages.enterEmail(email);
		facebookSignupPages.enterconfEmail(email);
		facebookSignupPages.enterPassword(person.getPassword());
		DateTime dob=person.getDateOfBirth();
		System.out.println(dob);
		facebookSignupPages.selectDay(Integer.toString(dob.getDayOfMonth()));
		facebookSignupPages.selectMonth(Integer.toString(dob.getMonthOfYear()));
		facebookSignupPages.selectYear(Integer.toString(dob.getYear()));
		Sex s=person.getSex();
		System.out.println(s);
		facebookSignupPages.selectGender(s.name());
		facebookSignupPages.clickSignUpButton();
		Assert.assertEquals(facebookSignupPages.getPageTitle(), "Facebook");
	}
	
	//Negative test cases
	//validate sign up buttons functionality when user details are not entered
	@Test(priority=8)
	public void validateclickOnSignUpButtonWhenUserInformationIsBlank() {
		facebookSignupPages.clickSignUpButton();
		Assert.assertEquals(facebookSignupPages.getSurNameFieldBoarderColor(),"#ff0000");
		
	}
	
	//to close browser after the test execution
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
