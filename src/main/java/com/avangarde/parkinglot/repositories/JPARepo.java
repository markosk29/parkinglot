package com.avangarde.parkinglot.repositories;

import java.util.List;

public interface JPARepo<E> {
    void create(E entity);
    List<E> readAll();
    E read(long id);
    void update(E entity);
    void delete(E entity);
}
