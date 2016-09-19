import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Spell {
  private String name;
  private String gesture;
  private String verbal;
  private String item;
  private String outcome;
  private String side_effects;
  private int power_rating;

  public Spell(String _name, String _gesture, String _verbal, String _item, String _outcome, String _side_effects, int _power_rating) {
    name = _name;
    gesture = _gesture;
    verbal = _verbal;
    item = _item;
    outcome = _outcome;
    side_effects = _side_effects;
    power_rating = _power_rating;
  }

  public String getName() {
    return name;
  }

  public String getGesture() {
    return gesture;
  }

  public String getVerbal() {
    return verbal;
  }

  public String getItem() {
    return item;
  }

  public String getOutcome() {
    return outcome;
  }

  public String getSideEffects() {
    return side_effects;
  }

  public int getPowerRating() {
    return power_rating;
  }

  public static List<Spell> getAllSpells () {
    String sql = "SELECT name, gesture, verbal, item, outcome, side_effects, power_rating FROM spells";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Spell.class);
    }
  }

  @Override
  public boolean equals(Object _otherSpell){
    if (!(_otherSpell instanceof Spell)) {
      return false;
    }
    else {
      Spell newSpell = (Spell) _otherSpell;
      return this.getName().equals(newSpell.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO spells (name, gesture, verbal, item, outcome, side_effects, power_rating) VALUES (:name, :gesture, :verbal, :item, :outcome, :side_effects, :power_rating)";
      con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("gesture", this.gesture)
        .addParameter("verbal", this.verbal)
        .addParameter("item", this.item)
        .addParameter("outcome", this.outcome)
        .addParameter("side_effects", this.side_effects)
        .addParameter("power_rating", this.power_rating)
        .executeUpdate();
    }
  }

  public static Spell find(String _name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM spells where name=:name";
      Spell spell = con.createQuery(sql)
        .addParameter("name", _name)
        .executeAndFetchFirst(Spell.class);
      return spell;
    }
  }

}
