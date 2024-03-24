package cycling;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AddCategorizedClimbToStageTest {
    public static void main(String[] args) {
        testAddCategorizedClimbToStage();
    }

    public static void testAddCategorizedClimbToStage() {
        CyclingPortalImpl portal = new CyclingPortalImpl();
        try {
            // Add a race
            portal.createRace("TourdeFrance", "France");
            portal.createRace("TourdeFrance2", "France");
            portal.createRace("TourdeFrance3", "France");
            // Add a stage to the race
            int stageId = portal.addStageToRace(1, "MountainStage", "Stage with mountain climbs", 50, LocalDateTime.of(2024, 7, 1, 12, 0), StageType.FLAT);

            // Add a categorized climb checkpoint to the stage
            int checkpointId = portal.addCategorizedClimbToStage(stageId, 1.90, CheckpointType.SPRINT, 8.0, 20.0);

            // Check if the checkpoint was added successfully
            assert checkpointId > 0 : "Checkpoint ID should be positive";

            System.out.println("Test passed: Categorized climb checkpoint added successfully to the stage.");
        } catch (IDNotRecognisedException | InvalidLocationException | InvalidStageStateException | InvalidStageTypeException | IllegalNameException | InvalidNameException | InvalidLengthException e) {
            System.out.println("Test failed: " + e.getMessage());
        }
                // Add some races and stages to the system
        try {
            // Add stages to races
            portal.addStageToRace(1, "Stage1", "Mountainous stage", 200, LocalDateTime.now(), StageType.FLAT);
            portal.addStageToRace(2, "Stage2", "Flat stage", 150, LocalDateTime.now(), StageType.FLAT);
            portal.addStageToRace(3, "stage1", "Mountainous stage", 180, LocalDateTime.now(), StageType.FLAT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test cases for getRaceStages method
        try {
            // Test case 1: Valid race ID with stages
            int[] stages1 = portal.getRaceStages(1);
            System.out.println("Stages for Race ID 1: " + Arrays.toString(stages1)); // Expected: [1, 2]

            // Test case 2: Valid race ID with no stages
            int[] stages2 = portal.getRaceStages(2);
            System.out.println("Stages for Race ID 2: " + Arrays.toString(stages2)); // Expected: []

            // Test case 3: Invalid race ID
            int[] stages3 = portal.getRaceStages(3); // Race ID 3 does not exist
            System.out.println("Stages for Race ID 3: " + Arrays.toString(stages3)); // Expected: IDNotRecognisedException
        } catch (IDNotRecognisedException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
