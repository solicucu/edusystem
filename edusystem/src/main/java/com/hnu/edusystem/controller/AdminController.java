package com.hnu.edusystem.controller;

import com.hnu.edusystem.domain.Admin;
import com.hnu.edusystem.domain.Result;
import com.hnu.edusystem.service.AdminService;
import com.hnu.edusystem.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


@Api(tags = {"管理员接口"})
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 新增
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/add")
    @ApiOperation(value="添加管理员信息",notes="id主键自增长")
    public Result<Admin> add(@Valid Admin admin, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(adminService.save(admin));
    }

    /**
     * 更新
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<Admin> update(@Valid Admin admin, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(adminService.update(admin));
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(String id) {
        adminService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param admins
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<Admin> admins) {
        adminService.deleteInBatch(admins);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<Admin> getById(String id) {
        return ResultUtil.success(adminService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<Admin>> getAll() {
        return ResultUtil.success(adminService.findAll());

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
    public Result<Page<Admin>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(adminService.findAllByPage(page, size, sortFieldName, asc));
    }
}
