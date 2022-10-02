package com.bbc.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//input[@id='search-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//form[@role='search']//following-sibling::div//ul/li//a")
    private List<WebElement> searchResultLinks;

    private SearchPage(WebDriver driver) {
        super(driver);
    }

    public static SearchPage waitUntilLoaded(WebDriver driver) {
        SearchPage searchPage = new SearchPage(driver);
        return searchPage.waitUntilLoaded();
    }

    public SearchPage search(String query) {
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.RETURN);
        return waitUntilLoaded();
    }

    public List<String> getSearchResultTitles() {
        return searchResultLinks.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private SearchPage waitUntilLoaded() {
        waitForDocumentReadyState();
        waitFor(visibilityOf(searchInput));
        return this;
    }
}
