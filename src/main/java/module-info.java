module org.intissar.jasper4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;


    opens org.intissar.jasper4 to javafx.fxml;
    exports org.intissar.jasper4;
}