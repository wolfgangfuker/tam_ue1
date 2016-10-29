import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class TestCase1 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Test
    public void whenBuyingOneBook_thenOrderIsProvisioned() throws Exception {
        driver.get(baseUrl + "/Warehouse/WareHouse.php");
        driver.findElement(By.name("Query_types")).click();
        driver.findElement(By.id("UserCode")).clear();
        driver.findElement(By.id("UserCode")).sendKeys("10000");
        new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText("BOOK");
        driver.findElement(By.name("Query_articles")).click();
        driver.findElement(By.id("c1")).click();
        driver.findElement(By.id("n1")).clear();
        driver.findElement(By.id("n1")).sendKeys("1");
        driver.findElement(By.name("Buy_articles")).click();
        assertThat(isElementPresent(By.xpath("//tr[6]/td"))).isTrue();
        assertThat(driver.findElement(By.xpath("//tr[6]/td")).getText()).contains("Orders fulfilled =", "Response #:");
//        assertThat("Orders fulfilled = 0 Response #:1000").isEqualTo(driver.findElement(By.xpath("//tr[6]/td")).getText());
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
