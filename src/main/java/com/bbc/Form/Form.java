package com.bbc.Form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Form{
    private final WebDriver driver;
    private final By locator;

    public Form(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    private String getFieldName(WebElement field) {
        return field.getAttribute("aria-label");
    }

    public void fillForm(Map<String, String> values){
        for (String fieldName: values.keySet()){
            getField(fieldName).sendKeys(values.get(fieldName));
        }
    }


    public String getFieldError(String fieldName) {
        return getField(fieldName)
                .findElement(By.xpath("./parent::div/div[@class='input-error-message']"))
                .getText();
    }

    private WebElement getField(String fieldName) {
        for (WebElement element : getListOfFields()) {
            if (getFieldName(element).contains(fieldName)) {
                return element;
            }
        }
        throw new NoSuchElementException("There is no such field: '" + fieldName + "'");
    }

    private List<WebElement> getListOfFields(){
        return driver.findElements(locator);
    }
}