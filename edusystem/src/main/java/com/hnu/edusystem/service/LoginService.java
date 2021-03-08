package com.hnu.edusystem.service;

import com.hnu.edusystem.domain.Login;
import com.hnu.edusystem.exception.EduException;
import com.hnu.edusystem.exception.EnumExceptions;
import com.hnu.edusystem.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    /**
     * 登陆认证
     */
    public Login loginConfirm(Login login){

        //验证账号
        if(login.getId() == null || loginRepository.findByIdAndType(login.getId(),login.getType()) == null){
            throw new EduException(EnumExceptions.ID_NOT_EXIST);
        }
        //验证密码
        else if(login.getPassword() == null ||!login.getPassword().equals(loginRepository.findOne(login.getId()).getPassword())){
            throw new EduException(EnumExceptions.PASSWORD_ERROR);
        }
        return login;
    }

    /**
     * 新增
     *
     * @param login
     * @return
     */
    public Login save(Login login) {

        // 验证是否存在
        if (login == null || (login.getId() != null && loginRepository.findOne(login.getId()) != null)) {
            throw new EduException(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return loginRepository.save(login);
    }

    /**
     * 更新
     *
     * @param login
     * @return
     */
    public Login update(Login login) {

        // 验证是否存在
        if (login == null || login.getId() == null || loginRepository.findOne(login.getId()) == null) {
            throw new EduException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return loginRepository.save(login);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(String id) {

        // 验证是否存在
        if (loginRepository.findOne(id) == null) {
            throw new EduException(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        loginRepository.delete(id);
    }

    /**
     * 批量删除
     *
     * @param logins
     */
    public void deleteInBatch(Collection<Login> logins) {
        loginRepository.deleteInBatch(logins);
    }

    /**
     * 通过编码查询
     *
     * @param id
     * @return
     */
    public Login findOne(String id) {
        return loginRepository.findOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Login> findAll() {
        return loginRepository.findAll();
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
    public Page<Login> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            Login.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }
        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return loginRepository.findAll(pageable);
    }

    public Login selectByAccount(String account) {
        return loginRepository.findById(account);
    }
}
