package edu.nu.dat605.entity;

import javax.persistence.*;

/**
 * Created by ZS13478 on 9/29/2016.
 */
@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "task")
    private String task;

    @Column(name = "complete")
    private Boolean complete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
