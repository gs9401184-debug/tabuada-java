package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Definir o tamanho da tela (Stage)
        stage.setWidth(500);
        stage.setHeight(500);

        // Componente principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);

        // Cabeçalho da tela
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #19686a; ");

        // Adicionando um label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 22; -fx-font-weight: bold");
        header.getChildren().add(labelTitulo);


        // Colocar o header no root
        root.getChildren().addAll(header);




        stage.setScene(scene);
        stage.setTitle("Tabuada");
        stage.show();

    }
}
