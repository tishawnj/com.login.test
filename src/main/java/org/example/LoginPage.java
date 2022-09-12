/*

Author @Tishawn Jones

This class contains the business logic of all the functionality needed to load the properties file,
read the properties file and insert it into the webpage.
It also contains different asserts needed to test whether conditions are true or false.


 */





package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.lang.System;
import java.util.concurrent.TimeUnit;


public class LoginPage {


    public String url = "https://www.hudl.com";
    WebDriver driver;
    public String expectedUrl = "https://www.hudl.com/login";


    String loginEmail;
    String loginPassword;

    String invalidPassword;

    String organizationEmail;

    String message = "This account can't log in with an organization yet. Please log in using your email and password.";



    Properties login = new Properties();

    public void loadFile() throws IOException {

        /*
        Method to load the login.properties file, we need to load this file before we interact with the
        webpage change the filename with the path of where the properties file is located.
         */
        try (FileReader in = new FileReader("/Users/mac/IdeaProjects/com.login.test/login.properties")) {
            System.out.println("Able to load file successfully");
            login.load(in);
        }

    }

    public void navigateToUrl() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "/Users/mac/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("Hudl Application is opened");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loadFile(); //load the file with the properties we want for the data to avoid hard-coding


    }
    public void loginWithOrganization() throws IOException, InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/header/div/a[2]")).click();
        organizationEmail = login.getProperty("organizationemail");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/div[2]/div/form/div/a/button")).click();

        driver.findElement(By.xpath("//*[@id=\'uniId_1\']")).sendKeys(organizationEmail);

        driver.findElement(By.xpath("//*[@id=\'app\']/section/div/div/form/div[1]/button")).click();
    }

    public void logintoApp()  {

        loginEmail = login.getProperty("email");
        loginPassword = login.getProperty("password");

        driver.findElement(By.xpath("/html/body/div[1]/header/div/a[2]")).click();
        //type in the email
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys(loginEmail);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(loginPassword);
        driver.findElement(By.xpath("//*[@id='logIn']")).click();
    }

    public void validateLogin() {

        //assert that we can login
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
        System.out.println("We are logged in");

    }


    public void logininWithincorrectCredentials() throws InterruptedException {

        loginEmail = login.getProperty("email");
        invalidPassword = login.getProperty("invalidpassword");

        driver.findElement(By.xpath("/html/body/div[1]/header/div/a[2]")).click();
        //type in the email
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys(loginEmail);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(invalidPassword);
        driver.findElement(By.xpath("//*[@id='logIn']")).click();


    }

    public void invalidLogin() {
        Assert.assertNotEquals(expectedUrl, url);
        System.out.println("Invalid credentials, Login Unsuccessful!");
    }

    public void promptoLoginwithEmailAndPassword()
    {

        //assert that we are back on the login screen with the message that user must enter their login credentials again
        String expectedMessage = driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div/form/div/div[3]/div/p")).getText();
        Assert.assertEquals(message,expectedMessage );

    }

    public void closeBrowser() {
        //close the browser
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
        System.out.println("Browser has been closed");
    }

}
