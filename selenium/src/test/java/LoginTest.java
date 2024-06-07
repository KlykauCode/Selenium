import klykadan.model.MainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        driver.get("https://kytary.cz/?gad_source=1&gclid=Cj0KCQjwjLGyBhCYARIsAPqTz1_I2tkquQv-W7Y4wYoDbXRVmG1fFwaiLJlXr42i1zPrmxDD0qiMOvoaAl5_EALw_wcB");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testLogin(String email, String password) {
        mainPage.clickAcceptCookiesButton();
        mainPage.loginWithCsv(email, password);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidLoginData.csv", numLinesToSkip = 1)
    public void testInvalidLogin(String email, String password) {
        mainPage.clickAcceptCookiesButton();
        mainPage.loginWithCsv(email, password);
        Assertions.assertTrue(mainPage.isLoginFailed());
    }
}
