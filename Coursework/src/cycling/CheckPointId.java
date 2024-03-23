package cycling;

public class CheckPointId {
    private double lenght;
    private double averageGradient;
    private double location;
    private CheckpointType type;
    private int CheckPointID;

public CheckPointId(double lenght, double averageGradient, double location, CheckpointType type, int CheckPointID){
    this.lenght = lenght;
    this.averageGradient = averageGradient;
    this.location = location;
    this.type = type;
    this.CheckPointID = CheckPointID;
}

public double getLength() {
    return lenght;
}

public double averageGradient() {
    return averageGradient;
}

public double location() {
    return location;
}

public CheckpointType type() {
    return type;
} 

public int CheckPointID() {
    return CheckPointID;
}

}
