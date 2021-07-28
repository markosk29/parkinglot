import Parking.ParkingFloor;
import Parking.ParkingLot;
import Parking.ParkingSpot;
import Spot.BikeSpot;
import Spot.CarSpot;
import Spot.SpotType;
import Vehicles.*;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Vehicle bike= new Bike();
        System.out.println(bike.getInfo());

        ParkingSpot bikeSpot = new BikeSpot(bike);
        System.out.println(bikeSpot.getInfo());

        Map<SpotType, List<ParkingSpot>> totalSpotsOnFloor = new HashMap<>();
        List<ParkingSpot> parkingSpots = new ArrayList<>();

        parkingSpots.add(bikeSpot);
        totalSpotsOnFloor.put(SpotType.BIKE, parkingSpots);

        Vehicle car = new Car();
        ParkingSpot parkingCarSpot1 = new CarSpot(car);
        ParkingSpot parkingCarSpot2 = new CarSpot(car);
        ParkingSpot parkingCarSpot3 = new CarSpot(car);
        ParkingSpot parkingCarSpot4 = new CarSpot(car);

        List<ParkingSpot> parkingCarList = new ArrayList<>();
        parkingCarList.add(parkingCarSpot1);
        parkingCarList.add(parkingCarSpot2);
        parkingCarList.add(parkingCarSpot3);
        parkingCarList.add(parkingCarSpot4);

        totalSpotsOnFloor.put(SpotType.CAR, parkingCarList);


        ParkingFloor parkingFloor = new ParkingFloor(1, totalSpotsOnFloor);
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addFloor(parkingFloor);

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car);

        parkingLot.unparkVehicle(car);
        parkingLot.unparkVehicle(car);

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car);






    }
}
