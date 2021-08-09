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
        int noOfEntries = freeSpots.size() + 1;

        String query = "SELECT * FROM parking.vehicles WHERE vehicle_type IN (";

        for(int i = 0; i < noOfEntries - 1; i++) {
            query += "?,";

        }

        query = query.substring(0, query.length() - 1);
        query += ") ORDER BY id DESC;";

        try(PreparedStatement statement = dbUtil.getConn().prepareStatement(query)){
            int index = 1;

            for(var set : freeSpots.entrySet()) {
                if(index < noOfEntries) {
                    statement.setString(index, set.getKey());

                    index++;
                }
            }

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                if(freeSpots.containsKey(resultSet.getString("vehicle_type").toUpperCase()) &&
                        freeSpots.get(resultSet.getString("vehicle_type").toUpperCase()) > 0) {

                    vehicles.add(VehicleBuilder.builder()
                            .createVehicle(resultSet.getString("vehicle_type").toUpperCase(),
                                    resultSet.getString("plate")));

                    freeSpots.replace(resultSet.getString("vehicle_type").toUpperCase(),
                            freeSpots.get(resultSet.getString("vehicle_type").toUpperCase()) - 1);
                }
            }

            System.out.println("Loaded " +vehicles.size()+ " vehicles from DB.");

            resultSet.close();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        dbUtil.close();

        return vehicles;
    }

    @Override
    public boolean updateParkingSpot(int id, boolean isOccupied) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();

        String query = "UPDATE parking.parking_spots " +
                "SET is_occupied = ? " +
                " WHERE id = ? ;";

        try(Connection conn = dbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setBoolean(1, isOccupied);
            preparedStatement.setInt(2, id);

            preparedStatement.execute();

            System.out.println("Parking spot " +id+ " occupied status: " +isOccupied);

            preparedStatement.close();

            return true;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        dbUtil.close();

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
        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql)) {
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

                if(freeSpots.containsKey(String.valueOf(entry.getKey()))) {
                    freeSpots.put((String.valueOf(entry.getKey())), freeSpots.get(String.valueOf(entry.getKey())) + count);
                } else {
                    freeSpots.put(String.valueOf(entry.getKey()), count);
                }
            }
        }

        return freeSpots;
    }
}
