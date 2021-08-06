package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.services.ParkingFloor;
import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.utils.DBUtil;
import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.services.VehicleBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleRepositoryImpl implements VehicleRepository {
    public static final String VEHICLE_TABLE_NAME = "parking.vehicles";
    public static final String VEHICLE_TYPE_COLUMN_NAME = "vehicle_type";
    public static final String VEHICLE_PLATE_COLUMN_NAME = "plate";

    private List<Vehicle> vehicles;

    public VehicleRepositoryImpl() {
        this.vehicles = new ArrayList<>();
    }

    @Override
    public List<Vehicle> loadLatestVehicles(ParkingLot parkingLot) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();

        Map<String, Integer> freeSpots = getNumberOfVehicles(parkingLot);

        String query = "SELECT * FROM parking.vehicles WHERE vehicle_type IN (";

        for(var set : freeSpots.entrySet()) {
            query += "'" +set.getKey()+ "',";

        }

        query = query.substring(0, query.length() - 1);
        query += ") ORDER BY id DESC;";

        System.out.println(query);

        try(Statement statement = dbUtil.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(query)){

            while(resultSet.next()) {
                if(freeSpots.containsKey(resultSet.getString("vehicle_type")) &&
                        freeSpots.get(resultSet.getString("vehicle_type")) > 0) {

                    vehicles.add(VehicleBuilder.builder()
                            .createVehicle(resultSet.getString("vehicle_type"), resultSet.getString("plate")));

                    freeSpots.replace(resultSet.getString("vehicle_type"),
                            freeSpots.get(resultSet.getString("vehicle_type")) - 1);
                }
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

    @Override
    public Vehicle findByIdVehicle(int id) throws SQLException {
        String sql = "SELECT * FROM " + VEHICLE_TABLE_NAME + " WHERE id= ? ;" ;
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;
        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString(VEHICLE_TYPE_COLUMN_NAME);
                String plate = resultSet.getString(VEHICLE_PLATE_COLUMN_NAME);
                Vehicle vehicle = new VehicleBuilder().createVehicle(type, plate);
                return vehicle;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            resultSet.close();
        }
        return null;
    }

    private Map<String, Integer> getNumberOfVehicles(ParkingLot parkingLot) {
        Map<String, Integer> freeSpots = new HashMap<>();
        List<ParkingFloor> parkingFloors = parkingLot.getFloors();

        for(ParkingFloor parkingFloor : parkingFloors) {
            for (var entry : parkingFloor.getSpotPairs().entrySet()) {
                int count = 0;

                for (var spot : entry.getValue()) {
                    if (!spot.isOcuppied()) {
                        count++;
                    }
                }

                freeSpots.put(String.valueOf(entry.getKey()), count);
            }
        }

        return freeSpots;
    }
}
