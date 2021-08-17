package com.avangarde.parkinglot.current.repositories;

import com.avangarde.parkinglot.current.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class VehicleRepository implements JPARepo<Vehicle> {
    private EntityManager entityManager;

    public VehicleRepository() {
        EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("com.avangarde.parkinglot");

        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void create(Vehicle entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Vehicle> readAll() {
       return this.entityManager
               .createQuery("find vehicles from Vehicle vehicles", Vehicle.class)
               .getResultList();
    }

    @Override
    public Vehicle read(long id) {
        return this.entityManager.find(Vehicle.class, id);
    }

    @Override
    public void update(Vehicle entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Vehicle entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }
}
