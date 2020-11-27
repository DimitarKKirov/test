package Selenium.pageObjects.pageObjectsEmag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Selenium.pageObjects.pManagerEmag.EmagRegularElements;

public class SearchInEmag extends EmagRegularElements {

    // this class represents the Home page of Emag web shop site

    // using the local driver the method is locating and clicking
    // the search field in the landing page
    // and entering the passed string variable
    public void enterInSearchField(String search) {
        WebElement searchField = driver.findElement(By.xpath("//input[@id=\"searchboxTrigger\"]"));
        searchField.click();
        searchField.sendKeys(search);
    }

    // using the remote driver that is set to connect and
    // use the docker standalone chrome/firefox container
    // the method is locating and clicking
    // the search field in the landing page
    // and entering the passed string variable
    public void enterInSearchFieldRemote(String search) {
        WebElement searchField = dockerDriver.findElement(By.xpath("//input[@id=\"searchboxTrigger\"]"));
        searchField.click();
        searchField.sendKeys(search);
    }

    // using the local driver the method is locating and clicking
    // the search button in order the search to be initiated
    public void clickSearch() {
        WebElement searchButton = driver.findElement(By.xpath("//i[@class=\"em em-search\"]"));
        searchButton.click();
    }

    // using the remote driver that is set to connect and
    // use the docker standalone chrome/firefox container
    // the method is locating and clicking
    // using the local driver the method is locating and clicking
    // the search button in order the search to be initiated
    public void clickSearchRemote() {
        dockerDriver.findElement(By.xpath("//i[@class=\"em em-close\"]")).click();
        WebElement searchButton = dockerDriver.findElement(By.xpath("//i[@class=\"em em-search\"]"));
        searchButton.click();
    }

    /*
    method for returning the search filter
    and found result after search criteria is passed in
    the search field
     */
    public String remoteSearchResult() {
       return dockerDriver.findElement(By.xpath("//span[@class=\"title-phrasing title-phrasing-sm\"]")).getText();

    }
}
