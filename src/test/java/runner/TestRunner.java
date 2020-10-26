package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/java/features"
        ,glue= "stepdefinition",
        plugin = {"pretty","html:target/cucumber-reports/report.html" },
        monochrome = true
                )

public class TestRunner {


  @AfterClass
    public static void writeExtentReport() {
       Reporter.loadXMLConfig(new File("config/extentReport.xml"));
    
}
}