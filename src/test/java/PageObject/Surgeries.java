package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Surgeries extends BasePage{
	 
	public Surgeries(WebDriver driver) {
		super(driver);
	}
	List<String> allSurgeries=new ArrayList<String>();
 
	
	//Locators
	@FindBy(xpath="(//div[text()='Surgeries'])[1]")
	WebElement clickSurgery;
	
	@FindBy(xpath="//h1[text()='Popular Surgeries']")
	WebElement popularSurgeryScroll;
	
	@FindBy(xpath="//p[@class='mt-12px AilmentItem-module_itemText__XvCHL']")
	List<WebElement> allSurgery;
	
	
	//Actions
	public boolean validateSurgeryButton() {
		return clickSurgery.isEnabled();
	}
	
	public void surgery() {
		clickSurgery.click();
	}
	
	public void surgeryScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",popularSurgeryScroll);
	}
	
	public List<String> allPossibleSurgeries() {
		for(WebElement e: allSurgery) {
			allSurgeries.add(e.getText());
		}
		return allSurgeries;
	}
	
	public void scrollUpSurgery(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",clickSurgery);
	}
}