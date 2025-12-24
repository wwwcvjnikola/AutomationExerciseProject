package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WriteReviewPage {

    WebDriver driver;

    WebElement yourName;
    WebElement emailAddress;
    WebElement addReviewHere;
    WebElement submitButton;
    WebElement confirmationMsg;

    public WriteReviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getYourName() {
        return driver.findElement(By.id("name"));
    }

    public WebElement getEmailAddress() {
        return driver.findElement(By.id("email"));
    }

    public WebElement getAddReviewHere() {
        return driver.findElement(By.id("review"));
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.id("button-review"));
    }

    public WebElement getConfirmationMsg() {
        return driver.findElement(By.cssSelector("div.alert-success"));
    }

    //******************************************************

    // kako bismo odlucili (po imenu proizvoda) na koji review da kliknemo
    public void clickOnReviewProductButton(String productName) {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-image-wrapper"));

        for (WebElement product : products) {

            String name = product.findElement(By.tagName("p")).getText();

            if (name.equalsIgnoreCase(productName)) {

                WebElement reviewProductButton = product.findElement(By.linkText("View Product"));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", reviewProductButton);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(reviewProductButton));
                wait.until(ExpectedConditions.elementToBeClickable(reviewProductButton));

                reviewProductButton.click();
                break;
            }
        }
    }

    public void enterName(String name) {
        getYourName().clear();
        getYourName().sendKeys(name);
    }

    public void enterEmail (String email) {
        getEmailAddress().clear();
        getEmailAddress().sendKeys(email);
    }

    public void enterReview (String review) {
        getAddReviewHere().clear();
        getAddReviewHere().sendKeys(review);
    }

    public void clickSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", getSubmitButton());

        wait.until(ExpectedConditions.visibilityOf(getSubmitButton()));
        wait.until(ExpectedConditions.elementToBeClickable(getSubmitButton()));

        getSubmitButton().click();
    }

}
