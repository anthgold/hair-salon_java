import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Category_instantiatesCorrectly_true() {
    Category myCategory = new Category("Honey do's");
    assertEquals(true, myCategory instanceof Category);
  }

  @Test
  public void getName_categoryInstantiatesWithName_String() {
    Category myCategory = new Category("Honey do's");
    assertEquals("Honey do's", myCategory.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Category.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Category firstCategory = new Category("Honey do's");
    Category secondCategory = new Category("Honey do's");
    assertTrue(firstCategory.equals(secondCategory));
  }

  @Test
  public void save_returnsTrueIfNamesAreTheSame() {
    Category myCategory = new Category("Honey do's");
    myCategory.save();
    assertTrue(Category.all().get(0).equals(myCategory));
  }

  @Test
  public void save_assignsIdToObject() {
    Category myCategory = new Category("Honey do's");
    myCategory.save();
    Category savedCategory = Category.all().get(0);
    assertEquals(myCategory.getId(), savedCategory.getId());
  }

  @Test
    public void find_findsCategoryInDatabase_true() {
    Category myCategory = new Category("Honey do's");
    myCategory.save();
    Category savedCategory = Category.find(myCategory.getId());
    assertTrue(myCategory.equals(savedCategory));
  }


}
