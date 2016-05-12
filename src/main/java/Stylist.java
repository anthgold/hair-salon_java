import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String description;
  private int categoryId;

  public Stylist(String description, int categoryId) {
    this.description = description;
    this.categoryId = categoryId;
  }

  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, description, categoryId FROM stylists";
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
      return this.getDescription().equals(newStylist.getDescription()) &&
             this.getId() == newStylist.getId() &&
             this.getCategoryId() == newStylist.getCategoryId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(description, categoryId) VALUES (:description, :categoryId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", this.description)
        .addParameter("categoryId", this.categoryId)
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
