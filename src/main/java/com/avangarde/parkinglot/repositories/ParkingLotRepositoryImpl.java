package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.parking.models.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.parking.services.ParkingFloor;
import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    public static final String PARKING_LOT_TABLE_NAME = "parking.parking_lots";
    public static final String PARKING_LOT_ID_COMLUMN_NAME = "id";
    public static final String PARKING_FLOOR_ID_COMLUMN_NAME = "id";
    public static final String PARKING_FLOOR_ID_PARKING_LOT_COMLUMN_NAME = "parking_lot_id";
    public static final String PARKING_FLOOR_TABLE_NAME = "parking.parking_floors";

    @Override
    public ParkingLot findByIdParkingLot(int id) throws SQLException {
        String sql = "SELECT * FROM " + PARKING_LOT_TABLE_NAME + " WHERE id= ?" + ";";
        ParkingLot parkingLot = null;
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql))
 {

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
        }

        finally {
            resultSet.close();
        }

        return null;

    }

    public List<ParkingFloor> findFloorsByLotId(int id) throws SQLException {
        ParkingFloorRepositoryImpl parkingFloorRepository = new ParkingFloorRepositoryImpl();
        StringBuilder sql = new StringBuilder("SELECT id FROM " + PARKING_FLOOR_TABLE_NAME + " WHERE "+ PARKING_FLOOR_ID_PARKING_LOT_COMLUMN_NAME + " = ? " + ";");
        List<ParkingFloor> floors = new ArrayList<>();
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql.toString()))
        {
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int floorID = resultSet.getInt(PARKING_FLOOR_ID_COMLUMN_NAME);
                List spots =  parkingFloorRepository.findSpotsByFloorId(floorID);
                Map spotPairs = parkingFloorRepository.convertListToMapOfParkingSpots(spots);
                ParkingFloor floor = ParkingFloor.ParkingFloorBuilder.builder().floorID(floorID).spotPairs(spotPairs).build();
                floors.add(floor);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            resultSet.close();
        }

        return floors;
    }
}

