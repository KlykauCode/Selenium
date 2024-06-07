import klykadan.model.MainPage;
import klykadan.model.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SearchTest {

    private WebDriver driver;
    private MainPage mainPage;
    private SearchPage searchPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
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
    public void searchTest(String productName) {
        mainPage.clickAcceptCookiesButton();
       mainPage.searchProduct(productName);
       mainPage.clickSearchButton();
       searchPage.scrollDown(500);
        Assertions.assertTrue(searchPage.verifySearchResults(productName));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidSearchData.csv", numLinesToSkip = 1)
    public void searchInvalidTest(String productName) {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct(productName);
        mainPage.clickSearchButton();
        searchPage.scrollDown(500);
        Assertions.assertTrue(searchPage.isSearchingFailed());
    }
}
