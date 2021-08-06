package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingFloorRepositoryImpl implements ParkingFloorRepository {
    @Override
    public int createOne(ParkingFloor parkingFloor, int associatedParkingLotId) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        String sql = "INSERT INTO parking.parking_floors VALUES (DEFAULT, ?, ?) RETURNING id;";
        try (PreparedStatement statement = dbUtil.getConn().prepareStatement(sql)) {
            statement.setInt(1, associatedParkingLotId);
            statement.setInt(2, parkingFloor.getParkingFloorNumber());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.println("Inserted Parking Floor with id = " + result.getInt("id"));
                return result.getInt("id");
            }
            return -1;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            dbUtil.close();
        }
    }
}
