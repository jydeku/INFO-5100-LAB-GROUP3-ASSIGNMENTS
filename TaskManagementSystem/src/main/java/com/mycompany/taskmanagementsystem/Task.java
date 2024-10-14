/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanagementsystem;



/**
 *
 * @author ISHAQ
 */
public class Task {
    private String taskName;
    private String description;
    private String priority; // E.g., High, Medium, Low
    private String deadline;
    private String status; // E.g., Pending, In Progress, Completed
    private String user;

    public Task(String taskName, String description, String priority, String deadline, String status, String user) {
        this.taskName = taskName;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
