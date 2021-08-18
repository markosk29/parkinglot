package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.entities.ParkingLot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ParkingLotJPARepo implements JPARepo<ParkingLot> {
    private EntityManager entityManager;

    public ParkingLotJPARepo() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void create(ParkingLot entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<ParkingLot> readAll() {
        return entityManager.createQuery("SELECT lots FROM ParkingLot lots", ParkingLot.class).getResultList();
    }

    @Override
    public ParkingLot read(long id) {
        return entityManager.find(ParkingLot.class, id);
    }

    @Override
    public void update(ParkingLot entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(ParkingLot entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }


}
