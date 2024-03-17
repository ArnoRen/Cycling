package cycling;

public class classRace {
    
  private String name;
  private int raceID;
  private String description;

  public classRace(String name, int raceID, String description) {
    this.name = name; 
    this.raceID = raceID;
    this.description = description;
  }

  public String getStageName() {
    return name; 
  }

  public String getDescription() {
    return description;
  }

  public int getRaceID() {
    return raceID;
  }
}
