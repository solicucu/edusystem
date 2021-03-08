package com.hnu.edusystem.domain;

import javax.persistence.*;


@Entity
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 教室/打印机
     */
    private String name;

    /**
     * 类型 0教室，1打印机
     */
    private Integer type;

    /**
     * 状态 0空闲，1已占用
     */
    private Integer status;

    /**
     * 预约信息
     */
    @ManyToOne(targetEntity = Reservation.class)
    @JoinColumn(referencedColumnName = "id")
    private String rseservation;

    /**
     * 备注
     */
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRseservation() {
        return rseservation;
    }

    public void setRseservation(String rseservation) {
        this.rseservation = rseservation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
