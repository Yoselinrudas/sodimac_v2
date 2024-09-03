package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.Area;
import com.brixton.sodimac_v2.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRegistryState(RegistryStateType registryState);
    List<Employee> findByArea(Area area);
}
