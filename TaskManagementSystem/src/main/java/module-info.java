module com.mycompany.taskmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.taskmanagementsystem to javafx.fxml;
    exports com.mycompany.taskmanagementsystem;
}
