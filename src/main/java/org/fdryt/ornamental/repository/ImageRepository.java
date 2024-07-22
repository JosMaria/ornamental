package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {}
