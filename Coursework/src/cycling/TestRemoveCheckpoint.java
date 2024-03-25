package cycling;

public class TestRemoveCheckpoint {

    public static void main(String[] args) {
        testRemoveCheckpointValid();
        testRemoveCheckpointInvalidStageState();
        testRemoveCheckpointInvalidCheckpointID();
    }

    public static void testRemoveCheckpointValid() {
        CyclingPortalImpl portal = new CyclingPortalImpl();
        int checkpointId = 1; // Assuming a valid checkpoint ID
        try {
            // Add some stages and checkpoints to the system
            portal.addStageToRace(1, "Stage1", "Description", 100.0, null, StageType.FLAT);
            //portal.addCheckpointToStage(1, checkpointId);

            portal.removeCheckpoint(checkpointId);
            System.out.println("Test case for removing a checkpoint: Passed");
        } catch (Exception e) {
            System.out.println("Test case for removing a checkpoint: Failed");
            e.printStackTrace();
        }
    }

    public static void testRemoveCheckpointInvalidStageState() {
        CyclingPortalImpl portal = new CyclingPortalImpl();
        int checkpointId = 1; // Assuming a valid checkpoint ID
        try {
            // Add some stages and checkpoints to the system
            portal.addStageToRace(1, "Stage1", "Description", 100.0, null, StageType.FLAT);
            //portal.addCheckpointToStage(1, checkpointId);

            // Set the stage state to "waiting for results"
            //portal.setStageState(1, "waiting for results");

            portal.removeCheckpoint(checkpointId);
            System.out.println("Test case for removing a checkpoint with invalid stage state: Failed");
        } catch (Exception e) {
            System.out.println("Test case for removing a checkpoint with invalid stage state: Passed");
        }
    }

    public static void testRemoveCheckpointInvalidCheckpointID() {
        CyclingPortalImpl portal = new CyclingPortalImpl();
        int invalidCheckpointId = 999; // Assuming an invalid checkpoint ID
        try {
            portal.removeCheckpoint(invalidCheckpointId);
            System.out.println("Test case for removing an invalid checkpoint ID: Failed");
        } catch (Exception e) {
            System.out.println("Test case for removing an invalid checkpoint ID: Passed");
        }
    }
}
