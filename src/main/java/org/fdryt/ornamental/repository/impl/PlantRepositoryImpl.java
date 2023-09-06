package org.fdryt.ornamental.repository.impl;

import jakarta.persistence.TypedQuery;
import org.fdryt.ornamental.commons.repository.AbstractNurserySqlRepository;
import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.repository.PlantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class PlantRepositoryImpl extends AbstractNurserySqlRepository<Plant, Integer> implements PlantRepository {

    public PlantRepositoryImpl() {
        super(Plant.class);
    }

    @Override
    public boolean existsByCommonName(String commonName) {
        var jpqlQuery = """
            SELECT CASE WHEN COUNT(p) > 0
                THEN TRUE
                ELSE FALSE
                END
            FROM Plant p
            WHERE p.fundamentalData.commonName = :commonName
            """;

        return em.createQuery(jpqlQuery, Boolean.class)
                .setParameter("commonName", commonName)
                .getSingleResult();
    }

    public Page<ItemResponseDTO> getAllItemsPaginated(Pageable pageable) {
        var jpqlQuery = """
                
                """;


        TypedQuery<ItemResponseDTO> query = em.createQuery(jpqlQuery, ItemResponseDTO.class)
                .setFirstResult(PageableUtils.getOffsetAsInteger(pageable))
                .setMaxResults(pageable.getPageSize());

        return PageableExecutionUtils.getPage(query.getResultList(), pageable, () -> 0);
    }
}
