package com.example.tests.mobile;

import com.example.pages.mobile.WikipediaMainScreen;
import com.example.pages.mobile.WikipediaSearchResultsScreen;
import com.example.utils.AppiumDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WikipediaMobileTests {
    private AndroidDriver driver;
    private WikipediaMainScreen mainScreen;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        driver = AppiumDriverManager.getDriver();
        mainScreen = new WikipediaMainScreen(driver);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        AppiumDriverManager.quitDriver();
    }

    @Test(priority = 1, description = "Проверка отображения главного экрана")
    public void testMainScreenDisplay() {
        Assert.assertTrue(mainScreen.isSearchContainerDisplayed(), 
            "Контейнер поиска должен отображаться на главном экране");
    }

    @Test(priority = 2, description = "Поиск статьи и проверка результатов")
    public void testSearchArticle() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        mainScreen.clickSearchContainer();
        
        mainScreen.enterSearchQuery("Java");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WikipediaSearchResultsScreen searchResults = new WikipediaSearchResultsScreen(driver);
        
        int resultsCount = searchResults.getSearchResultsCount();
        Assert.assertTrue(resultsCount > 0, 
            "Должны быть найдены результаты поиска. Найдено: " + resultsCount);
    }

    @Test(priority = 3, description = "Открытие статьи из результатов поиска")
    public void testOpenArticleAndCheckTitle() {
        mainScreen.closeSearchIfOpen();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        mainScreen.clickSearchContainer();
        
        mainScreen.enterSearchQuery("Java");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WikipediaSearchResultsScreen searchResults = new WikipediaSearchResultsScreen(driver);
        
        int resultsCount = searchResults.getSearchResultsCount();
        Assert.assertTrue(resultsCount > 0, 
            "Должны быть найдены результаты поиска перед открытием статьи. Найдено: " + resultsCount);
        
        String firstResultTitle = searchResults.getFirstResultTitle();
        Assert.assertFalse(firstResultTitle.isEmpty(), 
            "Заголовок первого результата не должен быть пустым");
        
        searchResults.clickFirstSearchResult();
        
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WikipediaSearchResultsScreen checkResults = new WikipediaSearchResultsScreen(driver);
        boolean resultsStillVisible = checkResults.isSearchResultsDisplayed();
        
        Assert.assertFalse(resultsStillVisible, 
            "После открытия статьи экран результатов поиска не должен отображаться");
    }
}
