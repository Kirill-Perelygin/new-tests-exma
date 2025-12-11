package com.example.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaArticleScreen {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public WikipediaArticleScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getArticleTitle() {
        try {
            WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("org.wikipedia:id/view_page_title_text")
            ));
            return title.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
