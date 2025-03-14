module com.example.exercicio01 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires static lombok;
    requires java.persistence;
    requires java.validation;

    opens com.example.exercicio01 to javafx.fxml;
    exports com.example.exercicio01;
}