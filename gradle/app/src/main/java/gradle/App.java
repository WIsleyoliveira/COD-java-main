package gradle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class App extends Application {

    private ArrayList<Tanque> tanquesAliado = new ArrayList<>();
    private ArrayList<Tanque> tanqueInimigo = new ArrayList<>();
    private Modos modos = new Modos(tanquesAliado, tanqueInimigo);
    private Random random = new Random();

    private ObservableList<String> alliedTanks = FXCollections.observableArrayList();
    private ObservableList<String> enemyTanks = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("JavaFX app started");
        primaryStage.setTitle("Tank Battle Game");

        Label alliedLabel = new Label("Tanques Aliados: 0");
        ListView<String> alliedList = new ListView<>(alliedTanks);

        Label enemyLabel = new Label("Tanques Inimigos: 0");
        ListView<String> enemyList = new ListView<>(enemyTanks);

        Button createTankBtn = new Button("Criar Tanque");
        createTankBtn.setOnAction(e -> showCreateTankDialog());

        Button startMatchBtn = new Button("Começar Partida");
        startMatchBtn.setOnAction(e -> showStartMatchDialog());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(alliedLabel, alliedList, enemyLabel, enemyList, createTankBtn, startMatchBtn);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateLabels(alliedLabel, enemyLabel);
    }

    private void showCreateTankDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Criar Tanque");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ToggleGroup typeGroup = new ToggleGroup();
        RadioButton alliedBtn = new RadioButton("Aliado");
        alliedBtn.setToggleGroup(typeGroup);
        alliedBtn.setSelected(true);
        RadioButton enemyBtn = new RadioButton("Inimigo");
        enemyBtn.setToggleGroup(typeGroup);

        TextField codinomeField = new TextField();
        codinomeField.setPromptText("Codinome");

        TextField blindagemField = new TextField();
        blindagemField.setPromptText("Blindagem");

        TextField velocidadeField = new TextField();
        velocidadeField.setPromptText("Velocidade");

        TextField poderField = new TextField();
        poderField.setPromptText("Poder de Fogo");

        ChoiceBox<String> typeBox = new ChoiceBox<>(FXCollections.observableArrayList("Leve", "Médio", "Pesado"));
        typeBox.setValue("Leve");

        grid.add(new Label("Tipo:"), 0, 0);
        grid.add(alliedBtn, 1, 0);
        grid.add(enemyBtn, 2, 0);
        grid.add(new Label("Codinome:"), 0, 1);
        grid.add(codinomeField, 1, 1);
        grid.add(new Label("Blindagem:"), 0, 2);
        grid.add(blindagemField, 1, 2);
        grid.add(new Label("Velocidade:"), 0, 3);
        grid.add(velocidadeField, 1, 3);
        grid.add(new Label("Poder de Fogo:"), 0, 4);
        grid.add(poderField, 1, 4);
        grid.add(new Label("Tipo de Tanque:"), 0, 5);
        grid.add(typeBox, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                try {
                    int id = random.nextInt(999) + 100;
                    String codinome = codinomeField.getText();
                    int blindagem = Integer.parseInt(blindagemField.getText());
                    int velocidade = Integer.parseInt(velocidadeField.getText());
                    int poderDeFogo = Integer.parseInt(poderField.getText());
                    String type = typeBox.getValue();
                    boolean isAllied = alliedBtn.isSelected();

                    Tanque tank = createTank(id, codinome, blindagem, velocidade, poderDeFogo, type);
                    if (isAllied) {
                        tanquesAliado.add(tank);
                        alliedTanks.add(codinome + " (" + type + ")");
                    } else {
                        tanqueInimigo.add(tank);
                        enemyTanks.add(codinome + " (" + type + ")");
                    }
                    updateLabels();
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Valores inválidos!");
                    alert.show();
                }
            }
            return button;
        });

        dialog.showAndWait();
    }

    private Tanque createTank(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, String type) {
        Date date = new Date();
        switch (type) {
            case "Leve": return new Leve(id, codinome, blindagem, velocidade, poderDeFogo, date);
            case "Médio": return new Medio(id, codinome, blindagem, velocidade, poderDeFogo, date);
            case "Pesado": return new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, date);
            default: return new Leve(id, codinome, blindagem, velocidade, poderDeFogo, date);
        }
    }

    private void showStartMatchDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Começar Partida");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ChoiceBox<String> modeBox = new ChoiceBox<>(FXCollections.observableArrayList("Treino", "PvP", "TvT"));
        modeBox.setValue("Treino");

        ChoiceBox<String> mapBox = new ChoiceBox<>(FXCollections.observableArrayList("Deserto", "Urbano", "Campo Aberto"));
        mapBox.setValue("Deserto");

        TextField dateField = new TextField();
        dateField.setPromptText("Data (dd/MM/yyyy)");

        TextField timeField = new TextField();
        timeField.setPromptText("Hora (HH:mm)");

        TextField durationField = new TextField();
        durationField.setPromptText("Duração (horas)");

        TextField teamSizeField = new TextField();
        teamSizeField.setPromptText("Tanques por time");

        grid.add(new Label("Modo:"), 0, 0);
        grid.add(modeBox, 1, 0);
        grid.add(new Label("Mapa:"), 0, 1);
        grid.add(mapBox, 1, 1);

        modeBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("Treino".equals(newVal)) {
                grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 1);
            } else {
                grid.add(new Label("Data:"), 0, 2);
                grid.add(dateField, 1, 2);
                grid.add(new Label("Hora:"), 0, 3);
                grid.add(timeField, 1, 3);
                grid.add(new Label("Duração:"), 0, 4);
                grid.add(durationField, 1, 4);
                if ("TvT".equals(newVal)) {
                    grid.add(new Label("Tanques por time:"), 0, 5);
                    grid.add(teamSizeField, 1, 5);
                } else {
                    grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 5);
                }
            }
        });

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                String mode = modeBox.getValue();
                int map = mapBox.getSelectionModel().getSelectedIndex() + 1;
                String result = "";
                if ("Treino".equals(mode)) {
                    result = modos.modoTreino(map);
                } else if ("PvP".equals(mode)) {
                    try {
                        double dur = Double.parseDouble(durationField.getText());
                        result = modos.modoPvP(dateField.getText(), timeField.getText(), dur, map);
                    } catch (NumberFormatException e) {
                        result = "Duração inválida!";
                    }
                } else if ("TvT".equals(mode)) {
                    try {
                        int size = Integer.parseInt(teamSizeField.getText());
                        double dur = Double.parseDouble(durationField.getText());
                        result = modos.modoTvT(size, dateField.getText(), timeField.getText(), dur, map);
                    } catch (NumberFormatException e) {
                        result = "Valores inválidos!";
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result);
                alert.show();
            }
            return button;
        });

        dialog.showAndWait();
    }

    private void updateLabels() {
        // Dummy, since labels are local, but we can refresh lists
    }

    private void updateLabels(Label allied, Label enemy) {
        allied.setText("Tanques Aliados: " + tanquesAliado.size());
        enemy.setText("Tanques Inimigos: " + tanqueInimigo.size());
    }
}
