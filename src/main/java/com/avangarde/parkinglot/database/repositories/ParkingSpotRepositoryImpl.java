package com.avangarde.parkinglot.database.repositories;

import com.avangarde.parkinglot.parking.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.entities.ParkingSpot;
import com.avangarde.parkinglot.utils.DBUtil;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {
    public static final String PARKING_SPOTS_TABLE_NAME = "parking.parking_spots";
    public static final String PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME = "is_occupied";
    public static final String PARKING_SPOT_SPOT_TYPE_COLUMN_NAME = "spot_type";

    @Override
    public int createOne(ParkingSpot parkingSpot, int associatedParkingFloorId) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        String sql = "INSERT INTO parking.parking_spots VALUES (DEFAULT, ?, NULL, ?, ?) RETURNING id";
        try (PreparedStatement statement = dbUtil.getConn().prepareStatement(sql)) {
            statement.setInt(1, associatedParkingFloorId);
            statement.setBoolean(2, false);
            statement.setString(3, parkingSpot.getType().toString());
            var result = statement.executeQuery();
            if (result.next()) {
//                System.out.println("Inserted Parking Spot with id = " + result.getInt("id"));
                return result.getInt("id");
            }
            return -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        } finally {
            dbUtil.close();
        }
    }

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
                parkingSpot.setOccupied(isOccupied);
                return parkingSpot;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close();
        }

        return null;
    }


    //Returns parking spot IDs
    public List<Integer> getFreeParkingSpotIDs(int parkingLotID) { //add to ParkingSpotRepository
        List<Integer> freeSpotIDs = new ArrayList<Integer>();

        //Open connection to DB
        DBUtil dbUtil = new DBUtil();

        String sql = "SELECT * FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE is_occupied = false AND parking_floor_id IN (SELECT id FROM parking.parking_floors WHERE parking_lot_id = ?);";

        ResultSet resultSet = null;

        try {
            dbUtil.open();
            PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql);
            pstmt.setInt(1, parkingLotID);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int spotID = resultSet.getInt("id");
                freeSpotIDs.add(spotID);
            }
            return freeSpotIDs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return null;
    }


    public boolean parkVehicleOnSpot(Vehicle vehicle, int vehicleID, int spotID) {
        String sql = "UPDATE " + PARKING_SPOTS_TABLE_NAME + " SET is_occupied = true, vehicle_id = ? " + " WHERE id= ?;";

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
        } finally {
            dbUtil.close();
        }
        return false;
    }
}
