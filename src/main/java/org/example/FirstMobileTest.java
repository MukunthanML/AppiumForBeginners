package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FirstMobileTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setAppPackage("com.google.android.deskclock")
                .setAppActivity("com.android.deskclock.DeskClock");

        AndroidDriver driver = new AndroidDriver(
                // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                new URL("http://192.168.1.161:4723"), options);
        try {
            WebElement globeElement = driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Cities']"));
            globeElement.click();


            // Take screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot as PNG
            Path destinationPath = Paths.get("clockScreenshot2.png");
            Files.copy(screenshotFile.toPath(), destinationPath);

            System.out.println("Screenshot saved as: " + destinationPath);
            driver.getPageSource();
        } finally {
            driver.quit();
        }
    }
}
