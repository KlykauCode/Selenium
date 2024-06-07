package klykadan.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    @FindBy(css = "#cpModal > div > div > div > div.btns > a.btn.btn-success")
    private WebElement acceptCookiesButton;

    @FindBy(css = "body > header > div > div.container > div.col-7.order-2.order-lg-last.offset-lg-1.offset-var-xl-0.offset-xxl-1.m-icons > a:nth-child(3) > span.desc")
    private WebElement loginButton;

    @FindBy(css = "#Login_LoginName")
    private WebElement emailButton;

    @FindBy(css = "#Login_Password")
    private WebElement passwordButton;

    @FindBy(css = "#loginModal > div > div > div > div:nth-child(1) > form > div.v-flex-xssm.h-stretch-flex-md > input")
    private WebElement prihlasitButton;

    @FindBy(css = "body > header > div > div.container > div.col-12.col-lg-5.col-xl-4.order-last.order-lg-3.m-search > div > input[type=search]")
    private WebElement searchField;

    @FindBy(css = "body > header > div > div.container > div.col-12.col-lg-5.col-xl-4.order-last.order-lg-3.m-search > div > button")
    private WebElement searchButton;

    @FindBy(css = "#loginModal > div > div > div > div:nth-child(1) > form > div.validation-error")
    private WebElement invalidValidation;

    @FindBy(css = "body > header > div > div.container > div.col-7.order-2.order-lg-last.offset-lg-1.offset-var-xl-0.offset-xxl-1.m-icons > a:nth-child(3)")
    private WebElement myProfileButton;

    @FindBy(css = "#profileModal > div > div > div > ul > li:nth-child(2) > a")
    private WebElement goToMyProfileButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptCookiesButton() {
        waitForElementVisibility(acceptCookiesButton);
        acceptCookiesButton.click();
    }

    public void loginWithCsv(String email, String password) {
        clickLoginButton();
        enterCredentials(email, password);
    }

    private void clickLoginButton() {
        waitForElementVisibility(loginButton);
        loginButton.click();
    }

    public void searchProduct(String productName){
        waitForElementVisibility(searchField);
        searchField.sendKeys(productName);
    }
    public void clickSearchButton(){
        waitForElementVisibility(searchButton);
        searchButton.click();
    }
    public boolean isLoginFailed() {
        try {
            return invalidValidation.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    private void enterCredentials(String email, String password) {
        waitForElementVisibility(emailButton);
        waitForElementVisibility(passwordButton);
        waitForElementVisibility(prihlasitButton);
        emailButton.sendKeys(email);
        passwordButton.sendKeys(password);
        prihlasitButton.click();
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
