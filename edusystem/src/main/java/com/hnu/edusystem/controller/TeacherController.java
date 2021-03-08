package com.hnu.edusystem.controller;

import com.hnu.edusystem.domain.Teacher;
import com.hnu.edusystem.domain.Result;
import com.hnu.edusystem.service.TeacherService;
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
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 新增
     *
     * @param teacher
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<Teacher> add(@Valid Teacher teacher , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(teacherService.save(teacher));
    }

    /**
     * 更新
     *
     * @param teacher
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<Teacher> update(@Valid Teacher teacher , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(teacherService.update(teacher));
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteId")
    public Result<Teacher> deleteId(String id) {
        teacherService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param teachers
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<Teacher> teachers) {
        teacherService.deleteInBatch(teachers);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<Teacher> getById(String id) {
        return ResultUtil.success(teacherService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/findAll")
    public Result<List<Teacher>> findAll() {
        return ResultUtil.success(teacherService.findAll());
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
    public Result<Page<Teacher>> getAllByPage(@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                              @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                                              @RequestParam(value = "sortFieldName" , defaultValue = "id") String sortFieldName ,
                                              @RequestParam(value = "asc" , defaultValue = "1") Integer asc ) {

        return ResultUtil.success(teacherService.findAllByPage(page , size , sortFieldName , asc));
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
    public Result<Page<Teacher>> getByNameByPage(@RequestParam(value = "name" , defaultValue = "") String name ,
                                                 @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                                 @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                                                 @RequestParam(value = "sortFieldName" , defaultValue = "id") String sortFieldName ,
                                                 @RequestParam(value = "asc" , defaultValue = "1") Integer asc) {

        return ResultUtil.success(teacherService.findByNameLikeByPage(name , page ,size ,sortFieldName , asc));
    }
}
