module com.example.youtube {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires com.google.gson;

    opens com.example.youtube to javafx.fxml;
    exports com.example.youtube;
}