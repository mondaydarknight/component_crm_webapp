package com.smartbee.crm.core.repositories;

import com.smartbee.crm.core.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLoginId(String loginId);

    @EntityGraph(attributePaths = "authority")
    Optional<User> findOneWithAuthorityByLoginId(String loginId);
}
