package klykadan.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    @FindBy(css = "body > main > div > div > div")
    private WebElement invalidSearching;

    @FindBy(css = "#k-item-container > article")
    private WebElement productArticle;

    @FindBy(css = "#k-item-container > article > a > span.title")
    private List<WebElement> searchResults;

    @FindBy(css = "#k-item-container > article > span.ctrl.touch-f > button.acart")
    private WebElement addToCartButton;

    @FindBy(css = "#k-item-container > article:nth-child(2) > span.ctrl.touch-f.active > button.afav")
    private WebElement addToFavouritesButton;

    @FindBy(css = "#cartProductsModal > div > div > div > div.btns > a.btn.btn-success")
    private WebElement goToCartButton;

    @FindBy(css = "#favProductsModal > div > div > div > div.btns")
    private WebElement goToFavButton;

    @FindBy(css = "#cart-icon")
    private WebElement cartIcon;

    @FindBy(css = "#fav-icon")
    private WebElement favIcon;

    @FindBy(css = "#comp-icon > span.msk.msk-compare")
    private WebElement comparsionIcon;

    @FindBy(css = "#compProductsModal > div > div > a")
    private WebElement goToCompButton;

    @FindBy(xpath = "/html/body/main/div/div[1]/div[2]/div[2]/div[1]/section/article/span[2]/button[1]")
    private WebElement addToComparsionButton;

    @FindBy(css = "body > header > div > div.container > div.col-12.col-lg-5.col-xl-4.order-last.order-lg-3.m-search > div > input[type=search]")
    private WebElement searchField;

    @FindBy(css = "body > header > div > div.container > div.col-12.col-lg-5.col-xl-4.order-last.order-lg-3.m-search > div > button")
    private WebElement searchButton;

    @FindBy(css = "#cart > div:nth-child(2) > div.cart-item > button")
    private WebElement deleteFromCart;

    @FindBy(css = "body > main > div > div.empty-cart")
    private WebElement emptyCart;

    @FindBy(xpath = "/html/body/main/div/div[1]/div[3]/div[1]/div/form/div[1]/div[1]/div/div[1]/div/div[1]/div")
    private WebElement leftSlider;

    @FindBy(xpath = "/html/body/main/div/div[1]/div[3]/div[1]/div/form/div[1]/div[1]/div/div[1]/div/div[2]/div")
    private WebElement rightSlider;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public void searchProduct(String productName) {
        waitForElementVisibility(searchField);
        searchField.sendKeys(productName);
    }

    public void clickSearchButton() {
        waitForElementToBeClickable(searchButton);
        searchButton.click();
    }

    public void slidePrices(int leftPercentage, int rightPercentage) {
        waitForElementVisibility(leftSlider);
        waitForElementVisibility(rightSlider);
        waitForElementToBeClickable(leftSlider);
        waitForElementToBeClickable(rightSlider);

        Actions move = new Actions(driver);
        move.clickAndHold(leftSlider).moveByOffset(leftPercentage, 0).release().perform();
        move.clickAndHold(rightSlider).moveByOffset(rightPercentage, 0).release().perform();
    }

    public void clickAddButton() {
        Actions builder = new Actions(driver);
        waitForElementToBeClickable(productArticle);
        builder.moveToElement(productArticle).perform();
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
    }

    public void clickDeleteFromCartButton() {
        waitForElementVisibility(deleteFromCart);
        deleteFromCart.click();
    }

    public void clickAddToFavouritesButton() {
        Actions builder = new Actions(driver);
        waitForElementVisibility(productArticle);
        builder.moveToElement(productArticle).perform();
        waitForElementVisibility(addToFavouritesButton);
        waitForElementToBeClickable(addToFavouritesButton);
        addToFavouritesButton.click();
    }

    public void clickAddToComparsionButton() {
        Actions builder = new Actions(driver);
        waitForElementVisibility(productArticle);
        builder.moveToElement(productArticle).perform();
        waitForElementVisibility(addToComparsionButton);
        waitForElementToBeClickable(addToComparsionButton);
        addToComparsionButton.click();
    }

    public boolean verifySearchResults(String expectedProductName) {
        for (WebElement result : searchResults) {
            if (result.getText().contains(expectedProductName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSearchingFailed() {
        try {
            return invalidSearching.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isCartEmpty() {
        try {
            waitForElementVisibility(emptyCart);
            return emptyCart.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void sleepThreadWait() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void goToCartPage() {
        Actions builder = new Actions(driver);
        waitForElementToBeClickable(cartIcon);
        builder.moveToElement(cartIcon).perform();
        waitForElementToBeClickable(goToCartButton);
        goToCartButton.click();
    }

    public void goToFavouritesPage() {
        Actions builder = new Actions(driver);
        waitForElementVisibility(favIcon);
        builder.moveToElement(favIcon).perform();
        waitForElementVisibility(goToFavButton);
        goToFavButton.click();
    }

    public void goToCompPage() {
        Actions builder = new Actions(driver);
        waitForElementVisibility(comparsionIcon);
        builder.moveToElement(comparsionIcon).perform();
        waitForElementVisibility(goToCompButton);
        goToCompButton.click();
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
