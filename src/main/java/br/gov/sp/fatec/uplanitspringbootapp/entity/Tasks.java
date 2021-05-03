package br.gov.sp.fatec.uplanitspringbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="up_tasks")
public class Tasks {
        
    //TODO jsonview
    @Id
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_check")
    private String taskCheck;

    @Column(name = "task_date_created")
    private String taskDateCreated;

    @Column(name = "task_date_due")
    private String taskDateDue;

    //gets and setters

    public String getTaskId(){
        return this.taskId;
    }

    public void setTaskId(String taskId){
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
