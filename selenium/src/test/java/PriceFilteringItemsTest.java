import klykadan.model.FavouritesPage;
import klykadan.model.MainPage;
import klykadan.model.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceFilteringItemsTest {
    private WebDriver driver;
    private MainPage mainPage;
    private SearchPage searchPage;
    private FavouritesPage favouritesPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
        favouritesPage = new FavouritesPage(driver);
        driver.get("https://kytary.cz/?gad_source=1&gclid=Cj0KCQjwjLGyBhCYARIsAPqTz1_I2tkquQv-W7Y4wYoDbXRVmG1fFwaiLJlXr42i1zPrmxDD0qiMOvoaAl5_EALw_wcB");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Check price sliders and checkboxes")
    public void checkPriceFilterAndCheckboxes() {
        String requestName = "guitar";
        int priceFrom = 530;
        int priceTo = 3490;

        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct(requestName);
        mainPage.clickSearchButton();
        searchPage.scrollDown(400);

        searchPage.slidePrices(63, -50);

        getSearchResults(priceFrom, priceTo);
    }

    private void getSearchResults(int priceFrom, int priceTo) {
        sleepThreadWait();
        List<WebElement> prices = driver.findElements(By.cssSelector(".product-list article .price .sell"));
        for (WebElement priceElement : prices) {
            String priceText = priceElement.getText().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceText);

            assertTrue(price >= priceFrom && price <= priceTo, "Price " + price + "are not same from  " + priceFrom + " to" + priceTo);

        }
    }
    private void sleepThreadWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}