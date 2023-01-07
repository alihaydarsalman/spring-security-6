package com.hzyazilimci.security.repository;

import com.hzyazilimci.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author hzyazilimci
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String username);
}
