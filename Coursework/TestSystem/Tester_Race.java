import java.util.Arrays;

import cycling.CyclingPortalImpl;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester_Race {

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
            System.out.println(portal.createRace("Istanbulpark","Turkiye"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(portal.createRace("Le Mans","France (ew) "));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(portal.getRaceIds()));

        try {
            portal.removeRaceById(3);
        } catch (IDNotRecognisedException e) {
            e.printStackTrace();
        }
        
        try {
            System.out.println(portal.viewRaceDetails(2));
        } catch (IDNotRecognisedException e) {
            e.printStackTrace();
        };

        System.out.println(Arrays.toString(portal.getRaceIds()));
    }
    
}
