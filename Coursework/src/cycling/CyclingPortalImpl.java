package cycling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.HashMap;


/**
 * CyclingPortal is a fully compiling, functioning implementor
 * of the CyclingPortal interface.
 * 
 * @author 730055520 - 730034y5
 * @version 1.0
 *
 */


public class CyclingPortalImpl implements CyclingPortal {

	private ArrayList<List<Object>> race = new ArrayList<>();
	private ArrayList<List<Object>> teams = new ArrayList<>();
	private ArrayList<List<Object>> stage = new ArrayList<>();
	private int theraceID = 0;
	private int[] raceIDsList = new int[1];

	@Override
	public int[] getRaceIds() {
		return raceIDsList;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		if (!name.getClass().equals(String.class)||name.equals(null)){
			throw new InvalidNameException("Name must be String!");
		};
		for (int i = 0; i < race.size(); i++) {
			List<Object> currentItem = race.get(i);
	
			// Compare currentItem with other items in the list
			for (int j = i+1; j < race.size(); j++) {
				List<Object> nextItem = race.get(j);
	
				// Compare the current item with the next item
				if (currentItem.equals(nextItem)) {      //ERROR
					 throw new IllegalNameException("Race already exists:" +name);
				}
			}
		}

		theraceID++;
		race.add(Arrays.asList(theraceID,name, description));
		int[] updatedRaceIDsList = Arrays.copyOf(raceIDsList, theraceID);
		updatedRaceIDsList[theraceID - 1] = theraceID;
		raceIDsList = updatedRaceIDsList;
		return theraceID;
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		// Search for the race with the given raceId
		for (List<Object> raceDetails : race) {
			int id = (int) raceDetails.get(0);
			if (id == raceId) {
				// Found the race, return its details
				String name = (String) raceDetails.get(1); // Assuming race name is stored at index 1
				String description = (String) raceDetails.get(2); // Assuming race description is stored at index 2
				return "Race ID: " + id + "\nName: " + name + "\nDescription: " + description;
			}
		}
		
		// If raceId is not recognized, throw IDNotRecognisedException
		throw new IDNotRecognisedException("Race ID not recognized: " + raceId);
		
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {

		for (List<Object> raceDetails : race) {
			int id = (int) raceDetails.get(0);
			if (id == raceId) {
				race.remove(raceId-1);
			};
		}

		int[] newArray = new int[raceIDsList.length - 1];
		for (int i = 0, j = 0; i < raceIDsList.length; i++) {
    		if (i != raceId) {
        		newArray[j] = raceIDsList[i];
        	j++;
    	}
		raceIDsList = newArray;
	}

	}

    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        for (List<Object> raceDetails : race) {
            int id = (int) raceDetails.get(0);

            // Check if the ID is recognized; if it is, throw an exception
            if (id == raceId) {
                throw new IDNotRecognisedException("Race ID not recognized: " + raceId);
            }
			//stage.add(Arrays.asList(raceId, stageName, description, length, startTime));
			return theraceID;
        }

        return 0;
    }

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {

	
		return 0;
		}
//	/**
	 //* Retrieves the list of stage IDs of a race.
	 //* <p>
	 //* The state of this MiniCyclingPortal must be unchanged if any
	 //* exceptions are thrown.
	 //* 
	 //* @param raceId The ID of the race being queried.
	 //* @return An array of stage IDs ordered (from first to last) by their sequence in the
	 //*         race or an empty array if none exists.
	 //* @throws IDNotRecognisedException If the ID does not match to any race in the
	 //*                                  system.
	 //*/
	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//Yarım 1/2
	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
			InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}
