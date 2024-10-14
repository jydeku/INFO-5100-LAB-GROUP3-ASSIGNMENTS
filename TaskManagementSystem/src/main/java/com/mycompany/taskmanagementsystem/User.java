/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanagementsystem;

/**
 *
 * @author ISHAQ
 */
public class User {
    private String username;
    private String password;
    private String role; // E.g., Admin, Regular User

    public User(){}
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String checkCredentials() {
        String access = "";
        if (username.equals("admin") && password.equals("admin") && role.equals("admin")){
            access = "admin";
        }
        else if (username.equals("user") && password.equals("user") && role.equals("user")){
            access = "user";
        }
        else if (username.equals("user2") && password.equals("user2") && role.equals("user2")){
            access = "user2";
        }
        else {
            access = "error";
        }
        return access;
    }
    
}
