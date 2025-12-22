package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {

    // HomePage je ujedno i Product page

    WebDriver driver;

    WebElement featuresItemsTitle;
    List<WebElement> navigationBar;
    List<WebElement> addToCartBtn;

    //Categories

    WebElement category;
    WebElement womenCategory;
    WebElement menCategory;
    WebElement kidsCategory;

    List<WebElement> childCategories;
    List<WebElement> products;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getNavigationBar() {
        return driver.findElements(By.cssSelector(".navbar-nav li"));
    }

    public List<WebElement> getAddToCartBtn() {
        return driver.findElements(By.cssSelector(".btn.btn-default.add-to-cart"));
    }

    // Title (container za proizvode) za proveru
    public WebElement getFeaturesItemsTitle() {
        return driver.findElement(By.cssSelector("h2.title.text-center"));
    }

    // glavni filter container
    public WebElement getCategory() {
        return driver.findElement(By.id("accordian"));
    }

    // Parent
    public WebElement getWomenCategory() {
        return driver.findElement(By.cssSelector("#accordian a[href='#Women']"));
    }

    public WebElement getMenCategory() {
        return driver.findElement(By.cssSelector("#accordian a[href='#Men']"));
    }

    public WebElement getKidsCategory() {
        return driver.findElement(By.cssSelector("#accordian a[href='#Kids']"));
    }

    // Child
    public List<WebElement> getWomenChildCategories() {
        return driver.findElements(By.cssSelector("#Women a"));
    }

    public List<WebElement> getMenChildCategories() {
        return driver.findElements(By.cssSelector("#Men a"));
    }

    public List<WebElement> getKidsChildCategories() {
        return driver.findElements(By.cssSelector("#Kids a"));
    }

    // nazivi proizvoda
    public List<WebElement> getProducts() {
        return driver.findElements(By.cssSelector(".productinfo p"));
    }

    //**************************************************

    public void clickOnNavBarCard(String navCardName) {
        for (int i = 0; i < getNavigationBar().size(); i++) {
            if (getNavigationBar().get(i).getText().equalsIgnoreCase(navCardName)) {
                getNavigationBar().get(i).click();
                break;
            }
        }
    }


    public WebElement addToCartButtonForProduct(String productName) {
        List<WebElement> products = driver.findElements(By.cssSelector(".single-products"));

        for (WebElement product : products) {

            String name = product.findElement(By.tagName("p")).getText();
            if (name.equals(productName)) {
               return product.findElement(By.cssSelector(".add-to-cart"));
            }
        }
        return null;
    }

    //********************************* FILTERI ******************************

    public void clickOnWomenChildCategory(String childName) {
        for (WebElement child : getWomenChildCategories()){
            if (child.getText().equalsIgnoreCase(childName)){
                child.click();
                break;
            }
        }
    }

    public void clickOnMenChildCategory(String childName) {
        for (WebElement child : getMenChildCategories()){
            if (child.getText().equalsIgnoreCase(childName)){
                child.click();
                break;
            }
        }
    }

    public void clickOnKidsChildCategory(String childName) {
        for (WebElement child : getKidsChildCategories()){
            if (child.getText().equalsIgnoreCase(childName)){
                child.click();
                break;
            }
        }
    }

    //************************* Verification ******************************

    public boolean verifyWomenChildCategories(String[] expectedChildren) {
        List<String> children = new ArrayList<>();
        for (WebElement child : getWomenChildCategories()) {
            children.add(child.getText().trim());
        }

        for (String expected : expectedChildren) {
            if (!children.contains(expected)) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyProductsMatchCategory(String keyword) {
        List<WebElement> products = getProducts();
        if (products.isEmpty()){
            return false;
        }

        for (WebElement product : products) {
            if (!product.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }


}
