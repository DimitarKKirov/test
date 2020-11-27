package Selenium.pManagers.shopLilly;

import Daemon.DockerEnv;
import Selenium.pageObjects.Drivers.DriverSwitchBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public abstract class LillyRegularsElements extends DockerEnv implements DriverSwitchBrowser {

    /*
    instantiation of singleton page classes, inheritance of methods of:
    1:MainMenu method for selecting from all products menu
    2:Wait method for all inheriting classes
    3:searching and returning current page title
    4:close tab and close browser methods
    */


    public static WebDriver driver;
    public static WebDriver dockerDriver;
    private static String browserName;
    private static String browserDriverPath;
    private static String browserProperty;


    private static void setDriver(WebDriver driver) {
        LillyRegularsElements.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void setDockerDriver(WebDriver dockerDriver) {
        this.dockerDriver = dockerDriver;
    }

    /*
     * depending on the name the method choose which
     * driver properties to load
     */
    private void changeBrowser(String s) {
        String path;
        try {
            if (s.equalsIgnoreCase("Chrome")) {
                path = chrome;
            } else {
                path = fireFox;
            }
            Properties tempProp = new Properties();
            tempProp.load(new FileInputStream(path));
            browserName = tempProp.getProperty("browserName");
            browserDriverPath = tempProp.getProperty("browserDriverPath");
            browserProperty = tempProp.getProperty("browserProps");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     method for initialization of a web driver
     before the driver is created
     the driver properties are loaded based on the name of the browser
     that is checked in the changeBrowser method
     this is only for exe web drivers
    */
    public WebDriver startBrowser(String url, String browserName) {

        changeBrowser(browserName);

        if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty(browserProperty, browserDriverPath);
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("FireFox")) {
            System.setProperty(browserProperty, browserDriverPath);
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chromeHeadless")) {
            System.setProperty(browserProperty, browserDriverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);

        }
        driver.manage().window().maximize();
        driver.get(url);
        setDriver(driver);
        return driver;

    }

    /*
       method for initialization of a remote web driver
       which driver will be created is based on the passed string for browser name
       after that check of the String browserName is passed the driver is created
       with the needed options,then the method returns the created driver and makes
       connection with the container via the docker url variable and opens the passed
       String variable for Url
    */
    public WebDriver remoteConnect(String url, String browserName) throws MalformedURLException {


        if (browserName.equalsIgnoreCase("Chrome")) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setPlatform(Platform.LINUX);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("PlatformName", "Linux");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            URL dockerURL = new URL("http://localhost:4445/wd/hub");
            dockerDriver = new RemoteWebDriver(dockerURL, options);


        } else if (browserName.equalsIgnoreCase("FireFox")) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setPlatform(Platform.LINUX);
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("PlatformName", "Linux");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            URL dockerURL = new URL("http://localhost:4444/wd/hub");
            dockerDriver = new RemoteWebDriver(dockerURL, options);


        }
        setDockerDriver(dockerDriver);
        dockerDriver.manage().window().maximize();
        dockerDriver.get(url);
        return dockerDriver;

    }

    /*
     * using the local driver
     * the method is instantiating a WebDriver wait class
     * that can be use in every
     * page object class of the current Emag test project if need
     * that extends this class
     */
    public WebDriverWait createWait(int timeOut) {
        return new WebDriverWait(driver, timeOut);
    }

    /*
     * using the remote web driver
     * the method is instantiating a WebDriver wait class
     * that can be use in every
     * page object class of the current Emag test project if need
     * that extends this class
     */
    public WebDriverWait createRemoteWait(int timeOut) {
        return new WebDriverWait(dockerDriver, timeOut);
    }

    /*
     * using the local driver
     * the method is locating and clicking
     * on to left hand menu category
     * that is set from passed String variable
     * */
    public void selectAllProductSubMenuElement(String name) {
        driver.findElement(By.xpath("//span[text()=\"" + name + "\"]")).click();

    }

    /*
     * using the remote web driver
     * the method is locating and clicking
     * on to left hand menu category
     * that is set from passed String variable
     * */
    public void selectRemoteAllProductSubMenuElement(String name) {
        dockerDriver.findElement(By.xpath("//span[text()=\"" + name + "\"]")).click();

    }

    /*
     *  using the local driver the method
     *  is locating and returning a String of
     *  the page title that the driver is currently on
     */
    public String getPageTitle() {

        return driver.getTitle();
    }

    /*
     *  using the remote web driver the method
     *  is locating and returning a String of
     *  the page title that the driver is currently on
     */
    public String getRemotePageTitle() {
        return dockerDriver.getTitle();
    }

    /*
     *using the local driver build in method
     * we are quiting it
     * by doing so we are closing the current browser
     */
    public void quitBrowser() {
        driver.quit();
    }
    /*
     * using the remote driver build in method
     * we are closing the connection to the container instance
     */
    public void quitRemoteBrowser() {
        dockerDriver.close();
    }

}
