package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VehicleJPARepo implements JPARepo<Vehicle> {
    private final EntityManager entityManager;

    @Autowired
    public VehicleJPARepo(EntityManager entityManager) {
        this.entityManager = entityManager;
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
                .createQuery("SELECT vehicles FROM Vehicle vehicles", Vehicle.class)
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
