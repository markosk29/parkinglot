package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.entities.ParkingSpot;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                System.out.println("Inserted Parking Spot with id = " + result.getInt("id"));
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
    public ParkingSpot findByIdParkingSpot(int id) throws SQLException {
        String sql = "SELECT * FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE id= ? ;";
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql)) {
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
            resultSet.close();
        }
        return null;
    }
}
