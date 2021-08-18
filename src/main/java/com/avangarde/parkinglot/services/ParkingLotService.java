package com.avangarde.parkinglot.services;

import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.entities.ParkingSpot;
import com.avangarde.parkinglot.repositories.JPARepo;
import com.avangarde.parkinglot.repositories.ParkingLotJPARepo;

public class ParkingLotService {
    private final JPARepo repo;

    public ParkingLotService() {
        this.repo = new ParkingLotJPARepo();
    }

    public ParkingLot loadLatestParkingLot() {
        System.out.println(repo.readAll().size());
        if (repo.readAll().size() > 0) {
            return (ParkingLot) repo.readAll().get(0);
        }
        return null;
    }

    public void summary(ParkingLot lot) {
        for (ParkingFloor floor:
                lot.getFloors()) {
            var count = 0;
            System.out.print("Floor " + floor.getLevel() + ":\n" + floor.getSpots().size());
            for (ParkingSpot spot : floor.getSpots()) {
                if (!spot.isOccupied()) {
                    count++;
                }
            }
            System.out.print("Free spots: " + count + "\n");
            System.out.println();
        }
    }

}
