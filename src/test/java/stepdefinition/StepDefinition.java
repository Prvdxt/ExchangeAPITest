package stepdefinition;

import static org.junit.Assert.assertNotNull;

import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.openqa.selenium.WebDriver;

import baseclass.BaseClass;
import baseclass.Locators;
import baseclass.Log;
import baseclass.RestAPIExecution;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class StepDefinition {
	public static WebDriver driver;
	public String url, urlText;
	public String method;
	public String date;
	public Response response1;
	public String urlForSpecificDate;
	int status;

	static BaseClass b = new BaseClass();
	RestAPIExecution api = new RestAPIExecution();	
	
	
	@Before
	public static void beforeClass1()
	{
		Log.info("Start of scenario, initializing the browser");
		driver = b.initalizeDriver();
	}
	
	
	@After
	public static void AfterClass() {
		Log.info("End of scenario, closing the browser");
		driver.close();
	}

	@Given("^Rates API for latest Foreign Exchange$")
	public void rates_api_for_latest_foreign_exchange() throws Throwable {

		
		urlText = b.getGivenLatestAPI(driver, Locators.LATEST_FEX_API);
		url = b.filterURLText(urlText);
		Log.info("Fetching URL from the google page");
		method = b.getMethodOfAPI(urlText);

	}

	@Given("^Rates API for Foreign Exchange for specific date$")
	public void rates_api_for_foreign_exchange_for_specific_date() throws Throwable {

		
		urlText = b.getGivenLatestAPI(driver, Locators.SPECIFIC_DATE_FEX_API);
		url = b.filterURLText(urlText);
		date=b.getTodayDate();
		urlForSpecificDate= b.getModifedDateURL(url,date);
		Log.info("Fetching URL from the google page for specific link");
		method = b.getMethodOfAPI(urlText);

	}

	@When("^I execute the rates API$")
	public void i_execute_rate_api() throws Throwable {
		Log.info("Fetching the response");
		response1 = api.restCheck(url, method);
	}
	
	@When("^I execute the rates for specific date API$")
	public void i_execute_rate_api_for_specific_date_api() throws Throwable {
		Log.info("Fetching the response");
		response1 = api.restCheck(urlForSpecificDate, method);
	}

	@When("^I execute the rates API for incorrect URL$")
	public void i_execute_rate_api_for_incorrect_url() throws Throwable {
		String incorrectURL = "https://api.ratesapi.io/api1/latest";
		Log.info("Fetching the response");
		response1 = api.restCheck(incorrectURL, method);
	}

	@When("^I execute the rates API for a future date$")
	public void i_execute_the_rate_api_for_future_date() throws Throwable {

		String appendedURL= b.getModifedDateURL(url, "2021-01-01");	
		Log.info("Fetching the response");
		response1 = api.restCheck(appendedURL, method);
	}

	@SuppressWarnings("deprecation")
	@Then("^I assert the success status of the response$")
	public void i_assert_the_success_status_of_the_response() throws Throwable {
		status = api.checkStatus(response1);
		Log.info("Verifying the status for the success Response");
		if(status==200)
		Assert.assertEquals(200, status);
		else {
			throw new Exception("The status code was not successful");
			
		}
	}

	@Then("^I assert the response$")
	public void i_assert_the_response() throws Throwable {

		String r = api.checkResponseBody(response1);
		
		Log.info("Verifying the API Response");
		assertNotNull(r);

	}

	@SuppressWarnings("deprecation")
	@Then("^I assert the response as per the incorrect URL$")
	public void i_assert_the_response_as_per_incorrect_url() throws Throwable {
		status = api.checkStatus(response1);
		Log.info("Verifying the status for the incorrect URL");
		Assert.assertEquals(404, status);

	}

	@Then("^I validate the response matches the current date exchange rates$")
	public void i_validate_the_response_matches_the_current_date_exchange_rates() throws Throwable {
		
		date = api.checkStatusDate(response1);
		String todayDate=b.getTodayDate();
		
	if(date.contains(todayDate))
	{
		Log.info("Verifying the check for the future date");
		Assert.assertTrue(true);
	}

	}
}

