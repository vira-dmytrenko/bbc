package com.bbc.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    final Duration defaultTimeout() {
        return Duration.ofSeconds(5);
    }

    <R> R waitFor(ExpectedCondition<R> expectedCondition, Duration duration) {
        return new WebDriverWait(driver, duration).until(expectedCondition);
    }

    <R> R waitFor(ExpectedCondition<R> expectedCondition) {
        return waitFor(expectedCondition, defaultTimeout());
    }

    Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    boolean isDocumentReady() {
        return executeScript("return document.readyState").equals("complete");
    }

    void waitForDocumentReadyState(Duration duration) {
        waitFor(d -> isDocumentReady(), duration);
    }

    void waitForDocumentReadyState() {
        waitForDocumentReadyState(defaultTimeout());
    }
}
