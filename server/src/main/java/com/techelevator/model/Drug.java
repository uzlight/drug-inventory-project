package com.techelevator.model;

import java.math.BigDecimal;

public class Drug {
    private int id;
    private int classId;
    private String name;
    private BigDecimal cost;
    private boolean isAvailable;

    public Drug() { }
    public Drug(int id, int classId, String name, BigDecimal cost, boolean isAvailable) {
        this.id = id;
        this.classId = classId;
        this.name = name;
        this.cost = cost;
        this.isAvailable = isAvailable;
    }

    public Drug(String name, BigDecimal cost, boolean isAvailable) {
        this(0, 0, name, cost, isAvailable);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", classId=" + classId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", isAvailable=" + isAvailable +
                '}';
    }

}
