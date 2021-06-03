package br.gov.sp.fatec.uplanitspringbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.uplanitspringbootapp.controller.View;

@Entity
@Table(name="up_tasks")
public class Tasks {
    
    //TODO: Json View
    @JsonView(View.Tasks.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @JsonView(View.TasksComplete.class)
    @Column(name = "task_name")
    private String taskName;

    @JsonView(View.TasksComplete.class)
    @Column(name = "task_check")
    private String taskCheck;

    @JsonView(View.TasksComplete.class)
    @Column(name = "task_date_created")
    private String taskDateCreated;

    @JsonView(View.TasksComplete.class)
    @Column(name = "task_date_due")
    private String taskDateDue;

    //gets and setters

    public Long getTaskId(){
        return this.taskId;
    }

    public void setTaskId(Long taskId){
        this.taskId = taskId;
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public String getTaskCheck(){
        return this.taskCheck;
    }

    public void setTaskCheck(String taskCheck){
        this.taskCheck = taskCheck;
    }

    public String getTaskDateCreated(){
        return this.taskDateCreated;
    }

    public void setTaskDateCreated(String taskDateCreated){
        this.taskDateCreated = taskDateCreated;
    }

    public String getTaskDateDue(){
        return this.taskDateDue;
    }

    public void setTaskDateDue(String taskDateDue){
        this.taskDateDue = taskDateDue;
    }
}
