package com.hnu.edusystem.service;

import com.hnu.edusystem.domain.Course;
import com.hnu.edusystem.exception.EduException;
import com.hnu.edusystem.exception.EnumExceptions;
import com.hnu.edusystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    /**
     * 新增
     *
     * @param course
     * @return
     */
    public Course save(Course course) {

        // 验证是否存在
        if (course == null || (course.getId() != null && courseRepository.findOne(course.getId()) != null)) {
            throw new EduException(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        //验证课程名重复
        else if(courseRepository.findByName(course.getName()) != null){
            throw new EduException(EnumExceptions.ADD_FAILED_CNAME_EXIST);
        }
        course.setSelected(0);
        return courseRepository.save(course);
    }

    public Course findByDayAndSession(String day, Integer session){
        return courseRepository.findByDayAndSession(day,session);
    }
    /**
     * 更新
     *
     * @param course
     * @return
     */
    public Course update(Course course) {

        // 验证是否存在
        if (course == null || course.getId() == null || courseRepository.findOne(course.getId()) == null) {
            throw new EduException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        course.setSelected(0);

        return courseRepository.save(course);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(String id) {

        // 验证是否存在
        if (courseRepository.findOne(id) == null) {
            throw new EduException(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        courseRepository.delete(id);
    }

    /**
     * 批量删除
     *
     * @param courses
     */
    public void deleteInBatch(Collection<Course> courses) {
        courseRepository.deleteInBatch(courses);
    }

    /**
     * 通过编码查询
     *
     * @param id
     * @return
     */
    public Course findOne(String id) {
        return courseRepository.findOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<Course> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            Course.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }
        //判断页码
        if(page < 0) page = 0;

        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return courseRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     *
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<Course> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
                                                Integer asc) {

        // 判断排序字段名是否存在
        try {
            Course.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }
        //判断页码
        if(page < 0) page = 0;

        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return courseRepository.findByNameLike("%" + name + "%", pageable);
    }
}
