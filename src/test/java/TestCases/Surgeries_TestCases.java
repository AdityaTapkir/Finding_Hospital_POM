package TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Surgeries;
import TestBase.BaseClass;
import Utilities.ExcelUtilis;

public class Surgeries_TestCases extends BaseClass{

	Surgeries s;
	
	List<String> allSurgeries=new ArrayList<String>();
	
	@Test(priority=10)
	public void TC_010_clickSurgery() {
		logger.info("*****Starting TC_010_clickSurgery Test Case*****");
		s=new Surgeries(driver);
		boolean btn=s.validateSurgeryButton();
		logger.info("Validating Surgery Button wether it is enabled or not");
		Assert.assertEquals(btn, true,"\nSurgery Button is not enabled");
		System.out.println("\nSurgery button is Enabled");
		logger.info("Surgery Button is Validated and is Enabled");
		s.surgery();
		logger.info("Clicked on Suirgery Button");
		logger.info("*****Test Case TC_010_clickSurgery Ended*****");
	}
	
	@Test(priority=11)
	public void TC_011_veryfingDirectedtoSurgeryPage() {
		logger.info("*****Starting TC_011_veryfingDirectedtoSurgeryPage Test Case*****");
		String title="Practo Care Surgeries | End to end care from top surgeons in your city";
		String currentPageTitle=driver.getTitle();
		logger.info("Getting the current title of the page");
		Assert.assertEquals(currentPageTitle, title,"\nInvalid Page is loaded");
		System.out.println("\nDirected to valid page ,i.e. Surgery\n");
		logger.info("Current title of the page is validated and we have directed to valid page.");
		s.surgeryScroll(driver);
		logger.info("Scrolling down to the surgery by using Javascript executer");
		logger.info("*****Test Case TC_011_veryfingDirectedtoSurgeryPage Ended*****");
	}
	
	@Test(priority=12)
	public void TC_012_allPossibleSurgery() throws InterruptedException, IOException {
		logger.info("*****Starting TC_012_allPossibleSurgery Test Case*****");
		Thread.sleep(2000);
		allSurgeries=s.allPossibleSurgeries();
		logger.info("Copying all possible surgeries from one list to another.");
		System.out.println("List of all Possible Surgeries :\n");
		int rowNo=0;
		for(String s1:allSurgeries) {
			System.out.println(s1);
			
			ExcelUtilis.write("All Possible Surgery List", 0, 0, "Surgeries");
			ExcelUtilis.write("All Possible Surgery List", rowNo+1, 0, s1);
			rowNo++;
		}
		logger.info("Printing the list of all possible surgeries.");
		s.scrollUpSurgery(driver);
		logger.info("Scrolling up till for corporate button");
		logger.info("*****Test Case TC_012_allPossibleSurgery Ended*****");
	}
}
