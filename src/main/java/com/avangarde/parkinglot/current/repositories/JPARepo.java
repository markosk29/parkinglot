package com.avangarde.parkinglot.current.repositories;

import java.util.List;

public interface JPARepo<E> {
    void create(E entity);
    List<E> readAll();
    E read(E entity);
    void update(E entity);
    void delete(E entity);
}