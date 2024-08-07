package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.PlantV3;
import org.fdryt.ornamental.dto.repertory.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepertoryRepository extends JpaRepository<PlantV3, String> {

    @Query(name = "findAllItems", nativeQuery = true)
    List<ItemDTO> findAllItems();
}
