package com.gaurav.parallelrunusingguice;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // 1. Declare the ThreadLocal variable
    private static final ThreadLocal<WebDriver> driverContext = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverFactory() {} 

    /**
     * Creates and sets a new WebDriver instance for the current thread.
     */
    public static void setDriver(String browser) {
        WebDriver driver;
        
        // Simplified driver initialization logic
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            // Add other browser setup logic here
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        driver.manage().window().maximize();
        // 2. Store the unique WebDriver instance in the ThreadLocal
        driverContext.set(driver); 
    }

    /**
     * Gets the WebDriver instance specific to the current thread.
     */
    public static WebDriver getDriver() {
        // 3. Retrieve the thread's unique WebDriver instance
        return driverContext.get(); 
    }

    /**
     * Closes the browser and removes the ThreadLocal value. CRITICAL step.
     */
    public static void removeDriver() {
        if (driverContext.get() != null) {
            driverContext.get().quit();
            // 4. Remove the thread's copy to prevent memory leaks in the thread pool
            driverContext.remove();
        }
    }
}
