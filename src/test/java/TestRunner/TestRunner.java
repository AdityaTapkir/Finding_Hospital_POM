package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features= {".//FeatureFile//"},
		//features= {".//FeatureFile/A_LaunchingPracto.feature"}
		//features= {".//FeatureFile/A_LaunchingPracto.feature",".//FeatureFile/B_FindDoctors.feature""}
		glue = "StepDefinations", 
		plugin = {"pretty",
				"html:reports/report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		dryRun = false,     //Checks mapping between scenario steps and step defination steps
		monochrome = true	//Avoids the junk characters in the output
		)

public class TestRunner {

}
