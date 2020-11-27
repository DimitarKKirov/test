package Selenium.pageObjects.pageObjectLillyShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Selenium.pManagers.shopLilly.LillyRegularsElements;

public class LillyLoginPage extends LillyRegularsElements {

    // this class represents the web page Login


    // using the local driver the method is locating
    // the email field and types the passed String variable
    public void emailField(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    // using the local driver the method is locating
    // the password field and types the passed String variable
    public void passField(String pass) {
        driver.findElement(By.id("pass")).sendKeys(pass);

    }

    // using the local driver the method is locating
    // and clicking the login button on the page
    public void clickLogin() {
        driver.findElement(By.id("send2")).click();
    }

    // using the remote driver that is set to connect and
    // use the docker standalone chrome/firefox container
    // is locating the email field and types the passed String variable
    public void emailRemoteField(String email) {
        dockerDriver.findElement(By.id("email")).sendKeys(email);
    }

    // using the remote driver that is set to connect and
    // use the docker standalone chrome/firefox container
    // is locating the password field and types the passed String variable
    public void passRemoteField(String pass) {
        dockerDriver.findElement(By.id("pass")).sendKeys(pass);

    }

    // using the remote driver that is set to connect and
    // use the docker standalone chrome/firefox container
    // is locating and clicking the login button on the page
    public void clickRemoteLogin() {
        dockerDriver.findElement(By.id("send2")).click();
    }

}
