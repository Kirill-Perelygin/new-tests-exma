package com.example.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikipediaArticlePage {
    private WebDriver driver;

    @FindBy(id = "firstHeading")
    private WebElement articleHeading;

    @FindBy(id = "mw-content-text")
    private WebElement articleContent;

    @FindBy(css = "#p-lang .interlanguage-link-target")
    private java.util.List<WebElement> languageLinks;

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(css = "nav#p-navigation")
    private WebElement navigationMenu;

    public WikipediaArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getArticleHeading() {
        return articleHeading.getText();
    }

    public boolean isArticleContentDisplayed() {
        return articleContent.isDisplayed();
    }

    public String getArticleContentText() {
        return articleContent.getText();
    }

    public int getLanguageLinksCount() {
        return languageLinks.size();
    }

    public boolean isNavigationMenuDisplayed() {
        return navigationMenu.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

