package com.gaurav.parallelrunusingguice;

import org.testng.Assert;
import org.testng.annotations.Test;


public class BingSearchTest extends BaseTest {

    @Test
    public void verifyBingTitle() {
        getDriver().get("https://www.bing.com/");
        String title = getDriver().getTitle();
        System.out.printf("[Thread ID: %d] Test 3: Navigated to Bing. Title: %s%n", 
            Thread.currentThread().getId(), title);
        Assert.assertTrue(title.contains("Bing"), "Bing title assertion failed.");
    }

    @Test
    public void checkBingHomePage() {
        getDriver().get("https://www.bing.com/");
        System.out.printf("[Thread ID: %d] Test 4: Checking Bing home page.%n", 
            Thread.currentThread().getId());
        // Simple assertion to show the test ran
        Assert.assertTrue(getDriver().getCurrentUrl().contains("bing"), "URL check failed.");
    }
}