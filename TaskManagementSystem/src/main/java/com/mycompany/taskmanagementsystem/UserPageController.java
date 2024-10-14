/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.taskmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ISHAQ
 */
public class UserPageController implements Initializable {
    
    //DatePicker
    //@FXML
    //private DatePicker txtDeadline
    
     //Table
    @FXML
    private TableView<Task> tableView;

    //Columns
    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> priorityColumn;
    
    @FXML
    private TableColumn<Task, String> deadlineColumn;
    
    @FXML
    private TableColumn<Task, String> statusColumn;
    
    @FXML
    private TableColumn<Task, String> userColumn;

    //Text input
    @FXML
    private TextField txtTask;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPriority;
    
    @FXML
    private TextField txtDeadline;
    
    @FXML
    private TextField txtStatus;
    
    private String loggedinUser;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("priority"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("deadline"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("user"));
        
        loadTasksFromFile();
        editData();
        setupTableColumns();
    }    
    
    
    @FXML
    ObservableList<Task> tasksFromFile; 
    
    @FXML
    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split(",");
                String taskTitle = taskData[0];
                String description = taskData[1];
                String priority = taskData[2];
                String deadline = taskData[3];
                String status = taskData[4];
                String user = taskData[5];
                
                if (user.equals("user")){
                    Task task = new Task(taskTitle, description, priority, deadline, status, user);
                    tasksFromFile = tableView.getItems();
                    tasksFromFile.add(task);
                    tableView.setItems(tasksFromFile);
                }
            }
        } catch (IOException e) { 
    }
        tableView.setItems(tasksFromFile);
        
    }
    
    
    
    
    
    
    // Add this method to set up the table columns and apply the cell factory
    @FXML
private void setupTableColumns() {
    //TableColumn<Task, String> statusColumn = new TableColumn<>("Status");
    //statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

    
    statusColumn.setCellFactory(new Callback<>() {
        @Override
        public TableCell<Task, String> call(TableColumn<Task, String> param) {
            return new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        switch (item) {
                            case "Completed":
                                setStyle("-fx-background-color: lightgreen");
                                break;
                            case "In Progress":
                                setStyle("-fx-background-color: lightyellow");
                                break;
                            case "Pending":
                                setStyle("-fx-background-color: lightcoral");
                                break;
                            default:
                                setStyle("");
                                break;
                        }
                    }
                }
            };
        }
    });

    tableView.getColumns().add(statusColumn);
}


    @FXML
    public String validateEntry(Task task){
        String errorMessage = "";
        if (task.getTaskName() == null || task.getTaskName().isBlank()){
            errorMessage += "Task name is requied";
        }
        
        if (task.getDescription() == null || task.getDescription().isBlank()){
            errorMessage += (!errorMessage.isBlank() ? "\n" : "")
                    + "Task description is requied";
        }
        
        if (task.getPriority() == null || task.getPriority().isBlank()){
            errorMessage += (!errorMessage.isBlank() ? "\n" : "")
                    + "Task priority is requied";
        }
        
        if (task.getDeadline() == null || task.getDeadline().isBlank()){
            errorMessage += (!errorMessage.isBlank() ? "\n" : "")
                    + "Task deadline is requiedd";
        }
        
        if (task.getStatus() == null || task.getStatus().isBlank()){
            errorMessage += (!errorMessage.isBlank() ? "\n" : "")
                    + "task status is requied";
        }
        
        if (task.getUser() == null || task.getUser().isBlank()){
            errorMessage += (!errorMessage.isBlank() ? "\n" : "")
                    + "Task needs to be assigned to an individual";
        }
        
        return errorMessage;
    }
    

    
    @FXML
    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"))) {
            ObservableList<Task> tasks = tableView.getItems();
            for (Task task : tasks) {
                writer.write(task.getTaskName() + "," + task.getDescription() + "," + task.getPriority() + "," + task.getDeadline() + "," + task.getStatus() + "," + task.getUser());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert anError = new Alert(Alert.AlertType.ERROR);
            //loginError.setTitle("Login Error");
            anError.setContentText("Error occurred while saving tasks to file");
            anError.showAndWait();
        }
    }
    
    
    @FXML
    public void Submit(ActionEvent event)throws IOException{
        String taskTitle = txtTask.getText().trim();
        String description = txtDescription.getText().trim();
        String priority = txtPriority.getText().trim();
        String deadline = txtDeadline.getText().trim();
        String status = txtStatus.getText().trim();
        Task task = new Task(taskTitle, description, priority, deadline, status, "user");
        String error = validateEntry(task);
        if (error.isBlank()){
            ObservableList<Task> tasks = tableView.getItems();
            tasks.add(task);
            tableView.setItems(tasks);
            saveTasksToFile();
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(error);
            VBox vbox = new VBox();
            vbox.getChildren().add(new Label(error));
            errorAlert.showAndWait();
        }
    }
    
    @FXML
    public void RemoveTask(ActionEvent event)throws IOException{
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
        saveTasksToFile();
    }
    
    
    
    @FXML
    private void editData(){
             
        taskColumn.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
        taskColumn.setOnEditCommit(event ->{
            Task task = event.getTableView().getItems().get(event.getTablePosition().getRow());
            task.setTaskName(event.getNewValue());
            saveTasksToFile();
            //System.out.println(task.getTaskName()+ "was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
        });
        
        descriptionColumn.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
        descriptionColumn.setOnEditCommit(event ->{
            Task task = event.getTableView().getItems().get(event.getTablePosition().getRow());
            task.setDescription(event.getNewValue());
            //System.out.println(task.getTaskName()+ "was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
        });
        
        priorityColumn.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
        priorityColumn.setOnEditCommit(event ->{
            Task task = event.getTableView().getItems().get(event.getTablePosition().getRow());
            task.setPriority(event.getNewValue());
            //System.out.println(task.getTaskName()+ "was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
        });
        
        deadlineColumn.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
        deadlineColumn.setOnEditCommit(event ->{
            Task task = event.getTableView().getItems().get(event.getTablePosition().getRow());
            task.setDeadline(event.getNewValue());
            //System.out.println(task.getTaskName()+ "was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
        });
        
        statusColumn.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
        statusColumn.setOnEditCommit(event ->{
            Task task = event.getTableView().getItems().get(event.getTablePosition().getRow());
            task.setStatus(event.getNewValue());
            //System.out.println(task.getTaskName()+ "was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
        });
    }
    
    
}
