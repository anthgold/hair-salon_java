import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String styleName;
  // private int stylistId;

  public Stylist(String styleName) {
    this.styleName = styleName;
    // this.stylistId = stylistId;
  }

  public String getStyleName() {
    return styleName;
  }

  public int getId() {
    return id;
  }
/*
  public int getstylistId() {
    return stylistId;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, stylename FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
*/
  // @Override
  // public boolean equals(Object otherStylist){
  //   if (!(otherStylist instanceof Stylist)) {
  //     return false;
  //   } else {
  //     Stylist newStylist = (Stylist) otherStylist;
  //     return this.getStylistName().equals(newStylist.getStylistName()) &&
  //            this.getId() == newStylist.getId() &&
  //            this.getstylistId() == newStylist.getstylistId();
  //   }
  // }
  //
  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO stylists(id, name) VALUES (:stylistId, :stylistName)";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("stylistName", this.stylistName)
  //       .addParameter("stylistId", this.stylistId)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static Stylist find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM stylists where id=:id";
  //     Stylist stylist = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Stylist.class);
  //     return stylist;
  //   }
  // }


}
