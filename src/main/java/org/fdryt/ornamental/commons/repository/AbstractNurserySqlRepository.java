package org.fdryt.ornamental.commons.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

/**
 * An abstract class which implements the common logic of the methods defined in the interface {@link NurseryRepository}
 * that represents the Data Access Layer of the application.
 * Most of the common methods are defined here.
 *
 * @param <TEntity> represents the type of the concrete entity managed by JPA.
 * @param <ID> represents the identifier of the entity which should be serializable.
 */
public abstract class AbstractNurserySqlRepository<TEntity, ID extends Serializable> implements NurseryRepository<TEntity, ID> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<TEntity> clazz;

    /**
     * The protected constructor of the abstract repository which should receive as parameters
     * two important dependencies.
     *
     * @param em Represents the {@link EntityManager} implementation.
     * @param clazz Is the {@link Class} of the concrete entity that will be managed by JPA.
     */
    protected AbstractNurserySqlRepository(EntityManager em, Class<TEntity> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    /**
     * It verifies if the entity for the given ID exists.
     *
     * @param id the entity identifier.
     * @return TRUE if it exist and FALSE if it does not.
     */
    @Override
    public boolean exists(ID id) {
        var queryFormat = """
            SELECT CASE WHEN COUNT(e)
                THEN TRUE
                ELSE FALSE
                END
            FROM %s e
            WHERE e.id =:id""".formatted(clazz.getSimpleName());

        return em.createQuery(queryFormat, Boolean.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * Gets the entity for the given ID. If it does not exist, then it will return an empty {@link Optional} result.
     *
     * @param id the entity identifier.
     * @return {@link Optional} of the TEntity
     */
    @Override
    public Optional<TEntity> findById(ID id) {
        TEntity foundEntity = em.find(clazz, id);
        return foundEntity == null ? Optional.empty() : Optional.of(foundEntity);
    }

    /**
     * Adds a new record to the database table based on the given entity.
     *
     * @param entity the new record with the required information.
     * @return the persisted entity.
     */
    @Transactional
    @Override
    public TEntity add(TEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Updates an existing entity with the new information.
     *
     * @param entity the entity to update.
     * @return the updated entity.
     */
    @Transactional
    @Override
    public TEntity update(TEntity entity) {
        return em.merge(entity);
    }

    /**
     * Deletes an existing entity based on the ID passed as parameter.
     *
     * @param id the entity ID.
     */
    @Transactional
    @Override
    public void deleteById(ID id) {
        TEntity foundEntity = em.find(clazz, id);

        if (null == foundEntity) {
            throw new EntityNotFoundException("Entity %s with ID: %s does not found.".formatted(clazz.getSimpleName(), id));
        }

        em.remove(foundEntity);
    }
}
