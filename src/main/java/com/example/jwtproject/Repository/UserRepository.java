package com.example.jwtproject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jwtproject.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    @Query("SELECT u FROM User u WHERE u.userName=:userName")
    Optional<User> findByUserName(@Param("userName")String userName);
}
