package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.entities.ParkingFloor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ParkingFloorJPARepo implements JPARepo<ParkingFloor> {

    private final EntityManager entityManager;

    @Autowired
    public ParkingFloorJPARepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(ParkingFloor entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<ParkingFloor> readAll() {
        return entityManager.createQuery("Select floor from ParkingFloor floor", ParkingFloor.class).getResultList();
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
