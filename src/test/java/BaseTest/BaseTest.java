package BaseTest;

import Pages.CartPage;
import Pages.ContactUsPage;
import Pages.HomePage;
import Pages.WriteReviewPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import java.io.IOException;


public class BaseTest {

    public WebDriver driver;

    public HomePage homePage;
    public ContactUsPage contactUsPage;
    public CartPage cartPage;
    public WriteReviewPage writeReviewPage;

    public ExcelReader webExcelReader;
    public ExcelReader uploadFileReader;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        webExcelReader = new ExcelReader("C:\\Users\\Katarina\\IdeaProjects\\ProjekatAutomationExercise\\src\\test\\resources\\ProjekatTabela.xlsx");
        uploadFileReader = new ExcelReader("C:\\Users\\Katarina\\IdeaProjects\\ProjekatAutomationExercise\\src\\test\\resources\\UploadFile.xlsx");
    }

    // Scroll
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    //Za proveru i preskakanje elementa koji nije prikazan
    public boolean isDisplayed(WebElement element) {
        boolean elementisDisplayed = false;
        try {
            elementisDisplayed = element.isDisplayed();
        } catch (Exception e){

        }
        return elementisDisplayed;
    }
}
