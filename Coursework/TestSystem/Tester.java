import cycling.BadMiniCyclingPortalImpl;
import cycling.CyclingPortal;
import cycling.CyclingPortalImpl;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester {

    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        System.out.println(portal.getRaceIds());

    }
    
}
