import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String styleName;
  private int clientId;

  public Stylist(String styleName, int clientId) {
    this.styleName = styleName;
    this.clientId = clientId;
  }

  public String getStyleName() {
    return styleName;
  }

  public int getId() {
    return id;
  }

  public int getClientId() {
    return clientId;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, stylename, clientId FROM stylists";
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
      return this.getStyleName().equals(newStylist.getStyleName()) &&
             this.getId() == newStylist.getId() &&
             this.getClientId() == newStylist.getClientId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(styleName, clientId) VALUES (:styleName, :clientId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("styleName", this.styleName)
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
