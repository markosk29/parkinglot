package com.avangarde.parkinglot.current.entities;

import com.avangarde.parkinglot.old.parking.SpotType;
import com.avangarde.parkinglot.old.vehicle.VehicleType;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JPATest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        ParkingSpot spot = new ParkingSpot();
        spot.setOccupied(false);
        spot.setType(SpotType.CAR);

        ParkingFloor floor = new ParkingFloor();
        floor.setLevel(0);
        Set<ParkingSpot> spots = new HashSet<>();
        spots.add(spot);
        floor.setSpots(spots);

        ParkingLot lot = new ParkingLot();
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(floor);
        lot.setFloors(floors);

        Vehicle vehicle = new Vehicle();
        vehicle.setPlate("CJ01BOS");
        vehicle.setVehicleType(VehicleType.CAR);

        floor.setLot(lot);
        spot.setFloor(floor);

        entityManager.persist(vehicle);
        entityManager.persist(lot);
        entityManager.getTransaction().commit();

        ParkingLot foundLot = entityManager.find(ParkingLot.class, (long) 1);

        System.out.println(foundLot.getFloors().get(0).getLevel());
        System.out.println(foundLot.getFloors().get(0).getSpots().iterator().next().getType());

        entityManager.getTransaction().begin();

        entityManager.remove(lot);

        entityManager.getTransaction().commit();
    }
}