package klykadan.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalInformationPage {
    private WebDriver driver;

    @FindBy(css = "#User_FirstName")
    private WebElement nameField;

    @FindBy(css = "#User_LastName")
    private WebElement surnameField;

    @FindBy(css = "#User_Phone")
    private WebElement phoneField;

    @FindBy(css = "#User_Email")
    private WebElement emailField;

    @FindBy(css = "#delivery-and-payment > div > div.navigation.pi > div:nth-child(2) > button")
    private WebElement makeOrderButton;

    public PersonalInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickMakeOrderButton(){
        waitForElementVisibility(makeOrderButton);
        makeOrderButton.click();
    }

    public void fillPersonalInfo(String name, String surname, String phone, String email){
        waitForElementVisibility(nameField);
        waitForElementVisibility(surnameField);
        waitForElementVisibility(phoneField);
        waitForElementVisibility(emailField);

        nameField.sendKeys(name);
        surnameField.sendKeys(surname);
        phoneField.sendKeys(phone);
        emailField.sendKeys(email);

//        clickMakeOrderButton();
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

}
