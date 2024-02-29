package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlantJpaRepository extends JpaRepository<Plant, Integer> {

    @Query("""
        SELECT
            CASE WHEN COUNT(p) > 0
                THEN TRUE
                ELSE FALSE
            END
        FROM Plant p
        WHERE p.fundamentalData.commonName = :commonName
    """)
    boolean existsByCommonName(@Param("commonName") String commonName);

    @Query("""
        SELECT NEW org.fdryt.ornamental.dto.nursery.ProductResponseDTO(
            p.id,
            p.fundamentalData.commonName,
            p.fundamentalData.scientificName.name,
            p.fundamentalData.scientificName.scientistLastnameInitial,
            p.status,
            f.name,
            p.fundamentalData.commonName)
        FROM Plant p
        LEFT JOIN Family f
            ON f.id = p.fundamentalData.family.id
    """)
    Page<ProductResponseDTO> findAllProducts(Pageable pageable);

    @Query("""
        SELECT NEW org.fdryt.ornamental.dto.nursery.ProductResponseDTO(
            p.id,
            p.fundamentalData.commonName,
            p.fundamentalData.scientificName.name,
            p.fundamentalData.scientificName.scientistLastnameInitial,
            p.status,
            f.name,
            p.fundamentalData.commonName)
        FROM Plant p
        LEFT JOIN Family f
            ON f.id = p.fundamentalData.family.id
        WHERE :classification MEMBER OF p.fundamentalData.classifications
    """)
    Page<ProductResponseDTO> findAllProductsByClassification(Pageable pageable, @Param("classification") Classification classification);

    @Query("""
        SELECT NEW org.fdryt.ornamental.dto.nursery.ItemResponseDTO(
            p.id,
            p.fundamentalData.commonName,
            p.fundamentalData.scientificName.name,
            p.fundamentalData.scientificName.scientistLastnameInitial,
            f.name)
        FROM Plant p
        LEFT JOIN Family f
            ON f.id = p.fundamentalData.family.id
        """)
    Page<ItemResponseDTO> findAllItems(Pageable pageable);

    @Query("""
            SELECT NEW org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO(
                p.id,
                p.fundamentalData.commonName)
            FROM Plant p
            """)
    List<SimpleInfoPlantResponseDTO> findAllSimpleInfoPlant();

    @Transactional
    @Modifying
    @Query("""
            UPDATE Plant p
            SET p.fundamentalData.family = NULL
            WHERE p.fundamentalData.family.id = :familyId
            """)
    int updatePlantFamilyIdToRemoveFamily(@Param("familyId") int familyId);
}
