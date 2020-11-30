package Selenium.steps.RemoteWebShopLilly;

import Selenium.mastePageManager.MasterManager;
import Selenium.pageObjects.pageObjectLillyShop.LillyHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import java.net.MalformedURLException;

public class LillyHomePageRemoteSteps {

    LillyHomePage lillyHomePage;


    @Given("open Home Page {string}")
    public void userIsOn(String homePage) {

        lillyHomePage = MasterManager.getMasterManager().lillyPageManager().lillyHomePage();
        try {
            lillyHomePage.remoteConnect(homePage, "chrome");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String title = lillyHomePage.getRemotePageTitle();
        Assert.assertEquals("Лили Дрогерие онлайн магазин | Лили Дрогерие", title);
    }

    @When("click on Вход button")
    public void clickEnterButton() {
        lillyHomePage.clickRemoteLogin();
    }

    @Then("you are redirected to the login page of Lilly website")
    public void redirectionToLoginPage() {
        String title = lillyHomePage.getRemotePageTitle();
        Assert.assertEquals("Вход | Лили Дрогерие", title);
        lillyHomePage.quitRemoteBrowser();

    }



}
