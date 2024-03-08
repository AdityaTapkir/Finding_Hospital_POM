package StepDefinations;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObject.FindDoctors;
import PageObject.HealthandWellness;
import PageObject.Surgeries;
import TestBase.BaseClass;
import Utilities.ExcelUtilis;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PractoStepDefination extends BaseClass{              
	
	public  static WebDriver driver;
	public static Logger logger;
	public Properties p;
	
	FindDoctors fd;
	Surgeries s;
	HealthandWellness hw;
	
	public String appURL;
	
	List<String> detailsDoctor=new ArrayList<String>(); 
	
	List<String> doctorName = new ArrayList<String>();
	List<String> doctorSpecialization = new ArrayList<String>();
	List<String> doctorExperience = new ArrayList<String>();
	List<String> doctorLocation = new ArrayList<String>();
	List<String> doctorFee = new ArrayList<String>();
	
	List<String> allSurgeries=new ArrayList<String>();
	
	@Given("User do the basic Setup")
	public void User_do_the_basic_Setup() throws IOException {
		logger = LogManager.getLogger(this.getClass());
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		driver =setupDriver("windows","chrome");	
	}
	@When("User get Url from the Properties file")
	public void User_get_Url_from_the_Properties_file() {
		appURL = p.getProperty("appUrl");
		logger.info("Captured the current Url");
	}
	@Then("Verify the Url")
	public void Verify_the_Url() throws InterruptedException {
		fd = new FindDoctors(driver);
		Thread.sleep(1000);
		String url="https://www.practo.com/";
		String currentURL=driver.getCurrentUrl();
		Assert.assertEquals(url,currentURL,"The link is invalid");
		System.out.println("Valid URL is loaded,URL is verified");
		logger.info("The Practo link has been verified");
	}
	
	@Given("User is on practo page")
	public void User_is_on_practo_page() {
		System.out.println("User is on practo page");
		logger.info("User is on practo page");
	}
	@When("User check if the find doctors is displayed")
	public void User_check_if_the_find_doctors_is_displayed() {
		fd = new FindDoctors(driver);
		Assert.assertEquals(fd.verifybutton(), true,"Find Doctor button is not displayed");
		logger.info(" Verifying Find Doctors button has been displayed ");
	}
	@Then("if displayed click on find doctors")
	public void if_displayed_click_on_find_doctors() {
		fd.findDoctors_click();
		logger.info("Clicked on Find Doctors button ");
		System.out.println("Clicked on find doctors button");
	}
	
	@Given("User navigate to find Doctors page")
	public void User_navigate_to_find_Doctors_page() throws InterruptedException {
		System.out.println("User is on find doctors page");
		logger.info("User navigated to find doctor page");
	}
	@When("User search for city and select city")
	public void User_search_for_city_and_select_city() throws Exception {
		fd = new FindDoctors(driver);
		fd.cityTextbox();
		logger.info("User clicked on city textbox and selected the city");
	}
	@And("User search for profession")
	public void User_search_for_speciality() throws InterruptedException {
		fd.professionTextbox();
		logger.info("User clicked on profession textbox");
		
	}
	@Then("User click on profession")
	public void User_click_on_speciality() throws Exception {
		Thread.sleep(2000);
		fd.selectProfession();
		logger.info("User clicked on dentist");
		System.out.println("User selected the profession");
		Thread.sleep(5000);
	}
	
	@Given("User select Patient Stories from dropdown")
	public void user_select_patient_story_from_dropdown() throws InterruptedException {
		fd= new FindDoctors(driver);
		fd.selectPatientStory();
		Thread.sleep(1000);
		logger.info(" Patient story has been select from dropdown");
		Thread.sleep(5000);
	}
 
	@When("User select Experience from dropdown")
	public void user_select_experience_from_dropdown() throws InterruptedException {
		fd.selectDoctorExperience();
		Thread.sleep(1000);
		logger.info(" Doctor experience has been select from dropdown");
		Thread.sleep(5000);
	}
 
	@When("User select Fees and Availability from All Filters")
	public void user_select_fees_and_availability() throws InterruptedException {
		fd.selectAllFilters();
		fd.selectFees();
		Thread.sleep(5000);
		logger.info(" Fees has been select from dropdown");
		fd.selectAllFilters();
		fd.selectAvailability();
		Thread.sleep(5000);
		logger.info(" Availability has been select from dropdown");
	}
 
	@Then("User select Sort By from the dropdown")
	public void user_select_relevance_from_the_dropdown() throws InterruptedException {
		fd.clickSortBy();
		fd.selectSortBy();
		Thread.sleep(5000);
		logger.info(" Sort By has been select from dropdown");
	}
	@And("User display the doctors details on console and store it in excel sheet")
	public void User_display_the_doctors_details() throws InterruptedException, IOException {
		
		System.out.println("List of top doctors :\n");
		detailsDoctor=fd.doctorDetails();
		
		if(detailsDoctor.size()>0) {
			System.out.println("List of top doctors :\n");
			for(int i=0;i<5;i++) {
				System.out.println(detailsDoctor.get(i));
				System.out.println("");
			}
			logger.info("Details of top 5 doctors displayed on the console");
		}
		else {
			System.out.println("Doctor details not available for printing");
		}
		
			
		if(detailsDoctor.size()>0) {
			for (int i = 0; i <5; i++) {
				
				ExcelUtilis.write("Doctor Details", 0, 0, "Doctor Name");
				ExcelUtilis.write("Doctor Details", 0, 1, "Doctor Specilization");
				ExcelUtilis.write("Doctor Details", 0, 2, "Doctor Experience");
				ExcelUtilis.write("Doctor Details", 0, 3, "Doctor Location");
				ExcelUtilis.write("Doctor Details", 0, 4, "Doctor Fees");
				
				doctorName.add(detailsDoctor.get(i).split("\\n+")[0]);
				doctorSpecialization.add(detailsDoctor.get(i).split("\\n+")[1]);
				doctorExperience.add(detailsDoctor.get(i).split("\\n+")[2]);
				doctorLocation.add(detailsDoctor.get(i).split("\\n+")[3]);
				doctorFee.add(detailsDoctor.get(i).split("\\n+")[4]);
				
				ExcelUtilis.write("Doctor Details", i+1, 0, doctorName.get(i));
				ExcelUtilis.write("Doctor Details", i+1, 1, doctorSpecialization.get(i));
				ExcelUtilis.write("Doctor Details", i+1, 2, doctorExperience.get(i));
				ExcelUtilis.write("Doctor Details", i+1, 3, doctorLocation.get(i));
				ExcelUtilis.write("Doctor Details", i+1, 4, doctorFee.get(i));		
			}
			logger.info("Printed the details of top doctors in excel");
		}
		else {
			ExcelUtilis.write("Doctor Details", 0, 0, "No doctor details available for printing");
			logger.info("Doctor details not available for printing and storing in excel");
		}
	}
	
	@Given("User naviagte to Surgeries button")
	public void user_naviagte_through_surgeries() {
		s = new Surgeries(driver);
		s.surgery();
		logger.info("Clicked on Surgery button");
	}
 
	@When("User scroll down till Popular Surgeries")
	public void user_scroll_down_till_surgeries() {
		s.surgeryScroll(driver);
		logger.info("Scrolldown till Popular surgeries");
	}
 
	@Then("User fetch all the popular surgeries")
	public void user_fetch_all_the_popular_surgeries() throws IOException {
		allSurgeries = s.allPossibleSurgeries();
		logger.info("Copying all possible surgeries from one list to another.");
		System.out.println("List of all Possible Surgeries :\n");
		int rowNo=0;
		for(String s1:allSurgeries) {
			System.out.println(s1);
			
			ExcelUtilis.write("All Possible Surgery List", 0, 0, "Surgeries");
			ExcelUtilis.write("All Possible Surgery List", rowNo+1, 0, s1);
			rowNo++;
		}
		logger.info("Total surgeries displayed on console");
		logger.info("List of All Possible surgeries stored in the excel sheet");
		
	}
	
	@And("User scroll up till For Corporate button")
	public void scroll_up_till_corporate() {
		s.scrollUpSurgery(driver);
		System.out.println("User scrolled up till For Corporate button");
		logger.info("Scrolling up till for corporate button");
	}
	
	@Given("User click on For Corporate button")
	public void user_click_on_for_corporate_button() {
		hw = new HealthandWellness(driver);
		hw.clickForCorporate();
		logger.info("Clicked on forCorporate button");
	}
 
	@When("User click on Health and Wellness button")
	public void click_on_health_and_wellness_button() {
		hw.clickHealthAndWellness();
		hw.healthAndWellnessScroll(driver);
		logger.info("Clicked on Health and Wellness button ");
	}
 
	@When("User verify schedule button with Invalid Contact Number")
	public void user_verify_schedule_button_with_invalid_contact_number() {
		Assert.assertEquals(hw.fillformWithInvalidNumber(),false,"The button is Enabled but the contact number is invalid");
		System.out.println("The button is disabled when the contact number is invalid");
		logger.info("The button is disabled when the contact number is invalid");
	}
 
	@When("verify schedule button with with invalid Email Id")
	public void verify_schedule_button_with_with_invalid_email_id() throws InterruptedException {
		hw.clearData();
		logger.info("The data of the form is cleared");
		Thread.sleep(2000);
		Assert.assertEquals(hw.fillingformWithInvalidEmail(),false,"The button is Enabled but the email is invalid");
		System.out.println("The button is disabled when the Email Id is invalid");
		logger.info("The button is disabled when the email Id is invalid");
	}
 
	@When("verify schedule button with with Valid Information")
	public void verify_schedule_button_with_with_valid_information() throws InterruptedException {
		hw.clearData();
		logger.info("The data of the form is cleared");
		Thread.sleep(2000);
		Assert.assertEquals(hw.fillingFormWithValidDetails(),true,"The information provided is not valid");
		System.out.println("The button is enabled when the valid data is entered");
		logger.info("The button is enabled  when entered the valid information");
		hw.submit();
		System.out.println("Clicked on submit button");
		logger.info("Clicked on Submitt button");
	}
 
	@Then("verify Thankyou message is displayed or not")
	public void verify_thankyou_message_is_displayed_or_not() throws InterruptedException {
		Assert.assertEquals(hw.validateThankYou().equalsIgnoreCase("THANK YOU"),true,"Displayed message is not valid");
		logger.info("Verified the ThankYou message displayed");
	}
	@And("User tear down the complete process")
	public void teardown() {
		driver.quit();
		System.out.println("Tear down Sucessfull");
		logger.info("Tear down Sucessfull");
	}
}

