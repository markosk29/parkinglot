package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.utils.DBUtil;
import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.services.VehicleBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingRepositoryImpl implements ParkingRepository {

    public static final String USER = "postgres";
    public static final String PASS = "123patru";
    public static final String URL = "jdbc:postgresql://localhost/postgres";

    public List<Vehicle> getVehicles() throws SQLException {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        // Establish conn to DB
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        // Create a query string
        String sql = "SELECT * FROM parking.vehicles";
        try (Statement statement = dbUtil.getConn().createStatement();
             // Send query
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Display values
                System.out.println(resultSet.getString("plate"));
                if (resultSet.getString("plate") == null) {
                    System.out.print("Vehicle Type: " + resultSet.getString("vehicle_type") + "\n");
                } else {
                    System.out.print("Plate: " + resultSet.getString("plate"));
                    System.out.print(", Vehicle Type: " + resultSet.getString("vehicle_type") + "\n");
                }
                Vehicle vehicle = VehicleBuilder.builder().createVehicle(resultSet.getString("vehicle_type"), resultSet.getString("plate"));

                vehicleList.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO
    @Override
    public List<ParkingLot> getParkingLots() throws SQLException {
        List<ParkingLot> parkingLots = new ArrayList<>();
        // Establish conn to DB
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        // Create a query string
        String sql = "SELECT * FROM parking_lots";
        try (Statement statement = dbUtil.getConn().createStatement();
             // Send query
             ResultSet resultSet = statement.executeQuery(sql)) {
            Integer id = null;
            // Process the ResultSet object
            while(resultSet.next()) {
                id = resultSet.getInt("id");
                ParkingLot parkingLot = new ParkingLot();
            }
            if (id == null) {
                System.out.println("No records found.");
            }
        }
        return parkingLots;
    }
}
