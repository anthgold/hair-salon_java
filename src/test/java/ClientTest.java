import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Charlene Cliente");
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void getClientName_clientInstantiatesWithClientName_String() {
    Client myClient = new Client("Charlene Cliente");
    assertEquals("Charlene Cliente", myClient.getClientName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfClientNamesAreTheSame() {
    Client firstClient = new Client("Charlene Cliente");
    Client secondClient = new Client("Charlene Cliente");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client myClient = new Client("Charlene Cliente");
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Charlene Cliente");
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
    public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Charlene Cliente");
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }


}
