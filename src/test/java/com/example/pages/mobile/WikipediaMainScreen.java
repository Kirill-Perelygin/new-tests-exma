package com.example.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaMainScreen {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public WikipediaMainScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickSearchContainer() {
        try {
            WebElement searchContainer = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("org.wikipedia:id/search_container")
            ));
            searchContainer.click();
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void enterSearchQuery(String query) {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchInput = longWait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("org.wikipedia:id/search_src_text")
        ));
        searchInput.clear();
        searchInput.sendKeys(query);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isMainToolbarDisplayed() {
        try {
            WebElement toolbar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("org.wikipedia:id/main_toolbar")
            ));
            return toolbar.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchContainerDisplayed() {
        try {
            WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("org.wikipedia:id/search_container")
            ));
            return container.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void closeSearchIfOpen() {
        try {
            WebElement closeButton = driver.findElement(By.id("org.wikipedia:id/search_close_btn"));
            if (closeButton.isDisplayed()) {
                closeButton.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}
