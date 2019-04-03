package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
    List<Designation> findByNameLike(String name);
}
