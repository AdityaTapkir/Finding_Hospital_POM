package TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.FindDoctors;
import TestBase.BaseClass;
import Utilities.ExcelUtilis;


public class FindDoctors_TestCases extends BaseClass{
	
	FindDoctors fd;
	
	List<String> detailsDoctor=new ArrayList<String>(); 
	
	List<String> doctorName = new ArrayList<String>();
	List<String> doctorSpecialization = new ArrayList<String>();
	List<String> doctorExperience = new ArrayList<String>();
	List<String> doctorLocation = new ArrayList<String>();
	List<String> doctorFee = new ArrayList<String>();
	
	@Test(priority=1)
	public void TC_001_findDoctors() throws Exception {
		logger.info("*****Starting TC_001_findDoctors Test Case*****");
		fd=new FindDoctors(driver);
		String URL="https://www.practo.com/";
		String CurrentURL=driver.getCurrentUrl();
		Assert.assertEquals(URL,CurrentURL,"The link is invalid");
		System.out.println("Valid URL is loaded ,URL is verified");
		logger.info("URL is varified");
		Assert.assertEquals(fd.verifybutton(), true,"Find Doctor button is not displayed");
		logger.info("Verifying Find Doctors button has been displayed");
		fd.findDoctors_click();
		logger.info("Clicked on Find Doctors");
		logger.info("*****Test Case TC_001_findDoctors Ended*****");
	}
	
	@Test(priority=2)
	public void TC_002_selectLocation() throws Exception {
		logger.info("*****Starting TC_002_selectLocation Test Case*****");
		fd.cityTextbox();
		Thread.sleep(1000);
		fd.selectCity();
		logger.info("Selected the City in city textbox");
		logger.info("*****Test Case TC_002_selectLocation Ended*****");
	}
	
	@Test(priority=3)
	public void TC_003_selectProfession() throws Exception {
		logger.info("*****Starting TC_003_selectProfession Test Case*****");
		fd.professionTextbox();
		logger.info("Clicked on Profession Textbox");
		Thread.sleep(1000);
		fd.selectProfession();
		logger.info("Profession is selected");
		logger.info("*****Test Case TC_003_selectProfession Ended*****");
	}
	
	@Test(priority=4)
	public void TC_004_selectStory() throws InterruptedException {
		logger.info("*****Starting TC_004_selectStory Test Case*****");
		Thread.sleep(3000);
		fd.selectPatientStory();
		logger.info("Patient story is selected");
		logger.info("*****Test Case TC_004_selectStory Ended*****");
	}
	
	@Test(priority=5)
	public void TC_005_doctorExperience() throws Exception {
		logger.info("*****Starting TC_005_doctorExperience Test Case*****");
		Thread.sleep(5000);
		fd.selectDoctorExperience();
		logger.info("Patient story is selected");
		logger.info("*****Test Case TC_005_doctorExperience Ended*****");
	}
	
	@Test(priority=6)
	public void TC_006_selectingDoctorFees() throws InterruptedException {
		logger.info("*****Starting TC_006_selectingDoctorFees Test Case*****");
		Thread.sleep(5000);
		fd.selectAllFilters();
		logger.info("Clicked on all filters dropdown");
		fd.selectFees();
		logger.info("Doctor Fees is selected");
		logger.info("*****Test Case TC_006_selectingDoctorFees Ended*****");
	}
	
	@Test(priority=7)
	public void TC_007_selectingDoctorsAvailability() throws InterruptedException {
		logger.info("*****Starting TC_007_selectingDoctorsAvailability Test Case*****");
		Thread.sleep(5000);
		fd.selectAllFilters();
		logger.info("Clicked on all filters dropdown");
		fd.selectAvailability();
		logger.info("Doctors Availability is Selected");
		logger.info("*****Test Case TC_007_selectingDoctorsAvailability Ended*****");
	}
	
	@Test(priority=8)
	public void TC_008_selectingSortBy() throws Exception {
		logger.info("*****Starting TC_008_selectingSortBy Test Case*****");
		Thread.sleep(5000);
		fd.clickSortBy();
		logger.info("Clicked on SortBy dropdown");
		fd.selectSortBy();
		logger.info("Sort By value is Selected");
		logger.info("*****Test Case TC_008_selectingSortBy Ended*****");
	}
	
	@Test(priority=9)
	public  void TC_009_topFiveDoctorDetails() throws InterruptedException, IOException {
		logger.info("*****Starting TC_009_topFiveDoctorDetails Test Case*****");
		Thread.sleep(3000);
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
		
		logger.info("*****Test Case TC_009_topFiveDoctorDetails Ended*****");
	}
}
