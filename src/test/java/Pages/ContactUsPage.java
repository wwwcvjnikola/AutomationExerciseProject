package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage {

    WebDriver driver;

    WebElement GetInTouchTitle;
    WebElement contactUsTitle;
    WebElement nameLabel;
    WebElement emailLabel;
    WebElement SubjectField;
    WebElement messageField;
    WebElement uploadBtn;
    WebElement submitBtn;

    WebElement successMsg;
    WebElement homeButton;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getGetInTouchTitle() {
        return driver.findElement(By.cssSelector("div.contact-form > h2.title.text-center"));
    }

    public WebElement getContactUsTitle() {
        return driver.findElement(By.cssSelector("div.col-sm-12 > h2.title.text-center"));
    }

    public WebElement getNameLabel() {
        return driver.findElement(By.cssSelector("input[name='name']"));
    }

    public WebElement getEmailLabel() {
        return driver.findElement(By.cssSelector("input[name='email']"));
    }

    public WebElement getSubjectField() {
        return driver.findElement(By.cssSelector("input[name='subject']"));
    }

    public WebElement getMessageField() {
        return driver.findElement(By.id("message"));
    }

    public WebElement getUploadBtn() {
        return driver.findElement(By.cssSelector("input[name='upload_file']"));
    }

    public WebElement getSubmitBtn() {
        return driver.findElement(By.cssSelector(".btn.btn-primary.pull-left.submit_form"));
    }

    public WebElement getSuccessMsg() {
        return driver.findElement(By.cssSelector("div.alert-success"));
    }

    public WebElement getHomeButton() {
        return driver.findElement(By.cssSelector("a.btn.btn-success"));
    }

    //********************************************************

    public void inputName(String Name){
        getNameLabel().clear();
        getNameLabel().sendKeys(Name);
    }

    public void emailInput(String Email) {
        getEmailLabel().clear();
        getEmailLabel().sendKeys(Email);
    }

    public void subjectInput(String Subject) {
        getSubjectField().clear();
        getSubjectField().sendKeys(Subject);
    }

    public void messageInput(String Message) {
        getMessageField().clear();
        getMessageField().sendKeys(Message);
    }

    public void uploadFile(String filePath){
        getUploadBtn().sendKeys(filePath);
    }

    public void clickSubmitBtn() {
        getSubmitBtn().click();
    }

}
