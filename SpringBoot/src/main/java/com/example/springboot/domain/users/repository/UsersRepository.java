package com.example.springboot.domain.users.repository;

import com.example.springboot.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}