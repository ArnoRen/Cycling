package cycling;
import java.time.*;

public class classStage{
  private int stageID;
  private String type;
  private int checkpointID;
  private double length;
  private double location;

  public classStage(String Name, double Length, int StageID, String Type, int RaceID, double Location, int CheckpointID, String Description, LocalDateTime startTime, StageType type, CheckpointType type, Double averageGradient){
    String name = Name; 
    double length = Length;
    int StageId = StageID;
    String type = Type;
    int raceID = RaceID;
    double location = Location;
    int checkpointID = CheckpointID;
    String description = Description;
    LocalDateTime StartTime = startTime;
    double averageGradient = averageGradient;
  }

  public String getStageName(){
    String name;
    return name; 
  }

  public double getLenght(){
    double lenght;
    return lenght ;
  }

  public String getType(){
    return type;
  }

  public int getStageID(){
    return stageID;
  }
  public String getDescription(){
    String description;
    return description;
  }
  public int getRaceID(){
    int raceID;
    return raceID;
  }
  public double getLocation(){
    return location;
  }
  public int getCheckpointID(){
    return checkpointID;
  }
  public LocalDateTime startTime(){
    LocalDateTime startTime;
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
  
    
