package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PictureJpaRepository extends JpaRepository<Picture, Integer> {

    Optional<Picture> findByName(String name);
}
