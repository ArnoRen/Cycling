package cycling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;


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
	private ArrayList<List<Object>> riders = new ArrayList<>();
	private int theraceID = 0;
	private int[] raceIDsList = new int[1];
	private int theteamID = 0;
	private int[] teamIDsList = new int[1];
	//private int raceStageId = 0;
	private int[] raceStageIdList = new int[1];
	private int[] raceStageIdArray;
	private int theriderid = 0;
	private int[] riderIDList = new int[1];
	int raceIndex = 0;
	private Map<Integer, Double> stageLengths; //stores lenght internally
	 

	@Override
	public int[] getRaceIds() {
		return raceIDsList;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		if (!name.getClass().equals(String.class)||name.equals(null)){
			throw new InvalidNameException("Name must be String!");
		};
		for (List<Object> raceDetails : race) {
			String existingName = (String) raceDetails.get(1); 
			if (existingName.equals(name)) {
				throw new IllegalNameException("Team already exists: " + name);
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

		race.removeIf(race -> (int) race.get(0) == raceId);

		raceIDsList = Arrays.stream(raceIDsList).filter(id -> id != raceId).toArray();
	}


	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		// check if the provided raceId exists in the system using stream().noneMatch()
		//noneMatch() of Stream class returns whether no elements of this stream match the provided predicate.
		//stream(): This method is called on a collection, converting it into a Stream. A Stream is a sequence of elements that supports various operations.
		//filter(): This method is an intermediate operation that takes a Predicate (a functional interface representing a boolean-valued function) as an argument. 
		//It filters the elements of the stream based on whether they satisfy the given predicate. Only elements that match the predicate will be included in the resulting stream.
		//count(): This method is a terminal operation that returns the count of elements in the stream. 
		if (race.stream().noneMatch(raceDetails -> (int) raceDetails.get(0) == raceId)) {
			// If the race ID is not recognized, throw IDNotRecognisedException. 
			throw new IDNotRecognisedException("Race ID not recognized: " + raceId);
		}
	//Otherwise, it counts the number of stages associated with the specified raceId using stream().filter().count().
	//stream().filter().count() method used to count the number of stages associated with a specific race ID.
		return (int) stage.stream().filter(stageDetails -> (int) stageDetails.get(0) == raceId).count();
		//lambda expression (parameter -> expression) == taking a parameter and returning to a value. 
	}

@Override
public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type)
        throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {

    // Check if the race ID exists
    boolean raceIdExists = false;
    for (List<Object> raceDetails : race) {
        int id = (int) raceDetails.get(0);
        if (id == raceId) {
			//the unique ID of the stage.
            raceIdExists = true;
            break;
        }
    }
	//If the ID does not match to any race in the system.
    if (!raceIdExists) {
        throw new IDNotRecognisedException("Race ID not recognized: " + raceId);
    }

    // Check if the stage name is valid
	//an identifier name for the stage
	//@throws InvalidNameException if the name is null, empty, has more than 30 characters, or has white spaces
	//@throws InvalidLengthException   If the length is less than 5km.
    if (stageName == null || stageName.isEmpty() || stageName.length() > 30 || stageName.contains(" ")) {
        throw new InvalidNameException("Invalid stage name");
    } 


    // stage name exist?
	//throw illegalnameexception if the name already exist in the platorm
	if (stage.stream().anyMatch(stageDetails -> ((String) stageDetails.get(1)).equals(stageName))) {
    	throw new IllegalNameException("A stage name already exists in the platform: " + stageName);
	}

    // Check if the length is valid
    if (length < 5) {
        throw new InvalidLengthException("Invalid stage length: " + length);
    }

    // @return the unique ID of the stage.
	// return the unique ID to the stage after the new stage added to the list
    // Create a new stage and add it to the list

	// @return the unique ID of the stage.
	// Return the unique ID of the stage after the new stage is added to the list
	// Create a new stage and add it to the list
	int uniqueStageID = stage.size() + 1; //+1 is used to generate a unique identifier in this stage

	// Add the new stage to the list
	stage.add(Arrays.asList(raceId, uniqueStageID, stageName, description, length, startTime, type));

	// Return the unique ID of the stage
	return uniqueStageID;
		}


	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		//check if the raceID exist in the system -as always
		if (race.stream().noneMatch(raceDetails -> (int) raceDetails.get(0) == raceId)) {
			//if not throw IDNotRecognisedException
			throw new IDNotRecognisedException("Race ID not recognized: " + raceId);
		}
		// Use an ArrayList to store the stage IDs
    	List<Integer> raceStageIdList = new ArrayList<>();
		for (List<Object> stageDetails : stage) {
        	int stageRaceId = (int) stageDetails.get(0); // Assuming race ID is at index 0
        	if (stageRaceId == raceId) {
            	raceStageIdList.add((int) stageDetails.get(1)); // Assuming stage ID is at index 1
        	}
    	}

    	// Convert the ArrayList to an array
    	int[] raceStageId = raceStageIdList.stream().mapToInt(Integer::intValue).toArray();

    	return raceStageId;
	}

	@Override
	// Gets the length of a stage in a race, in kilometres.
	//@param stageId The ID of the stage being queried.
	// return stages lenght
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		//stage ID exist? 
		if (stageLengths.containsKey(stageId)) { //The java.util.Map.containsKey() method is used to check whether a particular key is being mapped into the Map or not
            // Return the length of the stage
            return stageLengths.get(stageId);
        } else {
            // Throw an exception if the stageId is not recognized
            throw new IDNotRecognisedException("Stage ID not recognized: " + stageId);
        }
    }
	//Map is a way to keep track of information in the form of key-value pairs. 
	//In Java, a map can consist of virtually any number of key-value pairs, 
	//but the keys must always be unique — non-repeating.

	//??????????????????????????????
	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		/*
		 * race.removeIf(race -> (int) race.get(0) == raceId);

			raceIDsList = Arrays.stream(raceIDsList).filter(id -> id != raceId).toArray();
		 * 
		 * Race den kaldırma böyle, 2. de race id listten kaldırıyor
		 */
		// TODO Auto-generated method stub

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
	
		// Check if the race ID exists
    	boolean raceStageIdBool = false;
    	for (List<Object> raceStageIdList : race) {
        	int id = (int) raceStageIdList.get(0);
        	if (id == stageId) {
			//the unique ID of the stage.
            raceStageIdBool = true;	
            break;
        	}
    	}
		//If the ID does not match to any race in the system.
    	if (!raceStageIdBool) {
        	throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
    	}

		 List<List<Object>> originalRace = new ArrayList<>(race);
		
		//@throws InvalidStageTypeException  Time-trial stages cannot contain any checkpoint. ---check if the stage type is not time-trial
        for (List<Object> stage : race) {
            int id = (int) stage.get(0);
            String stageType = (String) stage.get(2);
            if (id == stageId && stageType.equals("time-trial")) {
                throw new InvalidStageTypeException("Time-trial stages cannot contain any checkpoint");
            }
        }
		//@throws InvalidLocationException   If the location is out of bounds of the stage length.  Check if the location is within the bounds of the stage length
        if (location < 0 || location > length) {
            throw new InvalidLocationException("Location is out of bounds of the stage length");
        }

		// @throws InvalidStageStateException If the stage is "waiting for results". Check if the stage is not in "waiting for results" state
        for (List<Object> stage : race) {
            int id = (int) stage.get(0);
            String state = (String) stage.get(1);
            if (id == stageId && state.equals("waiting for results")) {
                throw new InvalidStageStateException("Stage is in 'waiting for results' state");
            }
		}
		//@param stageId - The ID of the stage to which the climb checkpoint is being added.
		// call the class from classStage
	
	stageId++; 
	teams.add(Arrays.asList(stageID , name, description));

	int[] updatedRaceStageIdArray = Arrays.copyOf(raceStageIdArray, stageId);
	updatedRaceStageIdArray[stageId - 1] = stageId;
	raceStageIdArray = updatedRaceStageIdArray;
	return stageId;
		}
	// @param location The kilometre location where the climb finishes within the stage.


	
	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// Check if the race ID exists
    	boolean raceStageIdBool = false;
    	for (List<Object> raceStageIdList : race) {
        	int id = (int) raceStageIdList.get(0);
        	if (id == stageId) {
		//the unique ID of the stage.
            	raceStageIdBool = true;	
            	break;
        	}
    	}
	//If the ID does not match to any race in the system.
    	if (!raceStageIdBool) {
        	throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
    	}

	List<List<Object>> originalRace = new ArrayList<>(race);
		
	//@throws InvalidStageTypeException  Time-trial stages cannot contain any checkpoint. ---check if the stage type is not time-trial
        for (List<Object> stage : race) {
            int id = (int) stage.get(0);
            String stageType = (String) stage.get(2);
            if (id == stageId && stageType.equals("time-trial")) {
                throw new InvalidStageTypeException("Time-trial stages cannot contain any checkpoint");
            }
        }
		//@throws InvalidLocationException   If the location is out of bounds of the stage length.  Check if the location is within the bounds of the stage length
        if (location < 0 || location > length) {
            throw new InvalidLocationException("Location is out of bounds of the stage length");
        }

	// @throws InvalidStageStateException If the stage is "waiting for results". Check if the stage is not in "waiting for results" state
        for (List<Object> stage : race) {
            int id = (int) stage.get(0);
            String state = (String) stage.get(1);
            if (id == stageId && state.equals("waiting for results")) {
                throw new InvalidStageStateException("Stage is in 'waiting for results' state");
            }
	}
	public class SprintCheckpointManager {
		// Assuming a map to store checkpoint IDs against stage IDs
		private Map<Integer, Integer> checkpointMap;
		public SprintCheckpointManager() {
			checkpointMap = new HashMap<>();
		}
		public int addIntermediateSprintCheckpoint(int stageId, int location) {
			// Generate a unique checkpoint ID (you can use any suitable logic)
			int checkpointId = generateCheckpointId();
		
			// Store the checkpoint ID against the stage ID
			checkpointMap.put(stageId, checkpointId);
		
			// Perform any additional operations as required (e.g., store location information)
		
			// Return the ID of the created checkpoint
			return checkpointId;
		}
	}
	}

	@Override
	public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
		Checkpoint.removeIf(Checkpoint -> (int) Checkpoint.get(0) == checkpointId);

		checkpointIdList = Arrays.stream(checkpointIdList).filter(id -> id != checkpointId).toArray();

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
		if (!name.getClass().equals(String.class)||name.equals(null)){
			throw new InvalidNameException("Name must be String!");
		};
		for (List<Object> teamDetails : teams) {
			String existingName = (String) teamDetails.get(1); // Assuming name is stored at index 1
			if (existingName.equals(name)) {
				throw new IllegalNameException("Team already exists: " + name);
			}
		}

		theteamID++;
		teams.add(Arrays.asList(theteamID, name, description));
		int[] updatedteamIDsList = Arrays.copyOf(teamIDsList, theteamID);
		updatedteamIDsList[theteamID - 1] = theteamID;
		teamIDsList = updatedteamIDsList;
		return theteamID;
	}
	
	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		
		teams.removeIf(teams -> (int) teams.get(0) == teamId);

		teamIDsList = Arrays.stream(teamIDsList).filter(id -> id != teamId).toArray();

	}

	@Override
	public int[] getTeams() {
		return teamIDsList;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		int[] getriderIDList = Arrays.stream(riderIDList).filter(id -> id == teamId).toArray();
		return getriderIDList;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		for (List<Object> riderDetails : teams) {
			int existingID = (int) riderDetails.get(0); 
			if (existingID!=teamID) {
				throw new IDNotRecognisedException("ID not recognised: " + teamID);
			}
		}

		for (List<Object> riderDetails : teams) {
			String existingName = (String) riderDetails.get(1); 
			if (existingName.equals(name)) {
				throw new IllegalArgumentException("Rider already exists: " + name);
			}
		}

		theriderid++;
		int[] updatedRiderIDsList = Arrays.copyOf(riderIDList, theriderid);
		updatedRiderIDsList[theriderid - 1] = theriderid;
		riderIDList = updatedRiderIDsList;

		riders.add(Arrays.asList(teamID,theriderid, name, yearOfBirth));
		return theriderid;
	}

	
	//Yarım 1/2
	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {

		riders.removeIf(riders -> (int) riders.get(0) == riderId);

		riderIDList = Arrays.stream(riderIDList).filter(id -> id != riderId).toArray();
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
			InvalidStageStateException {
		for (List<Object> stageDetails : stage) {
			int existingID = (int) stageDetails.get(0); 
			if (existingID!=stageId) {
				throw new IDNotRecognisedException("ID not recognised: " + stageId);
			}
		}

		for (List<Object> riderDetails : riders) {
			int existingID = (int) riderDetails.get(0); 
			if (existingID!=riderId) {
				throw new IDNotRecognisedException("ID not recognised: " + riderId);
			}
		}
		
		// TODO Auto-generated method stub

	}

/* GET
 * for (List<Object> teamDetails : teams) {                      HANGİ LİSTEDE ARIYORSAN O
			String existingName = (String) teamDetails.get(1);   LİSTEDE ARADIĞIN ITEM KAÇINCI SIRADA (ID 0'DA MESELA İSE O NUMBER) 
			if (existingName.equals(name))                       EĞER STRING ISE BU ŞEKİLDE ARADIĞIN O MU DİYE KONTROL EDİYOR, TRUE ISE IF İN ALTI
 * 
 * DURUMA BAĞLI FOR İÇİNDE FOR DÖNGÜSÜ YAPIP O FOR DÖNGÜSÜNÜN İÇİNE DE GET KOYUP LİSTENİN İÇİNDEKİ LİSTEDEN BİR ŞEY ALABİLİRSİN
 * 
 * BUNLARIN HEPSİNİ FOR DÖNGÜSÜ İÇİNDE HER TAKIM İÇİN YAPARSAN HER RIDER IN BİLGİSİNİ ALABİLİRSİN
 */

 /*
  * Arrays.sort(array);    küçükten büyüğe sırala
  * Arrays.sort(arr, Collections.reverseOrder());   büyükten küçüğe sırala
  */

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

		for (int i = 0; i < race.size(); i++) {
        List<Object> raceDetails = race.get(i);
        String raceName = (String) raceDetails.get(1);
        if (raceName.equals(name)) {
            raceIndex = i-1;
            break;
        	}
    	}

		race.removeIf(race -> (String) race.get(1) == name);

		raceIDsList = Arrays.stream(raceIDsList).filter(id -> id != raceIndex).toArray();

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
