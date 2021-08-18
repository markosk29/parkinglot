package com.avangarde.parkinglot.current.repositories;

import com.avangarde.parkinglot.current.entities.ParkingSpot;
import com.avangarde.parkinglot.current.entities.Vehicle;
import com.avangarde.parkinglot.old.parking.SpotType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotRepository {
    private final EntityManager entityManager;

    public ParkingSpotRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        this.entityManager = emf.createEntityManager();
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ParkingSpot findById(long id) {
        return entityManager.find(ParkingSpot.class, id);
    }

    public List<ParkingSpot> getParkingSpots() {
        List<ParkingSpot> parkingSpots;
        parkingSpots = entityManager.createQuery("from ParkingSpot", ParkingSpot.class)
                .getResultList();
        return parkingSpots;
    }

    public List<ParkingSpot> getFreeParkingSpots() {
        List<ParkingSpot> freeSpots;
        freeSpots = entityManager.createQuery("from ParkingSpot parkingSpot where parkingSpot.isOccupied = false", ParkingSpot.class)
                .getResultList();
        return freeSpots;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        entityManager.getTransaction().begin();
        entityManager.persist(parkingSpot);
        entityManager.getTransaction().commit();
    }


    private ParkingSpot getFirstEmptySpotByType(String type) {
        List<ParkingSpot> parkingSpot = entityManager
                .createQuery("FROM ParkingSpot parkingSpot WHERE parkingSpot.isOccupied = false AND parkingSpot.type = :type", ParkingSpot.class)
                .setParameter("type", SpotType.valueOf(type))
                .getResultList();
        return parkingSpot.get(0);
    }


    public ParkingSpot getSpotByVehicle(Vehicle vehicle) {
        return (ParkingSpot) entityManager.createQuery("FROM ParkingSpot parkingSpot WHERE parkingSpot.vehicle.id = :nr")
                .setParameter("nr", vehicle.getId())
                .getSingleResult();
    }

    public void OccupySpotByVehicle(Vehicle vehicle) {
        ParkingSpot spotToOccupy = getFirstEmptySpotByType(vehicle.getVehicleType().toString());
        entityManager.getTransaction().begin();
        spotToOccupy.setOccupied(true);
        spotToOccupy.setCurrentVehicle(vehicle);
        System.out.println("Vehicle with id=" + vehicle.getId() + " has parked on spot with id=" + spotToOccupy.getId() + " type=" + spotToOccupy.getType());
        entityManager.getTransaction().commit();
    }

    public void leaveSpot(Vehicle vehicle) {
        ParkingSpot spotToLeave = getSpotByVehicle(vehicle);
        entityManager.getTransaction().begin();
        spotToLeave.setOccupied(false);
        spotToLeave.setCurrentVehicle(null);
        entityManager.persist(spotToLeave);
        System.out.println("Freeing spot id=" + spotToLeave.getId() + " type=" + spotToLeave.getType());
        entityManager.getTransaction().commit();
    }
}
