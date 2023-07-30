package org.fdryt.ornamental.commons.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * An Interface that represents the Data Access Layer of the application.
 * Most of the common methods are defined here.
 *
 * @param <TEntity> represents the type of the concrete entity managed by JPA.
 * @param <ID> represents the identifier of the entity which should be serializable.
 */
public interface NurseryRepository<TEntity, ID extends Serializable> {

    /**
     * It verifies if the entity for the given ID exists.
     *
     * @param id the entity identifier.
     * @return TRUE if it exists and FALSE if it does not.
     */
    boolean exists(ID id);

    /**
     * Gets the entity for the given ID. If it does not exist, then it will return an empty {@link Optional} result.
     *
     * @param id the entity identifier.
     * @return {@link Optional} of the TEntity
     */
    Optional<TEntity> findById(ID id);

    /**
     * Adds a new record to the database table based on the given entity.
     *
     * @param entity the new record with the required information.
     * @return the persisted entity.
     */
    TEntity add(TEntity entity);

    /**
     * Adds new records to the database table based on the given entities passed as parameter.
     *
     * @param entities the collection of entities.
     * @return the persisted entities.
     */
    Collection<TEntity> addAll(Collection<TEntity> entities);

    /**
     * Updates an existing entity with the new information.
     *
     * @param entity the entity to update.
     * @return the updated entity.
     */
    TEntity update(TEntity entity);

    /**
     * Deletes an existing entity based on the ID passed as parameter.
     *
     * @param id the entity ID.
     */
    void deleteById(ID id);

}
