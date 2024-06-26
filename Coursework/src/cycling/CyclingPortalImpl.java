package cycling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


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
	private int id = 0;
	private int theraceID = 0;
	private int[] raceIDsList = new int[1];
	private int theteamID = 0;
	private int[] teamIDsList = new int[1];
	private int CheckPointID = 0;
	//private int[] CheckPointIDs = new int[1];
	//private int raceStageId = 0;
	//private int[] raceStageIdList = new int[1];
	//private int[] raceStageIdArray;
	private int theriderid = 0;
	private int[] riderIDList = new int[1];
	int raceIndex = 0;
	private HashMap<Integer, Double> stageLengths = new HashMap<Integer, Double>();
	private HashMap<String, Integer> stageNameToId = new HashMap<String, Integer>();
	private HashMap<Integer, String> stageIdToName = new HashMap<Integer, String>();
	private HashMap<String, Object> stageNames = new HashMap<String, Object>();
	private HashMap<Integer, List<Integer>> raceStages = new HashMap<Integer, List<Integer>>();
	private HashMap<Integer, Integer> stageRaces = new HashMap<Integer, Integer>();
	private HashMap<Integer, CheckPointId> CheckPointIds = new HashMap<Integer, CheckPointId>();
	//private HashMap<Integer, CheckPointId> CheckPointIdName = new HashMap<Integer, CheckPointId>();
	private HashMap<Integer, int[]> stageCheckpoints = new HashMap<Integer, int[]>(); //stage ID and list of the checkpointIDs -- use that to remove checkpoint 
	private int[] Checkpoints = new int[220];  //list of checkpoints
	private HashMap<Integer, Integer> CheckpointStage = new HashMap<Integer, Integer>();
	private HashMap<Integer, int[]> stagecheckpoint = new HashMap<Integer, int[]>();
	private HashMap<Integer, String> StagePreparation = new HashMap<Integer, String>();
	private HashMap<Integer, String> RaceIdNames = new HashMap<Integer, String>();
	private HashMap<Integer, Double> stageIDtolenght = new HashMap<Integer, Double>();

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

		//TODO Race removlanırsa sonucu da kaldır
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
				raceIdExists = true;
				break;
			}
		}
	
		// If the race ID is not recognized, throw IDNotRecognisedException
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
	if (stage.stream().anyMatch(stageDetails -> {
		Object stageDetail = stageDetails.get(1);
		if (stageDetail instanceof String) {
			return ((String) stageDetail).equals(stageName);
		} else {
			return false; // Or handle the case when stageDetail is not a String
		}
	})) {
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

	//adding new stage to the list 
	stage.add(Arrays.asList(raceId, uniqueStageID, stageName, description, length, startTime, type));
	stageLengths.put(uniqueStageID, length);



	//Return the unique ID of the stage
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

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {

		if (race.stream().noneMatch(raceDetails -> (int) raceDetails.get(0) == stageId)) {
			// If the race ID is not recognized, throw IDNotRecognisedException. 
			throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
		}

		if (stageIdToName.containsKey(stageId)) {
    		String thisName = stageIdToName.get(stageId); 
    		stageNameToId.remove(thisName); 
    		stageIdToName.remove(stageId); 
    		stageNames.remove(thisName);

    if (stageNameToId.containsValue(stageId)) {
        String stageNameToRemove = null;
        for (Map.Entry<String, Integer> entry : stageNameToId.entrySet()) {
            if (entry.getValue() == stageId) {
                stageNameToRemove = entry.getKey();
                break;
            }
        }
        if (stageNameToRemove != null) {
            stageNameToId.remove(stageNameToRemove);
            stageIdToName.remove(stageId);
            stageNames.remove(stageNameToRemove);

            // Remove stage from raceStages
            List<Integer> racesToRemoveFrom = raceStages.get(stageId);
            if (racesToRemoveFrom != null) {
                for (Integer raceId : racesToRemoveFrom) {
                    List<Integer> stagesInRace = raceStages.get(raceId);
                    if (stagesInRace != null) {
                        stagesInRace.remove(stageId);
                    }
                }
            }

            // Remove stage from stageRaces
            stageRaces.remove(stageId);
        }
    } else {
        throw new IDNotRecognisedException("Stage ID not recognized");
    	}
		}
	}
		

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		int newcheckpointId = id;
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
		//Throw exception
    	if (!raceStageIdBool) {
        	throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
    	}
		if(RaceIdNames.get(stageId) == null){
			throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
		}
		
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
		
		CheckPointId newCheckpoint = new CheckPointId (length, averageGradient, location, type, id);
		CheckPointIds.put(id , newCheckpoint);
		Checkpoints[id] = id; //list of id 
		//id = id of the checkpoint
		id++;
		stageCheckpoints.remove(stageId);//remove the old one
		stageCheckpoints.put(stageId, Checkpoints);	//put the new one

		}
		return newcheckpointId;
		
	}

	
	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

		int newId = id;

				//Check if the race ID exists
				boolean raceStageIdBool = false;
				for (List<Object> raceStageIdList : race) {
					int raceId = (int) raceStageIdList.get(0);
					if (raceId == stageId) {
						raceStageIdBool = true;
						break;
					}
				}
				
				if (!raceStageIdBool) {
					throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
				}
			
				//@throws InvalidStageTypeException  Time-trial stages cannot contain any checkpoint.
				for (List<Object> stage : race) {
					int id = (int) stage.get(0);
					String stageType = (String) stage.get(2);
					if (id == stageId && stageType.equals("time-trial")) {
						throw new InvalidStageTypeException("Time-trial stages cannot contain any checkpoint");
					}
				}
			
				//Check if the location is within the bounds of the stage length otherwise, throw an exception
				Double length = stageLengths.get(stageId);
				if (location < 0 || location > length) {
					throw new InvalidLocationException("Location is out of bounds of the stage length");
				}
			
				//Check if the stage is not in "waiting for results" state, otherwise throw an exception
				for (List<Object> stage : race) {
					int id = (int) stage.get(0);
					String state = (String) stage.get(1);
					if (id == stageId && state.equals("waiting for results")) {
						throw new InvalidStageStateException("Stage is in 'waiting for results' state");
					}
				}
			
				//create a new checkpoint
				CheckPointId newCheckpoint = new CheckPointId(location, id);
				int[] existedcheckpoints = stageCheckpoints.get(stageId);
				if (existedcheckpoints == null) {
					existedcheckpoints = new int[1];
				} else {
					int existednumber = existedcheckpoints.length;
					int[] newCheckpoints = Arrays.copyOf(existedcheckpoints, existednumber + 1);
					existedcheckpoints = newCheckpoints;
				}
				existedcheckpoints[existedcheckpoints.length - 1] = CheckPointID;
				CheckPointIds.put(id, newCheckpoint);
				Checkpoints[id] = id;
				id++;
				stageCheckpoints.put(stageId, existedcheckpoints);
			
				return newId;
			}

	

	@Override
	public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
		int stageId = CheckpointStage.get(checkpointId);
		int [] removeCheckpoint = stageCheckpoints.get(stageId);
		for(Integer i: removeCheckpoint){
			if (removeCheckpoint[i] == checkpointId){
				removeCheckpoint[i] = -1;
			}

		}
		if(RaceIdNames.get(stageId)== null){
			throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
		}
		for (List<Object> stage : race) {
					int id = (int) stage.get(0);
					String state = (String) stage.get(1);
					if (id == stageId && state.equals("waiting for results")) {
						throw new InvalidStageStateException("Stage is in 'waiting for results' state");
					}
				}
		//remove the data from all the hashmaps
		stageCheckpoints.remove(stageId);
		stagecheckpoint.remove(stageId);
		stageCheckpoints.put(stageId, removeCheckpoint);
		StagePreparation.remove(stageId);
	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		StagePreparation.remove(stageId);
		StagePreparation.put(stageId, "waiting for results");
		if(RaceIdNames.get(stageId)== null){
			throw new IDNotRecognisedException("Race ID not recognized: " + stageId);
		}
		for (List<Object> stage : race) {
					int id = (int) stage.get(0);
					String state = (String) stage.get(1);
					if (id == stageId && state.equals("waiting for results")) {
						throw new InvalidStageStateException("Stage is in 'waiting for results' state");
					}
				}
	}

	@Override
	public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
		if (!stageNameToId.containsValue(stageId)) {
			throw new IDNotRecognisedException("Stage ID not recognized: " + stageId);
		}
		if (!stageCheckpoints.containsKey(stageId)) {
			return new int[0];
		}
		int[] checkpoints = stageCheckpoints.get(stageId);

		List<Integer> validCheckpointList = new ArrayList<>(); //to store the valid checkpoints(just for this part)
		for (int checkpointId : checkpoints) {
			if (checkpointId != -1) { //to check if the checkpoint removed before
				validCheckpointList.add(checkpointId); //if it is valid add to the list
			}
		}
		int[] validCheckpoints = new int[validCheckpointList.size()]; //convert the list to the array
		for (int i = 0; i < validCheckpointList.size(); i++) {
			validCheckpoints[i] = validCheckpointList.get(i);
		}
		return validCheckpoints;
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

		for (List<Object> riderDetails : riders) {
			int existingID = (int) riderDetails.get(0); 
			if (existingID==teamId) {
				riders.remove(riderDetails);
			}
		}
	}

	@Override
	public int[] getTeams() {
		return teamIDsList;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		
		int[] getriderIDList = Arrays.stream(riderIDList).filter(id -> id == teamId).toArray();
		return getriderIDList; //TODO
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		for (List<Object> teamDetails : teams) {
			int existingID = (int) teamDetails.get(0); 
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

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {

		riders.removeIf(riders -> (int) riders.get(1) == riderId);

		riderIDList = Arrays.stream(riderIDList).filter(id -> id != riderId).toArray();
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
			InvalidStageStateException { //TODO InvalidStage
		
		List<Object>addthat=Arrays.asList(stageId, checkpoints);
		
		if(checkpoints.length!= stageIDtolenght.get(stageId)){
			throw new InvalidCheckpointTimesException("Invalid Checkpoint Times:" + checkpoints.length);
		}
		
		for (List<Object> stageDetails : stage) {
			int existingID = (int) stageDetails.get(0); 
			if (existingID!=stageId) {
				throw new IDNotRecognisedException("ID not recognised: " + stageId);
			}
		}

		for (List<Object> riderDetails : riders) {
			int existingID = (int) riderDetails.get(1); 
			if (existingID==riderId) {
				for (Object obj : riderDetails){
					if(obj instanceof List){
						List<Object> innerList = (List<Object>) obj;
						int stageInInnerList = (int) innerList.get(0);
						if(stageInInnerList==stageId){
							throw new DuplicatedResultException("Duplicated Result"+ addthat);
						}
					}
				}
			}
		}

		for (List<Object> riderDetails : riders) {
			int existingID = (int) riderDetails.get(1); 
			if (existingID!=riderId) {
				throw new IDNotRecognisedException("ID not recognised: " + riderId);
			}
		}

		for (int i=0; i<=riders.size(); i++) {
			List<Object> tempList = riders.get(i);
			int existingID = (int) tempList.get(1);
			if (existingID == riderId){
				tempList.add(addthat);
			}
		}
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
	  for (List<Object> riderDetails : riders) {
		  int existingID = (int) riderDetails.get(1); 
		  if (existingID == riderId) {
			  for (Object obj : riderDetails) {
				  if (obj instanceof List) {
					  List<Object> innerList = (List<Object>) obj;
					  int stageInInnerList = (int) innerList.get(0);
					  if (stageInInnerList == stageId) {
						  List<LocalTime> riderResults = innerList.stream()
								  .filter(item -> item instanceof LocalTime)
								  .map(item -> (LocalTime) item)
								  .collect(Collectors.toList());
						  LocalTime[] end = riderResults.toArray(new LocalTime[riderResults.size()]);
						  return end;
					  }
				  }
			  }
			  // If the specified stage is not found for the rider, return null or throw an exception
			  // throw new IDNotRecognisedException("Stage ID not recognised for rider: " + stageId);
			  // return null;
		  }
	  }
	  // If the rider ID is not found, throw an exception
	  throw new IDNotRecognisedException("Rider ID not recognised: " + riderId);
  }

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		LocalTime[] ridersone =getRiderResultsInStage(stageId, riderId);
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
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			outputStream.writeObject(this);
		} catch (IOException e) {
			// If an IOException occurs, throw it to indicate failure
			throw new IOException(e);
		}
	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		//TODO
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

		//TODO Race removlanırsa sonucu da kaldır
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
