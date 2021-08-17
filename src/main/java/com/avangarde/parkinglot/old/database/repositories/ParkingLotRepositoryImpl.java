package com.avangarde.parkinglot.old.database.repositories;

import com.avangarde.parkinglot.old.parking.models.ParkingFloor;
import com.avangarde.parkinglot.old.parking.models.ParkingLot;
import com.avangarde.parkinglot.old.parking.models.ParkingSpot;
import com.avangarde.parkinglot.old.utils.DBUtil;
import com.avangarde.parkinglot.old.vehicle.models.Vehicle;
import org.postgresql.PGStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    public static final String PARKING_LOT_TABLE_NAME = "parking.parking_lots";
    public static final String PARKING_LOT_ID_COMLUMN_NAME = "id";
    public static final String PARKING_FLOOR_ID_COMLUMN_NAME = "id";
    public static final String PARKING_FLOOR_ID_PARKING_LOT_COMLUMN_NAME = "parking_lot_id";
    public static final String PARKING_FLOOR_TABLE_NAME = "parking.parking_floors";

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
//                System.out.println("Inserted Parking Lot with id = " + result.getInt("id"));
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

    @Override
    public ParkingLot findByIdParkingLot(int id) throws SQLException {
        String sql = "SELECT * FROM " + PARKING_LOT_TABLE_NAME + " WHERE id= ?" + ";";
        ParkingLot parkingLot = null;
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql)) {

            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int lotId = resultSet.getInt(PARKING_LOT_ID_COMLUMN_NAME);
                List<ParkingFloor> floors = findFloorsByLotId(lotId);
                parkingLot = new ParkingLot(floors);
            }
            return parkingLot;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
            dbUtil.close();
        }

        return null;

    }

    @Override
    public ParkingLot loadLatestParkingLot() {
        String query = "SELECT * FROM parking.parking_lots ORDER BY id DESC LIMIT 1;";

        int parkingLotId;

        DBUtil dbUtil = new DBUtil();
        dbUtil.open();

        try {
            PreparedStatement statement = dbUtil.getConn().prepareStatement(query);
            PGStatement pgStatement = (PGStatement) statement;
            pgStatement.setPrepareThreshold(1);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            parkingLotId = resultSet.getInt("id");

            resultSet.close();
            statement.close();

            List<ParkingFloor> floors = findFloorsByLotId(parkingLotId);

            System.out.println("Latest parking lot loaded from DB.");

            ParkingLot parkingLot = new ParkingLot(floors);
            parkingLot.setId(parkingLotId);

            return parkingLot;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbUtil.close();
        }

        System.out.println("Failed to find the latest parking lot.");

        return null;
    }

    public List<ParkingFloor> findFloorsByLotId(int id) throws SQLException {
        ParkingFloorRepositoryImpl parkingFloorRepository = new ParkingFloorRepositoryImpl();
        StringBuilder sql = new StringBuilder("SELECT id FROM " + PARKING_FLOOR_TABLE_NAME + " WHERE " + PARKING_FLOOR_ID_PARKING_LOT_COMLUMN_NAME + " = ? " + ";");
        List<ParkingFloor> floors = new ArrayList<>();
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql.toString())) {
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int floorID = resultSet.getInt(PARKING_FLOOR_ID_COMLUMN_NAME);
                List spots = parkingFloorRepository.findSpotsByFloorId(floorID);
                Map spotPairs = parkingFloorRepository.convertListToMapOfParkingSpots(spots);
                ParkingFloor floor = ParkingFloor.ParkingFloorBuilder.builder().floorID(floorID).spotPairs(spotPairs).build();
                floors.add(floor);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
            dbUtil.close();
        }

        return floors;
    }


    public void occupySpots(List<Vehicle> vehicleList, List<Integer> freeSpotsIDs, ParkingLot parkingLot) {
        ParkingSpotRepositoryImpl parkingSpotRepository = new ParkingSpotRepositoryImpl();
        List<Vehicle> parkedVehicles = new ArrayList<>();
        //System.out.println("Vehicles: " + vehicleList.size());
        System.out.println("Empty parking spots: " + freeSpotsIDs.size() + "\n");
        //String freeSpotsIDsString = Arrays.toString(freeSpotsIDs.toArray()).replace("[","").replace("]","");
        int count = 0;
        boolean reset = false;

        //Check if the given list has any free parking spots
        if (freeSpotsIDs.size() != 0) {

            for (Vehicle vehicle : vehicleList) {
                System.out.println("Your vehicle: " + vehicle.getType());

                for (Integer spotID : freeSpotsIDs) {
                    ParkingSpot parkingSpot = parkingSpotRepository.findByIdParkingSpot(spotID);

                    if (vehicle.getType().toString().equals(parkingSpot.getType().toString())
                            && !parkingSpot.isOccupied()) {

                        System.out.println("Ocuppying DB Spot...");
                        parkingSpotRepository.parkVehicleOnSpot(vehicle, vehicle.getId(), spotID);
                        parkedVehicles.add(vehicle);
                        parkingLot.parkVehicle(vehicle);
                        freeSpotsIDs.remove(spotID);
                        count++;
                        break;
                    }
//                        if (freeSpotsIDs.size() == 0
//                                || count >= vehicleList.size()) {
//                            reset = true;
//                        }
//                    }
//
//                    if (!parkedVehicles.contains(vehicle)) {
//                        vehicleList.remove(vehicle);
//                    }
//
//                    if (vehicleList.indexOf(vehicle) == vehicleList.size() - 1
//                    && vehicleList.size() == 0) {
//                        reset = true;
//                    }
                }
//
////            System.out.println(parkedVehicles.size() + " out of " + vehicleList.size() + " vehicles parked \n");
////        } else {
////            System.out.println("There are no parking spots available at this time.");
////        }
            }
        }
    }}