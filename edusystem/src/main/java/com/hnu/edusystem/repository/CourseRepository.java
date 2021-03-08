package com.hnu.edusystem.repository;

import com.hnu.edusystem.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    /**
     * 通过名称模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<Course> findByNameLike(String name, Pageable pageable);
    Course findByDayAndSession(String day, Integer session);
    Course findByName(String name);
}
