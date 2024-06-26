package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy(css = ".rt-table .rt-tr-group")
    private List<WebElement> bookRows;

    @FindBy(id = "delete-record-undefined")
    private WebElement deleteAllBooksButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCollectionEmpty() {
        return bookRows.isEmpty();
    }

    public int getBooksCount() {
        return bookRows.size();
    }

    public void deleteAllBooks() {
        deleteAllBooksButton.click();
    }
}
