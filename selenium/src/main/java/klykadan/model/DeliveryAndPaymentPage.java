package klykadan.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeliveryAndPaymentPage {
    private WebDriver driver;

    @FindBy(css = "#delivery-options > label:nth-child(1)")
    private WebElement deliveryButton;

    @FindBy(css = "#P_DOB_1")
    private WebElement paymentButton;

    @FindBy(css = "#button-next")
    private WebElement nextButton;

    public DeliveryAndPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickDlivery(){
        waitForElementVisibility(deliveryButton);
        deliveryButton.click();
    }
    public void clickPayment(){
        waitForElementVisibility(paymentButton);
        paymentButton.click();
    }
    public void goToPersonalInfoPage(){
        clickDlivery();
        clickPayment();
        waitForElementVisibility(nextButton);
        nextButton.click();
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }
}
