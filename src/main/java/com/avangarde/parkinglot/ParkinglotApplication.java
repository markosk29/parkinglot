package com.avangarde.parkinglot;

import com.avangarde.parkinglot.repositories.ParkingLotRepository;
import com.avangarde.parkinglot.repositories.ParkingLotRepositoryImpl;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.SQLException;

public class ParkinglotApplication {





	public static void main(String[] args) throws SQLException {


		ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
		parkingLotRepository.findByIdParkingLot(1).summary();
		UnPark unPark = new UnPark();
		unPark.unparkRandomVehicles();
		parkingLotRepository.findByIdParkingLot(1).summary();


	}







}
