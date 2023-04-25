package com.example.demo.api.repository.user;

import java.util.Optional;

import com.example.demo.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserId(String userId);
}
