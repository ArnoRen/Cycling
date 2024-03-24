package cycling;

public class TestRemoveStageById {
    public static void main(String[] args) {
        // Create an instance of CyclingPortal implementation
        CyclingPortal portal = new CyclingPortalImpl();

        // Add some stages to the system
        try {
            // Add a race
            portal.createRace("TourdeFrance", "France");
            portal.createRace("TourdeFrance2", "France");
            portal.createRace("TourdeFrance3", "France");

            portal.addStageToRace(1, "Stage1", "Description", 100.0, null, StageType.FLAT);
            portal.addStageToRace(2, "Stage2", "Description", 150.0, null, StageType.FLAT);
        } catch (IDNotRecognisedException | IllegalNameException | InvalidNameException | InvalidLengthException e) {
            e.printStackTrace();
        }

        // Test case 1: Remove an existing stage
        try {
            portal.removeStageById(1);
            System.out.println("Stage 1 removed successfully.");
        } catch (IDNotRecognisedException e) {
            System.out.println(e.getMessage());
        }

        // Test case 2: Try to remove a non-existent stage
        try {
            portal.removeStageById(4);
            System.out.println("Stage 3 removed successfully.");
        } catch (IDNotRecognisedException e) {
            System.out.println(e.getMessage());
        }
    }
}