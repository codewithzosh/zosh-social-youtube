package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
