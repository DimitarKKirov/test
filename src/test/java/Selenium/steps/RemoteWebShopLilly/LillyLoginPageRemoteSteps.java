package Selenium.steps.RemoteWebShopLilly;

import Selenium.mastePageManager.MasterManager;
import Selenium.pageObjects.pageObjectLillyShop.LillyLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LillyLoginPageRemoteSteps {

    private WebDriver driver1;
    private LillyLoginPage lillyLoginPage;

    @Given("the user is on the {string} page")
    public void loginPage(String pageLink) {
        lillyLoginPage = MasterManager.getMasterManager().lillyPageManager().lillyLoginPage();
        lillyLoginPage.startBrowser(pageLink,"chrome");
        String title = lillyLoginPage.getPageTitle();
        Assert.assertEquals("Вход | Лили Дрогерие", title);
    }

    @When("user enters the correct {string} and {string}")
    public void FiledFilling(String username, String password) {
        lillyLoginPage.emailField(username);
        lillyLoginPage.passField(password);

    }

    @When("clicks on button Вход")
    public void clicksButtonLogin() {
        lillyLoginPage.clickLogin();
    }

    @Then("user is successfully logged in")
    public void successfullyLoggedIn() {
        String title = lillyLoginPage.getPageTitle();
        Assert.assertEquals("Лили Дрогерие онлайн магазин | Лили Дрогерие", title);
        lillyLoginPage.quitBrowser();
    }
}
