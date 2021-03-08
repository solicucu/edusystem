package com.hnu.edusystem.controller;

import com.hnu.edusystem.domain.Login;
import com.hnu.edusystem.domain.Result;
import com.hnu.edusystem.service.LoginService;
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
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登陆验证
     */
    @RequestMapping(value = "/confirm")
    public Result<Login> loginConfirm(@Valid Login login, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(loginService.loginConfirm(login));
    }

    /**
     * 新增
     *
     * @param login
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<Login> add(@Valid Login login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(loginService.save(login));
    }

    /**
     * 更新
     *
     * @param login
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<Login> update(@Valid Login login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(loginService.update(login));
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(String id) {
        loginService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param logins
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<Login> logins) {
        loginService.deleteInBatch(logins);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<Login> getById(String id) {
        return ResultUtil.success(loginService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<Login>> getAll() {
        return ResultUtil.success(loginService.findAll());

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
    public Result<Page<Login>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(loginService.findAllByPage(page, size, sortFieldName, asc));
    }
}
