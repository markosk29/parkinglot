package com.avangarde.parkinglot;

import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.repositories.*;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class ParkinglotApplication {





	public static void main(String[] args) throws SQLException {

		//Initialize DB repos
		ParkingLotRepositoryImpl parkingLotRepository = new ParkingLotRepositoryImpl();
		ParkingSpotRepositoryImpl parkingSpotRepository = new ParkingSpotRepositoryImpl();
		VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

		//Fetch parking lot from DB and store it
		ParkingLot parkingLot = parkingLotRepository.findByIdParkingLot(2);
		parkingLot.summary();

		//Fetch free parking spots and vehicles lists
		List<Integer> freeSpotIDs = parkingSpotRepository.getFreeParkingSpotIDs(2);
		List<Vehicle> vehicleList = vehicleRepository.loadLatestVehicles(parkingLot);

		//Park vehicles
		parkingLotRepository.occupySpots(vehicleList, freeSpotIDs, parkingLot);
		parkingLot.summary();

//		//Unpark vehicles
//		UnPark unPark = new UnPark();
//		unPark.unparkRandomVehicles();
//		parkingLot.summary();


	}

}
