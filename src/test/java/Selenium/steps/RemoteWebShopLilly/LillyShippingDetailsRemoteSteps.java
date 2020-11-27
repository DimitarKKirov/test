package Selenium.steps.RemoteWebShopLilly;

import Selenium.mastePageManager.MasterManager;
import Selenium.pageObjects.pageObjectLillyShop.LillyHomeProductsListsPage;
import Selenium.pageObjects.pageObjectLillyShop.LillyShippingDetailsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LillyShippingDetailsRemoteSteps {

    LillyShippingDetailsPage shippingDetails;

    @Given("and clicks checkout")
    public void clickCheckOut() {
        LillyHomeProductsListsPage cartPage = MasterManager.getMasterManager().lillyPageManager().lillyHomeProductsListsPage();
        cartPage.clickRemoteCheckOut();
    }

    @When("user fill the necessary Data {string},{string},{string},{string},{string} and {string}")
    public void fillNecessaryData(String name, String lastName, String phone, String city, String email, String office) {

        shippingDetails = MasterManager.getMasterManager().lillyPageManager().lillyShippingDetailsPage();
        shippingDetails.clickRemoteCookies();
        shippingDetails.createRemoteWait(5).until(ExpectedConditions.textToBePresentInElement(shippingDetails.getRemoteStepTitle(), "Адрес за доставка"));
        shippingDetails.enterRemoteEmailToRegister(email);
        shippingDetails.fieldRemoteFirstName(name);
        shippingDetails.fieldRemoteLastName(lastName);
        shippingDetails.fieldRemotePhone(phone);
        shippingDetails.enterRemoteCity(city);
        WebElement elementCity = shippingDetails.createRemoteWait(5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-select2-id=\"11\"]")));
        shippingDetails.moveRemoteToElement(elementCity);
        shippingDetails.clickElement(elementCity);
        shippingDetails.enterRemoteAddressShipmentOffice(office);
        WebElement elementOffice = shippingDetails.createRemoteWait(5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-select2-id=\"23\"]")));
        shippingDetails.moveRemoteToElement(elementOffice);
        shippingDetails.clickElement(elementOffice);


    }

    @When("pres submit button")
    public void pressContinue() {
//        shippingDetails.moveToElement(shippingDetails.getStepTitle());
        WebElement radioShipping = shippingDetails.createRemoteWait(5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value=\"extensaspeedy_speedy_505\"]")));
        shippingDetails.clickElement(radioShipping);
        shippingDetails.clickRemoteContinue();
    }

    @Then("the user is redirected to the payment page")
    public void redirectedToPaymentPage() {
        String title = shippingDetails.getRemotePageTitle();
        Assert.assertEquals("Поръчай | Лили Дрогерие",title);
        shippingDetails.quitRemoteBrowser();
    }
}
