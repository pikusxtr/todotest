package todotest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import todotest.config.Configuration;

public abstract class TestBase {
  WebDriver driver;

  @BeforeAll
  public static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void setupTest() {
    driver = new ChromeDriver();
    driver.get(Configuration.BASE_URL);
  }

  @AfterEach
  public void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
