package org.example;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.TapOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class SauceLabsAppTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.saucelabs.mydemoapp.android")
                .setNoReset(true)
                .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.MainActivity");

        AndroidDriver driver = new AndroidDriver(new URL("http://192.168.1.161:4723"), options);
//        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement backPack = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Sauce Labs Backpack']"));
            backPack.click();
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Add to cart\"))")).click();
            WebElement cart = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]"));
            cart.click();
            WebElement proceedToCheckout = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt")));
            proceedToCheckout.click();
            WebElement loginLabel = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginTV")));
            Assert.isTrue(loginLabel.isDisplayed(), "Failed to display Login label!");
            System.out.println("Login page is displayed");

        } finally {
            driver.quit();
        }
    }
}

