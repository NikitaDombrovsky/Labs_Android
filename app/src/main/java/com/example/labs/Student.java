package com.example.labs;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String group;
    private int score;
    public Student(String name, String group, int score) {
        this.name = name;
        this.group = group;
        this.score = score;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGroup() {
        return group;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setGroup(String group) {
        this.group = group;
    }
}
