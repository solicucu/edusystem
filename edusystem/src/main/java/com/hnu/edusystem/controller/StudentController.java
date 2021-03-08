package com.hnu.edusystem.controller;

import com.hnu.edusystem.domain.Student;
import com.hnu.edusystem.domain.Result;
import com.hnu.edusystem.service.StudentService;
import com.hnu.edusystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;



@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 新增
     *
     * @param student
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<Student> add(@Valid Student student ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(studentService.save(student));
    }

    /**
     * 更新
     *
     * @param student
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<Student> update(@Valid Student student , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(studentService.update(student));
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(String id) {
        studentService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param students
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<Student> students) {
        studentService.deleteInBatch(students);
        return ResultUtil.success();
    }

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<Student> getById(String id) {
        return ResultUtil.success(studentService.findOne(id));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<Student>> getAll() {
        return ResultUtil.success(studentService.findAll());
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
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<Student>> getAllPage(@RequestParam(value = "page" , defaultValue = "0" ) Integer page,
                                            @RequestParam(value = "size" , defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortFieldName" , defaultValue = "id") String sortFieldName,
                                            @RequestParam(value = "asc" , defaultValue = "1") Integer asc) {
        return ResultUtil.success(studentService.findAllByPage(page , size , sortFieldName ,asc));
    }

    /**
     * 通过名称模糊查询-分页
     *
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<Student>> getByNameLikeByPage(@RequestParam(value = "name" , defaultValue = "") String name,
                                                     @RequestParam(value = "page" , defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size" , defaultValue = "10") Integer size,
                                                     @RequestParam(value = "sortFieldName" , defaultValue = "id") String sortFieldName,
                                                     @RequestParam(value = "asc" , defaultValue = "1") Integer asc) {
        return ResultUtil.success(studentService.findByNameLikeByPage(name , page, size , sortFieldName , asc));
    }
}

