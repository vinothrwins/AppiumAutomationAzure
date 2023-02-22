package com.nxtgenai.testng;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

public class APIDemo {

	public URL url;
	public DesiredCapabilities capabilities;
	public AndroidDriver driver;

	@Test(priority = 1)
	public void launchApplication() throws MalformedURLException {

		url = new URL("http://127.0.0.1:4723/wd/hub/");

		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Andriod");
		capabilities.setCapability("platformVersion", "12.0"); // optional 
		capabilities.setCapability("deviceName","Vinoth"); // optional
		capabilities.setCapability("udid","1398796045001TA"); 
		capabilities.setCapability("appPackage","io.appium.android.apis");
		capabilities.setCapability("appActivity","io.appium.android.apis.ApiDemos");
		// Below code will skip the permission access issue 
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");

		driver = new AndroidDriver(url,capabilities);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 2)
	public void handlingRadioButton() throws InterruptedException {

		driver.findElement(AppiumBy.androidUIAutomator("UiSelector().text(\"Views\")")).click();		
		System.out.println("Views is clicked");

		// scroll and select Radio Group
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"Radio Group\"))"))
		.click();
		System.out.println("Radio Group is selected");

		// select Breakfast
		WebElement breakfastRadioBtn = driver.findElement(AppiumBy.accessibilityId("Breakfast"));
		breakfastRadioBtn.click();
		System.out.println("Breakfast Radio Button is selected");

		Thread.sleep(3000);

		// press back key
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	@Test(priority = 3)
	public void handlingCheckBox() throws InterruptedException {

		// select System UI Visibility
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"System UI Visibility\"))"))
		.click();
		System.out.println("System UI Visibility is selected");

		// select system UI Modes
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"System UI Modes\"))"))
		.click();
		System.out.println("System UI Modes is selected");

		// selecting LOW_PROFILE checkbox 
		WebElement lowprofileCheckBox = driver.findElement(By.
				xpath("//android.widget.CheckBox[@content-desc=\"LOW_PROFILE\"]"));
		lowprofileCheckBox.click();
		System.out.println("LOW_PROFILE checkbox is selected");

		// selecting FULLSCREEN checkbox 
		WebElement fullscreenCheckBox = driver.findElement(By.
				xpath("(//android.widget.CheckBox[@content-desc=\"FULLSCREEN\"])[2]"));
		fullscreenCheckBox.click();
		System.out.println("FULLSCREEN checkbox is selected");

		// selecting action mode checkbox 
		WebElement actionModeCheckBox = driver.findElement(By.
				xpath("//android.widget.CheckBox[@content-desc=\"Action Mode\"]"));
		actionModeCheckBox.click();
		System.out.println("Action Mode checkbox is selected");		

		Thread.sleep(3000);

		// press back key
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	@Test(priority = 4)
	public void closeApplication() {
		driver.quit();
	}

}
