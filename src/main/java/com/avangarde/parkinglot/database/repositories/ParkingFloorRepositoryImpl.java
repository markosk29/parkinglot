package com.avangarde.parkinglot.database.repositories;

import com.avangarde.parkinglot.parking.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.SpotType;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.parking.entities.ParkingSpot;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingFloorRepositoryImpl implements ParkingFloorRepository {
    public static final String PARKING_FLOOR_TABLE_NAME = "parking.parking_floors";
    public static final String PARKING_SPOTS_TABLE_NAME = "parking.parking_spots";
    public static final String PARKING_FLOOR_NUMBER_COLUMN = "parking_floor_number";
    public static final String PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME = "is_occupied";
    public static final String PARKING_SPOT_SPOT_TYPE_COLUMN_NAME = "spot_type";
    public static final String PARKING_FLOOR_ID_COLUMN_NAME = "parking_floor_id";


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
//                System.out.println("Inserted Parking Floor with id = " + result.getInt("id"));
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

    @Override
    public ParkingFloor findByIdParkingFloor(int id) throws SQLException {
        String sql = "SELECT * FROM " + PARKING_FLOOR_TABLE_NAME + " WHERE id= ?" + ";";
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ParkingFloor parkingFloor = null;
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql)) {

            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int floorID = resultSet.getInt(PARKING_FLOOR_NUMBER_COLUMN);
                List<ParkingSpot> parkingSpots = findSpotsByFloorId(floorID);
                parkingFloor = new ParkingFloor.ParkingFloorBuilder().floorID(floorID).spotPairs(convertListToMapOfParkingSpots(parkingSpots)).build();
            }
            return parkingFloor;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            dbUtil.close();
        }

        return null;
    }


    public List<ParkingSpot> findSpotsByFloorId(int id) throws SQLException {
        String sql = "SELECT " + PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME + ", " + PARKING_SPOT_SPOT_TYPE_COLUMN_NAME +
                " FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE " + PARKING_FLOOR_ID_COLUMN_NAME + "=?" + ";";
        List<ParkingSpot> spots = new ArrayList<>();
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;
        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql)) {
            ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ParkingSpot spot = parkingSpotFactory.createParkingSpot(resultSet.getString(PARKING_SPOT_SPOT_TYPE_COLUMN_NAME));
                spot.setOccupied(resultSet.getBoolean(PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME));
                spots.add(spot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            dbUtil.close();
        }

        return spots;
    }

    public Map<SpotType, List<ParkingSpot>> convertListToMapOfParkingSpots(List<ParkingSpot> parkingSpots) {
        Map<SpotType, List<ParkingSpot>> spotPairs = new HashMap<>();
        for (ParkingSpot spot : parkingSpots
        ) {
            if (spotPairs.containsKey(spot.getType())) {
                spotPairs.get(spot.getType()).add(spot);
            } else {
                spotPairs.put(spot.getType(), new ArrayList<>());
                spotPairs.get(spot.getType()).add(spot);
            }
        }
        return spotPairs;
    }

    public int getFloorCountFromDB() throws SQLException {
        int floorCount = 0;

        //Open connection to DB
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();


        String sql = "SELECT COUNT(id) FROM " + PARKING_FLOOR_TABLE_NAME + " WHERE id IS NOT NULL;";
        //String sql = "SELECT COUNT(id) FROM parking.parking_floors WHERE id IS NOT NULL;";

        try {
            Statement stmt = dbUtil.getConn().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int floorCountFromDB = resultSet.getInt("count");
                floorCount += floorCountFromDB;
            }
            return floorCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close();
        }
        return -1;
    }
}