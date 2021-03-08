package com.hnu.edusystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TC  {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * 教工号
     */
//    @Id
//    @ManyToOne(targetEntity = Teacher.class)
//    @JoinColumn(referencedColumnName = "id")
    private String tid;

    /**
     * 教师姓名
     */
 //   @ManyToOne(targetEntity = Teacher.class)
  //  @JoinColumn(referencedColumnName = "name")
    private String tname;

    /**
     * 课程号
     */
  //  @Id
//    @ManyToOne(targetEntity = Course.class)
//    @JoinColumn(referencedColumnName = "id")
    private String cid;

    /**
     * 课程名
     */
 //   @ManyToOne(targetEntity = Course.class)
  //  @JoinColumn(referencedColumnName = "name")
    private String cname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

    @Override
    public String toString() {
        return "TC{" +
                "tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
