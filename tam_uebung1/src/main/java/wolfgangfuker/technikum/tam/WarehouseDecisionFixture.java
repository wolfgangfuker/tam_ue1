package wolfgangfuker.technikum.tam;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class WarehouseDecisionFixture {
    private WebDriver driver;

    private String articleType;

    public void beginTable() {
        System.setProperty("webdriver.gecko.driver", "D:\\FH\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1/warehouse/WareHouse.php");
    }

    public void setArticleType(String type) {
        this.articleType = type;

    }

    public boolean loaded() {
        driver.findElement(By.name("Query_types")).click();
        try {
            new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText(articleType);
        } catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void endTable() {
        driver.quit();
    }
}
