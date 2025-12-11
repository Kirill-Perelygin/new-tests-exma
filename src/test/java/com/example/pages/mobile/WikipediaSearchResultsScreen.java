package com.example.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WikipediaSearchResultsScreen {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public WikipediaSearchResultsScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSearchResultsDisplayed() {
        try {
            WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("org.wikipedia:id/search_results_container")
            ));
            return container.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getSearchResultsCount() {
        try {
            List<WebElement> titles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.id("org.wikipedia:id/page_list_item_title")
            ));
            return titles.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickFirstSearchResult() {
        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("org.wikipedia:id/page_list_item_title")
        ));
        firstResult.click();
    }

    public String getFirstResultTitle() {
        try {
            WebElement firstResult = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("org.wikipedia:id/page_list_item_title")
            ));
            return firstResult.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
