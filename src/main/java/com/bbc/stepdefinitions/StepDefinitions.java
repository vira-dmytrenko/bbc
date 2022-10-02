package com.bbc.stepdefinitions;


import com.bbc.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class StepDefinitions {

    private final Map<String, String> savedData = new HashMap<>();
    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;
    private NewsPage newsPage;
    private CoronavirusPage coronavirusPage;
    private YourCoronavirusStoriesPage yourCoronavirusStoriesPage;
    private QuestionsPage questionsPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @After
    public void tearDown() {
        driver.close();
    }

    @Given("user opens BBC home page {string}")
    public void openHomePage(final String url) {
        homePage = HomePage.open(driver, url);
    }

    @When("user clicks on News link in navigation bar")
    public void clickOnNewsLinkInNavigationBar() {
        newsPage = homePage()
                .clickOnNavBarNewsLink();
    }

    @When("user remembers headline article search category as {string}")
    public void rememberCategoryOfHeadlineArticle(final String key) {
        String category = newsPage().getHeadlineArticleCategory();
        save(key, category);
    }

    @When("user searches value saved as {string}")
    public void searchSavedValue(final String key) {
        String query = getSaved(key);
        search(query);
    }

    @Then("headline article title is {string}")
    public void checkHeadlineArticle(final String expectedTitle) {
        String actualHeadlineText = newsPage().getHeadlineArticleTitleText();
        Assert.assertEquals("Unexpected headline article title", expectedTitle, actualHeadlineText);
    }

    @Then("secondary article titles are {listOfStrings}")
    public void checkFirstSecondaryArticleTitles(final List<String> expectedFirstNTitles) {
        List<String> actualTitles = newsPage().getSecondaryArticleTitlesText();
        Assert.assertTrue(String.format("There are only %d article titles but at least %d are expected",
                actualTitles.size(), expectedFirstNTitles.size()), expectedFirstNTitles.size() <= actualTitles.size());
        for (int i = 0; i < expectedFirstNTitles.size(); i++) {
            Assert.assertEquals(String.format("Unexpected title of secondary article %d", i + 1),
                    expectedFirstNTitles.get(i), actualTitles.get(i));
        }
    }

    @Then("search results contain title {string} at position {int}")
    public void checkSearchResultTitlePresent(final String expectedTitle, final int expectedPositionFromTop) {
        if (expectedPositionFromTop <= 0) {
            throw new IllegalArgumentException("expectedPositionFromTop must be greater than zero.");
        }
        List<String> actualTitles = searchPage().getSearchResultTitles();
        Assert.assertTrue("There are only " + actualTitles.size() + " search results", actualTitles.size() >= expectedPositionFromTop);
        int idx = actualTitles.indexOf(expectedTitle);
        Assert.assertTrue(String.format("Expected search result '%s' is not found. Actual search result at position %d is '%s'",
                expectedTitle, expectedPositionFromTop, actualTitles.get(expectedPositionFromTop - 1)), idx >= 0);
        Assert.assertEquals("Expected title is present but has wrong position from the top", expectedPositionFromTop, idx + 1);
    }

    // https://stackoverflow.com/questions/64366164/passing-list-of-strings-as-cucumber-parameter
    @ParameterType("(?:[^;]*)(?:;\\s?[^;]*)*")
    public List<String> listOfStrings(String arg) {
        return Arrays.asList(arg.split(";\\s?"));
    }
    private void save(String key, String value) {
        savedData.put(key, value);
    }

    private String getSaved(String key) {
        return savedData.get(key);
    }


    public void search(String query) {
        searchPage = newsPage().clickSearchButton().search(query);
    }

    private HomePage homePage() {
        return homePage;
    }

    private NewsPage newsPage() {
        return newsPage;
    }

    private CoronavirusPage coronavirusPage(){
        return coronavirusPage;
    }

    private YourCoronavirusStoriesPage yourCoronavirusStoriesPage() {
        return yourCoronavirusStoriesPage;
    }

    private SearchPage searchPage() {
        return searchPage;
    }

    @And("user clicks on Coronavirus tab")
    public void clickOnCoronavirusTab() {
        coronavirusPage = newsPage()
                .clickOnCoronavirusTab();
    }

    @And("user clicks on 'Your Coronavirus Stories' tab")
    public void userClicksOnTab() {
        yourCoronavirusStoriesPage = coronavirusPage()
                .clickOnYourCoronavirusStoriesLink();
    }

    @And("user clicks on 'Your questions answered: What questions do you have?' link")
    public void clickOnYourQuestionsAnsweredWhatQuestionsDoYouHaveLink() {
        questionsPage = yourCoronavirusStoriesPage()
                .clickOnYourCoronavirusStoriesLink();
    }

    @And("user fills form")
    public void userFillsForm(DataTable values) {
        questionsPage.fillForm(values.asMap(String.class, String.class));
    }

    @And("user clicks 'Submit' button")
    public void clickSubmitButton() {
        questionsPage.clickSubmitButton();
    }

    @Then("user checks {string} appears in empty required {string}")
    public void checkErrorMessages(String expectedMessage, String field) {
        Assert.assertEquals(expectedMessage, questionsPage.getFieldError(field));
    }
}
