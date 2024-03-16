public class Stage{
  private int stageID;
  private String type;
  private int checkpointID;
  private double length;
  private double location;

  public Stage(String Name, double Length, int StageID, String Type, int RaceID, double Location, int CheckpointID, String Description, LocalDateTime startTime, StageType type, CheckpointType type, Double averageGradient){
    name = Name; 
    length = Length;
    StageId = StageID;
    type = Type;
    raceID = RaceID;
    location = Location;
    checkpointID = CheckpointID;
    description = Description;
    startt-Time = startTime;
    averageGradient = averageGradient;
  }

  public String getStageName(){
    return name; 
  }

  public double getLenght(){
    return lenght ;
  }

  public String getType(){
    return type;
  }

  public int getStageID(){
    return stageID;
  }
  public String getDescription(){
    return description;
  }
  public integer getRaceID(){
    return raceID;
  }
  public double getLocation(){
    return location;
  }
  public int getCheckpointID(){
    return checkpointID;
  }
  public LocalDateTime startTime(){
    return startTime;
  }
  public StageType type(){
    return type; 
  }
  public CheckpointType type(){
    return type;
  }
  public Double averageGradient(){
    return averageGradient;
  }
}
  
    
