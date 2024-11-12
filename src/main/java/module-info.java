module com.example.tableviewredigerbart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tableviewredigerbart to javafx.fxml;
    exports com.example.tableviewredigerbart;
}