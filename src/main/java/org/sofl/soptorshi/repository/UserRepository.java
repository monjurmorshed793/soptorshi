package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Cacheable(value = "user", key = "#userId")
    User getByUserId(String userId);
}
