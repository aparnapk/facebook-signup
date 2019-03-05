package com.facebook.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.facebook.base.Base;

public class FacebookSignupPages extends Base{
	//Page WebElements
	@FindBy(name="firstname")
	WebElement firstName;
	
	@FindBy(name="lastname")
	WebElement surName;

	@FindBy(name="reg_email__")
	WebElement eMail;
	
	@FindBy(name="reg_email_confirmation__")
	WebElement  confirmeMail;
	
	@FindBy(name="reg_passwd__")
	WebElement password;

	@FindBy(id="day")
	WebElement day;
	
	@FindBy(id="month")
	WebElement month;
	
	@FindBy(id="year")
	WebElement year;
	
	@FindBy(name="websubmit")
	WebElement signUpBtn;
	
	//Page Factory initialization
	public FacebookSignupPages(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void enterFirstName(String fName) {
		firstName.sendKeys(fName);
	}
	
	public String readFirstName() {
		return firstName.getAttribute("value");
	}
	
	public void enterSurName(String LName) {
		surName.sendKeys(LName);
	}
	
	public String readSurName() {
		return surName.getAttribute("value");
	}
	
	public void enterEmail(String emal) {
		eMail.sendKeys(emal);
	}
	
	public String readEmail() {
		return eMail.getAttribute("value");
	}
	
	public void enterconfEmail(String confemal) {
		confirmeMail.sendKeys(confemal);
	}
	
	public String readconfEmail() {
		return confirmeMail.getAttribute("value");
	}
	
	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public String readPassword() {
		return password.getAttribute("value");
	}
	
	public void selectGender(String sex) {
		List<WebElement> gender = driver.findElements(By.name("sex"));
		if(sex.equals("FEMALE")) {
			gender.get(0).click();
		}
		else {
			gender.get(1).click();
		}
	}
	
	public void selectDay(String  dayOfmonth) {
		selectValueFromDropDown(day,dayOfmonth);
	}
	
	public void selectMonth(String  monthOfYear) {
		selectValueFromDropDown(month,monthOfYear);
	}
	
	public void selectYear(String  yr) {
		selectValueFromDropDown(year,yr);
	}
	
	public void selectValueFromDropDown(WebElement ele,String sel) {
		Select select =new Select(ele);
		select.selectByValue(sel);
	}
	
	public void clickSignUpButton() {
		 signUpBtn.click();
	}
	public String  getFirstNameFieldBoarderColor() {
		return(validateFieldBackgroundColor(firstName));
	}
	
	public String  getSurNameFieldBoarderColor() {
		return(validateFieldBackgroundColor(surName));
	}
	public String validateFieldBackgroundColor(WebElement ele) {
		String color=ele.getCssValue("border-color");
		String hex = Color.fromString(color).asHex();
		return hex;
	}
		
}
