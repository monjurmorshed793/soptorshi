package org.sofl.soptorshi;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.apache.catalina.Context;
import org.apache.catalina.session.StandardManager;
import org.sofl.soptorshi.config.UserRoles;
import org.sofl.soptorshi.model.*;
import org.sofl.soptorshi.model.enums.EmploymentType;
import org.sofl.soptorshi.model.enums.UserStatus;
import org.sofl.soptorshi.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableCaching
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @Bean
    @Transactional
    public CommandLineRunner loadData(RoleRepository roleRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, DesignationRepository designationRepository, EmployeeRepository employeeRepository, UserRepository userRepository){
        return (args)->{
            List<Role> roleList = roleRepository.findAll();
            if(roleList.size()==0){
                List<Role> newRoleList = new ArrayList<>();
                List<String> roles = Arrays.asList(UserRoles.getAllRoles());
                roles.forEach(r->{
                    newRoleList.add(new Role(r, r));
                });
                roleRepository.saveAll(newRoleList);

                List<Department> departmentList = new ArrayList<>();

                departmentList.add(new Department("Accounts Department", "Accounts Dept", "Accounts Department"));
                departmentList.add(new Department("Production Department", "Production Dept", ""));
                departmentList.add(new Department("Cold Storage Department", "Cold Storage Department", ""));
                departmentList.add(new Department("Lab & Quality Department", "Lab & Quality Dept", ""));
                departmentList.add(new Department("Machine & Maintenance Dept", "Machine & Maintenance Dept", ""));
                departmentList.add(new Department("HR & Admin Department", "H$ & Admin Dept", ""));
                departmentRepository.saveAll(departmentList);

                Location location = new Location("Factory", "Factory");
                locationRepository.save(location);

                Designation designation = new Designation("HR Admin", "");
                designationRepository.save(designation);


                Employee employee = new Employee();
                employee.setEmployeeId("11001");
                employee.setName("Monjur-E-Morshed");
                employee.setEmploymentType(EmploymentType.ACTIVE);
                employee.setDesignation(designation);
                Role role = roleRepository.findByName("ROLE_ADMIN");
                employee.setRole(Arrays.asList(role));
                employee.setDepartment(departmentList.get(departmentList.size()-1));
                employee.setLocation(location);
                employee.setFatherName("Late Md. Sohrab Ali Miah");
                employee.setMotherName("Mowluda Khanam Mary");
                employeeRepository.save(employee);

                User user = new User();
                user.setUserId(employee.getEmployeeId());
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                user.setPassword(bCryptPasswordEncoder.encode("12345"));
                user.setStatus(UserStatus.ACTIVE);
                user.setLastModified(Instant.now());
                userRepository.save(user);
            }
        };
    }


}
