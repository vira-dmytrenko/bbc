package com.bbc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class YourCoronavirusStoriesPage extends BasePage{

    @FindBy(xpath = "//h3[text()='Your questions answered: What questions do you have?']/parent::a")
    private WebElement questionPageLink;

    YourCoronavirusStoriesPage(WebDriver driver) {
        super(driver);
    }

    public static YourCoronavirusStoriesPage waitUntilLoaded(WebDriver driver) {
        YourCoronavirusStoriesPage page = new YourCoronavirusStoriesPage(driver);
        return page.waitUntilLoaded();
    }

    private YourCoronavirusStoriesPage waitUntilLoaded() {
        waitForDocumentReadyState();
        waitFor(visibilityOf(questionPageLink));
        return this;
    }

    public QuestionsPage clickOnYourCoronavirusStoriesLink() {
        questionPageLink.click();
        return QuestionsPage.waitUntilLoaded(driver);
    }

}
