package com.bbc.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CoronavirusPage extends BasePage{
    @FindBy(xpath = "//li[contains(@class, 'gs-o-list-ui__item--flush gel-long')]//span[contains(text(),'Your Coronavirus Stories')]")
    private WebElement yourCoronavirusStoriesLink;
    @FindBy(xpath = "//button[@class='tp-close tp-active']")
    private WebElement popUpCloseButton;
    CoronavirusPage(WebDriver driver) {
        super(driver);
    }

    public static CoronavirusPage waitUntilLoaded(WebDriver driver) {
        CoronavirusPage page = new CoronavirusPage(driver);
        return page.waitUntilLoaded();
    }

    private CoronavirusPage waitUntilLoaded() {
        waitForDocumentReadyState();
        try {
            waitFor(visibilityOf(popUpCloseButton));
            popUpCloseButton.click();
        } catch (TimeoutException e){
            System.out.println("Popup did not appear");
        }
        return this;
    }

    public YourCoronavirusStoriesPage clickOnYourCoronavirusStoriesLink() {
        yourCoronavirusStoriesLink.click();
        return YourCoronavirusStoriesPage.waitUntilLoaded(driver);
    }

}
