import Spot.ParkingSpot;
import Spot.SpotType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private int idParkingFloor;

    private Map<SpotType, List<ParkingSpot>> totalSpots;

    public void addParkingSpots(SpotType type, ParkingSpot[] spots) {
        if (!this.totalSpots.containsKey(type)) {
            ArrayList<ParkingSpot> parkingSpots = (ArrayList<ParkingSpot>) Arrays.asList(spots);
            totalSpots.put(type, parkingSpots);
            System.out.println("Added " +spots.length+ " parking spots, type: " + type);
        } else {
            totalSpots.get(type).addAll(Arrays.asList(spots));
            System.out.println("Spots of type " + type + " exists, added new parking spots. New total: " + totalSpots.size());
        }
    }

    public int getIdParkingFloor() {
        return idParkingFloor;
    }

    public void setIdParkingFloor(int idParkingFloor) {
        this.idParkingFloor = idParkingFloor;
    }

    public Map<SpotType, List<ParkingSpot>> getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(Map<SpotType, List<ParkingSpot>> totalSpots) {
        this.totalSpots = totalSpots;
    }
}
