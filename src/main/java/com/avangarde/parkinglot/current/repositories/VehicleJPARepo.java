package com.avangarde.parkinglot.current.repositories;

import com.avangarde.parkinglot.current.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class VehicleJPARepo implements JPARepo<Vehicle> {
    private EntityManager entityManager;

    public VehicleJPARepo() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void create(Vehicle entity) {

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Vehicle> readAll() {
        return entityManager
                .createQuery("Select vehicle from Vehicle vehicle", Vehicle.class)
                .getResultList();
    }

    @Override
    public Vehicle read(long id) {
        return entityManager.find(Vehicle.class, id);
    }

    @Override
    public void update(Vehicle entity) {

    }

    @Override
    public void delete(Vehicle entity) {

    }
}
