package cycling;

public class classRace {
    
  private String name;
  private int raceID;
  private String description;
  private int NumberOfStages;

  public classRace(String name, int raceID, String description, int NumberOfStages) {
    this.name = name; 
    this.raceID = raceID;
    this.description = description;
    this.NumberOfStages= NumberOfStages;
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
  public int getNumberOfStages(){
    return NumberOfStages;
  }
}
