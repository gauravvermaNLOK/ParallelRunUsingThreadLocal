package com.gaurav.threadlocalparallelrun;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class BaseTest {

    // Helper method to get the thread-local driver for subclasses
    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    /**
     * Runs before every test method. Initializes a unique driver for the thread.
     * Use @Parameters to read browser value from testng.xml or from command line.
     */
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
    	// 1. Set the driver instance for the current thread
        DriverFactory.setDriver(browser);
        
        // 2. Retrieve the newly set instance for the current thread
        WebDriver driver = DriverFactory.getDriver();
        
        // 3. Print the Thread ID and the Driver's unique hash code
        System.out.printf("[Thread ID: %d] Initializing driver for browser: %s, Driver HashCode: %d%n", 
            Thread.currentThread().getId(), 
            browser,
            driver.hashCode()); // driver.hashCode() returns the object's unique integer hash
    }

    /**
     * Runs after every test method. Cleans up the driver and the ThreadLocal.
     */
    @AfterMethod
    public void tearDown() {
        System.out.printf("[Thread ID: %d] Quitting driver and removing ThreadLocal value.%n", 
            Thread.currentThread().getId());
        DriverFactory.removeDriver();
    }
}