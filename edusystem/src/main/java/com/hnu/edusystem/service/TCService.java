package com.hnu.edusystem.service;

import com.hnu.edusystem.domain.TC;
import com.hnu.edusystem.exception.EduException;
import com.hnu.edusystem.exception.EnumExceptions;
import com.hnu.edusystem.repository.CourseRepository;
import com.hnu.edusystem.repository.TCRepository;
import com.hnu.edusystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class TCService {
    @Autowired
    private TCRepository tcRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    /**
     * 新增——教务处排课
     *
     * @param tc
     * @return
     */
    public TC save(String cid, String tid) {

        // 验证是否存在
        if (tcRepository.findByTidAndCid(tid, cid) != null) {
            throw new EduException(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        TC tc = new TC();
        if(courseRepository.findOne(cid) == null){
            throw new EduException(EnumExceptions.FAILED_COURSE_NOT_EXIST);
        }
        tc.setCname(courseRepository.getOne(cid).getName());
        tc.setCid(cid);

        if(teacherRepository.findOne(tid) == null){
            throw new EduException(EnumExceptions.FAILED_TEACHER_NOT_EXIST);
        }
        tc.setTname(teacherRepository.getOne(tid).getName());
        tc.setTid(tid);


        return tcRepository.save(tc);
    }

    /**
     * 更新——教务处排课
     *
     * @param tc
     * @return
     */
    public TC update(TC tc) {

        // 验证是否存在
        if (tc == null || tcRepository.findByTidAndCid(tc.getTid(),tc.getCid()) == null) {
            throw new EduException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return tcRepository.save(tc);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(String tid,String cid) {

        // 验证是否存在
        if (tcRepository.findByTidAndCid(tid, cid) == null) {
            throw new EduException(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        tcRepository.delete(tcRepository.findByTidAndCid(tid, cid));
    }

    /**
     * 通过教师分页查询
     *
     * @param tname
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<TC> findByTnameByPage(String tname , Integer page , Integer size , String sortFieldName ,
                                      Integer asc) {

        //判断排序字段名是否存在
        try {
            TC.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在则设置为id
            sortFieldName = "cid";
        }
        //判断页码
        if(page < 0) page = 0;

        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC , sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC , sortFieldName);
        }

        Pageable pageable = new PageRequest(page , size ,sort);
        return tcRepository.findByTnameOrderByCid(tname,pageable);
    }

    public List<TC> findByTid(String tid){
        return tcRepository.findByTid(tid);
    }

    /**
     * 通过课程分页查询
     *
     * @param cname
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<TC> findByCnameByPage(String cname , Integer page , Integer size , String sortFieldName ,
                                      Integer asc) {

        //判断排序字段名是否存在
        try {
            TC.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在则设置为id
            sortFieldName = "tid";
        }
        //判断页码
        if(page < 0) page = 0;

        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC , sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC , sortFieldName);
        }

        Pageable pageable = new PageRequest(page , size ,sort);
        return tcRepository.findByCnameOrderByTid(cname,pageable);
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<TC> findAll() {
        return tcRepository.findAll();
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
    public Page<TC> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            TC.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为cid
            sortFieldName = "cid";
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
        return tcRepository.findAll(pageable);
    }
}
