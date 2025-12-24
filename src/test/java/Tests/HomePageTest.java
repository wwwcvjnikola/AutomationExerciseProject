package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest extends BaseTest {
    WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://automationexercise.com/");

        homePage = new HomePage(driver);
    }

    @Test
    public void pageCheck(){
        String expectedUrl = "https://automationexercise.com/";
        //System.out.println(driver.getTitle());
        String expectedTitle = "Automation Exercise";
        String expectedH2Title = "FEATURES ITEMS";

        //Asertacije

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertEquals(driver.getTitle(), expectedTitle);
        Assert.assertEquals(homePage.getFeaturesItemsTitle().getText(), expectedH2Title);
    }

    @Test
    public void addToCartBtnCheck() {
        for (int i=1; i < webExcelReader.getLastRow("ProductsNames"); i++) {

            String productName = webExcelReader.getStringData("ProductsNames", i, 0);
            WebElement btn = homePage.addToCartButtonForProduct(productName);

            scrollToElement(btn);
            wait.until(ExpectedConditions.visibilityOf(btn));
            wait.until(ExpectedConditions.elementToBeClickable(btn));

            // Asertacija za vidljivost svakog "add to cart" btn-a
            Assert.assertTrue(isDisplayed(btn), "--" + productName);

            // Da li je klikabilan svaki "add to cart" btn na strani
            Assert.assertTrue(btn.isEnabled(), "--" + productName);

        }
    }

    @Test
    public void testWomenCategory() throws InterruptedException {
        WebElement women = homePage.getWomenCategory();
        scrollToElement(women);
        wait.until(ExpectedConditions.elementToBeClickable(women));
        women.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getChildCategories("Women")));
        Assert.assertTrue( homePage.verifyChildCategories("Women", new String[]{"Dress", "Tops", "Saree"}));

        homePage.clickOnChildCategory("Women", "Dress");
        Assert.assertTrue(homePage.verifyProductsMatchCategory("dress"));
    }

    @Test void testMenCategory() {
        WebElement men = homePage.getMenCategory();
        scrollToElement(men);
        wait.until(ExpectedConditions.elementToBeClickable(men));
        men.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getChildCategories("Men")));
        Assert.assertTrue( homePage.verifyChildCategories("Men", new String[]{"Tshirts", "Jeans"}));

        homePage.clickOnChildCategory("Men", "Jeans");
        Assert.assertTrue(homePage.verifyProductsMatchCategory("jeans"));
    }

}
