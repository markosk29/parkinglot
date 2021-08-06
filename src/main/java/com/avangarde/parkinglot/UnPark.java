package com.avangarde.parkinglot;

import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnPark {
    private final DBUtil dbUtil = new DBUtil();
    public static final String PARKING_SPOTS_TABLE_NAME = "parking.parking_spots";
    public static final String PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME = "is_occupied";
    public static final String PARKING_SPOT_VEHICLE_ID_COLUMN_NAME = "vehicle_id";



    public void unparkRandomVehicles() throws SQLException {
        List<Integer> vehicleIdList = getVehiclesIds();
        int numberOfVehiclesToUnpark = rand(1, vehicleIdList.size());
       if(vehicleIdList.size()!=0) {
           System.out.println("Number of vehicles to unpark: " + numberOfVehiclesToUnpark);
       }
        try {
            while (numberOfVehiclesToUnpark > 0) {
                int randomVehicleIdIndex = rand(0, vehicleIdList.size() - 1);
                int randomVehicleId = vehicleIdList.get(randomVehicleIdIndex);
                System.out.println(randomVehicleId);
                if (freeSpotUpdateByVehicleId(randomVehicleId) &&
                        deleteVehicleIdFromParkingSpot(randomVehicleId)) {
                    System.out.println("Vehicle with id " + randomVehicleId + " left the parking lot.");
                } else {
                    System.out.println("Vehicle with id " + randomVehicleId + " error at leaving the parking lot!");
                }
                vehicleIdList.remove(randomVehicleIdIndex);
                numberOfVehiclesToUnpark--;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The parking lot is empty!");
        }
    }

    private List<Integer> getVehiclesIds() {
        List<Integer> vehicleIdList = new ArrayList<>();
        dbUtil.open();
        String sql = "SELECT " + PARKING_SPOT_VEHICLE_ID_COLUMN_NAME + " FROM " + PARKING_SPOTS_TABLE_NAME +
                " WHERE " + PARKING_SPOT_VEHICLE_ID_COLUMN_NAME + " IS NOT NULL;";
        try (Statement stmt = dbUtil.getConn().createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                vehicleIdList.add(resultSet.getInt(PARKING_SPOT_VEHICLE_ID_COLUMN_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.close();
        }

        return vehicleIdList;
    }

    private boolean freeSpotUpdateByVehicleId(int vehicleId) {
        dbUtil.open();
        String sql = "UPDATE " + PARKING_SPOTS_TABLE_NAME +
                " SET " + PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME + " = false" +
                " WHERE " + PARKING_SPOT_VEHICLE_ID_COLUMN_NAME + " = ?" + ";";
        try(PreparedStatement preparedStatement = dbUtil.getConn().prepareStatement(sql))
        {
            preparedStatement.setInt(1, vehicleId);
            preparedStatement.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println(e.getMessage());

        }finally {
            dbUtil.close();
        }
        return false;
    }

    private boolean deleteVehicleIdFromParkingSpot(int vehicleId) {
        dbUtil.open();
        String sql = "UPDATE " + PARKING_SPOTS_TABLE_NAME +
                " SET " + PARKING_SPOT_VEHICLE_ID_COLUMN_NAME + " = null" +
                " WHERE " + PARKING_SPOT_VEHICLE_ID_COLUMN_NAME + " = ?" + ";";

        try(PreparedStatement preparedStatement = dbUtil.getConn().prepareStatement(sql)){
            preparedStatement.setInt(1, vehicleId);
            preparedStatement.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbUtil.close();
        }
        return false;
    }

    private int rand (int min, int max) {
        return (int) (Math.floor((Math.random() * (max-min+1) + min)));
    }
}
