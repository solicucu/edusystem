package com.hnu.edusystem.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
    /**
     * 管理员ID
     */
    @Id
    private String id;

    /**
     * 所属学院
     */
    private String dept;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
