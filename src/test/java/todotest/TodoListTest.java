package todotest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todotest.pages.TodoMainPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoListTest extends TestBase {
  private static final String TODO_ELEM_ONE_NAME = "Element 01";
  private static final String TODO_ELEM_TWO_NAME = "Element 02";
  private static final String TODO_ELEM_THREE_NAME = "Element 03";
  private static final List<String> todoElements = List.of(TODO_ELEM_ONE_NAME, TODO_ELEM_TWO_NAME, TODO_ELEM_THREE_NAME);
  private TodoMainPage todoMainPage;

  @BeforeEach
  void setUpInTest() {
    todoMainPage = new TodoMainPage(driver);
  }

  @Test
  public void threeCreatedElementsDisplayedOnTodoList() {
    todoMainPage.createListElements(todoElements);
    List<String> todoListElementNames = todoMainPage.getListElementNames();

    assertThat(todoListElementNames).hasSize(3);
    assertThat(todoListElementNames).containsExactly(todoListElementNames.toArray(new String[0]));
  }

}
