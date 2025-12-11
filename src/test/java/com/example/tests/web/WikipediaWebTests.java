package com.example.tests.web;

import com.example.pages.web.WikipediaArticlePage;
import com.example.pages.web.WikipediaMainPage;
import com.example.pages.web.WikipediaSearchResultsPage;
import com.example.utils.WebDriverManagerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WikipediaWebTests {
    private WebDriver driver;
    private WikipediaMainPage mainPage;

    @BeforeClass
    public void setUp() {
        driver = WebDriverManagerUtil.getDriver();
        mainPage = new WikipediaMainPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverManagerUtil.quitDriver();
    }

    @Test(priority = 1, description = "Проверка открытия главной страницы Wikipedia")
    public void testOpenMainPage() {
        mainPage.open();
        String pageTitle = mainPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Wikipedia"), 
            "Заголовок страницы должен содержать 'Wikipedia', но содержит: " + pageTitle);
        Assert.assertTrue(mainPage.isLanguageSectionDisplayed(), 
            "Секция выбора языка должна отображаться");
    }

    @Test(priority = 2, description = "Проверка поиска статьи по ключевому слову")
    public void testSearchArticle() {
        mainPage.open();
        mainPage.searchFor("Java programming language");
        
        WikipediaSearchResultsPage searchResultsPage = new WikipediaSearchResultsPage(driver);
        Assert.assertTrue(searchResultsPage.isSearchResultsDisplayed(), 
            "Результаты поиска должны отображаться");
        Assert.assertTrue(searchResultsPage.getSearchResultsCount() > 0, 
            "Должны быть найдены результаты поиска");
    }

    @Test(priority = 3, description = "Проверка открытия статьи и проверка заголовка")
    public void testOpenArticleAndCheckTitle() {
        mainPage.open();
        mainPage.searchFor("Metallica");
        
        WikipediaSearchResultsPage searchResultsPage = new WikipediaSearchResultsPage(driver);
        searchResultsPage.clickFirstSearchResult();
        
        WikipediaArticlePage articlePage = new WikipediaArticlePage(driver);
        String heading = articlePage.getArticleHeading();
        Assert.assertNotNull(heading, "Заголовок статьи должен быть отображен");
        Assert.assertFalse(heading.isEmpty(), "Заголовок статьи не должен быть пустым");
        Assert.assertTrue(articlePage.isArticleContentDisplayed(), 
            "Содержимое статьи должно отображаться");
    }

    @Test(priority = 4, description = "Проверка переключения языков")
    public void testLanguageSwitching() {
        mainPage.open();
        String initialUrl = mainPage.getCurrentUrl();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        mainPage.clickEnglishLanguage();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String englishUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(initialUrl, englishUrl, 
            "URL должен измениться после переключения на английский язык");
        Assert.assertTrue(englishUrl.contains("en.wikipedia.org"), 
            "URL должен содержать 'en.wikipedia.org', но содержит: " + englishUrl);
    }

    @Test(priority = 5, description = "Проверка навигации по меню")
    public void testNavigationMenu() {
        mainPage.open();
        mainPage.searchFor("Test automation");
        
        WikipediaSearchResultsPage searchResultsPage = new WikipediaSearchResultsPage(driver);
        searchResultsPage.clickFirstSearchResult();
        
        WikipediaArticlePage articlePage = new WikipediaArticlePage(driver);
        Assert.assertTrue(articlePage.isNavigationMenuDisplayed(), 
            "Меню навигации должно отображаться на странице статьи");
    }

    @Test(priority = 6, description = "Проверка поиска на русском языке")
    public void testSearchInRussianLanguage() {
        mainPage.open();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        mainPage.clickRussianLanguage();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WikipediaMainPage russianMainPage = new WikipediaMainPage(driver);
        russianMainPage.searchFor("Автоматизация тестирования");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WikipediaSearchResultsPage searchResultsPage = new WikipediaSearchResultsPage(driver);
        Assert.assertTrue(searchResultsPage.isSearchResultsDisplayed(), 
            "Результаты поиска должны отображаться на русском языке");
    }
}
