package Tests;

import BaseTest.BaseTest;
import Pages.ContactUsPage;
import Pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContactUsTest extends BaseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://automationexercise.com/");

        homePage = new HomePage(driver);
        homePage.clickOnNavBarCard("Contact us");
        contactUsPage = new ContactUsPage(driver);

    }

    @Test (priority = 10)
    public void pageTest(){
        String expectedURL = "https://automationexercise.com/contact_us";
        String expectedFormTitle1 = "CONTACT US";
        String expectedFormTitle2 = "GET IN TOUCH";

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertEquals(contactUsPage.getContactUsTitle().getText(), expectedFormTitle1);
        Assert.assertEquals(contactUsPage.getGetInTouchTitle().getText(), expectedFormTitle2);
    }

    @Test (priority = 20)
    public void formInput() throws InterruptedException {
        for (int i=1; i<= webExcelReader.getLastRow("ContactUs"); i++) {

            String name = webExcelReader.getStringData("ContactUs", i, 0);
            String email = webExcelReader.getStringData("ContactUs", i, 1);
            String subject = webExcelReader.getStringData("ContactUs", i, 2);
            String message = webExcelReader.getStringData("ContactUs", i, 3);

            wait.until(ExpectedConditions.visibilityOf(contactUsPage.getNameLabel()));

            // popunjavanje forme nakon cega ide i upload
            contactUsPage.inputName(name);
            contactUsPage.emailInput(email);
            contactUsPage.subjectInput(subject);
            contactUsPage.messageInput(message);

            Thread.sleep(2000);


            // putanja fajla
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/UploadFile.xlsx";

            // Upload
            contactUsPage.uploadFile(filePath);

            // scroll do submit Btn
            scrollToElement(contactUsPage.getSubmitBtn());
            contactUsPage.clickSubmitBtn();

            // Popup koji izlazi nakon submit-a
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            String expectedMsg = "Success! Your details have been submitted successfully.";
            WebElement homeBtn = contactUsPage.getHomeButton();
            String homeUrl = "https://automationexercise.com/";

            Assert.assertEquals(contactUsPage.getSuccessMsg().getText().trim(), expectedMsg);
            Assert.assertTrue(homeBtn.isDisplayed());
            wait.until(ExpectedConditions.elementToBeClickable(homeBtn));
            homeBtn.click();
            Assert.assertEquals(driver.getCurrentUrl(), homeUrl);

        }
    }
}
