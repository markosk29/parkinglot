package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingSpot;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {
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
}
