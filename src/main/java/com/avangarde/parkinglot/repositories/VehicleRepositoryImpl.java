package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.utils.DBUtil;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleRepositoryImpl implements VehicleRepository {
    @Override
    public int createOne(Vehicle vehicle) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        String sql = "INSERT INTO parking.vehicles VALUES (DEFAULT, ?, ?) RETURNING id;";
        try (PreparedStatement statement = dbUtil.getConn().prepareStatement(sql)) {
            statement.setString(1, vehicle.getType().toString());
            statement.setString(2, vehicle.getPlate());
            var result = statement.executeQuery();
            if (result.next()) {
                System.out.println("Inserted vehicle with id = " + result.getInt("id"));
                return result.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            dbUtil.close();
        }
    }
}
