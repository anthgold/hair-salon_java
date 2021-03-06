import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.WebDriver;
import org.sql2o.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Salon Tracker");
  }

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a new stylist"));
    fill("#styleName").with("Sarah Shearer");
    submit(".btn");
    assertThat(pageSource()).contains("Your stylist has been saved.");
  }

  @Test
  public void stylistIsDisplayedTest() {
    goTo("http://localhost:4567/stylists/new");
    fill("#styleName").with("Sarah Shearer");
    submit(".btn");
    click("a", withText("View stylists"));
    assertThat(pageSource()).contains("Sarah Shearer");
  }

  @Test
  public void stylistShowPageDisplaysName() {
    goTo("http://localhost:4567/stylists/new");
    fill("#styleName").with("Tawnya Trims");
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Tawnya Trims"));
    assertThat(pageSource()).contains("Tawnya Trims");
  }

  @Test
  public void stylistClientsFormIsDisplayed() {
    goTo("http://localhost:4567/stylists/new");
    fill("#styleName").with("Irene H. Hare");
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Irene H. Hare"));
    click("a", withText("Add a new client"));
    assertThat(pageSource()).contains("Add a client to Irene H. Hare");
  }

  @Test
  public void clientsIsAddedAndDisplayed() {
    goTo("http://localhost:4567/stylists/new");
    fill("#styleName").with("Sally Shears"); // nee name
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Sally Shears"));
    click("a", withText("Add a new client"));
    fill("#clientName").with("Cat Carlson"); // nee description
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Sally Shears"));
    assertThat(pageSource()).contains("Cat Carlson");
  }

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
  // public void stylistNotFoundMessageShown() {
  //   goTo("http://localhost:4567/stylists/999");
  //   assertThat(pageSource()).contains("Stylist not found");
  // }

}
