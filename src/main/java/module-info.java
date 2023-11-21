module com.example.dambetrix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.dambetrix to javafx.fxml;
    exports com.example.dambetrix;
}