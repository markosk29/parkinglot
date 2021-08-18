package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.entities.ParkingFloor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ParkingFloorJPARepo implements JPARepo<ParkingFloor> {

    private EntityManager entityManager;

    public ParkingFloorJPARepo() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void create(ParkingFloor entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<ParkingFloor> readAll() {
        return entityManager.createQuery("Select floor from ParkingFloor floor",ParkingFloor.class).getResultList();
    }

    @Override
    public ParkingFloor read(long id) {
       return entityManager.find(ParkingFloor.class, id);
    }

    @Override
    public void update(ParkingFloor entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(ParkingFloor entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
