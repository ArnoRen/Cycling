import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.CyclingPortalImpl;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester {

    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        try {
            System.out.println(portal.createRace("Spa","Belgium"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(portal.createRace("LeMans","France"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(portal.createRace("Spa","Belgium"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }


        System.out.println(Arrays.toString(portal.getRaceIds()));
        try {
            System.out.println(portal.viewRaceDetails(1));
        } catch (IDNotRecognisedException e) {
            e.printStackTrace();
        };
    }
    
}
