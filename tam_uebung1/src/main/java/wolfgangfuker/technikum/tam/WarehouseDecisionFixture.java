package wolfgangfuker.technikum.tam;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SystemClock;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WarehouseDecisionFixture {
    private WebDriver driver;

    private String articleType;
    private String articleName;
    private String userCode;
    private String amount;

    public void beginTable() {
        System.setProperty("webdriver.gecko.driver", "D:\\FH\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1/warehouse/WareHouse.php");
    }

    public void setArticleType(String type) {
        this.articleType = type;
        System.out.println("ArticleType set:" + type);
    }

    public boolean loaded() {
        driver.findElement(By.name("Query_types")).click();
        try {
            new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText(articleType);
        } catch (NoSuchElementException e) {
            return false;
        } catch (UnreachableBrowserException e2) {
            return false;
        }
        return true;
    }

    public String articleNamePresent() {

//        elements.stream()
//                .filter()
        return "Newsweek";
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
        System.out.println("Usercode set:" + userCode);
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
        System.out.println("ArticleName=" + articleName);
    }

    public boolean articleLoaded() {
        driver.findElement(By.name("Query_types")).click();
        new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText(articleType);
        driver.findElement(By.id("UserCode")).clear();
        driver.findElement(By.id("UserCode")).sendKeys(userCode);
        driver.findElement(By.name("Query_articles")).click();

        List<WebElement> elements = driver.findElements(By.xpath("//tr/td[@style='text-align:left']"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
            if (element.getText().equals(articleName)) {
                return true;
            }
        }
        return false;
    }

    public boolean articleWrongLoaded() {
        driver.findElement(By.id("UserCode")).clear();
        driver.findElement(By.id("UserCode")).sendKeys(userCode);
        driver.findElement(By.name("Query_articles")).click();

        List<WebElement> elements = driver.findElements(By.xpath("//tr/td[@style='text-align:left']"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
            if (element.getText().equals(articleName)) {
                return true;
            }
        }
        return false;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String output() {
        driver.findElement(By.name("Query_types")).click();
        new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText(articleType);
        driver.findElement(By.id("UserCode")).clear();
        driver.findElement(By.id("UserCode")).sendKeys(userCode);
        driver.findElement(By.name("Query_articles")).click();

        driver.findElement(By.xpath("//tr[td/text() = \"" + articleName + "\"]/td/input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//tr[td/text() = \"" + articleName + "\"]/td/input[@type='text']")).clear();
        driver.findElement(By.xpath("//tr[td/text() = \"" + articleName + "\"]/td/input[@type='text']")).sendKeys(amount);

        driver.findElement(By.name("Buy_articles")).click();
        String result=driver.findElement(By.xpath("//tr[6]/td[@colspan='2'][not(./table)]")).getText();

        return result;
    }

    public void endTable() {
        driver.quit();
    }
}
