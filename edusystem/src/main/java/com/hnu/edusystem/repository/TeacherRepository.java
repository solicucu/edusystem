package com.hnu.edusystem.repository;

import com.hnu.edusystem.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher, String> {
    /**
     * 通过名称模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<Teacher> findByNameLike(String name, Pageable pageable);
    Teacher findByName(String name);
}
