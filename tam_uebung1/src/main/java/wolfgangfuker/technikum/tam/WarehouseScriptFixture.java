package wolfgangfuker.technikum.tam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WarehouseScriptFixture {
    private WebDriver driver;
    public void beginTable() {
        //C:\Users\basti\Documents\TAM
        System.setProperty("webdriver.gecko.driver", "D:\\FH\\geckodriver.exe");
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
        driver.findElement(By.name("Query_articles")).click();

    }

    public void selectArticleType(String articleType) {
        new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText(articleType);

    }

    public void buyArticleWithAmount(String article, String amount) {
        driver.findElement(By.xpath("//tr[td/text() = \"Newsweek\"]/td/input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//tr[td/text() = \"Newsweek\"]/td/input[@type='text']")).clear();
        driver.findElement(By.xpath("//tr[td/text() = \"Newsweek\"]/td/input[@type='text']")).sendKeys(amount);
    }

    public String resultStatus() {
        driver.findElement(By.name("Buy_articles")).click();
        String result=driver.findElement(By.xpath("//tr[6]/td[@colspan='2'][not(./table)]")).getText();
        endTable();
        return result;
    }

    public void endTable(){
        driver.quit();
    }
}
