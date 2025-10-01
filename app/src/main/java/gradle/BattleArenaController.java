package gradle;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class BattleArenaController implements Initializable {
    
    @FXML private Label battleStatusLabel;
    @FXML private Label roundLabel;
    @FXML private Label winsLabel;
    @FXML private Label lossesLabel;
    @FXML private Label playerTankName;
    @FXML private Label playerTankType;
    @FXML private Label playerTankAscii;
    @FXML private Label playerHealthLabel;
    @FXML private ProgressBar playerHealthBar;
    @FXML private Label enemyTankName;
    @FXML private Label enemyTankType;
    @FXML private Label enemyTankAscii;
    @FXML private Label enemyHealthLabel;
    @FXML private ProgressBar enemyHealthBar;
    @FXML private Label battleActionLabel;
    @FXML private Button attackButton;
    @FXML private Button defendButton;
    @FXML private Button specialButton;
    @FXML private Button surrenderButton;
    @FXML private ScrollPane logScrollPane;
    @FXML private VBox battleLogContainer;
    @FXML private VBox resultDialog;
    @FXML private Label resultTitle;
    @FXML private Label resultMessage;
    @FXML private VBox toastContainer;
    @FXML private Label toastMessage;
    
    private Tanque playerTank;
    private Tanque enemyTank;
    private int playerHealth = 100;
    private int enemyHealth = 100;
    private int currentRound = 1;
    private int wins = 0;
    private int losses = 0;
    private boolean isPlayerTurn = true;
    private boolean battleInProgress = false;
    private boolean playerDefending = false;
    private Random random = new Random();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeBattle();
        updateUI();
    }
    
    private void initializeBattle() {
        // Verificar se há tanques disponíveis
        if (MainApp.getTanquesAliado().isEmpty()) {
            // Criar um tanque padrão do jogador se não houver
            playerTank = new Leve(1001, "Meu Tanque", 50, 50, 50, new java.util.Date()) {};
        } else {
            playerTank = MainApp.getTanquesAliado().get(0);
        }
        
        // Criar inimigo aleatório se não houver
        if (MainApp.getTanqueInimigo().isEmpty()) {
            createRandomEnemy();
        } else {
            enemyTank = MainApp.getTanqueInimigo().get(0);
        }
        
        // Reset health
        playerHealth = 100;
        enemyHealth = 100;
        battleInProgress = true;
        playerDefending = false;
        
        addBattleLog("🎮 Batalha iniciada!");
        addBattleLog("👊 " + playerTank.getCodinome() + " VS " + enemyTank.getCodinome());
    }
    
    private void createRandomEnemy() {
        String[] enemyNames = {"Destroyer", "Annihilator", "War Machine", "Death Bringer", "Iron Fist", "Thunder Strike"};
        String name = enemyNames[random.nextInt(enemyNames.length)];
        
        int armor = 30 + random.nextInt(50);
        int speed = 30 + random.nextInt(50);
        int firepower = 30 + random.nextInt(50);
        
        int tankType = random.nextInt(3);
        if (tankType == 0) {
            enemyTank = new Leve(9999, name, armor, speed, firepower, new java.util.Date()) {};
        } else if (tankType == 1) {
            enemyTank = new Medio(9999, name, armor, speed, firepower, new java.util.Date(), 0, 0, 0.0, null) {};
        } else {
            enemyTank = new Pesado(9999, name, armor, speed, firepower, new java.util.Date()) {};
        }
    }
    
    private void updateUI() {
        Platform.runLater(() -> {
            if (playerTank != null) {
                playerTankName.setText(playerTank.getCodinome());
                
                if (playerTank instanceof Leve) {
                    playerTankType.setText("⚡ LEVE");
                    playerTankAscii.setText("   ▄██▄\n  ██████\n ████████\n   ██ ██");
                } else if (playerTank instanceof Medio) {
                    playerTankType.setText("⚔️ MÉDIO");
                    playerTankAscii.setText("  ████████\n ██      ██\n████████████\n ██  ██  ██");
                } else {
                    playerTankType.setText("🛡️ PESADO");
                    playerTankAscii.setText(" ██████████\n███      ███\n█████████████\n███  ██  ███");
                }
            }
            
            if (enemyTank != null) {
                enemyTankName.setText(enemyTank.getCodinome());
                
                if (enemyTank instanceof Leve) {
                    enemyTankType.setText("⚡ LEVE");
                    enemyTankAscii.setText("   ▄██▄\n  ██████\n ████████\n   ██ ██");
                } else if (enemyTank instanceof Medio) {
                    enemyTankType.setText("⚔️ MÉDIO");
                    enemyTankAscii.setText("  ████████\n ██      ██\n████████████\n ██  ██  ██");
                } else {
                    enemyTankType.setText("🛡️ PESADO");
                    enemyTankAscii.setText(" ██████████\n███      ███\n█████████████\n███  ██  ███");
                }
            }
            
            updateHealthBars();
            updateBattleStatus();
            roundLabel.setText("Rodada: " + currentRound);
            winsLabel.setText(String.valueOf(wins));
            lossesLabel.setText(String.valueOf(losses));
        });
    }
    
    private void updateHealthBars() {
        playerHealthLabel.setText(playerHealth + "/100");
        playerHealthBar.setProgress(playerHealth / 100.0);
        
        enemyHealthLabel.setText(enemyHealth + "/100");
        enemyHealthBar.setProgress(enemyHealth / 100.0);
    }
    
    private void updateBattleStatus() {
        if (!battleInProgress) {
            battleStatusLabel.setText("Batalha finalizada");
            battleActionLabel.setText("Fim de jogo");
            return;
        }
        
        if (isPlayerTurn) {
            battleStatusLabel.setText("Sua vez!");
            battleActionLabel.setText("Escolha sua ação");
            enableButtons(true);
        } else {
            battleStatusLabel.setText("Vez do inimigo");
            battleActionLabel.setText("Inimigo pensando...");
            enableButtons(false);
            
            // IA do inimigo
            Timeline enemyTurn = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> performEnemyAction()));
            enemyTurn.play();
        }
    }
    
    private void enableButtons(boolean enabled) {
        attackButton.setDisable(!enabled);
        defendButton.setDisable(!enabled);
        specialButton.setDisable(!enabled);
    }
    
    @FXML
    private void handleAttack() {
        if (!battleInProgress || !isPlayerTurn) return;
        
        playerDefending = false;
        int damage = calculateDamage(playerTank.getPoderDeFogo(), enemyTank.getBlindagem());
        
        enemyHealth = Math.max(0, enemyHealth - damage);
        
        addBattleLog("💥 " + playerTank.getCodinome() + " ataca causando " + damage + " de dano!");
        
        if (enemyHealth <= 0) {
            endBattle(true);
        } else {
            isPlayerTurn = false;
            updateUI();
        }
    }
    
    @FXML
    private void handleDefend() {
        if (!battleInProgress || !isPlayerTurn) return;
        
        playerDefending = true;
        addBattleLog("🛡️ " + playerTank.getCodinome() + " se prepara para defender!");
        
        isPlayerTurn = false;
        updateUI();
    }
    
    @FXML
    private void handleSpecialAttack() {
        if (!battleInProgress || !isPlayerTurn) return;
        
        playerDefending = false;
        int baseDamage = calculateDamage(playerTank.getPoderDeFogo(), enemyTank.getBlindagem());
        int specialDamage = (int)(baseDamage * 1.5); // 50% mais dano
        
        enemyHealth = Math.max(0, enemyHealth - specialDamage);
        
        addBattleLog("⚡ " + playerTank.getCodinome() + " usa ataque especial causando " + specialDamage + " de dano!");
        
        if (enemyHealth <= 0) {
            endBattle(true);
        } else {
            isPlayerTurn = false;
            updateUI();
        }
    }
    
    @FXML
    private void handleSurrender() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Desistir");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Tem certeza que deseja desistir da batalha?");
        
        if (confirmAlert.showAndWait().orElse(null) == ButtonType.OK) {
            addBattleLog("🏳️ " + playerTank.getCodinome() + " desistiu da batalha!");
            endBattle(false);
        }
    }
    
    private void performEnemyAction() {
        if (!battleInProgress || isPlayerTurn) return;
        
        // IA simples: 70% chance de atacar, 30% de defender
        boolean enemyAttacks = random.nextDouble() < 0.7;
        
        if (enemyAttacks) {
            int damage = calculateDamage(enemyTank.getPoderDeFogo(), playerTank.getBlindagem());
            
            if (playerDefending) {
                damage = (int)(damage * 0.5); // Defesa reduz dano pela metade
                addBattleLog("💥 " + enemyTank.getCodinome() + " ataca, mas " + playerTank.getCodinome() + " defendeu! Dano reduzido: " + damage);
            } else {
                addBattleLog("💥 " + enemyTank.getCodinome() + " ataca causando " + damage + " de dano!");
            }
            
            playerHealth = Math.max(0, playerHealth - damage);
            
            if (playerHealth <= 0) {
                endBattle(false);
                return;
            }
        } else {
            addBattleLog("🛡️ " + enemyTank.getCodinome() + " se prepara para defender!");
        }
        
        currentRound++;
        isPlayerTurn = true;
        updateUI();
    }
    
    private int calculateDamage(int attackerFirepower, int defenderArmor) {
        int baseDamage = attackerFirepower / 3;
        int armorReduction = defenderArmor / 5;
        int damage = Math.max(5, baseDamage - armorReduction + random.nextInt(10));
        return damage;
    }
    
    private void endBattle(boolean playerWon) {
        battleInProgress = false;
        enableButtons(false);
        
        if (playerWon) {
            wins++;
            resultTitle.setText("🏆 VITÓRIA!");
            resultMessage.setText("Você derrotou " + enemyTank.getCodinome() + "!");
            addBattleLog("🏆 " + playerTank.getCodinome() + " é vitorioso!");
        } else {
            losses++;
            resultTitle.setText("💀 DERROTA!");
            resultMessage.setText(enemyTank.getCodinome() + " te derrotou!");
            addBattleLog("💀 " + playerTank.getCodinome() + " foi derrotado!");
        }
        
        updateUI();
        showResultDialog();
    }
    
    private void showResultDialog() {
        resultDialog.setVisible(true);
        
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), resultDialog);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    
    @FXML
    private void handlePlayAgain() {
        resultDialog.setVisible(false);
        currentRound = 1;
        isPlayerTurn = true;
        battleLogContainer.getChildren().clear();
        initializeBattle();
        updateUI();
    }
    
    @FXML
    private void handleExit() {
        MainApp.showMainMenu();
    }
    
    public void setMainApp() {
        // Método para compatibilidade, inicialização já é feita no initialize()
    }
    
    @FXML
    private void handleBack() {
        if (battleInProgress) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Sair da Batalha");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("A batalha está em andamento. Deseja realmente sair?");
            
            if (confirmAlert.showAndWait().orElse(null) != ButtonType.OK) {
                return;
            }
        }
        
        MainApp.showMainMenu();
    }
    
    private void addBattleLog(String message) {
        Platform.runLater(() -> {
            Label logLabel = new Label(message);
            logLabel.getStyleClass().add("subtitle");
            logLabel.setWrapText(true);
            logLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #cccccc;");
            
            battleLogContainer.getChildren().add(logLabel);
            
            // Auto-scroll para o final
            Timeline scrollDelay = new Timeline(new KeyFrame(Duration.millis(50), 
                e -> logScrollPane.setVvalue(1.0)));
            scrollDelay.play();
        });
    }
}
