package com.gaurav.parallelrunusingguice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GoogleSearchTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() {
    	 WebDriver driver = getDriver();
        driver.get("https://www.yahoo.com/");
        String title = driver.getTitle();
        System.out.printf("[Thread ID: %d] Test 1: Navigated to Yahoo, Driver HashCode: %d%n", 
           Thread.currentThread().getId(), driver.hashCode());
        Assert.assertTrue(title.contains("Yahoo"), "Yahoo title assertion failed.");
    }

    @Test
    public void performSearch() {
        WebDriver driver = getDriver();
    	driver.get("https://www.google.com/");
        // Simulate a search action
    	WebElement searchTextBox = driver.findElement(By.name("q"));
        searchTextBox.sendKeys("Gaurav Verma Medium blog Guice");
        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        System.out.printf("[Thread ID: %d] Test 2: Performing search on Google, Driver HashCode: %d%n", 
            Thread.currentThread().getId(), driver.hashCode());
        // Simple assertion to show the test ran
        Assert.assertTrue(driver.getCurrentUrl().contains("google"), "URL check failed.");
    }
}