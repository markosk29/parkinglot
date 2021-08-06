package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingLot;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    @Override
    public int createOne(ParkingLot parkingLot) {
        // Establish conn to DB
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        // Create a query string
        String sql = "INSERT INTO parking.parking_lots VALUES (DEFAULT) RETURNING id;";
        try (var statement = dbUtil.getConn().createStatement();
             // Send query
             var result = statement.executeQuery(sql)) {
            // Process the ResultSet object
            if (result.next()) {
                System.out.println("Inserted Parking Lot with id = " + result.getInt("id"));
                return result.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            // Close the connection
            dbUtil.close();
        }
    }
}
