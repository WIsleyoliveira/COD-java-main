package gradle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamModeController implements Initializable {

    @FXML private Button size1v1Button;
    @FXML private Button size2v2Button;
    @FXML private Button size3v3Button;
    @FXML private Button size4v4Button;
    @FXML private Button size5v5Button;

    @FXML private VBox alliedTeamContainer;
    @FXML private VBox enemyTeamContainer;

    @FXML private Button autoModeButton;
    @FXML private Button manualModeButton;
    @FXML private Button startBattleButton;

    private int teamSize = 1;
    private boolean isManualMode = false;
    private List<Tanque> alliedTeam = new ArrayList<>();
    private List<Tanque> enemyTeam = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTeamSizeButtons();
        setupControlModeButtons();
        updateTeamDisplay();
        updateStartButton();
    }

    public void setMainApp() {
        // Configurações específicas se necessário
    }

    private void setupTeamSizeButtons() {
        size1v1Button.setOnAction(e -> setTeamSize(1));
        size2v2Button.setOnAction(e -> setTeamSize(2));
        size3v3Button.setOnAction(e -> setTeamSize(3));
        size4v4Button.setOnAction(e -> setTeamSize(4));
        size5v5Button.setOnAction(e -> setTeamSize(5));
    }

    private void setupControlModeButtons() {
        autoModeButton.setOnAction(e -> setControlMode(false));
        manualModeButton.setOnAction(e -> setControlMode(true));
    }

    private void setTeamSize(int size) {
        teamSize = size;
        alliedTeam.clear();
        enemyTeam.clear();
        updateTeamDisplay();
        updateStartButton();

        // Highlight selected button
        resetTeamSizeButtonStyles();
        switch (size) {
            case 1: size1v1Button.getStyleClass().add("selected"); break;
            case 2: size2v2Button.getStyleClass().add("selected"); break;
            case 3: size3v3Button.getStyleClass().add("selected"); break;
            case 4: size4v4Button.getStyleClass().add("selected"); break;
            case 5: size5v5Button.getStyleClass().add("selected"); break;
        }
    }

    private void resetTeamSizeButtonStyles() {
        size1v1Button.getStyleClass().remove("selected");
        size2v2Button.getStyleClass().remove("selected");
        size3v3Button.getStyleClass().remove("selected");
        size4v4Button.getStyleClass().remove("selected");
        size5v5Button.getStyleClass().remove("selected");
    }

    private void setControlMode(boolean manual) {
        isManualMode = manual;
        updateStartButton();

        // Highlight selected button
        autoModeButton.getStyleClass().remove("selected");
        manualModeButton.getStyleClass().remove("selected");
        if (manual) {
            manualModeButton.getStyleClass().add("selected");
        } else {
            autoModeButton.getStyleClass().add("selected");
        }
    }

    @FXML
    private void handleAddTank() {
        if (alliedTeam.size() < teamSize) {
            showTankSelectionDialog(true);
        } else {
            showAlert("Time Completo", "Seu time já está completo!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleAddBot() {
        if (alliedTeam.size() < teamSize) {
            alliedTeam.add(createBotTank());
            updateTeamDisplay();
            updateStartButton();
        } else {
            showAlert("Time Completo", "Seu time já está completo!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleAddEnemyTank() {
        if (enemyTeam.size() < teamSize) {
            showTankSelectionDialog(false);
        } else {
            showAlert("Time Completo", "O time inimigo já está completo!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleAddEnemyBot() {
        if (enemyTeam.size() < teamSize) {
            enemyTeam.add(createBotTank());
            updateTeamDisplay();
            updateStartButton();
        } else {
            showAlert("Time Completo", "O time inimigo já está completo!", Alert.AlertType.WARNING);
        }
    }

    private void showTankSelectionDialog(boolean isAllied) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Selecionar Tanque");
        dialog.setHeaderText("Escolha um tanque existente ou crie um novo");

        VBox content = new VBox(10);
        content.setPadding(new Insets(20));

        // Option to create new tank
        Button createNewButton = new Button("➕ Criar Novo Tanque");
        createNewButton.setOnAction(e -> {
            dialog.setResult(ButtonType.OK);
            dialog.close();
            MainApp.showTankCreation();
        });

        // List existing tanks
        ListView<Tanque> tankList = new ListView<>();
        List<Tanque> availableTanks = isAllied ? MainApp.getTanquesAliado() : MainApp.getTanqueInimigo();
        tankList.getItems().addAll(availableTanks);

        tankList.setCellFactory(param -> new ListCell<Tanque>() {
            @Override
            protected void updateItem(Tanque tank, boolean empty) {
                super.updateItem(tank, empty);
                if (empty || tank == null) {
                    setText(null);
                } else {
                    String type = tank instanceof Leve ? "⚡ LEVE" :
                                 tank instanceof Medio ? "⚔️ MÉDIO" : "🛡️ PESADO";
                    setText(type + " - " + (tank.getCodinome() != null ? tank.getCodinome() : "Tanque"));
                }
            }
        });

        content.getChildren().addAll(createNewButton, new Label("Ou selecione um existente:"), tankList);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(button -> {
            if (button == ButtonType.OK) {
                Tanque selectedTank = tankList.getSelectionModel().getSelectedItem();
                if (selectedTank != null) {
                    if (isAllied) {
                        alliedTeam.add(selectedTank);
                    } else {
                        enemyTeam.add(selectedTank);
                    }
                    updateTeamDisplay();
                    updateStartButton();
                }
            }
        });
    }

    private Tanque createBotTank() {
        // Create a random bot tank
        String[] types = {"Leve", "Medio", "Pesado"};
        String randomType = types[(int)(Math.random() * types.length)];
        String codinome = "Bot " + randomType + " " + (int)(Math.random() * 1000);

        Tanque botTank;
        switch (randomType) {
            case "Leve":
                botTank = new Leve(MainApp.getNextTankId(), codinome, 30, 80, 20, new java.util.Date());
                break;
            case "Medio":
                botTank = new Medio(MainApp.getNextTankId(), codinome, 30, 60, 40, new java.util.Date(), 0, 0, 0.0, null);
                break;
            default:
                botTank = new Pesado(MainApp.getNextTankId(), codinome, 60, 40, 40, new java.util.Date());
                break;
        }
        botTank.Caracterisrticas();
        return botTank;
    }

    private void updateTeamDisplay() {
        updateTeamContainer(alliedTeamContainer, alliedTeam, "Aliado");
        updateTeamContainer(enemyTeamContainer, enemyTeam, "Inimigo");
    }

    private void updateTeamContainer(VBox container, List<Tanque> team, String teamType) {
        container.getChildren().clear();

        if (team.isEmpty()) {
            Label emptyLabel = new Label("Nenhum tanque no time " + teamType.toLowerCase());
            emptyLabel.getStyleClass().add("stat-label");
            container.getChildren().add(emptyLabel);
        } else {
            for (int i = 0; i < team.size(); i++) {
                Tanque tank = team.get(i);
                HBox tankRow = createTankRow(tank, i + 1, teamType);
                container.getChildren().add(tankRow);
            }
        }

        // Add empty slots
        for (int i = team.size(); i < teamSize; i++) {
            Label slotLabel = new Label("Slot " + (i + 1) + " - Vazio");
            slotLabel.getStyleClass().add("stat-label");
            slotLabel.setStyle("-fx-text-fill: #666;");
            container.getChildren().add(slotLabel);
        }
    }

    private HBox createTankRow(Tanque tank, int position, String teamType) {
        HBox row = new HBox(10);
        row.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        row.setPadding(new Insets(5));

        Label positionLabel = new Label(position + ".");
        positionLabel.getStyleClass().add("stat-label");

        String type = tank instanceof Leve ? "⚡ LEVE" :
                     tank instanceof Medio ? "⚔️ MÉDIO" : "🛡️ PESADO";
        Label typeLabel = new Label(type);
        typeLabel.getStyleClass().add("stat-label");

        Label nameLabel = new Label(tank.getCodinome() != null ? tank.getCodinome() : "Tanque");
        nameLabel.getStyleClass().add("stat-value");

        row.getChildren().addAll(positionLabel, typeLabel, nameLabel);
        return row;
    }

    private void updateStartButton() {
        boolean canStart = alliedTeam.size() == teamSize && enemyTeam.size() == teamSize;
        startBattleButton.setDisable(!canStart);
        if (canStart) {
            startBattleButton.setText("⚔️ INICIAR BATALHA (" + (isManualMode ? "MANUAL" : "AUTOMÁTICO") + ")");
        } else {
            startBattleButton.setText("⚔️ CONFIGURE OS TIMES PARA INICIAR");
        }
    }

    @FXML
    private void handleStartBattle() {
        if (isManualMode) {
            // Start manual battle mode
            MainApp.startTeamBattle(alliedTeam, enemyTeam, true);
        } else {
            // Start automatic battle mode
            MainApp.startTeamBattle(alliedTeam, enemyTeam, false);
        }
    }

    @FXML
    private void handleBack() {
        MainApp.showMainMenu();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
