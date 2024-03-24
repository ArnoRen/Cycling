package cycling;

public class TestGetStageLength {
    public static void main(String[] args) {
        // Create an instance of CyclingPortal implementation
        CyclingPortal portal = new CyclingPortalImpl();

        // Add some stages with lengths
        try {
            // Add a race
            portal.createRace("TourdeFrance", "France");
            portal.createRace("TourdeFrance2", "France");
            portal.createRace("TourdeFrance3", "France");

            portal.addStageToRace(1, "Stage1", "Description", 100.0, null, StageType.FLAT);
            portal.addStageToRace(1, "Stage2", "Description", 150.0, null, StageType.FLAT);
        } catch (IDNotRecognisedException | IllegalNameException | InvalidNameException | InvalidLengthException e) {
            e.printStackTrace();
        }

        // Test case 1: Get length of an existing stage
        try {
            double length1 = portal.getStageLength(1);
            System.out.println("Length of Stage 1: " + length1 + " km");
        } catch (IDNotRecognisedException e) {
            System.out.println(e.getMessage());
        }

        // Test case 2: Try to get length of a non-existent stage
        try {
            double length2 = portal.getStageLength(3);
            System.out.println("Length of Stage 3: " + length2 + " km");
        } catch (IDNotRecognisedException e) {
            System.out.println(e.getMessage());
        }
    }
}