package com.techelevator.model;

public class DrugClass {

    private int id;
    private String name;

    public DrugClass() { }
    public DrugClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DrugClass(String name) {
        this(0, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DrugClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
