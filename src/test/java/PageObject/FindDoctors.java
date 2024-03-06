package PageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BaseClass;

public class FindDoctors extends BasePage{
	
	

	public FindDoctors(WebDriver driver) {
		super(driver);
	}
	
	List<String> topDoctorDetails=new ArrayList<String>();
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	
	//Locators
	@FindBy(xpath="(//div[@class='product-tab__title'])[1]")
	WebElement findDoctors;
	
	@FindBy(xpath="//input[@placeholder='Search location']")
	WebElement searchCity_textbox;  
	
	@FindBy(xpath="//i[@class='icon-ic_cross_solid']")
	WebElement searchCityCancle;
	
	@FindBy(xpath="//div[text()='Bangalore']")
	WebElement clickCity;
	
	@FindBy(xpath="//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
	WebElement searchSpeciality;
	
	@FindBy(xpath="//div[normalize-space()='Dentist']")
	WebElement clickDentist;
	
	@FindBy(xpath="(//*[text()='Patient Stories'])[1]")
	WebElement clickPatientStories;
	
	//@FindBy(xpath="//span[normalize-space()='30+ Patient Stories']")
	@FindBy(xpath="(//ul[@class='c-dropdown__list'])[2]")
	List<WebElement>setPatientStories;
	
	@FindBy(xpath="(//span[contains(text(),'Experience')])[1]")
	WebElement clickExperience;
	
	//@FindBy(xpath="//li[@aria-label='10+ Years of experience']")
	@FindBy(xpath="(//ul[@class='c-dropdown__list'])[3]")
	List<WebElement> setExperience;
	
	@FindBy(xpath="//span[contains(text(),'All Filters')]")
	WebElement clickAllFilters;
	
	//@FindBy(xpath="//span[contains(text(),'Above ₹500')]")
	@FindBy(xpath="(//div[@class='pure-u-4-24'])[1]")
	List<WebElement> setFees;
	
	//@FindBy(xpath="//label[@for='Availability2']")
	@FindBy(xpath="(//div[@class='pure-u-4-24'])[2]")
	List<WebElement> setAvailability;
	
	@FindBy(xpath="//span[@class='c-sort-dropdown__selected c-dropdown__selected']")
	WebElement clickSortBy;
	
	@FindBy(xpath="//li[@aria-label='Experience - High to Low']")
	WebElement setSortBy;
	
	@FindBy(xpath="//div[@data-qa-id='doctor_card']//div[@class='info-section']")
	List<WebElement> doctors;
	
	//Actions
	
	
	public boolean verifybutton() {
		return findDoctors.isDisplayed();
	}
	
	public void findDoctors_click() {
		wait.until(ExpectedConditions.visibilityOf(findDoctors));
		findDoctors.click();
	}
	
	public void cityTextbox() throws Exception {
		searchCity_textbox.click();
		searchCity_textbox.clear();
		Thread.sleep(1000);
		searchCity_textbox.sendKeys(BaseClass.p.getProperty("location"));
	}
	
	public void selectCity() {
		clickCity.click();
	}
	
	
	public void professionTextbox() throws InterruptedException {
		searchSpeciality.click();
		Thread.sleep(1000);
		searchSpeciality.sendKeys(BaseClass.p.getProperty("speciality"));
	}
		
	public void selectProfession() throws Exception {
		clickDentist.click();
	}
	
	public void selectPatientStory(){
		clickPatientStories.click();
		Random patientStories=new Random();
		int i=patientStories.nextInt(setPatientStories.size());
		setPatientStories.get(i).click();
		
	}
	
	public void selectDoctorExperience() {
		clickExperience.click();
		Random doctorExperience=new Random();
		int i=doctorExperience.nextInt(setExperience.size());
		setExperience.get(i).click();
	}
	
	public void selectAllFilters() {
		clickAllFilters.click();
	}
	
	public void selectFees() {
		Random doctorFees=new Random();
		int i=doctorFees.nextInt(setFees.size());
		setFees.get(i).click();
	}
	
	public void selectAvailability() {
		Random doctorAvailability=new Random();
		int i=doctorAvailability.nextInt(setAvailability.size());
		setAvailability.get(i).click();
	}
	
	public void clickSortBy() {
		clickSortBy.click();
	}
	
	public void selectSortBy() {
		setSortBy.click();
	}
	
	public List<String> doctorDetails(){
		for(WebElement e:doctors) {
			topDoctorDetails.add(e.getText());
		}
		return topDoctorDetails;
	}

	

}
