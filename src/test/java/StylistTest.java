import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Sally Shears");
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_String() {
    Stylist myStylist = new Stylist("Sally Shears");
    assertEquals("Sally Shears", myStylist.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Stylist firstStylist = new Stylist("Sally Shears");
    Stylist secondStylist = new Stylist("Sally Shears");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Sally Shears");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Sally Shears");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
    public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Sally Shears");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
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
