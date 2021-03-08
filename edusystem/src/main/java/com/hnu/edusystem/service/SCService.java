package com.hnu.edusystem.service;

import com.hnu.edusystem.domain.Course;
import com.hnu.edusystem.domain.SC;
import com.hnu.edusystem.exception.EduException;
import com.hnu.edusystem.exception.EnumExceptions;
import com.hnu.edusystem.repository.CourseRepository;
import com.hnu.edusystem.repository.SCRepository;
import com.hnu.edusystem.repository.StudentRepository;
import com.hnu.edusystem.repository.TCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class SCService {
    @Autowired
    private SCRepository scRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TCRepository tcRepository;

    /**
     * 新增——学生选课
     *
     * @param sc
     * @return
     */
    public SC save(String sid ,String cid) {

        // 验证是否存在
        if (sid == null || cid == null || scRepository.findBySidAndCid(sid, cid) != null) {
            throw new EduException(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        //判断课程是否人满
        if(courseRepository.findOne(cid).getNum().equals(courseRepository.findOne(cid).getSelected())){
            throw new EduException(EnumExceptions.FAILED_NUM_MAX);
        }

        //判断时间是否冲突
        if(scRepository.findByDayAndSessionAndSid(courseRepository.findOne(cid).getDay(),courseRepository.findOne(cid).getSession(),sid) != null){
            throw new EduException(EnumExceptions.FAILED_TIME_CONFLICT);
        }

        SC sc = new SC();
        sc.setSid(sid);
        sc.setSname(studentRepository.findOne(sid).getName());
        sc.setCid(cid);
        sc.setCname(courseRepository.findOne(cid).getName());
        sc.setDay(courseRepository.findOne(cid).getDay());
        sc.setSession(courseRepository.findOne(cid).getSession());

        //已选人数+1
        Course course = courseRepository.findOne(cid);
        course.setSelected(course.getSelected()+1);
        courseRepository.save(course);

        return scRepository.save(sc);
    }

    /**
     * 更新——教师录入成绩
     *
     * @param sc
     * @return
     */
    public SC update(String sid, String cid, Integer grade) {
        // 验证是否存在
        if (sid == null || cid == null || scRepository.findBySidAndCid(sid, cid) == null) {
            throw new EduException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        SC sc = scRepository.findBySidAndCid(sid, cid);
        sc.setGrade(grade);

        return scRepository.save(sc);
    }

    /**
     * 通过sid和cid删除
     *
     * @param id
     */
    public void delete(String sid,String cid) {

        // 验证是否存在
        if (scRepository.findBySidAndCid(sid, cid) == null) {
            throw new EduException(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        scRepository.delete(scRepository.findBySidAndCid(sid, cid));
    }

    /**
     * 通过学生查询——退课用
     * @param sid
     * @return
     */
    public Page<SC> findBySidByPage(String sid, Integer page , Integer size , String sortFieldName , Integer asc) {

        //判断排序字段名是否存在
        try {
            SC.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在则设置为id
            sortFieldName = "sid";
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

        return scRepository.findBySidOrderByCid(sid,pageable);
    }

    /**
     * 通过课程分页查询——更新成绩用
     *
     * @param cname
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<SC> findByCnameAndTidByPage(String cname ,String tid, Integer page , Integer size , String sortFieldName ,
                                    Integer asc) {

        //判断排序字段名是否存在
        try {
            SC.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在则设置为id
            sortFieldName = "sid";
        }

        //判断页码
        if(page < 0) page = 0;

        //判断该教师是否对应该课程
        if(tcRepository.findByCnameAndTid(cname,tid) == null){
            throw new EduException(EnumExceptions.FAILED_COURSE_NOT_EXIST);
        }

        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC , sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC , sortFieldName);
        }

        Pageable pageable = new PageRequest(page , size ,sort);
        return scRepository.findByCnameLikeOrderBySid("%" + cname + "%",pageable);
    }

    /**
     * 查询课程的学生
     */
    public SC findByCnameAndSnameAndTid(String cname, String sname, String tid){
        //判断该教师是否对应该课程
        if(tcRepository.findByCnameAndTid(cname,tid) == null){
            throw new EduException(EnumExceptions.FAILED_COURSE_NOT_EXIST);
        }

        return scRepository.findByCnameAndSname(cname, sname);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<SC> findAll() {
        return scRepository.findAll();
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
    public Page<SC> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            SC.class.getDeclaredField(sortFieldName);
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
        return scRepository.findAll(pageable);
    }
}
