package com.example.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaMainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = ".central-featured-lang")
    private WebElement languageSection;

    public WikipediaMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.wikipedia.org");
    }

    public void searchFor(String query) {
        PageFactory.initElements(driver, this);
        
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
        input.clear();
        input.sendKeys(query);
        
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement button = null;
        
        try {
            button = shortWait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[type='submit'], button#searchButton, button.searchButton, " +
                              "form button[type='submit'], .search-form button")
            ));
        } catch (Exception e) {
        }
        
        if (button != null) {
            try {
                button.click();
            } catch (Exception e) {
                input.sendKeys(Keys.RETURN);
            }
        } else {
            input.sendKeys(Keys.RETURN);
        }
    }

    public void clickEnglishLanguage() {
        try {
            WebElement englishLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".central-featured-lang a[href*='en.wikipedia.org']")
            ));
            englishLink.click();
        } catch (Exception e) {
            WebElement englishLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'en.wikipedia.org') and contains(text(), 'English')]")
            ));
            englishLink.click();
        }
    }

    public void clickRussianLanguage() {
        try {
            WebElement russianLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".central-featured-lang a[href*='ru.wikipedia.org']")
            ));
            russianLink.click();
        } catch (Exception e) {
            WebElement russianLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'ru.wikipedia.org')]")
            ));
            russianLink.click();
        }
    }

    public void clickGermanLanguage() {
        try {
            WebElement germanLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".central-featured-lang a[href*='de.wikipedia.org']")
            ));
            germanLink.click();
        } catch (Exception e) {
            WebElement germanLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'de.wikipedia.org')]")
            ));
            germanLink.click();
        }
    }

    public boolean isLanguageSectionDisplayed() {
        return languageSection.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
