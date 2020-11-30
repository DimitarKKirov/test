package Selenium.pageObjects.pageObjectLillyShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Selenium.pManagers.shopLilly.LillyRegularsElements;

/**
 * this class represents the Login page of Lilly web shop site
 * class with predefined web elements with actions
 * and method set for the current test purposes
 */
public class LillyLoginPage extends LillyRegularsElements {



    /**
     * using the local driver the method is locating
     * the email field and types the passed String variable
     * @param email passed string variable that is used
     *              for login to the web site,
     *              it holds the user Email
     */
    public void emailField(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    /**
     * using the local driver the method is locating
     * the password field and types the passed String variable
     * @param pass passed string variable that is used
     *             for login to the web site,
     *             it holds the user Password
     */
    public void passField(String pass) {
        driver.findElement(By.id("pass")).sendKeys(pass);

    }

    /**
     * using the local driver the method is locating
     * and clicking the login button on the page
     */
    public void clickLogin() {
        driver.findElement(By.id("send2")).click();
    }

    /**
     * using the remote driver that is set to connect and
     * use the docker standalone chrome/firefox container
     * is locating the email field and types the passed String variable
     * @param email passed string variable that is used
     *              for login to the web site,
     *              it holds the user Email
     */
    public void emailRemoteField(String email) {
        dockerDriver.findElement(By.id("email")).sendKeys(email);
    }

    /**
     * using the remote driver that is set to connect and
     * use the docker standalone chrome/firefox container
     * is locating the password field and types the passed String variable
     * @param pass passed string variable that is used
     *      *              for login to the web site,
     *      *              it holds the user Password
     */
    public void passRemoteField(String pass) {
        dockerDriver.findElement(By.id("pass")).sendKeys(pass);

    }

    /**
     * using the remote driver that is set to connect and
     * use the docker standalone chrome/firefox container
     * is locating and clicking the login button on the page
     */
    public void clickRemoteLogin() {
        dockerDriver.findElement(By.id("send2")).click();
    }

}
