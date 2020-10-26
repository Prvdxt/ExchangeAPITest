package baseclass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.response.Response;

public class BaseClass {

	public WebDriver initalizeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:/sws/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://ratesapi.io/documentation/");
		return driver;
	}

	public WebElement findElementByXpath(WebDriver driver, String type) {

		WebElement rateAPIURL = driver.findElement(By.xpath(type));
		return rateAPIURL;

	}

	public String getGivenLatestAPI(WebDriver driver, String type) {

		WebElement getURL = findElementByXpath(driver, type);
		String urlText = getURL.getText();
		return urlText;

	}

	public String filterURLText(String urlText) {
		String arr[] = urlText.split(" ");
		return arr[1];

	}

	public String getMethodOfAPI(String urlText) {
		String arr[] = urlText.split(" ");
		return arr[0];
	}

	public String getModifedDateURL(String url, String date) {
		String ar[] = url.split("/");
		String futureDateURLString = url.replace(ar[4], date);
		return futureDateURLString;
	}

	public String getTodayDate() {
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		System.out.println(modifiedDate);
		return modifiedDate;
	}

}
