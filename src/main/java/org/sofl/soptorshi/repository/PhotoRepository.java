package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
