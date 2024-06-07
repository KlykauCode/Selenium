import klykadan.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MakeOrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private SearchPage searchPage;
    private ShoppingCartPage shoppingCartPage;
    private DeliveryAndPaymentPage deliveryAndPaymentPage;
    private PersonalInformationPage personalInformationPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        deliveryAndPaymentPage = new DeliveryAndPaymentPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
        driver.get("https://kytary.cz/?gad_source=1&gclid=Cj0KCQjwjLGyBhCYARIsAPqTz1_I2tkquQv-W7Y4wYoDbXRVmG1fFwaiLJlXr42i1zPrmxDD0qiMOvoaAl5_EALw_wcB");
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/personalInfoData.csv", numLinesToSkip = 1)
    public void makeOrderTest(String name, String surname, String phone, String email) {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct("UCOOLELE UC-002-BL");
        mainPage.clickSearchButton();
        searchPage.scrollDown(600);
        searchPage.clickAddButton();
        searchPage.goToCartPage();
        shoppingCartPage.scrollDown(900);
        shoppingCartPage.clickContinueOrderButton();
        deliveryAndPaymentPage.goToPersonalInfoPage();
        personalInformationPage.fillPersonalInfo(name, surname, phone, email);
    }
}
