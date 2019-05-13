package pl.edu.agh.mwo;
import java.util.*;

public class TripManager {
	private HashMap<String,Trip> tripList;
	
	public TripManager() {
		tripList = new HashMap<String, Trip>();
	}
	
	public void add(Trip trip) throws TripAlreadyExistsException {
		if (tripList.get(trip.getName()) != null) {
			throw new TripAlreadyExistsException();
		}
		else {
			tripList.put(trip.getName(),trip);
		}
	}
	
	public HashMap<String,Trip> getTrips() {
		return tripList;
	}

	public void remove(String name) {
		tripList.remove(name);
	}

	public List<Trip> findTrip(String keyword) {
		List<Trip> results = new ArrayList<Trip>();
        for (String tripName : tripList.keySet()) {
            if (keyword.isEmpty()) {
				results.add(tripList.get(tripName));
            }
        	if (tripName.toLowerCase().contains(keyword.toLowerCase()) ||
                    tripList.get(tripName).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
            	results.add(tripList.get(tripName));
                return results;
            }
        	List<Photo> photos = tripList.get(tripName).getPhotos();
        	for (Photo photo : photos) {
        		if (photo.getComment().contains(keyword)) {
					results.add(tripList.get(tripName));
				}
			}
        }
        return results;
	}
}
