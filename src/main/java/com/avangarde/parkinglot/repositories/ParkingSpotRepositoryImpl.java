package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.parking.models.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.utils.DBUtil;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;

public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {
    public static final String PARKING_SPOTS_TABLE_NAME = "parking.parking_spots";
    public static final String PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME = "is_occupied";
    public static final String PARKING_SPOT_SPOT_TYPE_COLUMN_NAME = "spot_type";

    @Override
    public ParkingSpot findByIdParkingSpot(int id) throws SQLException {
        String sql = "SELECT * FROM " + PARKING_SPOTS_TABLE_NAME + " WHERE id= ? ;";
        DBUtil dbUtil = new DBUtil();
        dbUtil.open();
        ResultSet resultSet = null;

        try (PreparedStatement pstmt = dbUtil.getConn().prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String spotType = resultSet.getString(PARKING_SPOT_SPOT_TYPE_COLUMN_NAME);
                boolean isOccupied = resultSet.getBoolean(PARKING_SPOT_IS_OCCUPIED_COLUMN_NAME);
                ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
                ParkingSpot parkingSpot = parkingSpotFactory.createParkingSpot(spotType);
                parkingSpot.setOcuppied(isOccupied);
                return parkingSpot;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            resultSet.close();
        }
        return null;
    }
}
