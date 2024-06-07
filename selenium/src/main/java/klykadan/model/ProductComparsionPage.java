package klykadan.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductComparsionPage {
    WebDriver driver;

    @FindBy(css = "#compare > div.theader > div.dragscroll.syncscroll")
    private List<WebElement> compProducts;

    public ProductComparsionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyCompProducts(String expectedProductName1, String expectedProductName2) {
        for (WebElement result : compProducts) {
            if (result.getText().contains(expectedProductName1) && result.getText().contains(expectedProductName2)) {
                return true;
            }
        }
        return false;
    }
}
