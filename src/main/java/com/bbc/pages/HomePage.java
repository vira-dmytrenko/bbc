package com.bbc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h2[@class='module__title']/span")
    private WebElement greeting;

    @FindBy(xpath = "//div[@id='orb-header']//li[@class='orb-nav-newsdotcom']/a")
    private WebElement headerLinkNews;


    private HomePage(WebDriver driver) {
        super(driver);
    }

    public static HomePage open(WebDriver driver, String url) {
        driver.navigate().to(url);
        HomePage page = new HomePage(driver);
        return page.waitUntilLoaded();
    }

    private HomePage waitUntilLoaded() {
        waitFor(ExpectedConditions.textToBePresentInElement(greeting, "Welcome to BBC.com"));
        return this;
    }

    public NewsPage clickOnNavBarNewsLink() {
        headerLinkNews.click();
        return NewsPage.waitUntilLoaded(driver);
    }

}
