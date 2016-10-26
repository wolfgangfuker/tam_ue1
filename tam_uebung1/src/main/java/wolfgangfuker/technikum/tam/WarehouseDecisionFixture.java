package wolfgangfuker.technikum.tam;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class WarehouseDecisionFixture {
    private WebDriver driver;

    private String articleType;

    public void beginTable() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.gecko.driver", "D:/Privat/Installs/geckodriver.exe");

        //Now you can Initialize marionette driver to launch firefox
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new MarionetteDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String baseUrl = "http://127.0.0.1/";
        driver.get(baseUrl + "/warehouse/WareHouse.php");
    }

    public void setArticleType(String type) {
        this.articleType = type;

    }

    public void endTable() {
        driver.quit();
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
}
