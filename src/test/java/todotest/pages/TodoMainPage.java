package todotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static todotest.config.Configuration.TIME_OUT_IN_SECONDS;

public class TodoMainPage {
  WebDriver driver;
  WebDriverWait wait;

  public TodoMainPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
  }

  private final By todoInputLocator = By.cssSelector("input.new-todo");
  private final By listElementLabelLocator = By.cssSelector("ul.todo-list>li label");

  public void createListElements(List<String> elementsToCreate) {
    elementsToCreate.forEach(element -> {
      WebElement todoInput = getVisibleElementWithWait(todoInputLocator);
      todoInput.sendKeys(element);
      todoInput.sendKeys(Keys.RETURN);
    });
  }

  public List<String> getListElementNames() {
    List<WebElement> elementsList = getVisibleElementsWithWait(listElementLabelLocator);
    return elementsList
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
  }


  private WebElement getVisibleElementWithWait(By locator) {
    return wait
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  private List<WebElement> getVisibleElementsWithWait(By locator) {
    return wait
            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }
}
