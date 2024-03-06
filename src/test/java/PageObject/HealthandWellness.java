package PageObject;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthandWellness extends BasePage{
	 
	public HealthandWellness(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	
	//Locators
	@FindBy(xpath="(//span[@class='nav-interact'])[1]")
	WebElement forCorporates;
	
	@FindBy(xpath="(//div[@class='u-d-item'])[1]")
	WebElement healthAndWellness;
	
	@FindBy(xpath="//h1[@class='ui header title']")
	WebElement headscroll;
	
	//@FindBy(xpath="//input[@id='name']")
	@FindBy(id="name")
	WebElement name;
	
	//@FindBy(xpath="//input[@id='organizationName']")
	@FindBy(id="organizationName")
	WebElement organizationName;
	
	//@FindBy(xpath="//input[@id='contactNumber']")
	@FindBy(id="contactNumber")
	WebElement contactNumber;
	
	//@FindBy(xpath="//input[@id='officialEmailId']")
	@FindBy(id="officialEmailId")
	WebElement emailId;
	
	//@FindBy(xpath="//select[@id='organizationSize']")
	@FindBy(id="organizationSize")
	WebElement organizationSize;
	
	//@FindBy(xpath="//select[@id='interestedIn']")
	@FindBy(id="interestedIn")
	WebElement intrestedIn;
	
	@FindBy(xpath="//button[contains(text(),'Schedule a demo')]")
	WebElement submitButton;
	
	@FindBy(xpath="//div[@class='u-text--bold text-alpha']")
	WebElement thankYou;
	
	//Actions
	public void clickForCorporate() {
		forCorporates.click();
	}
	
	public void clickHealthAndWellness() {
		healthAndWellness.click();
	}
	
	public void healthAndWellnessScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",headscroll);
	}
	
	public void clearData() {
		name.clear();
		organizationName.clear();
		contactNumber.clear();
		emailId.clear();
	}
	
	public boolean  fillformWithInvalidNumber() {
		name.sendKeys(RandomStringUtils.random(6,true,false));
		organizationName.sendKeys(RandomStringUtils.random(10,true,false));
		contactNumber.sendKeys("12345678912");
		emailId.sendKeys(RandomStringUtils.random(5,true,false)+"@gmail.com");
		Select orgSize = new Select(organizationSize);
		orgSize.selectByValue("<=500");
		Select interested = new Select(intrestedIn);
		interested.selectByValue("Taking a demo");
		
		return submitButton.isEnabled();
	}
	
	public boolean fillingformWithInvalidEmail() {
		
		name.sendKeys(RandomStringUtils.random(6,true,false));
		organizationName.sendKeys(RandomStringUtils.random(10,true,false));
		contactNumber.sendKeys("6785435678");
		emailId.sendKeys(RandomStringUtils.random(5,true,false));
		Select orgSize = new Select(organizationSize);
		orgSize.selectByValue("<=500");
		Select interested = new Select(intrestedIn);
		interested.selectByValue("Taking a demo");
		
		return submitButton.isEnabled();
	}
	
	public boolean fillingFormWithValidDetails() {
		name.sendKeys("Aditya");
		organizationName.sendKeys("AdityaOrg");
		contactNumber.sendKeys("9632587466");
		emailId.sendKeys("adi123@gmail.com");
		
		organizationSize.click();
		Select organization_size_dropdown = new Select(organizationSize);
		organization_size_dropdown.selectByVisibleText("501-1000");
		
		intrestedIn.click();
		Select intrested_in_dropdown = new Select(intrestedIn);
		intrested_in_dropdown.selectByVisibleText("Taking a demo");
		
		return submitButton.isEnabled();
	}

	public void submit() {
		submitButton.click();
	}
	
	public String validateThankYou() {
		wait.until(ExpectedConditions.visibilityOf(thankYou));
		return thankYou.getText();
	}
}
