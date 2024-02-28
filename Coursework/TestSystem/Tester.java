import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.CyclingPortalImpl;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester {

    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        System.out.println(Arrays.toString(portal.getRaceIds()));
        try {
            System.out.println(portal.createRace("Porsche","Penske"));
        } catch (IllegalNameException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

    }
    
}
