package com.hnu.edusystem.domain;

import javax.persistence.*;

@Entity
public class Course {
    /**
     * 课程号
     */
    @Id
    private String id;
    /**
     * 课程名
     */
    private String name;

    /**
     * 上课星期数
     *
     */
    private String day;

    /**
     * 上课节次
     */
    private Integer session;

    /**
     * 课程上限人数
     */
    private Integer num;

    /**
     * 已选人数
     */
    private Integer selected;
    /**
     * 上课地点
     */
    private String location;

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", day='" + day + '\'' +
                ", session=" + session +
                ", num=" + num +
                ", selected=" + selected +
                ", location='" + location + '\'' +
                '}';
    }
}
