package com.example.tableviewredigerbart;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class EditableTableViewExample extends Application {

    // Definer en ObservableList, som indeholder dataene til tabellen
    private final ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("Alice", 20),
            new Person("Bob", 25),
            new Person("Charlie", 30)
    );

    @Override
    public void start(Stage primaryStage) {
        // Opret TableView
        TableView<Person> tableView = new TableView<>();
        tableView.setEditable(true);

        // Kolonne for navn
        TableColumn<Person, String> nameColumn = new TableColumn<>("Navn");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setName(event.getNewValue());
        });

        // Kolonne for alder
        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Alder");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageColumn.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setAge(event.getNewValue());
        });

        // Tilføj kolonnerne til TableView
        tableView.getColumns().addAll(nameColumn, ageColumn);

        // Tilføj data til TableView
        tableView.setItems(data);

        // Layout
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Redigerbar TableView Eksempel");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Modelklasse Person
    public static class Person {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty age;

        public Person(String name, int age) {
            this.name = new SimpleStringProperty(name);
            this.age = new SimpleIntegerProperty(age);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public int getAge() {
            return age.get();
        }

        public void setAge(int age) {
            this.age.set(age);
        }

        public SimpleIntegerProperty ageProperty() {
            return age;
        }
    }
}
