package com.fullstackbd.student.common.service;

import com.fullstackbd.student.common.exceptions.DatabaseAddException;
import com.fullstackbd.student.common.exceptions.DatabaseDeleteException;
import com.fullstackbd.student.common.exceptions.DatabaseUpdateException;
import com.fullstackbd.student.common.exceptions.ModelNotFoundException;

import java.util.List;

/**
 * Crud Service v1.0
 * CrudService is an interface that contains the basic CRUD operations. It is a generic interface that can be used for any entity.
 *
 * @param <E>
 * @param <D>
 * @param <I>
 */
public interface CrudService<E, D, I> {
    /**
     * This is the method to get all the entities.
     *
     * @return List<E>
     */
    List<E> findAll();

    /**
     * This is the method to save an entity.
     *
     * @param dto
     * @return E (saved entity)
     * @throws DatabaseAddException
     */
    E save(D dto) throws DatabaseAddException;

    /**
     * This is the method to find an entity by id.
     *
     * @param id
     * @return E (found entity)
     * @throws ModelNotFoundException
     */
    E findById(I id) throws ModelNotFoundException;

    /**
     * This is the method to update an entity by id.
     *
     * @param id
     * @param dto
     * @return E (updated entity)
     * @throws DatabaseUpdateException
     */
    E update(I id, D dto) throws DatabaseUpdateException;

    /**
     * This is the method to delete an entity by id.
     *
     * @param id
     * @return E (deleted entity)
     * @throws DatabaseDeleteException
     */
    E deleteById(I id) throws DatabaseDeleteException;
}
