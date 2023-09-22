package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataJpaRepository extends JpaRepository<FileData, Integer> {

    Optional<FileData> findByName(String fileName);
}
