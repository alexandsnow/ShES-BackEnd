package com.GH.king.domain;

import javax.persistence.*;

/**
 * Created by gy on 2016/7/19.
 */
@Entity
@Table(name = "QuestionBank")
public class Question {
    private int Id;
    private String Group;
    private String Title;
    private String ItemA;
    private String ItemB;
    private String ItemC;
    private String ItemD;
    private String Answer;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    @Column(name = "GroupType")
    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    @Column(name="Title")
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
    @Column(name="ItemA")
    public String getItemA() {
        return ItemA;
    }

    public void setItemA(String itemA) {
        ItemA = itemA;
    }
    @Column(name="ItemB")
    public String getItemB() {
        return ItemB;
    }

    public void setItemB(String itemB) {
        ItemB = itemB;
    }
    @Column(name="ItemC")
    public String getItemC() {
        return ItemC;
    }

    public void setItemC(String itemC) {
        ItemC = itemC;
    }
    @Column(name="ItemD")
    public String getItemD() {
        return ItemD;
    }

    public void setItemD(String itemD) {
        ItemD = itemD;
    }
    @Column(name="Answer")
    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void show() {
        System.out.println(this.Title+"\n"+
        this.Group+"\n"+
        this.ItemA+"\n"+
        this.ItemB+"\n"+
        this.ItemC+"\n"+
        this.ItemD+"\n"+
        this.Answer+"\n"
        );
    }
}

