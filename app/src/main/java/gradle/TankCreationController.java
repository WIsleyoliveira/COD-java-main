package gradle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class TankCreationController implements Initializable {
    
    @FXML private Button backButton;
    @FXML private ToggleButton allyToggle;
    @FXML private ToggleButton enemyToggle;
    @FXML private ToggleButton lightToggle;
    @FXML private ToggleButton mediumToggle;
    @FXML private ToggleButton heavyToggle;
    @FXML private TextField tankNameField;
    @FXML private Slider armorSlider;
    @FXML private Slider speedSlider;
    @FXML private Slider firepowerSlider;
    @FXML private Label armorValueLabel;
    @FXML private Label speedValueLabel;
    @FXML private Label firepowerValueLabel;
    @FXML private Label pointsLabel;
    @FXML private ProgressBar armorProgressBar;
    @FXML private ProgressBar speedProgressBar;
    @FXML private ProgressBar firepowerProgressBar;
    @FXML private ProgressBar totalPointsBar;
    @FXML private Label previewTitle;
    @FXML private Label previewType;
    @FXML private Label tankAscii;
    @FXML private Label classBonusLabel;
    @FXML private ProgressBar previewArmorBar;
    @FXML private ProgressBar previewSpeedBar;
    @FXML private ProgressBar previewFirepowerBar;
    @FXML private Label previewArmorValue;
    @FXML private Label previewSpeedValue;
    @FXML private Label previewFirepowerValue;
    @FXML private Button createButton;
    @FXML private VBox toastContainer;
    @FXML private Label toastMessage;
    
    private ToggleGroup teamGroup;
    private ToggleGroup classGroup;
    private static final int MAX_TOTAL_POINTS = 300;
    private Random random = new Random();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupToggleGroups();
        setupSliderListeners();
        setupInitialValues();
        updatePreview();
    }
    
    private void setupToggleGroups() {
        teamGroup = new ToggleGroup();
        allyToggle.setToggleGroup(teamGroup);
        enemyToggle.setToggleGroup(teamGroup);
        allyToggle.setSelected(true);
        
        classGroup = new ToggleGroup();
        lightToggle.setToggleGroup(classGroup);
        mediumToggle.setToggleGroup(classGroup);
        heavyToggle.setToggleGroup(classGroup);
        lightToggle.setSelected(true);
        
        teamGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> updatePreview());
        classGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            updateClassBonus();
            updatePreview();
        });
    }
    
    private void setupSliderListeners() {
        armorSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateStatValue(armorValueLabel, armorProgressBar, newVal.intValue());
            validatePoints();
            updatePreview();
        });
        
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateStatValue(speedValueLabel, speedProgressBar, newVal.intValue());
            validatePoints();
            updatePreview();
        });
        
        firepowerSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateStatValue(firepowerValueLabel, firepowerProgressBar, newVal.intValue());
            validatePoints();
            updatePreview();
        });
        
        tankNameField.textProperty().addListener((obs, oldVal, newVal) -> updatePreview());
    }
    
    private void setupInitialValues() {
        armorSlider.setValue(50);
        speedSlider.setValue(50);
        firepowerSlider.setValue(50);
        tankNameField.setText("Tank-" + (random.nextInt(9000) + 1000));
        updateClassBonus();
    }
    
    private void updateStatValue(Label valueLabel, ProgressBar progressBar, int value) {
        valueLabel.setText(String.valueOf(value));
        progressBar.setProgress(value / 100.0);
    }
    
    private void validatePoints() {
        int totalUsed = (int)(armorSlider.getValue() + speedSlider.getValue() + firepowerSlider.getValue());
        int remaining = MAX_TOTAL_POINTS - totalUsed;
        
        pointsLabel.setText(remaining + " / " + MAX_TOTAL_POINTS);
        totalPointsBar.setProgress((double)totalUsed / MAX_TOTAL_POINTS);
        
        if (remaining < 0) {
            pointsLabel.setStyle("-fx-text-fill: #ff6b6b;");
            createButton.setDisable(true);
        } else {
            pointsLabel.setStyle("-fx-text-fill: #4facfe;");
            createButton.setDisable(false);
        }
    }
    
    private void updatePreview() {
        Platform.runLater(() -> {
            String tankName = tankNameField.getText().trim();
            if (tankName.isEmpty()) tankName = "Meu Tanque";
            previewTitle.setText("🚗 " + tankName.toUpperCase());
            
            String team = allyToggle.isSelected() ? "🤝 ALIADO" : "⚔️ INIMIGO";
            String tankClass = lightToggle.isSelected() ? "⚡ LEVE" : 
                              mediumToggle.isSelected() ? "⚔️ MÉDIO" : "🛡️ PESADO";
            previewType.setText(team + " - " + tankClass);
            
            updateTankAscii();
            
            int armor = (int)armorSlider.getValue();
            int speed = (int)speedSlider.getValue();
            int firepower = (int)firepowerSlider.getValue();
            
            previewArmorValue.setText(String.valueOf(armor));
            previewSpeedValue.setText(String.valueOf(speed));
            previewFirepowerValue.setText(String.valueOf(firepower));
            
            previewArmorBar.setProgress(armor / 100.0);
            previewSpeedBar.setProgress(speed / 100.0);
            previewFirepowerBar.setProgress(firepower / 100.0);
            
            String color = allyToggle.isSelected() ? "#4facfe" : "#ff6b6b";
            previewTitle.setStyle("-fx-text-fill: " + color + ";");
        });
    }
    
    private void updateTankAscii() {
        String ascii;
        if (lightToggle.isSelected()) {
            ascii = "   ▄██▄\\n  ██████\\n ████████\\n   ██ ██";
        } else if (mediumToggle.isSelected()) {
            ascii = "  ████████\\n ██      ██\\n████████████\\n ██  ██  ██";
        } else {
            ascii = " ██████████\\n███      ███\\n█████████████\\n███  ██  ███";
        }
        tankAscii.setText(ascii);
    }
    
    private void updateClassBonus() {
        String bonus;
        if (lightToggle.isSelected()) {
            bonus = "⚡ Leve: +20% Velocidade, -10% Blindagem";
        } else if (mediumToggle.isSelected()) {
            bonus = "⚔️ Médio: Balanceado, +10% Poder de Fogo";
        } else {
            bonus = "🛡️ Pesado: +30% Blindagem, -20% Velocidade";
        }
        classBonusLabel.setText(bonus);
    }
    
    public void setMainApp() {
        // Configurações específicas se necessário
    }
    
    @FXML
    private void handleBack() {
        MainApp.showMainMenu();
    }
    
    @FXML
    private void handleRandomize() {
        String[] prefixes = {"Wolf", "Tiger", "Eagle", "Storm", "Iron", "Steel", "Fire", "Thunder"};
        String[] suffixes = {"Hunter", "Warrior", "Guardian", "Striker", "Crusher", "Destroyer", "Phantom", "Beast"};
        tankNameField.setText(prefixes[random.nextInt(prefixes.length)] + "-" + 
                             suffixes[random.nextInt(suffixes.length)]);
        
        if (random.nextDouble() < 0.3) {
            enemyToggle.setSelected(true);
        } else {
            allyToggle.setSelected(true);
        }
        
        int classRandom = random.nextInt(3);
        switch (classRandom) {
            case 0: lightToggle.setSelected(true); break;
            case 1: mediumToggle.setSelected(true); break;
            case 2: heavyToggle.setSelected(true); break;
        }
        
        randomizeStats();
        showToast("🎲 Configuração aleatória aplicada!");
    }
    
    private void randomizeStats() {
        int armor = 20 + random.nextInt(81);
        int speed = 20 + random.nextInt(81);
        int firepower = 20 + random.nextInt(81);
        
        int currentTotal = armor + speed + firepower;
        if (currentTotal > MAX_TOTAL_POINTS) {
            double factor = (double) MAX_TOTAL_POINTS / currentTotal;
            armor = Math.max(20, (int)(armor * factor));
            speed = Math.max(20, (int)(speed * factor));
            firepower = Math.max(20, (int)(firepower * factor));
        }
        
        armorSlider.setValue(armor);
        speedSlider.setValue(speed);
        firepowerSlider.setValue(firepower);
    }
    
    @FXML
    private void handleReset() {
        allyToggle.setSelected(true);
        lightToggle.setSelected(true);
        tankNameField.setText("Tank-" + (random.nextInt(9000) + 1000));
        armorSlider.setValue(50);
        speedSlider.setValue(50);
        firepowerSlider.setValue(50);
        showToast("🔄 Configurações resetadas!");
    }
    
    @FXML
    private void handleCreateTank() {
        try {
            if (!validateInput()) return;
            
            createTank();
            showToast("✅ Tanque criado com sucesso!");
            
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> MainApp.showMainMenu());
            pause.play();
            
        } catch (Exception e) {
            showAlert("Erro", "Erro ao criar tanque: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean validateInput() {
        String tankName = tankNameField.getText().trim();
        if (tankName.isEmpty()) {
            showAlert("Validação", "Nome do tanque não pode estar vazio!", Alert.AlertType.WARNING);
            return false;
        }
        
        int totalPoints = (int)(armorSlider.getValue() + speedSlider.getValue() + firepowerSlider.getValue());
        if (totalPoints > MAX_TOTAL_POINTS) {
            showAlert("Validação", "Total de pontos excede o limite permitido!", Alert.AlertType.WARNING);
            return false;
        }
        
        return true;
    }
    
    private void createTank() {
        Random rand = new Random();
        int id = rand.nextInt(9999) + 1000;
        String codinome = tankNameField.getText().trim();
        int blindagem = (int)armorSlider.getValue();
        int velocidade = (int)speedSlider.getValue();
        int poderDeFogo = (int)firepowerSlider.getValue();
        Date horaEntrada = new Date();
        
        Tanque novoTanque;
        
        if (lightToggle.isSelected()) {
            novoTanque = new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {};
        } else if (mediumToggle.isSelected()) {
            novoTanque = new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {};
        } else {
            novoTanque = new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {};
        }
        
        if (allyToggle.isSelected()) {
            MainApp.getTanquesAliado().add(novoTanque);
        } else {
            MainApp.getTanqueInimigo().add(novoTanque);
        }
    }
    
    @FXML
    private void handlePreviewBattle() {
        showToast("⚔️ Teste de combate em breve!");
    }
    
    @FXML
    private void handleSaveTemplate() {
        showToast("💾 Funcionalidade em desenvolvimento!");
    }
    
    private void showToast(String message) {
        Platform.runLater(() -> {
            toastMessage.setText(message);
            toastContainer.setVisible(true);
            
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), toastContainer);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
            
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), toastContainer);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished(f -> toastContainer.setVisible(false));
                fadeOut.play();
            });
            pause.play();
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