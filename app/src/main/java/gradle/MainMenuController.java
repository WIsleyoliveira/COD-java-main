package gradle;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    
    @FXML private Text titleText;
    @FXML private Label subtitleLabel;
    @FXML private Label allyTankCountLabel;
    @FXML private Label enemyTankCountLabel;
    @FXML private Label battleCountLabel;
    
    @FXML private Button createTankButton;
    @FXML private Button battleModeButton;
    @FXML private Button teamModeButton;
    @FXML private Button statsButton;
    @FXML private Button quickTrainingButton;
    @FXML private Button randomBattleButton;
    @FXML private Button settingsButton;
    @FXML private Button exitButton;
    
    @FXML private ScrollPane tankScrollPane;
    @FXML private HBox tankContainer;
    @FXML private VBox noTanksPlaceholder;
    
    private int battleCount = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAnimations();
        setupHoverEffects();
        refreshTankDisplay();
        updateStats();
    }
    
    public void setMainApp() {
        refreshTankDisplay();
        updateStats();
    }
    
    private void initializeAnimations() {
        FadeTransition titleFade = new FadeTransition(Duration.seconds(1), titleText);
        titleFade.setFromValue(0.0);
        titleFade.setToValue(1.0);
        titleFade.play();
        
        FadeTransition subtitleFade = new FadeTransition(Duration.seconds(1.5), subtitleLabel);
        subtitleFade.setFromValue(0.0);
        subtitleFade.setToValue(1.0);
        subtitleFade.setDelay(Duration.seconds(0.5));
        subtitleFade.play();
    }
    
    private void setupHoverEffects() {
        setupButtonHover(createTankButton);
        setupButtonHover(battleModeButton);
        setupButtonHover(statsButton);
        setupButtonHover(quickTrainingButton);
        setupButtonHover(randomBattleButton);
        setupButtonHover(settingsButton);
        setupButtonHover(exitButton);
    }
    
    private void setupButtonHover(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.05);
        scaleIn.setToY(1.05);
        
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);
        
        button.setOnMouseEntered(e -> scaleIn.play());
        button.setOnMouseExited(e -> scaleOut.play());
    }
    
    private void updateStats() {
        Platform.runLater(() -> {
            allyTankCountLabel.setText(String.valueOf(MainApp.getTanquesAliado().size()));
            enemyTankCountLabel.setText(String.valueOf(MainApp.getTanqueInimigo().size()));
            battleCountLabel.setText(String.valueOf(battleCount));
        });
    }
    
    private void refreshTankDisplay() {
        Platform.runLater(() -> {
            tankContainer.getChildren().clear();
            
            int totalTanks = MainApp.getTanquesAliado().size() + MainApp.getTanqueInimigo().size();
            
            if (totalTanks == 0) {
                tankContainer.getChildren().add(noTanksPlaceholder);
            } else {
                tankContainer.getChildren().remove(noTanksPlaceholder);
                
                for (Tanque tank : MainApp.getTanquesAliado()) {
                    VBox tankCard = createTankCard(tank, "ALIADO", "#4facfe");
                    tankContainer.getChildren().add(tankCard);
                }
                
                for (Tanque tank : MainApp.getTanqueInimigo()) {
                    VBox tankCard = createTankCard(tank, "INIMIGO", "#ff6b6b");
                    tankContainer.getChildren().add(tankCard);
                }
            }
            
            updateStats();
        });
    }
    
    private VBox createTankCard(Tanque tank, String type, String color) {
        VBox card = new VBox(10);
        card.getStyleClass().add("tank-card");
        card.setPrefWidth(250);
        card.setPrefHeight(200);
        card.setPadding(new Insets(15));
        
        Label titleLabel = new Label("🚗 " + (tank.getCodinome() != null ? tank.getCodinome() : "Tanque"));
        titleLabel.getStyleClass().add("title-secondary");
        titleLabel.setStyle("-fx-text-fill: " + color + ";");
        
        Label typeLabel = new Label(type);
        typeLabel.getStyleClass().add("subtitle");
        
        String tankClass = "Desconhecido";
        if (tank instanceof Leve) tankClass = "⚡ LEVE";
        else if (tank instanceof Medio) tankClass = "⚔️ MÉDIO";
        else if (tank instanceof Pesado) tankClass = "🛡️ PESADO";
        
        Label classLabel = new Label(tankClass);
        classLabel.getStyleClass().add("stat-label");
        
        VBox statsBox = new VBox(5);
        statsBox.getChildren().addAll(
            createStatRow("🛡️ Blindagem:", String.valueOf(tank.getBlindagem())),
            createStatRow("⚡ Velocidade:", String.valueOf(tank.getVelocidade())),
            createStatRow("💥 Poder de Fogo:", String.valueOf(tank.getPoderDeFogo()))
        );
        
        card.getChildren().addAll(titleLabel, typeLabel, classLabel, statsBox);
        
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), card);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
        return card;
    }
    
    private HBox createStatRow(String label, String value) {
        HBox row = new HBox(10);
        
        Label labelComp = new Label(label);
        labelComp.getStyleClass().add("stat-label");
        labelComp.setStyle("-fx-font-size: 12px;");
        
        Label valueComp = new Label(value);
        valueComp.getStyleClass().add("stat-value");
        valueComp.setStyle("-fx-font-size: 12px;");
        
        row.getChildren().addAll(labelComp, valueComp);
        return row;
    }
    
    @FXML
    private void handleCreateTank() {
        MainApp.showTankCreation();
    }
    
    @FXML
    private void handleBattleMode() {
        if (MainApp.getTanquesAliado().isEmpty() || MainApp.getTanqueInimigo().isEmpty()) {
            showAlert("Atenção!", "Você precisa ter pelo menos um tanque aliado e um inimigo para batalhar!", Alert.AlertType.WARNING);
            return;
        }
        MainApp.showArenaSelection();
    }
    
    @FXML
    private void handleStats() {
        showAlert("Estatísticas", 
            String.format("📊 ESTATÍSTICAS DO SISTEMA\n\n" +
                "🚗 Tanques Aliados: %d\n" +
                "💀 Tanques Inimigos: %d\n" +
                "⚔️ Batalhas Realizadas: %d\n" +
                "🎯 Total de Tanques: %d", 
                MainApp.getTanquesAliado().size(),
                MainApp.getTanqueInimigo().size(),
                battleCount,
                MainApp.getTanquesAliado().size() + MainApp.getTanqueInimigo().size()
            ), Alert.AlertType.INFORMATION);
    }
    
    @FXML
    private void handleQuickTraining() {
        if (MainApp.getTanquesAliado().isEmpty() || MainApp.getTanqueInimigo().isEmpty()) {
            showAlert("Treino Indisponível", "Crie tanques aliados e inimigos primeiro!", Alert.AlertType.WARNING);
            return;
        }
        
        battleCount++;
        updateStats();
        
        showAlert("⚡ TREINO RÁPIDO CONCLUÍDO!", 
            String.format("Simulação de treino executada!\n\n" +
                "🏆 Tanque Aliado: %s\n" +
                "💀 Tanque Inimigo: %s\n" +
                "🎯 Resultado: %s", 
                MainApp.getTanquesAliado().get(0).getCodinome(),
                MainApp.getTanqueInimigo().get(0).getCodinome(),
                new Random().nextBoolean() ? "VITÓRIA!" : "DERROTA!"
            ), Alert.AlertType.INFORMATION);
    }
    
    @FXML
    private void handleRandomBattle() {
        if (MainApp.getTanquesAliado().isEmpty() || MainApp.getTanqueInimigo().isEmpty()) {
            showAlert("Batalha Indisponível", "Crie tanques aliados e inimigos primeiro!", Alert.AlertType.WARNING);
            return;
        }
        
        battleCount++;
        updateStats();
        MainApp.showBattle();
    }

    @FXML
    private void handleTeamMode() {
        MainApp.showTeamMode();
    }
    
    @FXML
    private void handleSettings() {
        showAlert("⚙️ Configurações", "Configurações do sistema serão implementadas em breve!", Alert.AlertType.INFORMATION);
    }
    
    @FXML
    private void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Deseja realmente sair?");
        alert.setContentText("Todos os dados não salvos serão perdidos.");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}