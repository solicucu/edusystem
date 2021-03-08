package com.hnu.edusystem.domain;

import com.mysql.jdbc.Blob;

import javax.persistence.*;


@Entity
public class GraduationProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(referencedColumnName = "id")
    private String sid;

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(referencedColumnName = "id")
    private String tid;

    /**
     * 课题
     */
    private String project;

    /**
     * 进度
     */
    private String schedule;

    /**
     * 论文
     */
    private String paper;

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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }
}
