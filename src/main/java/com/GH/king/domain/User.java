package com.GH.king.domain;

import javax.persistence.*;

/**
 * Created by gy on 2016/7/19.
 */
@Entity
@Table(name = "Users")
public class User {

    private int Id;
    private String name;
    private String password;
    private String score;
    private String pic;
    private String department;
    private String questions;
    private String answers;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "Score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    @Column(name = "Pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "Questions")
    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    @Column(name = "Answers")
    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
