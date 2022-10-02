package com.bbc.pages;

import com.bbc.Form.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class QuestionsPage extends BasePage {

    private static final By FORM_FIELDS_LOCATOR = By.xpath("//textarea[contains(@class, 'text-input')]|//input[contains(@class, 'text-input')]");
    @FindBy(xpath = "//div[@class='hearken-embed cleanslate']")
    private WebElement questionForm;

    @FindBy(xpath = "//button[@class='button']")
    private WebElement submitButton;

    private final Form form;
    QuestionsPage(WebDriver driver) {
        super(driver);
        this.form = new Form(driver, FORM_FIELDS_LOCATOR);
    }

    public static QuestionsPage waitUntilLoaded(WebDriver driver) {
        QuestionsPage page = new QuestionsPage(driver);
        return page.waitUntilLoaded();
    }

    private QuestionsPage waitUntilLoaded() {
        waitForDocumentReadyState();
        waitFor(visibilityOf(questionForm));
        return this;
    }

    public void fillForm(Map<String, String> values){
        form.fillForm(values);
    }

    public String getFieldError(String fieldName) {
        return form.getFieldError(fieldName);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

}
