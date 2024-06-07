package klykadan.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FavouritesPage {
    private WebDriver driver;

    @FindBy(css = "#k-item-container > article")
    private List<WebElement> favouriteProducts;

    public FavouritesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean verifyFavouritesProducts(String expectedProductName) {
        for (WebElement result : favouriteProducts) {
            if (result.getText().contains(expectedProductName)) {
                return true;
            }
        }
        return false;
    }
}
