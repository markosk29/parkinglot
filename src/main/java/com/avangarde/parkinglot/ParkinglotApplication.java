package com.avangarde.parkinglot;

import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.SQLException;

public class ParkinglotApplication {





	public static void main(String[] args) throws SQLException {

		UnPark unPark = new UnPark();
		unPark.unparkRandomVehicles();

	}







}
