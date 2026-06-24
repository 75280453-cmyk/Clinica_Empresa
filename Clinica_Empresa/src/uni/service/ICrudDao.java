package uni.service;

import java.util.List;

// I - Interface Segregation: solo define operaciones CRUD esenciales
// D - Dependency Inversion: las clases DAO dependen de esta abstracción
public interface ICrudDao<T> {

    void create(T o) throws Exception;

    void update(T o) throws Exception;

    void delete(T o) throws Exception;

    T find(Object o) throws Exception;

    List<T> readAll() throws Exception;
}
