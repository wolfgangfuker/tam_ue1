package wolfgangfuker.technikum.tam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class WarehouseScriptFixture {
    private WebDriver driver;

    public void beginTable() {
        System.setProperty("webdriver.gecko.driver", "D:\\Privat\\Installs\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1/warehouse/WareHouse.php");
    }

    public void loadArticleTypes() {
        beginTable();
        driver.findElement(By.name("Query_types")).click();
    }

    public void queryArticlesWithUserCode(String userCode) {
        driver.findElement(By.id("UserCode")).clear();
        driver.findElement(By.id("UserCode")).sendKeys(userCode);
    }

    public void selectArticleType(String articleType) {
        new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText(articleType);
        driver.findElement(By.name("Query_articles")).click();
    }

    public void buyArticleWithAmount(String article, String amount) {
        driver.findElement(By.id("c1")).click();
        driver.findElement(By.id("n1")).clear();
        driver.findElement(By.id("n1")).sendKeys(amount);
    }

    public String resultStatus() {
        driver.findElement(By.name("Buy_articles")).click();
        String result = driver.findElement(By.xpath("//tr[6]/td")).getText();
        endTable();
        return result;
    }

    public void endTable(){
        driver.quit();
    }
}
