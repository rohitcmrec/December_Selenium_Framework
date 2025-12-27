package com.qa.openCart.factory;

import com.qa.openCart.errors.AppErrors;
import com.qa.openCart.exceptions.BrowserException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {

    private WebDriver driver;
    private Properties properties;
    private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
    OptionManager optionManager;

    public WebDriver initDriver(Properties prop) throws MalformedURLException {
        optionManager = new OptionManager(prop);
        String browser = prop.getProperty("browser");
        System.out.println("Name of the browser is " + browser);

        switch (browser) {
            case "chrome":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    initRemoteDriver("chrome");
                } else {
                    tldriver.set(new ChromeDriver(optionManager.getChromeOptions()));
                    //driver = new ChromeDriver();
                }
                break;
            case "firefox":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    initRemoteDriver("firefox");
                } else {
                    tldriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
                    //driver = new FirefoxDriver();
                }
                break;
            case "edge":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    initRemoteDriver("edge");
                } else {
                    tldriver.set(new EdgeDriver());
                    //driver = new SafariDriver();
                }
                break;
            default:
                System.out.println("Invalid browser entered");
                throw new BrowserException("Invalid browser entered");
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tldriver.get();
    }

    public Properties initProp() {
        String env = System.getProperty("env");
        properties = new Properties();
        FileInputStream fp;
        if (env == null) {
            env = "qa";
            System.out.println("Environment name is null, running on default QA env");
        }
        try {
            switch (env) {
                case "qa":
                    fp = new FileInputStream("./src/test/resources/config/qaConfig.properties");
                    System.out.println("Running on Environment " + env);
                    break;
                case "dev":
                    fp = new FileInputStream("./src/test/resources/config/devConfig.properties");
                    System.out.println("Running on Environment " + env);
                    break;
                case "uat":
                    fp = new FileInputStream("./src/test/resources/config/uatConfig.properties");
                    System.out.println("Running on Environment " + env);
                    break;
                default:
                    fp = new FileInputStream("./src/test/resources/config/qaConfig.properties");
                    System.out.println("Running on Environment " + env);
                    break;
            }
            properties.load(fp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String getScreenshot(String methodName) throws IOException {

        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + System.currentTimeMillis() + ".png";
        File des = new File(path);
        FileHandler.copy(src, des);
        return path;

    }

    private void initRemoteDriver(String browser) throws MalformedURLException {
        System.out.println("Running on grid using browser " + browser);
        switch (browser) {
            case "chrome":
                tldriver.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionManager.getChromeOptions()));
                break;
            case "firefox":
                tldriver.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionManager.getFirefoxOptions()));
                break;
            case "edge":
                tldriver.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionManager.getEdgeOptions()));
                break;
            default:
                throw new BrowserException("browser not found");
        }
    }
}
