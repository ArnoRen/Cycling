package cycling;
public class TestAddIntermediateSprintToStage {

    public static void main(String[] args) {
        testAddIntermediateSprintToValidStage();
        testAddIntermediateSprintToInvalidStageID();
        testAddIntermediateSprintToTimeTrialStage();
        testAddIntermediateSprintWithInvalidLocation();
    }

    public static void testAddIntermediateSprintToValidStage() {
        CyclingPortalImpl objectUnderTest = new CyclingPortalImpl(); // Replace YourClassName with the actual class name
        int validStageId = 1; // Assuming a valid stage ID
        double validLocation = 50.0; // Assuming a valid location
        CyclingPortal portal = new CyclingPortalImpl();

                // Add some stages to the system


        try {
            // Add a race
            portal.createRace("TourdeFrance", "France");
            portal.createRace("TourdeFrance2", "France");
            portal.createRace("TourdeFrance3", "France");
        
            portal.addStageToRace(1, "Stage1", "Description", 100.0, null, StageType.FLAT);
            portal.addStageToRace(2, "Stage2", "Description", 150.0, null, StageType.FLAT);
       
            int result = objectUnderTest.addIntermediateSprintToStage(validStageId, validLocation);
            System.out.println("Test case for adding intermediate sprint to a valid stage: Passed");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Test case for adding intermediate sprint to a valid stage: Failed");
            e.printStackTrace();
        }
    }

    public static void testAddIntermediateSprintToInvalidStageID() {
        CyclingPortalImpl objectUnderTest = new CyclingPortalImpl(); // Replace YourClassName with the actual class name
        int invalidStageId =963; // Assuming an invalid stage ID
        double validLocation = 50.0; // Assuming a valid location
        try {
            objectUnderTest.addIntermediateSprintToStage(invalidStageId, validLocation);
            System.out.println("Test case for adding intermediate sprint to a stage with invalid stage ID: Failed");
        } catch (Exception e) {
            System.out.println("Test case for adding intermediate sprint to a stage with invalid stage ID: Passed");
        }
    }

    public static void testAddIntermediateSprintToTimeTrialStage() {
        CyclingPortalImpl objectUnderTest = new CyclingPortalImpl(); // Replace YourClassName with the actual class name
        int timeTrialStageId = 1; // Assuming a time-trial stage ID
        double validLocation = 50.0; // Assuming a valid location
        try {
            objectUnderTest.addIntermediateSprintToStage(timeTrialStageId, validLocation);
            System.out.println("Test case for adding intermediate sprint to a time-trial stage: Failed");
        } catch (Exception e) {
            System.out.println("Test case for adding intermediate sprint to a time-trial stage: Passed");
        }
    }

    public static void testAddIntermediateSprintWithInvalidLocation() {
        CyclingPortalImpl objectUnderTest = new CyclingPortalImpl(); // Replace YourClassName with the actual class name
        int validStageId = 1; // Assuming a valid stage ID
        double invalidLocation = -10.0; // Assuming an invalid location
        try {
            objectUnderTest.addIntermediateSprintToStage(validStageId, invalidLocation);
            System.out.println("Test case for adding intermediate sprint with invalid location: Failed");
        } catch (Exception e) {
            System.out.println("Test case for adding intermediate sprint with invalid location: Passed");
        }
    }
}