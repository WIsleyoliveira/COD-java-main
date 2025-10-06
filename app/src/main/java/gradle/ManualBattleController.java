// Pacote do projeto
package gradle;

// Importações do JavaFX para interface gráfica
import javafx.fxml.FXML; // Anotação para injetar elementos da interface FXML
import javafx.fxml.Initializable; // Interface para inicialização do controller
import javafx.geometry.Insets; // Para definir margens e espaçamentos
import javafx.scene.control.*; // Componentes de controle da UI (botões, labels, etc.)
import javafx.scene.layout.HBox; // Layout horizontal
import javafx.scene.layout.VBox; // Layout vertical
import javafx.scene.text.Text; // Texto na interface
import javafx.animation.KeyFrame; // Frame de animação
import javafx.animation.Timeline; // Timeline para animações temporizadas
import javafx.util.Duration; // Duração para animações

// Importações do Java padrão
import java.net.URL; // Para URLs (usado na inicialização FXML)
import java.util.ArrayList; // Lista dinâmica
import java.util.List; // Interface List
import java.util.ResourceBundle; // Recursos de localização (não usado aqui)

// Controlador para a tela de batalha manual - gerencia a lógica da batalha entre dois times
public class ManualBattleController implements Initializable {

    // Elementos da interface injetados via FXML
    @FXML private VBox alliedTeamContainer; // Container para exibir tanques aliados
    @FXML private VBox enemyTeamContainer; // Container para exibir tanques inimigos
    @FXML private VBox battleLogVBox; // Container para o log da batalha
    @FXML private Button alliedAttackButton; // Botão para ataque dos aliados
    @FXML private Button enemyAttackButton; // Botão para ataque dos inimigos
    @FXML private Button endTurnButton; // Botão para finalizar turno (não usado no sistema automático)
    @FXML private Label turnLabel; // Label que mostra de quem é o turno atual
    @FXML private Label battleStatusLabel; // Label que mostra o status da batalha

    // Dados do jogo
    private List<Tanque> alliedTeam; // Lista de tanques do time aliado
    private List<Tanque> enemyTeam; // Lista de tanques do time inimigo
    private boolean isAlliedTurn; // Indica se é o turno dos aliados
    private boolean battleEnded = false; // Indica se a batalha terminou
    private StringBuilder battleLog = new StringBuilder(); // Log de texto da batalha

    // Sistema de cooldown para ataques
    private boolean attackOnCooldown = false; // Indica se há cooldown ativo
    private int cooldownTurns = 0; // Contador de turnos de cooldown restantes
    private final int MAX_COOLDOWN = 2; // Número máximo de turnos de cooldown
    private Timeline cooldownTimeline; // Timeline para controlar o cooldown automaticamente

    // Método chamado quando o controller é inicializado (implementação da interface Initializable)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialização será feita pelo método setTeams
    }

    // Configura os times para a batalha e inicia o jogo
    public void setTeams(List<Tanque> alliedTeam, List<Tanque> enemyTeam) {
        this.alliedTeam = new ArrayList<>(alliedTeam); // Copia a lista de aliados
        this.enemyTeam = new ArrayList<>(enemyTeam); // Copia a lista de inimigos
        updateTeamDisplays(); // Atualiza a exibição dos times na interface
        // Sorteia quem começa a batalha (50% de chance para cada time)
        isAlliedTurn = Math.random() < 0.5;
        cooldownTurns = 0; // Reseta contador de cooldown
        attackOnCooldown = false; // Remove qualquer cooldown ativo
        updateTurnDisplay(); // Atualiza o display do turno
        updateBattleStatus(); // Atualiza o status da batalha
        addToBattleLog("🏁 Batalha iniciada! Time Aliado vs Time Inimigo"); // Log de início
        addToBattleLog("⚔️ " + alliedTeam.size() + "v" + enemyTeam.size() + " - Modo Manual"); // Log do formato
        addToBattleLog("🎲 Time " + (isAlliedTurn ? "Aliado" : "Inimigo") + " começa a batalha!"); // Log de quem começa
    }

    private void updateTeamDisplays() {
        updateTeamContainer(alliedTeamContainer, alliedTeam, "Aliado");
        updateTeamContainer(enemyTeamContainer, enemyTeam, "Inimigo");
    }

    private void updateTeamContainer(VBox container, List<Tanque> team, String teamType) {
        container.getChildren().clear();

        Label teamLabel = new Label("Time " + teamType);
        teamLabel.getStyleClass().add("team-label");
        container.getChildren().add(teamLabel);

        for (int i = 0; i < team.size(); i++) {
            Tanque tank = team.get(i);
            HBox tankRow = createTankRow(tank, i + 1, teamType);
            container.getChildren().add(tankRow);
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

        Label healthLabel = new Label("❤️ " + tank.getVida());
        healthLabel.getStyleClass().add("health-label");

        // Highlight if tank is dead
        if (tank.getVida() <= 0) {
            row.setStyle("-fx-background-color: rgba(255, 0, 0, 0.2);");
            nameLabel.setText(nameLabel.getText() + " 💀");
        }

        row.getChildren().addAll(positionLabel, typeLabel, nameLabel, healthLabel);
        return row;
    }

    private void updateTurnDisplay() {
        if (battleEnded) {
            turnLabel.setText("🏆 BATALHA FINALIZADA");
            alliedAttackButton.setDisable(true);
            enemyAttackButton.setDisable(true);
            endTurnButton.setDisable(true);
        } else {
            String currentTeam = isAlliedTurn ? "ALIADO" : "INIMIGO";
            String cooldownText = attackOnCooldown ? " (⏳ Cooldown: " + cooldownTurns + ")" : "";
            turnLabel.setText("🎯 VEZ DO TIME " + currentTeam + cooldownText);
            alliedAttackButton.setDisable(!isAlliedTurn || attackOnCooldown);
            enemyAttackButton.setDisable(isAlliedTurn || attackOnCooldown);
            endTurnButton.setDisable(attackOnCooldown);
        }
    }

    private void updateBattleStatus() {
        int alliedAlive = countAliveTanks(alliedTeam);
        int enemyAlive = countAliveTanks(enemyTeam);

        if (alliedAlive == 0 && enemyAlive == 0) {
            battleStatusLabel.setText("🤝 EMPATE!");
            battleEnded = true;
        } else if (alliedAlive == 0) {
            battleStatusLabel.setText("🏆 TIME INIMIGO VENCEU!");
            battleEnded = true;
        } else if (enemyAlive == 0) {
            battleStatusLabel.setText("🏆 TIME ALIADO VENCEU!");
            battleEnded = true;
        } else {
            battleStatusLabel.setText("❤️ Aliados: " + alliedAlive + " | ❤️ Inimigos: " + enemyAlive);
        }
    }

    private int countAliveTanks(List<Tanque> team) {
        return (int) team.stream().filter(t -> t.getVida() > 0).count();
    }

    @FXML
    private void handleAlliedAttack() {
        if (!isAlliedTurn || battleEnded || attackOnCooldown) return;
        showAttackDialog(true);
    }

    @FXML
    private void handleEnemyAttack() {
        if (isAlliedTurn || battleEnded || attackOnCooldown) return;
        showAttackDialog(false);
    }

    private void showAttackDialog(boolean isAlliedAttacker) {
        List<Tanque> attackingTeam = isAlliedAttacker ? alliedTeam : enemyTeam;
        List<Tanque> defendingTeam = isAlliedAttacker ? enemyTeam : alliedTeam;

        // Filter alive attackers and defenders
        List<Tanque> aliveAttackers = attackingTeam.stream()
                .filter(t -> t.getVida() > 0)
                .toList();
        List<Tanque> aliveDefenders = defendingTeam.stream()
                .filter(t -> t.getVida() > 0)
                .toList();

        if (aliveAttackers.isEmpty()) {
            addToBattleLog("❌ Nenhum tanque vivo no time " + (isAlliedAttacker ? "aliado" : "inimigo") + " para atacar!");
            return;
        }

        if (aliveDefenders.isEmpty()) {
            addToBattleLog("❌ Nenhum alvo disponível!");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Escolher Ataque");
        dialog.setHeaderText("Selecione o tanque atacante e o alvo");

        VBox content = new VBox(10);
        content.setPadding(new Insets(20));

        // Attacker selection
        Label attackerLabel = new Label("Tanque Atacante:");
        ComboBox<Tanque> attackerCombo = new ComboBox<>();
        attackerCombo.getItems().addAll(aliveAttackers);
        attackerCombo.setCellFactory(param -> new ListCell<Tanque>() {
            @Override
            protected void updateItem(Tanque tank, boolean empty) {
                super.updateItem(tank, empty);
                if (empty || tank == null) {
                    setText(null);
                } else {
                    String type = tank instanceof Leve ? "⚡ LEVE" :
                                 tank instanceof Medio ? "⚔️ MÉDIO" : "🛡️ PESADO";
                    setText(type + " - " + (tank.getCodinome() != null ? tank.getCodinome() : "Tanque") + " (❤️ " + tank.getVida() + ")");
                }
            }
        });
        attackerCombo.getSelectionModel().selectFirst();

        // Target selection
        Label targetLabel = new Label("Alvo:");
        ComboBox<Tanque> targetCombo = new ComboBox<>();
        targetCombo.getItems().addAll(aliveDefenders);
        targetCombo.setCellFactory(param -> new ListCell<Tanque>() {
            @Override
            protected void updateItem(Tanque tank, boolean empty) {
                super.updateItem(tank, empty);
                if (empty || tank == null) {
                    setText(null);
                } else {
                    String type = tank instanceof Leve ? "⚡ LEVE" :
                                 tank instanceof Medio ? "⚔️ MÉDIO" : "🛡️ PESADO";
                    setText(type + " - " + (tank.getCodinome() != null ? tank.getCodinome() : "Tanque") + " (❤️ " + tank.getVida() + ")");
                }
            }
        });
        targetCombo.getSelectionModel().selectFirst();

        content.getChildren().addAll(attackerLabel, attackerCombo, targetLabel, targetCombo);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(button -> {
            if (button == ButtonType.OK) {
                Tanque attacker = attackerCombo.getSelectionModel().getSelectedItem();
                Tanque target = targetCombo.getSelectionModel().getSelectedItem();

                if (attacker != null && target != null) {
                    executeAttack(attacker, target, isAlliedAttacker);
                }
            }
        });
    }

    private void executeAttack(Tanque attacker, Tanque target, boolean isAlliedAttacker) {
        String attackerTeam = isAlliedAttacker ? "Aliado" : "Inimigo";
        String targetTeam = isAlliedAttacker ? "Inimigo" : "Aliado";

        // Calculate damage based on tank types
        int damage = calculateDamage(attacker, target);

        // Apply damage
        int oldHealth = target.getVida();
        target.setVida(Math.max(0, oldHealth - damage));

        // Log the attack
        String attackLog = String.format("⚔️ %s %s ataca %s %s (-%d ❤️) [%d → %d]",
                attackerTeam,
                attacker.getCodinome() != null ? attacker.getCodinome() : "Tanque",
                targetTeam,
                target.getCodinome() != null ? target.getCodinome() : "Tanque",
                damage, oldHealth, target.getVida());

        addToBattleLog(attackLog);

        // Check if target died
        if (target.getVida() <= 0) {
            addToBattleLog("💀 " + targetTeam + " " + (target.getCodinome() != null ? target.getCodinome() : "Tanque") + " foi destruído!");
        }

        // Ativar cooldown após ataque - nenhum time pode atacar durante cooldown
        attackOnCooldown = true;
        cooldownTurns = MAX_COOLDOWN;
        addToBattleLog("⏳ Ataque realizado! Cooldown global de " + MAX_COOLDOWN + " turnos ativado.");

        updateTeamDisplays();
        updateBattleStatus();
        updateTurnDisplay();

        if (battleEnded) {
            updateTurnDisplay();
        } else {
            // Iniciar timeline para processar cooldown automaticamente
            startCooldownTimeline();
        }
    }

    private void startCooldownTimeline() {
        if (cooldownTimeline != null) {
            cooldownTimeline.stop();
        }

        cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            switchTurn();
        }));
        cooldownTimeline.setCycleCount(MAX_COOLDOWN);
        cooldownTimeline.play();
    }

    private void switchTurn() {
        if (attackOnCooldown) {
            cooldownTurns--;
            if (cooldownTurns <= 0) {
                attackOnCooldown = false;
                // Após cooldown, alternar turno para o outro time
                isAlliedTurn = !isAlliedTurn;
                String currentTeam = isAlliedTurn ? "Aliado" : "Inimigo";
                addToBattleLog("✅ Cooldown terminado! 🔄 Turno passou para o time " + currentTeam);
            } else {
                addToBattleLog("⏳ Cooldown restante: " + cooldownTurns + " turnos");
            }
        } else {
            // Se não estiver em cooldown, apenas alternar turno
            isAlliedTurn = !isAlliedTurn;
            String currentTeam = isAlliedTurn ? "Aliado" : "Inimigo";
            addToBattleLog("🔄 Turno passou para o time " + currentTeam);
        }
        updateTurnDisplay();
    }

    private int calculateDamage(Tanque attacker, Tanque target) {
        // Base damage calculation
        int baseDamage = attacker.getAtaque();

        // Type advantages/disadvantages
        double multiplier = 1.0;

        if (attacker instanceof Leve && target instanceof Pesado) {
            multiplier = 1.5; // Light tanks are effective against heavy tanks
        } else if (attacker instanceof Pesado && target instanceof Medio) {
            multiplier = 1.5; // Heavy tanks are effective against medium tanks
        } else if (attacker instanceof Medio && target instanceof Leve) {
            multiplier = 1.5; // Medium tanks are effective against light tanks
        } else if (attacker instanceof Pesado && target instanceof Leve) {
            multiplier = 0.7; // Heavy tanks are less effective against light tanks
        } else if (attacker instanceof Leve && target instanceof Medio) {
            multiplier = 0.7; // Light tanks are less effective against medium tanks
        } else if (attacker instanceof Medio && target instanceof Pesado) {
            multiplier = 0.7; // Medium tanks are less effective against heavy tanks
        }

        return (int) (baseDamage * multiplier);
    }

    @FXML
    private void handleEndTurn() {
        if (battleEnded) return;

        switchTurn();
    }

    @FXML
    private void handleBackToTeamMode() {
        MainApp.showTeamMode();
    }

    private void addToBattleLog(String message) {
        battleLog.append(message).append("\n");

        // Update UI log
        Label logEntry = new Label(message);
        logEntry.getStyleClass().add("log-entry");
        logEntry.setWrapText(true);
        battleLogVBox.getChildren().add(0, logEntry); // Add to top

        // Limit log entries to prevent UI overflow
        if (battleLogVBox.getChildren().size() > 50) {
            battleLogVBox.getChildren().remove(50);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
