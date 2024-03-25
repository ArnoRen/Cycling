import java.util.Arrays;

import cycling.CyclingPortalImpl;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester_Riders {

    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        try {
               System.out.println(portal.createRider(1,"Matt Campbell",2004));
           } catch (IllegalArgumentException | IDNotRecognisedException e) {
               e.printStackTrace();
           }

           try {
            System.out.println(portal.createRider(1,"Vanthoor",2006));
        } catch (IllegalArgumentException | IDNotRecognisedException e) {
            e.printStackTrace();
        }
        

        try {
            System.out.println(Arrays.toString(portal.getTeamRiders(1)));
        } catch (IDNotRecognisedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
}
