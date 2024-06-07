package klykadan.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    private WebDriver driver;

    @FindBy(css = "#cart > div:nth-child(2) > div.cart-item > div.name")
    private List<WebElement> cartProducts;

    @FindBy(css = "#button-next")
    private WebElement continueOrderButton;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }
    public boolean verifyCartProducts(String expectedProductName) {
        for (WebElement result : cartProducts) {
            if (result.getText().contains(expectedProductName)) {
                return true;
            }
        }
        return false;
    }
    public void clickContinueOrderButton(){
        waitForElementVisibility(continueOrderButton);
        continueOrderButton.click();
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
