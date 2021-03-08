package com.hnu.edusystem.controller;

import com.hnu.edusystem.domain.Result;
import com.hnu.edusystem.domain.TC;
import com.hnu.edusystem.service.TCService;
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
@RequestMapping(value = "/tc")
public class TCController {
    @Autowired
    private TCService tcService;


    /**
     * 新增——教务处排课
     *
     * @param tc
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<TC> add(String cid, String tid) {

        return ResultUtil.success(tcService.save(cid, tid));
    }

    /**
     * 更新——教师录入成绩
     *
     * @param tc
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<TC> update(@Valid TC tc, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(tcService.update(tc));
    }

    /**
     * 通过tid和cid删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result<Object> delete(String tid,String cid) {
        tcService.delete(tid, cid);
        return ResultUtil.success();
    }

    /**
     * 通过教师查询-分页
     *
     * @param sname
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByTnameByPage")
    public Result<Page<TC>> getByTnameByPage(@RequestParam(value = "tname" , defaultValue = "") String tname,
                                             @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                             @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                                             @RequestParam(value = "sortFieldName" , defaultValue = "cid") String sortFieldName ,
                                             @RequestParam(value = "asc" , defaultValue = "1") Integer asc) {

        return ResultUtil.success(tcService.findByTnameByPage(tname , page ,size ,sortFieldName , asc));
    }

    @RequestMapping(value = "getByTid")
    public Result<List<TC>> getByTid(String tid){
        return ResultUtil.success(tcService.findByTid(tid));
    }
    /**
     * 通过课程查询-分页
     *
     * @param cname
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByCnameByPage")
    public Result<Page<TC>> getByCnameByPage(@RequestParam(value = "cname" , defaultValue = "") String cname ,
                                             @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                             @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                                             @RequestParam(value = "sortFieldName" , defaultValue = "tid") String sortFieldName ,
                                             @RequestParam(value = "asc" , defaultValue = "1") Integer asc) {

        return ResultUtil.success(tcService.findByCnameByPage(cname , page ,size ,sortFieldName , asc));
    }
    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<TC>> getAll() {
        return ResultUtil.success(tcService.findAll());

    }

    /**
     * 查询所有-分页——排课结果
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param atc
     * @return
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<TC>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sortFieldName", defaultValue = "cid") String sortFieldName,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(tcService.findAllByPage(page, size, sortFieldName, asc));
    }
}
