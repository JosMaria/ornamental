package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            p.fundamentalData.family.name,
            p.fundamentalData.commonName)
        FROM Plant p
    """)
    Page<ProductResponseDTO> findAllProducts(Pageable pageable);

    @Query("""
        SELECT NEW org.fdryt.ornamental.dto.nursery.ProductResponseDTO(
            p.id,
            p.fundamentalData.commonName,
            p.fundamentalData.scientificName.name,
            p.fundamentalData.scientificName.scientistLastnameInitial,
            p.status,
            p.fundamentalData.family.name,
            p.fundamentalData.commonName)
        FROM Plant p
        WHERE :classification MEMBER OF p.fundamentalData.classifications
    """)
    Page<ProductResponseDTO> findAllProductsByClassification(Pageable pageable, @Param("classification") Classification classification);

    @Query("""
        SELECT NEW org.fdryt.ornamental.dto.nursery.ItemResponseDTO(
            p.id,
            p.fundamentalData.commonName,
            p.fundamentalData.scientificName.name,
            p.fundamentalData.scientificName.scientistLastnameInitial,
            p.fundamentalData.family.name)
        FROM Plant p
        """)
    Page<ItemResponseDTO> findAllItems(Pageable pageable);

    @Query("""
            SELECT NEW org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO(
                p.id,
                p.fundamentalData.commonName)
            FROM Plant p
            """)
    List<SimpleInfoPlantResponseDTO> findAllSimpleInfoPlant();
}
