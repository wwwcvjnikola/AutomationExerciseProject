package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;

    WebElement addToCartBtn;
    WebElement continueShoppingBtn;
    WebElement proceedToCheckoutBtn;
    List <WebElement> cartList;
    WebElement cartIsEmptyMsg;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getProducts() {
        return driver.findElements(By.cssSelector(".single-products"));
    }

    public WebElement getProductName(WebElement product) {
        return product.findElement(By.tagName("p"));
    }

    public WebElement getAddToCartBtn(WebElement product) {
        return product.findElement(By.cssSelector(".btn.btn-default.add-to-cart"));
    }

    public WebElement getContinueShoppingBtn() {
        return driver.findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block"));
    }

    public WebElement getProceedToCheckoutBtn() {
        return driver.findElement(By.cssSelector(".btn.btn-default.check_out"));
    }

    public List<WebElement> getCartList() {
        return driver.findElements(By.id("cart_info_table"));
    }

    public WebElement getCartIsEmptyMsg() {
        return driver.findElement(By.id("empty_cart"));
    }

    //***********************************

    // Metoda za dodavanje proizvoda na osnovu njegovog naziva
    public void clickOnAddToCartBtn(String expectedProductName) {

        for (WebElement product : getProducts()) {

            String name = getProductName(product).getText();

            if (name.equalsIgnoreCase(expectedProductName)) {


                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].scrollIntoView({block:'center'});",
                                product
                        );

                // hover
                Actions actions = new Actions(driver);
                actions.moveToElement(product).perform();


                addToCartBtn = getAddToCartBtn(product);


                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addToCartBtn));
                addToCartBtn.click();
                return;
            }
        }
    }

    public void clickOnContinueShoppingBtn(){
        getContinueShoppingBtn().click();
    }

    // Metoda za proveru da li je odredjeni proizvod dodat u korpu
    public boolean isProductAddedToCart(String productName) {
        for (WebElement item: getCartList()){
            String name = item.findElement(By.linkText(productName)).getText().trim();
            if (name.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    // Metoda za brisanje proizvoda iz korpe na osnovu njegovog imena
    public void deleteProductFromTheCart(String productName) {
        for (WebElement item: getCartList()){
            String name = item.findElement(By.linkText(productName)).getText().trim();
            if (name.equals(productName)){
                item.findElement(By.className("cart_quantity_delete")).click();
                break;
            }
        }

    }
}
