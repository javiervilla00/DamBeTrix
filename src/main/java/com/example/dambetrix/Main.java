package com.example.dambetrix;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Porra porra;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        porra = new Porra();

        Button agregarParticipanteBtn = new Button("Agregar Participante");
        agregarParticipanteBtn.setOnAction(e -> mostrarVentanaAgregarParticipante());

        Button verParticipantesBtn = new Button("Ver Participantes");
        verParticipantesBtn.setOnAction(e -> mostrarVentanaVerParticipantes());

        Button encontrarGanadorBtn = new Button("Encontrar Ganador");
        encontrarGanadorBtn.setOnAction(e -> encontrarGanador());

        VBox root = new VBox(10);
        root.getChildren().addAll(agregarParticipanteBtn, verParticipantesBtn, encontrarGanadorBtn);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("App de Porra");
        primaryStage.show();
    }

    private void mostrarVentanaAgregarParticipante() {
        Stage stage = new Stage();
        stage.setTitle("Agregar Participante");

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre");

        TextField notaField = new TextField();
        notaField.setPromptText("Nota Apostada");

        TextField premioField = new TextField();
        premioField.setPromptText("Premio");

        Button agregarBtn = new Button("Agregar");
        agregarBtn.setOnAction(e -> {
            try {
                String nombre = nombreField.getText();
                double notaApostada = Double.parseDouble(notaField.getText());
                String premio = premioField.getText();

                // Aquí deberías cargar la imagen del participante si es necesario
                // BufferedImage imagenParticipante = ...

                Participante participante = new Participante(nombre, notaApostada, premio, null);
                porra.agregarParticipante(participante);

                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Ingrese una nota válida.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(nombreField, notaField, premioField, agregarBtn);

        Scene scene = new Scene(layout, 250, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarVentanaVerParticipantes() {
        Stage stage = new Stage();
        stage.setTitle("Participantes");

        ListView<String> listView = new ListView<>();
        for (Participante p : porra.obtenerParticipantes()) {
            listView.getItems().add(p.getNombre());
        }

        Button detallesBtn = new Button("Ver Detalles");
        detallesBtn.setOnAction(e -> {
            String selectedName = listView.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                Participante selectedParticipante = obtenerParticipantePorNombre(selectedName);
                if (selectedParticipante != null) {
                    mostrarAlerta("Detalles",
                            "Nombre: " + selectedParticipante.getNombre() + "\n" +
                                    "Nota Apostada: " + selectedParticipante.getNotaApostada() + "\n" +
                                    "Premio: " + selectedParticipante.getPremio());
                }
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(listView, detallesBtn);

        Scene scene = new Scene(layout, 250, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void encontrarGanador() {
        // Simulación de la nota real obtenida en el examen
        double notaReal = 7.8;

        Participante ganador = porra.encontrarGanador(notaReal);
        if (ganador != null) {
            mostrarAlerta("Ganador", "El ganador es: " + ganador.getNombre());
        } else {
            mostrarAlerta("Ganador", "No hay ganador.");
        }
    }

    private Participante obtenerParticipantePorNombre(String nombre) {
        for (Participante p : porra.obtenerParticipantes()) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
