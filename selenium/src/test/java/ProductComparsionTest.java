import klykadan.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductComparsionTest {
    private WebDriver driver;
    private SearchPage searchPage;
    private MainPage mainPage;
    private ProductComparsionPage comparsionPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
        comparsionPage = new ProductComparsionPage(driver);
        driver.get("https://kytary.cz/?gad_source=1&gclid=Cj0KCQjwjLGyBhCYARIsAPqTz1_I2tkquQv-W7Y4wYoDbXRVmG1fFwaiLJlXr42i1zPrmxDD0qiMOvoaAl5_EALw_wcB");
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddProductsToComparison(){
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct("BLOND STR-1H BK");
        mainPage.clickSearchButton();
        searchPage.scrollDown(600);
        searchPage.clickAddToComparsionButton();

        searchPage.searchProduct("UCOOLELE UC-002-BL");
        searchPage.clickSearchButton();
        searchPage.scrollDown(600);
        searchPage.clickAddToComparsionButton();
        searchPage.goToCompPage();
        Assertions.assertTrue(comparsionPage.verifyCompProducts("BLOND STR-1H BK", "UCOOLELE UC-002-BL"));
    }
}
