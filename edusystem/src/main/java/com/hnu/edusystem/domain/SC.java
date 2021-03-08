package com.hnu.edusystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SC{

//   private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * 学号
     */
//    @Id
//    @ManyToOne(targetEntity = Student.class)
//    @JoinColumn(referencedColumnName = "id")
    private String sid;

    /**
     * 学生姓名
     */
  //  @ManyToOne(targetEntity = Student.class)
  //  @JoinColumn(name = "student_name", referencedColumnName = "name")
    private String sname;

    /**
     * 课程号
     */
//   @Id
//    @ManyToOne(targetEntity = Course.class)
//    @JoinColumn(referencedColumnName = "id")
    private String cid;

    /**
     * 课程名
     */
 //   @ManyToOne(targetEntity = Course.class)
  //  @JoinColumn(name = "course_name",referencedColumnName = "name")
    private String cname;

    /**
     * 上课星期数
     */
    private String day;

    /**
     * 上课节次
     */
    private Integer session;

    /**
     * 成绩
     */
    private Integer grade;

    public String getDay() {
        return day;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SC{" +
                "id=" + id +
                ", sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", day='" + day + '\'' +
                ", session='" + session + '\'' +
                ", grade=" + grade +
                '}';
    }
}
