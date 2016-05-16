import org.sql2o.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      String deleteClientsQuery = "DELETE FROM clients *;";
      con.createQuery(deleteStylistsQuery).executeUpdate();
      con.createQuery(deleteClientsQuery).executeUpdate();
    }
  }

  // @Test
  // public void rootTest() {
  //   goTo("http://localhost:4567/");
  //   assertThat(pageSource()).contains("Stylist list!");
  // }
  //
  // @Test
  // public void stylistIsCreatedTest() {
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Add a new stylist"));
  //   fill("#styleName").with("Mow the lawn");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Your stylist has been saved.");
  // }
  //
  // @Test
  // public void stylistIsDisplayedTest() {
  //   goTo("http://localhost:4567/stylists/new");
  //   fill("#styleName").with("Mow the lawn");
  //   submit(".btn");
  //   click("a", withText("View stylists"));
  //   assertThat(pageSource()).contains("Mow the lawn");
  // }
  //
  // @Test
  // public void multipleStylistsAreDisplayedTest() {
  //   goTo("http://localhost:4567/stylists/new");
  //   fill("#styleName").with("Mow the lawn");
  //   submit(".btn");
  //   goTo("http://localhost:4567/stylists/new");
  //   // click("a", withText("Go Back")); removed this revision
  //   fill("#styleName").with("Buy groceries");
  //   submit(".btn");
  //   click("a", withText("View stylists"));
  //   assertThat(pageSource()).contains("Mow the lawn");
  //   assertThat(pageSource()).contains("Buy groceries");
  // }
  //
  // @Test
  // public void stylistShowPageDisplaysStyleName() {
  //   goTo("http://localhost:4567/stylists/new");
  //   fill("#styleName").with("Make the doughnuts");
  //   submit(".btn");
  //   click("a", withText("View stylists"));
  //   click("a", withText("Make the doughnuts"));
  //   assertThat(pageSource()).contains("Make the doughnuts");
  // }
  //
  // @Test
  // public void stylistNotFoundMessageShown() {
  //   goTo("http://localhost:4567/stylists/999");
  //   assertThat(pageSource()).contains("Stylist not found");
  // }

}
