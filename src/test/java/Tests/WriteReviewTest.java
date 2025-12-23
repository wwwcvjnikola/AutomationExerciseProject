package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import Pages.WriteReviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WriteReviewTest extends BaseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://automationexercise.com/");

        homePage = new HomePage(driver);
        writeReviewPage = new WriteReviewPage(driver);
    }

    @Test
    public void writeReviewTest() throws InterruptedException {
        String productName = "Blue Top";
        String yourName = "Nikola";
        String emaliAdress = "test@mail.com";
        String addReview = "Thank you ITBootcamp!";
        String expectedURL = "https://automationexercise.com/product_details/1";
        String expectedConfirmationMsg = "Thank you for your review.";

        writeReviewPage.clickOnReviewProductButton(productName);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(writeReviewPage.getSubmitButton().isDisplayed());

        // unos podataka u formu

        writeReviewPage.enterName(yourName);
        writeReviewPage.enterEmail(emaliAdress);
        writeReviewPage.enterReview(addReview);
        writeReviewPage.clickSubmitButton();

        // Asertacija potvrdne poruke
        Assert.assertEquals(writeReviewPage.getConfirmationMsg().getText(), expectedConfirmationMsg);
    }




}

