package com.GH.king.domain;

import javax.persistence.*;

/**
 * Created by gy on 2016/7/19.
 */
@Entity
@Table(name = "QuestionBank")
public class Question {
    private int id;
    private String group;
    private String title;
    private String itemA;
    private String itemB;
    private String itemC;
    private String itemD;
    private String answer;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int Id) {
        id = Id;
    }
    @Column(name = "GroupType")
    public String getGroup() {
        return group;
    }

    public void setGroup(String Group) {
        group = Group;
    }

    @Column(name="Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        title = Title;
    }
    @Column(name="ItemA")
    public String getItemA() {
        return itemA;
    }

    public void setItemA(String ItemA) {
        itemA = ItemA;
    }
    @Column(name="ItemB")
    public String getItemB() {
        return itemB;
    }

    public void setItemB(String ItemB) {
        itemB = ItemB;
    }
    @Column(name="ItemC")
    public String getItemC() {
        return itemC;
    }

    public void setItemC(String ItemC) {
        itemC = ItemC;
    }
    @Column(name="ItemD")
    public String getItemD() {
        return itemD;
    }

    public void setItemD(String ItemD) {
        itemD = ItemD;
    }
    @Column(name="Answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String Answer) {
        answer = Answer;
    }
}

