package com.hnu.edusystem.repository;

import com.hnu.edusystem.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByIdAndType(String id, Integer type);
    Login findById(String id);
}
