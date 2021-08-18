package com.avangarde.parkinglot.current.services;

import com.avangarde.parkinglot.current.repositories.JPARepo;
import com.avangarde.parkinglot.current.repositories.ParkingLotJPARepo;
import com.avangarde.parkinglot.old.parking.models.ParkingLot;

public class ParkingLotService {
    private JPARepo repo;

    public ParkingLotService() {
        this.repo = new ParkingLotJPARepo();
    }

    public ParkingLot loadLatestParkingLot() {
        if (repo.readAll().size() > 0) {
            return (ParkingLot) repo.readAll().get(0);
        }
        return null;
    }
}
