package com.avangarde.parkinglot.services;

import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.entities.ParkingSpot;
import com.avangarde.parkinglot.repositories.JPARepo;
import com.avangarde.parkinglot.repositories.ParkingLotJPARepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    private final JPARepo repo;

    public ParkingLotService(ParkingLotJPARepo parkingLotJPARepo) {
        this.repo = parkingLotJPARepo;
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
