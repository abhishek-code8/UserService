package com.team5.userAuthentication.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.userAuthentication.model.User;

@Repository
public interface UserAutheticationRepository extends JpaRepository<User, String> {
    
    User findByUserIdAndUserPassword(String userId, String userPassword);

//	Optional<User> findById(String userId);

//	Optional<User> findUserById(String userId);

}