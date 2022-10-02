package com.bbc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class NewsPage extends BasePage {

    @FindBy(xpath = "//div[@id='news-top-stories-container']//a[1]/h3")
    private WebElement headlineArticleTitle;

    @FindBy(xpath = "(//div[@id='news-top-stories-container']//div[@data-entityid='container-top-stories#1']/div[1]//a)[last()]")
    private WebElement headlineArticleCategoryLink;

    @FindBy(xpath = "//div[@id='news-top-stories-container']//a[1]/h3")
    private List<WebElement> topStoriesHeaders;

    @FindBy(xpath = "//div[@id='orb-header']//a[@id='orbit-search-button']")
    private WebElement searchButtonLink;
    @FindBy(xpath = "//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//span[contains(text(),'Coronavirus')]")
    private WebElement coronavirusTab;

    private NewsPage(WebDriver driver) {
        super(driver);
    }

    public static NewsPage waitUntilLoaded(WebDriver driver) {
        NewsPage page = new NewsPage(driver);
        return page.waitUntilLoaded();
    }

    private NewsPage waitUntilLoaded() {
        waitForDocumentReadyState();
        waitFor(visibilityOf(headlineArticleTitle));
        return this;
    }

    public String getHeadlineArticleTitleText() {
        return headlineArticleTitle.getText();
    }

    public List<String> getSecondaryArticleTitlesText() {
        return topStoriesHeaders.stream()
                .skip(1)
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getHeadlineArticleCategory() {
        return headlineArticleCategoryLink.getText();
    }

    public SearchPage clickSearchButton() {
        searchButtonLink.click();
        return SearchPage.waitUntilLoaded(driver);
    }

    public CoronavirusPage clickOnCoronavirusTab() {
        coronavirusTab.click();
        return CoronavirusPage.waitUntilLoaded(driver);
    }
}
