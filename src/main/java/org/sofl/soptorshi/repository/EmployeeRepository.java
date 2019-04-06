package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    @Cacheable(value = "employee", key = "#employeeId")
    Employee findByEmployeeId(String employeeId);

}
