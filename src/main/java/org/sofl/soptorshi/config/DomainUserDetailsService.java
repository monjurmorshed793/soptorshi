package org.sofl.soptorshi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofl.soptorshi.model.Employee;
import org.sofl.soptorshi.repository.EmployeeRepository;
import org.sofl.soptorshi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService, Serializable {
    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.debug("Authenticating {}", login);
        return createSpringSecurityUser(login);
    }

    private User createSpringSecurityUser(String login){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Employee employee = employeeRepository.findByEmployeeId(login);
        employee.getRole().forEach(r->{
            grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        org.sofl.soptorshi.model.User user = userRepository.getByUserId(login);
        return new User(user.getUserId(),  user.getPassword(), grantedAuthorities);
    }
}
