import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Testcase1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://127.0.0.1/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void whenBuyingOneBook_thenOrderIsProvisioned() throws Exception {
    driver.get(baseUrl + "/warehouse/WareHouse.php");
    driver.findElement(By.name("Query_types")).click();
    driver.findElement(By.id("UserCode")).clear();
    driver.findElement(By.id("UserCode")).sendKeys("10000");
    new Select(driver.findElement(By.name("ArticleType"))).selectByVisibleText("BOOK");
    driver.findElement(By.name("Query_articles")).click();
    driver.findElement(By.id("c1")).click();
    driver.findElement(By.id("n1")).clear();
    driver.findElement(By.id("n1")).sendKeys("1");
    driver.findElement(By.name("Buy_articles")).click();
    assertEquals("Orders fulfilled = 1 Response #:", driver.findElement(By.xpath("//tr[6]/td")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
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
