package com.example.javatest.demotest;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "person")
public class Person {
//    @Column(name = "gender")
    boolean gender;
//    @Column(name = "age")
    int age;
//    @Column(name = "no")
    String no;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(name = "name")
    String name;

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

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

    public Person(String name, String no, int age, boolean gender) {
        this.name = name;
        this.no = no;
        this.age = age;
        this.gender = gender;
    }

    public Person(){

    }

}
