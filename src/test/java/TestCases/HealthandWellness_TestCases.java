package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.HealthandWellness;
import TestBase.BaseClass;

public class HealthandWellness_TestCases extends BaseClass{
	
	HealthandWellness hw;
	
	@Test(priority=13)
	public void TC_013_navigatingToHealthAndWellness() throws InterruptedException {
		logger.info("*****Starting TC_013_navigatingToHealthAndWellness Test Case*****");
		hw=new HealthandWellness(driver);
		hw.clickForCorporate();
		logger.info("Clicked on For Corporate");
		Thread.sleep(1000);
		hw.clickHealthAndWellness();
		logger.info("Clicked on Health and Wellness.");
		logger.info("*****Test Case TC_013_navigatingToHealthAndWellness Ended*****");
	}
	
	@Test(priority=14)
	public void TC_014_verifyingDirectedtoHealthAndWellnessPase() {
		logger.info("*****Starting TC_014_verifyingDirectedtoHealthAndWellnessPase Test Case*****");
		String title="Employee Health | Corporate Health & Wellness Plans | Practo";
		String currentPageTitle=driver.getTitle();
		logger.info("Getting title of the current page");
		Assert.assertEquals(currentPageTitle, title,"\nInvalid Page is Loaded");
		System.out.println("\nDirected to Valid Page i.e. Health and Wellness");
		logger.info("Title of current page is valid and we are diverted to correct page");
		logger.info("*****Test Case TC_014_verifyingDirectedtoHealthAndWellnessPase Ended*****");
	}
	
	@Test(priority=15)
	public void TC_015_fillformWithInvalidNumber() throws InterruptedException {
		logger.info("*****Starting TC_015_fillformWithInvalidNumber Test Case*****");
		//hw.healthAndWellnessScroll(driver);
		Assert.assertEquals(hw.fillformWithInvalidNumber(), false, "\nSubmit button is enabled even if invalid contact number is filled.");
		System.out.println("Functionality is working fine when form is filled with invalid number.");
		logger.info("The submit button is disabled when the form is filled with invalid contact number.");
		Thread.sleep(1000);
		logger.info("*****Test Case TC_015_fillformWithInvalidNumber Ended*****");
	}
	
	@Test(priority=16)
	public void TC_016_fillingformWithInvalidEmail() throws InterruptedException {
		logger.info("*****Starting TC_016_fillingformWithInvalidEmail Test Case*****");
		hw.clearData();
		logger.info("Previous data of formFillingWithInvalidNumber is cleared.");
		Thread.sleep(1000);
		Assert.assertEquals(hw.fillingformWithInvalidEmail(), false,"\nSubmit button is enabled even if invalid email is entered.");
		System.out.println("Functionality is working fine when form is filled with invalid Email.");
		logger.info("The submit button is disabled when the form is filled with invalid Email-id");
		Thread.sleep(1000);
		logger.info("*****Test Case TC_016_fillingformWithInvalidEmail Ended*****");
	}
	
	@Test(priority=17)
	public void TC_017_fillingFormWithValidInput() throws InterruptedException {
		logger.info("*****Starting TC_017_fillingFormWithValidInput Test Case*****");
		
		hw.clearData();
		logger.info("Previous data of formFillingWithInvalidEmail is cleared.");
		Thread.sleep(1000);
		Assert.assertEquals(hw.fillingFormWithValidDetails(), true,"\nInvalid data is Entered");
		System.out.println("Valid data is entered");
		logger.info("The submit button is enabled when the form is filled with the valid data");
		hw.submit();
		logger.info("Clicked on Submit button");
		logger.info("*****Test Case TC_017_fillingFormWithValidInput Ended*****");
	}
	
	@Test(priority=18)
	public void TC_018_validatingThankYou() throws InterruptedException {
		logger.info("*****Starting TC_018_validatingThankYou Test Case*****");
		//Thread.sleep(60000);
		
		String message=hw.validateThankYou();
		logger.info("Validating the Thank You message");
		Assert.assertEquals(message, "THANK YOU","\nForm not submitted");
		System.out.println("Your form is submitted sucessfully");
		logger.info("Thank You message is validated and the form is submitted Sucessfully");
		logger.info("*****Test Case TC_018_validatingThankYou Ended*****");
	}
}
