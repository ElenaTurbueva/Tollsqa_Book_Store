package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BooksPage extends BasePage {

    @FindBy(css = ".rt-table .rt-tr-group")
    private List<WebElement> bookRows;

    public BooksPage(WebDriver driver) {
        super(driver);
    }

    public void addBooksToCollection(int count) {
        for (int i = 0; i < count; i++) {
            WebElement addButton = bookRows.get(i).findElement(By.cssSelector(".text-right.fullButton button"));
            addButton.click();
            driver.switchTo().alert().accept();
            try {
                Thread.sleep(1000); // Задержка для ожидания алерта
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}