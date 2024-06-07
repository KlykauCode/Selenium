import klykadan.model.MainPage;
import klykadan.model.SearchPage;
import klykadan.model.ShoppingCartPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShoppingCartTest {
    private WebDriver driver;
    private MainPage mainPage;
    private SearchPage searchPage;
    private ShoppingCartPage shoppingCartPage;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        driver.get("https://kytary.cz/?gad_source=1&gclid=Cj0KCQjwjLGyBhCYARIsAPqTz1_I2tkquQv-W7Y4wYoDbXRVmG1fFwaiLJlXr42i1zPrmxDD0qiMOvoaAl5_EALw_wcB");
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/searchData.csv", numLinesToSkip = 1)
    public void addToCartTest(String productName) {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct(productName);
        mainPage.clickSearchButton();
        searchPage.scrollDown(600);
        searchPage.clickAddButton();
        searchPage.goToCartPage();
        Assertions.assertTrue(shoppingCartPage.verifyCartProducts(productName));
    }

    @Test
    public void deleteFromCartTest() {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct("BLOND STR-1H BK");
        mainPage.clickSearchButton();
        searchPage.scrollDown(600);
        searchPage.clickAddButton();
        searchPage.goToCartPage();

        searchPage.clickDeleteFromCartButton();
        Assertions.assertTrue(searchPage.isCartEmpty());
    }
}
