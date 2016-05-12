import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getDescription_stylistInstantiatesWithDescription_String() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    assertEquals("Sue Stylish", myStylist.getDescription());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    Stylist firstStylist = new Stylist("Sue Stylish", 1);
    Stylist secondStylist = new Stylist("Sue Stylish", 1);
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
    public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  // @Test
  // public void save_savesClientIdIntoDB_true() {
  //   Client myClient = new Client("Household chores");
  //   myClient.save();
  //   Stylist myStylist = new Stylist("Sue Stylish", myClient.getId());
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.find(myStylist.getId());
  //   assertEquals(savedStylist.getClientId(), myClient.getId());
  // }


}
