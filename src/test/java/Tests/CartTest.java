package Tests;

import BaseTest.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://automationexercise.com/");

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test  (priority = 10)
    public void addProductInCart(){
        String expectedUrl = "https://automationexercise.com/view_cart";
        String producName = "Blue Top";

        cartPage.clickOnAddToCartBtn(producName);
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.getContinueShoppingBtn()));
        cartPage.clickOnContinueShoppingBtn();
        homePage.clickOnNavBarCard("Cart");

        // provera da li smo na Cart strani (URL i Proceed To Checkout Btn)
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertTrue(cartPage.getProceedToCheckoutBtn().isDisplayed());

        // provera da li je dodadti proizvod vidljiv u cart-u
        Assert.assertTrue(cartPage.isProductAddedToCart(producName));


    }

    @Test (priority = 20)
    public void deleteAddedProductFromCart(){
        String producName = "Blue Top";
        String expectedMsg = "Cart is empty!";

        cartPage.clickOnAddToCartBtn(producName);
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.getContinueShoppingBtn()));
        cartPage.clickOnContinueShoppingBtn();
        homePage.clickOnNavBarCard("Cart");

        // provera da li je dodadti proizvod vidljiv u cart-u
        Assert.assertTrue(cartPage.isProductAddedToCart(producName));

        // brisanje proizvoda
        cartPage.deleteProductFromTheCart(producName);

        // provera da li je proizvod obrisan
        // prva asertacija da se tekst proizvoda ne nalazi vise na strani
        // druga asertacija da se poruka za praznu korpu pojavila
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(producName)));
        Assert.assertTrue(driver.findElements(By.linkText(producName)).isEmpty());
        Assert.assertTrue(cartPage.getCartIsEmptyMsg().getText().trim().contains(expectedMsg));
    }
}
