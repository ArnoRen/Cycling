import java.util.Arrays;

import cycling.CyclingPortalImpl;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortal;

public class Tester_Stage {

    public static void main(String[] args) {
        
        MiniCyclingPortal portal = new CyclingPortalImpl();

        try {
            System.out.println(portal.getNumberOfStages(2));
        } catch (IDNotRecognisedException e) {
            e.printStackTrace();
     
        
        System.out.println(portal.addStageToRace());
    

