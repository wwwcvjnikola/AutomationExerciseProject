package Pages;

import org.apache.poi.xddf.usermodel.XDDFColorRgbBinary;
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

    // Parent
    public WebElement getWomenCategory() {
        return driver.findElement(By.cssSelector("#accordian a[href='#Women']"));
    }

    public WebElement getMenCategory() {
        return driver.findElement(By.cssSelector("#accordian a[href='#Men']"));
    }


    // Child
    public List<WebElement> getChildCategories(String parentName) {
        return driver.findElements(By.cssSelector("#" + parentName + " a"));
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
            if (normalizeText(name).equals(normalizeText(productName))) {
               return product.findElement(By.cssSelector(".add-to-cart"));
            }
        }
        return null;
    }

    //********************************* FILTERI ******************************

    public void clickOnChildCategory(String parentName, String childName) {

        for (WebElement child: getChildCategories(parentName)) {
            if (normalizeText(child.getText()).equals(normalizeText(childName))) {
                child.click();
                break;
            }
        }
    }

    //************************* Verification ******************************

    // Metoda za proveru child kategorija da li su svi child elementi prisutni
    public boolean verifyChildCategories(String parentName, String[] expectedChildren) {
        List<String> actualChildren = new ArrayList<>();

        for (WebElement child: getChildCategories(parentName)) {
            actualChildren.add(normalizeText(child.getText()));
        }
        for (String expected:expectedChildren) {
            if (!actualChildren.contains(normalizeText(expected))) {
                return false;
            }
        }
        return true;
    }

    // Metoda za proveru nakon filtriranja po nazivu (keyword) u reci proizvoda
    public boolean verifyProductsMatchCategory(String keyword) {
        List<WebElement> productList = getProducts();

        if (productList.isEmpty()) {
            return false;
        }

        for (WebElement product: productList) {
            if (!normalizeText(product.getText()).contains(normalizeText(keyword))) {
                return false;
            }
        }
        return true;
    }


    // ****************** Utility ****************************

    // Hvala Deki!!
    private String normalizeText(String text) {
        return text.replaceAll("\\s+", "").trim().toLowerCase();
    }


}
