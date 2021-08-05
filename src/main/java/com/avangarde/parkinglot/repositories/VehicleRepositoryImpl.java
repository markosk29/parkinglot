package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.utils.DBUtil;
import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.services.VehicleBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryImpl implements VehicleRepository {

    private List<Vehicle> vehicles;

    public VehicleRepositoryImpl() {
        this.vehicles = new ArrayList<>();
    }

    @Override
    public List<Vehicle> loadAllVehicles() {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();

        String query = "SELECT * FROM parking.vehicles";

        try(Statement statement = dbUtil.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(query)){

            while(resultSet.next()) {
                vehicles.add(VehicleBuilder.builder()
                        .createVehicle(resultSet.getString("vehicle_type"), resultSet.getString("plate")));
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return vehicles;
    }

    @Override
    public boolean updateParkingSpot(int id, boolean isOccupied) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();

        String query = "UPDATE parking.parking_spots " +
                "SET is_occupied = " +isOccupied+
                " WHERE id = " +id+ ";";

        try(Connection conn = dbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.execute();

            return true;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void printVehicles(){
        for(Vehicle vehicle : this.vehicles){
            System.out.println(vehicle.getInfo());
        }
    }
}
