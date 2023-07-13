module com.example.conversorfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.conversorfx to javafx.fxml;
    exports com.example.conversorfx;
}