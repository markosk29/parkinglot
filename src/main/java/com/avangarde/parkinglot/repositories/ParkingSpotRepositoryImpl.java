package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.parking.models.ParkingSpotFactory;
import com.avangarde.parkinglot.utils.DBUtil;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {
    public static final String PARKING_SPOTS_TABLE_NAME = "parking.parking_spots";
    public static final String PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME = "is_occupied";
    public static final String PARKING_SPOT_SPOT_TYPE_COLUMN_NAME = "spot_type";

    @Override
    public ParkingSpot findByIdParkingSpot(int id) {
        DBUtil dbUtil = new DBUtil();
        String sql = "SELECT * FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE id= ? ;";
        ResultSet resultSet = null;

        try {
            dbUtil.open();
            PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String spotType = resultSet.getString(PARKING_SPOT_SPOT_TYPE_COLUMN_NAME);
                boolean isOccupied = resultSet.getBoolean(PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME);
                ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
                ParkingSpot parkingSpot = parkingSpotFactory.createParkingSpot(spotType);
                parkingSpot.setOcuppied(isOccupied);
                return parkingSpot;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Returns parking spots
    public List<ParkingSpot> getFreeParkingSpotsFromDB() {
        List<ParkingSpot> freeSpotsFromDB = new ArrayList<ParkingSpot>();

        //Open connection to DB
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();


        String sql = "SELECT * FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE is_occupied = false;";
        //String sql = "SELECT * FROM parking.parking_spots WHERE is_occupied = false;";
        ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();

        try {
            Statement stmt = dbUtil.getConn().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
//              int spotID = resultSet.getInt("id");
//				int floorID = resultSet.getInt("floor_id");
//				int vehicleID = resultSet.getInt("vehicle_id");
//				boolean isOccupied = resultSet.getBoolean("is_occupied");
                String spotType = resultSet.getString("spot_type");

                //Create new parking spot and add it to list
                ParkingSpot parkingSpot = parkingSpotFactory.createParkingSpot(spotType);
                freeSpotsFromDB.add(parkingSpot);
            }

            dbUtil.close();
            return freeSpotsFromDB;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return null;
    }

    //Returns parking spot IDs
    public List<Integer> getFreeParkingSpotIDsFromDB() { //add to ParkingSpotRepository
        List<Integer> freeSpotIDsFromDB = new ArrayList<Integer>();

        //Open connection to DB
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();

        String sql = "SELECT * FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE is_occupied = false;";
        ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();

        try {
            Statement stmt = dbUtil.getConn().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int spotID = resultSet.getInt("id");
                int floorID = resultSet.getInt("parking_floor_id");
                int vehicleID = resultSet.getInt("vehicle_id");
                boolean isOccupied = resultSet.getBoolean("is_occupied");
                String spotType = resultSet.getString("spot_type");

                ParkingSpot parkingSpot = parkingSpotFactory.createParkingSpot(spotType);

                //Add spot ID to list
                freeSpotIDsFromDB.add(spotID);
            }

            dbUtil.close();
            return freeSpotIDsFromDB;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return null;
    }


    public boolean parkVehicleOnDBSpot(Vehicle vehicle, int vehicleID, int spotID) {
        String sql = "UPDATE " +PARKING_SPOTS_TABLE_NAME+ " SET is_occupied = true, vehicle_id = ? WHERE id= ?;";

        DBUtil dbUtil = new DBUtil();

        try {
            dbUtil.open();
            PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql);
            pstmt.setInt(1, vehicleID);
            pstmt.setInt(2, spotID);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbUtil.close();
        return false;
    }
}
