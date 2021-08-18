package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.entities.ParkingSpot;
import com.avangarde.parkinglot.entities.Vehicle;
import com.avangarde.parkinglot.auxs.intermeds.SpotType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotJPARepo implements JPARepo{
    private EntityManager entityManager;

    public ParkingSpotJPARepo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void create(Object entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<ParkingSpot> readAll() {
        entityManager.getTransaction().begin();

        return entityManager
                .createQuery("Select parkingSpot from ParkingSpot parkingSpot", ParkingSpot.class)
                .getResultList();
    }

    @Override
    public Object read(long id) {
        return entityManager.find(ParkingSpot.class, id);
    }

    @Override
    public void update(Object entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Object entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ParkingSpot findById(long id) {
        return entityManager.find(ParkingSpot.class, id);
    }

    public List<ParkingSpot> getParkingSpots() {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots = entityManager.createQuery("from ParkingSpot", ParkingSpot.class)
                .getResultList();
        return parkingSpots;
    }

    public List<ParkingSpot> getFreeParkingSpots() {
        List<ParkingSpot> freeSpots = new ArrayList<>();
        freeSpots = entityManager.createQuery("from ParkingSpot parkingSpot where parkingSpot.isOccupied = false", ParkingSpot.class)
                .getResultList();
        return freeSpots;
    }



    private ParkingSpot getFirstEmptySpotByType(String type) {
        List<ParkingSpot> parkingSpots = entityManager.createQuery("FROM ParkingSpot parkingSpot WHERE parkingSpot.isOccupied = false AND parkingSpot.type = :type", ParkingSpot.class)
                .setParameter("type", SpotType.valueOf(type))
                .getResultList();
        return parkingSpots.get(0);
    }


    public ParkingSpot getSpotByVehicle(Vehicle vehicle) {
         List<ParkingSpot> parkingSpots = entityManager.createQuery("FROM ParkingSpot parkingSpot WHERE parkingSpot.currentVehicle.id = :nr")
                .setParameter("nr", vehicle.getId())
                .getResultList();
        if (parkingSpots.size() == 0) {
            return null;
        } else return parkingSpots.get(0);
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
        if(spotToLeave != null){
        entityManager.getTransaction().begin();
        spotToLeave.setOccupied(false);
        spotToLeave.setCurrentVehicle(null);
        entityManager.persist(spotToLeave);
        System.out.println("Freeing spot id=" + spotToLeave.getId() + " type=" + spotToLeave.getType());
        entityManager.getTransaction().commit();}

    }
}
