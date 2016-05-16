import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Cora Cliente", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void getName_clientInstantiatesWithName_String() {
    Client myClient = new Client("Cora Cliente", 1);
    assertEquals("Cora Cliente", myClient.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0); // fail sql exception
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    Client firstClient = new Client("Cora Cliente", 1);
    Client secondClient = new Client("Cora Cliente", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Client myClient = new Client("Cora Cliente", 1);
    myClient.save(); // fail null pointer
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Cora Cliente", 1);
    myClient.save(); // fail null pointer
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
    public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Cora Cliente", 1);
    myClient.save(); // fail null pointer
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  // @Test
  // public void save_savesClientIdIntoDB_true() {
  //   Client myClient = new Client("Charlie Craphound");
  //   myClient.save();
  //   Stylist myStylist = new Stylist("Cora Cliente", myClient.getId());
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.find(myStylist.getId());
  //   assertEquals(savedStylist.getClientId(), myClient.getId());
  // }



}
