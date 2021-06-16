package todotest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static todotest.config.Configuration.TIME_OUT_IN_SECONDS;

public class TodoMainPage {
  @FindBy(css = "input.new-todo")
  WebElement todoInput;

  @FindAll({@FindBy(css = "ul.todo-list>li label")})
  List<WebElement> listElementLabels;

  WebDriver driver;
  WebDriverWait wait;

  public TodoMainPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    PageFactory.initElements(driver, this);
  }

  public void createListElements(List<String> elementsToCreate) {
    elementsToCreate.forEach(element -> {
      WebElement todoInput = getVisibleElementWithWait(this.todoInput);
      todoInput.sendKeys(element);
      todoInput.sendKeys(Keys.RETURN);
    });
  }

  public List<String> getListElementNames() {
    List<WebElement> elementsList = getVisibleElementsWithWait(listElementLabels);
    return elementsList
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
  }


  private WebElement getVisibleElementWithWait(WebElement webElement) {
    return wait
            .until(ExpectedConditions.visibilityOf(webElement));
  }

  private List<WebElement> getVisibleElementsWithWait(List<WebElement> webElements) {
    return wait
            .until(ExpectedConditions.visibilityOfAllElements(webElements));
  }
}
