package com.example.events;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import com.opencsv.CSVWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
public class registros extends Application{
    private static final int limite = 100;
    private TextField name;
    private TextField lastname;
    private TextField usern;
    private TextField cel;
    private ComboBox sex;
    private TextField correo;
    private TextArea descrip;


    @Override
    public void start(Stage stage) {
        stage.setTitle("Registro de usuarios");
        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setMinWidth(673);
        stage.setMaxWidth(673);
        stage.setMinHeight(680);
        stage.setMaxHeight(680);


//---------------------------Titulo---------------------------------------
        Label title = new Label("Registro de usuarios");
        title.setStyle("-fx-background-color: blue");
        title.setLayoutX(220);
        title.setLayoutY(10);
        title.setFont(new Font("Arial", 25));
//------------------------------------------------------------------------
        Label nombre = new Label("Nombre: ");
        nombre.setLayoutX(15);
        nombre.setLayoutY(100);
        nombre.setFont(new Font("Arial", 20));
        name = new TextField();
        name.setLayoutX(150);
        name.setLayoutY(100);
        name.setPrefWidth(400);
        name.setPromptText("Ingrese su nombre");

        Label apellido = new Label("Apellido: ");
        apellido.setLayoutX(15);
        apellido.setLayoutY(150);
        apellido.setFont(new Font("Arial", 20));
        lastname = new TextField();
        lastname.setLayoutX(150);
        lastname.setLayoutY(150);
        lastname.setPrefWidth(400);
        lastname.setPromptText("Ingrese apellido");

        Label username = new Label("Nombre de usuario: ");
        username.setLayoutX(15);
        username.setLayoutY(200);
        username.setFont(new Font("Arial", 20));
        usern = new TextField();
        usern.setLayoutX(220);
        usern.setLayoutY(200);
        usern.setPrefWidth(400);
        usern.setPromptText("Ingrese el nombre de usuario");

        Label tel = new Label("Telefono: ");
        tel.setLayoutX(15);
        tel.setLayoutY(250);
        tel.setFont(new Font("Arial", 20));
        cel = new TextField();
        cel.setLayoutX(150);
        cel.setLayoutY(250);
        cel.setPrefWidth(400);
        cel.setPromptText("Ingrese el número de tel");

        Label sexo = new Label("Sexo: ");
        sexo.setLayoutX(15);
        sexo.setLayoutY(300);
        sexo.setFont(new Font("Arial", 20));
        sex = new ComboBox<>(FXCollections.observableArrayList("Masculino", "Femenimo"));
        sex.setValue("Seleccione");
        sex.setLayoutX(150);
        sex.setLayoutY(300);
        sex.setPrefWidth(200);

        Label email = new Label("Correo: ");
        email.setLayoutX(15);
        email.setLayoutY(350);
        email.setFont(new Font("Arial", 20));
        correo = new TextField();
        correo.setLayoutX(150);
        correo.setLayoutY(350);
        correo.setPrefWidth(400);
        correo.setPromptText("Ingrese el correo");

        Label desc = new Label("Descripción: ");
        desc.setLayoutX(15);
        desc.setLayoutY(400);
        desc.setFont(new Font("Arial", 20));
        descrip = new TextArea();
        descrip.setLayoutX(150);
        descrip.setLayoutY(400);
        descrip.setPrefHeight(120);
        descrip.setWrapText(true);

        Label counterL = new Label("Palabras restantes: " + limite);
        counterL.setLayoutX(15);
        counterL.setLayoutY(440);

        descrip.textProperty().addListener((observableValue, oldValue, newValue) -> {
            int remainingwords = limite - counter(newValue);
            counterL.setText("Palabras restantes: " + remainingwords);

            if (remainingwords < 0) {
                descrip.setText(oldValue);
            }
        });

        //---------------------------generar documento---------------------------
        Button pdf = new Button();
        pdf.setText("Crear .pdf");
        pdf.setLayoutX(200);
        pdf.setLayoutY(570);
        pdf.setOnAction(actionEvent -> pdf_generator());

        Button csv = new Button();
        csv.setText("Crear .csv");
        csv.setLayoutX(400);
        csv.setLayoutY(570);
        csv.setOnAction(actionEvent -> csv_generator());

        Button add_csv = new Button();
        add_csv.setText("Agregar a CSV");
        add_csv.setLayoutX(500);
        add_csv.setLayoutY(570);

        root.getChildren().addAll(title, nombre, name, apellido, lastname, tel,
                        cel, sexo, sex, username, usern, email, correo, desc, descrip,
                        counterL, pdf, csv, add_csv);
        stage.setScene(scene);
        stage.show();
    }

    private void csv_generator(){
        try (CSVWriter writer = new CSVWriter(new FileWriter(usern.getText() + ".csv")))
        {
            if (!(name.getText().isEmpty() || (lastname.getText().isEmpty()) ||
                    (usern.getText().isEmpty()) || (cel.getText().isEmpty()) ||
                    (correo.getText().isEmpty()) || (descrip.getText().isEmpty())) || sex.getValue().equals("Seleccione"))
                {
                    String[] data1 = {"Nombre", "Apellido", "Username", "Telefono", "Sexo", "Correo", "Descrip"};
                    String[] data2 = {name.getText(), lastname.getText(), usern.getText(), cel.getText(),
                                      sex.getValue().toString(), correo.getText(), descrip.getText()};
                    writer.writeNext(data1);
                    writer.writeNext(data2);
                    showAlert2("CSV","Se ha creado correctamente");
                }else
                {
                    showAlert("Error", "Algunos de los campos no se ha llenado!");
                }
        }catch (IOException e)
        {
            showAlert("Error", "Algo ha salido mal.");
        }
    }
    private void pdf_generator(){
            String outputFilePath = "example.pdf";
            try {
                File file = new File(outputFilePath);
                file.createNewFile();
                //PdfWriter writer = new PdfWriter(file);
                //PdfDocument pdf = new PdfDocument(writer);
                //pdf.addNewPage();
                //pdf.close();
        }catch (Exception e){
            showAlert("Error", "Algo ha salido mal.");
        }
    }

    public static void main(String[] args){
        launch();
    }

    private int counter(String text){
        String[] palabras = text.split("\\s+");
        return palabras.length;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert2(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
