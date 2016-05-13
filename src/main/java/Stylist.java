import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String description;
  private int clientId;

  public Stylist(String description, int clientId) {
    this.description = description;
    this.clientId = clientId;
  }

  public String getStylistName() {
    return description;
  }

  public int getId() {
    return id;
  }

  public int getClientId() {
    return clientId;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, description, clientId FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName()) &&
             this.getId() == newStylist.getId() &&
             this.getClientId() == newStylist.getClientId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(description, clientId) VALUES (:description, :clientId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", this.description)
        .addParameter("clientId", this.clientId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }


}
