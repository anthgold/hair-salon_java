import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getStyleName_stylistInstantiatesWithStyleName_String() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    assertEquals("Sue Stylish", myStylist.getStyleName()); // fail 2
  }                                                 // AssertionError

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStyleNamesAreTheSame() {
    Stylist firstStylist = new Stylist("Sue Stylish", 1);
    Stylist secondStylist = new Stylist("Sue Stylish", 1);
    assertTrue(firstStylist.equals(secondStylist)); // fail 3
  }

  @Test
  public void save_returnsTrueIfStyleNamesAreTheSame() {
    Stylist myStylist = new Stylist("Sue Stylish", 1);
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist)); // fail 4
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
    assertTrue(myStylist.equals(savedStylist)); // fail 1
  }

  @Test
  public void save_savesClientIdIntoDB_true() {
    Client myClient = new Client("Charlie Craphound");
    myClient.save();
    Stylist myStylist = new Stylist("Sue Stylish", myClient.getId());
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertEquals(savedStylist.getClientId(), myClient.getId());
  }

  // @Test
  // public void getClients_retrievesALlClientsFromDatabase_clientsList() {
  //   Stylist myStylist = new Stylist("Sarah Shearer");
  //   myStylist.save();
  //   Client firstClient = new Client("Cara Cnafelc", myStylist.getId());
  //   firstClient.save();
  //   Client secondClient = new Client("Candice Copperfield", myStylist.getId());
  //   secondClient.save();
  //   Client[] clients = new Client[] { firstClient, secondClient };
  //   assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  // }

}
