import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class SpellTest {

  Spell magicMissile = new Spell("Magic Missile", "point", "Go go magic missile!", null, "bolt of magic light shoots from finger in direction pointing", "headache, nausea, vomitting", 1);

  Spell beamMeUp = new Spell("beamMeUp", "tap", "Beam me up Scotty!", null, "User disappears", "Potential recombination issues", 3);

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/magic_test", null, null);
  }

  // @After
  // public void tearDown() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String deleteSpellsQuery = "DELETE FROM spells *;";
  //     // String deleteCategoriesQuery = "DELETE FROM categories *;";
  //     con.createQuery(deleteSpellsQuery).executeUpdate();
  //     // con.createQuery(deleteCategoriesQuery).executeUpdate();
  //   }
  // }

  // Constructor
  @Test
  public void newSpell_instantiatesCorrectly() {
    Spell testSpell = magicMissile;
    assertTrue(testSpell instanceof Spell);
  }

  // Getters
  @Test
  public void getName_returnsCorrectName_String() {
    Spell testSpell = magicMissile;
    String expectedOutput = "Magic Missile";
    assertEquals(expectedOutput, testSpell.getName());
  }
  @Test
  public void getGesture_returnsCorrectGesture_String() {
    Spell testSpell = magicMissile;
    String expectedOutput = "point";
    assertEquals(expectedOutput, testSpell.getGesture());
  }
  @Test
  public void getVerbal_returnsCorrectVerbal_String() {
    Spell testSpell = magicMissile;
    String expectedOutput = "Go go magic missile!";
    assertEquals(expectedOutput, testSpell.getVerbal());
  }
  @Test
  public void getItem_returnsCorrectItem_String() {
    Spell testSpell = magicMissile;
    String expectedOutput = null;
    assertEquals(expectedOutput, testSpell.getItem());
  }
  @Test
  public void getOutcome_returnsCorrectOutcome_String() {
    Spell testSpell = magicMissile;
    String expectedOutput = "bolt of magic light shoots from finger in direction pointing";
    assertEquals(expectedOutput, testSpell.getOutcome());
  }
  @Test
  public void getSideEffects_returnsCorrectSideEffects_String() {
    Spell testSpell = magicMissile;
    String expectedOutput = "headache, nausea, vomitting";
    assertEquals(expectedOutput, testSpell.getSideEffects());
  }
  @Test
  public void getPowerRating_returnsCorrectPowerRating_int() {
    Spell testSpell = magicMissile;
    int expectedOutput = 1;
    assertEquals(expectedOutput, testSpell.getPowerRating());
  }

  @Test
  public void getAllSpells_returnsAllInstancesOfSpell_true() {
    Spell firstSpell = magicMissile;
    Spell secondSpell = magicMissile;
    firstSpell.save();
    secondSpell.save();
    assertEquals(true, Spell.getAllSpells().get(0).equals(firstSpell));
    assertEquals(true, Spell.getAllSpells().get(1).equals(secondSpell));
  }

  @Test
  public void find_returnsSpellWithSameName_secondTask() {
    Spell firstSpell = magicMissile;
    Spell secondSpell = beamMeUp;
    firstSpell.save();
    secondSpell.save();
    assertEquals(Spell.find(secondSpell.getName()), secondSpell);
  }
}
