package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByNameLike(String name);
}
