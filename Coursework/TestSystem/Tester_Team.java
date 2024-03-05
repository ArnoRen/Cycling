import java.util.Arrays;

import cycling.CyclingPortalImpl;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester_Team {

    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        try {
            System.out.println(portal.createTeam("Porsche Peniske","Porsche 963"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(portal.createTeam("AF Course","499P"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(portal.createTeam("AF Course","499P"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(portal.getTeams()));

        try {
            portal.removeTeam(2);
        } catch (IDNotRecognisedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(portal.getTeams()));
    }
    
}
