package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldMenorMultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        // Definir o tamanho da tela (Stage)
        stage.setHeight(550);

        // Ao clicar no fechar da janela aparece a confirmação
        stage.setOnCloseRequest(e ->{
            sairTabuada();
            e.consume();
        });

        // Bloquear o redimensionamento da janela
        stage.setResizable(false);

        // Componente principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);

        // Cabeçalho da tela
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #19686a; ");

        // Adicionando um label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 22; -fx-font-weight: bold");

        Label labelSubTitulo = new Label("Contrua tabuadas sem limites!");
        labelSubTitulo.setStyle("-fx-text-fill: #ffffff");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubTitulo);


        // Criar o multiplicando
        GridPane gridFormulario = new GridPane();
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);
        Label labelMultiplicando = new Label("Multiplicando");
        textFieldMultiplicando = new TextField();

        Label labelMenorMultiplicador = new Label("Menor Multiplicador");
        textFieldMenorMultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior Multiplicador");
        textFieldMaiorMultiplicador = new TextField();

        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);
        gridFormulario.add(labelMenorMultiplicador, 0, 1);
        gridFormulario.add(textFieldMenorMultiplicador, 1, 1);
        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        // Criar o componente de botões
        HBox boxBotoes = new HBox();
        boxBotoes.setAlignment(Pos.CENTER_RIGHT);
        boxBotoes.setSpacing(10);
        boxBotoes.setPadding(new Insets(0,20,20,20));
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setPrefWidth(90);
        btnCalcular.setPrefHeight(40);
        btnCalcular.setOnAction(e -> {
            calcularTabuada();
        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefWidth(70);
        btnLimpar.setPrefHeight(40);
        btnLimpar.setOnAction(e -> {
            limparTabuada();
        });
        Button btnSair = new Button("Sair");
        btnSair.setPrefWidth(70);
        btnSair.setPrefHeight(40);
        btnSair.setOnAction(e -> {
            sairTabuada();
        });

        // Adicionar os botões na boxBotoes
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        // Adicionar um componente ListView
        VBox boxResultado = new VBox();
        boxResultado.setPadding(new Insets(0,15,15,15));
        boxResultado.setAlignment(Pos.CENTER);
        Label labelResultado = new Label("Resultado");
        labelResultado.setStyle(
                "-fx-text-fill: #19686a;-fx-font-size: 18;-fx-font-weight: bold;");

        // Adicionar o ListView
        listaTabuada = new ListView();

        // Adicionar o label ao boxResultado
        boxResultado.getChildren().add(labelResultado);
        boxResultado.getChildren().add(listaTabuada);

        // Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(boxBotoes);
        root.getChildren().add(boxResultado);

        stage.setScene(scene);
        stage.setTitle("Tabuada");
        stage.show();

    }
    public void calcularTabuada() {

        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
        int menorMultiplicador = Integer.parseInt(textFieldMenorMultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        if (menorMultiplicador > maiorMultiplicador){
            int auxiliar = maiorMultiplicador;
            maiorMultiplicador = menorMultiplicador;
            menorMultiplicador = auxiliar;
        }

        int tamanho =maiorMultiplicador - menorMultiplicador + 1;
        String[] tabuada = new String[tamanho];


        int contador = 0;
        while (contador < tamanho) {
            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + " x " + menorMultiplicador + " = " + produto;
            contador++;
            menorMultiplicador++;
        }


        listaTabuada.getItems().clear();
        listaTabuada.getItems().addAll(tabuada);

    }
    public void limparTabuada() {
        textFieldMultiplicando.setText("");
        textFieldMenorMultiplicador.setText("");
        textFieldMaiorMultiplicador.setText("");
        listaTabuada.getItems().clear();
        textFieldMultiplicando.requestFocus();

    }
    public void sairTabuada() {
        Alert alerta = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Confirma a saída?",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> botaoPressionado = alerta.showAndWait();
        if (botaoPressionado.get() == ButtonType.YES) {
            Alert alerta2 = new Alert(Alert.AlertType.INFORMATION,
                    "Até logo",
                    new ButtonType[0]
            );
            alerta2.showAndWait();
            System.exit(0);
        }
    }
}
