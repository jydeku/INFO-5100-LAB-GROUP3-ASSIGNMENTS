/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.taskmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAQ
 */
public class LoginPageController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    User user;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    
    private String loggedinUser;
    

    
    @FXML
    public void login(ActionEvent event)throws IOException{
        String username = txtUsername.getText().trim(); 
        String password = txtPassword.getText().trim();
        String role = txtUsername.getText().trim();
        user = new User (username, password, role);
        loggedinUser = txtUsername.getText().trim();
        
        if (username.isBlank() || password.isBlank()){
            Alert loginBlankError = new Alert(AlertType.ERROR);
            //loginBlankError.setTitle("Login Error");
            loginBlankError.setContentText("Username and Password is required");
            loginBlankError.showAndWait();
        }
        else if (user.checkCredentials().equals("admin")){
            //administrator login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            root = loader.load();
            //AdminPageController adminPageController = loader.getController();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (user.checkCredentials().equals("user")){
            //user login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
            root = loader.load();
           // UserPageController userPageController = loader.getController();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (user.checkCredentials().equals("error")){
            Alert loginError = new Alert(AlertType.NONE);
            loginError.setContentText("Please enter the correct login details");
            loginError.showAndWait();
        }
        else{
            Alert anError = new Alert(AlertType.ERROR);
            //loginError.setTitle("Login Error");
            anError.setContentText("Error here");
            anError.showAndWait();
        }
        
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}

