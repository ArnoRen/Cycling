import cycling.BadMiniCyclingPortalImpl;
import cycling.CyclingPortal;
import cycling.CyclingPortalImpl;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester {

    int[] raceId = {3,4,5} ;
    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        System.out.println(portal.getRaceIds());

    }
    
}
