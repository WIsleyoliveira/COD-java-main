// Pacote do projeto
package gradle;

// Importações para JavaFX: animações, controles, layouts, utilitários
import javafx.animation.FadeTransition; // Para animações de fade in/out
import javafx.animation.ScaleTransition; // Para animações de escala (hover)
import javafx.application.Platform; // Para executar código na thread da UI
import javafx.fxml.FXML; // Anotação para injetar elementos FXML
import javafx.fxml.Initializable; // Interface para inicialização do controlador
import javafx.geometry.Insets; // Para definir margens e preenchimentos
import javafx.scene.control.*; // Controles como Button, Label, Alert
import javafx.scene.layout.HBox; // Layout horizontal
import javafx.scene.layout.VBox; // Layout vertical
import javafx.scene.text.Text; // Texto simples
import javafx.util.Duration; // Para durações de animações

// Importações Java padrão
import java.net.URL; // Para URLs (usado em initialize)
import java.util.Random; // Para gerar resultados aleatórios
import java.util.ResourceBundle; // Para recursos de internacionalização

// Controlador do menu principal da aplicação JavaFX
// Gerencia a interface do menu principal, animações, exibição de tanques e navegação
public class MainMenuController implements Initializable {

    // Elementos injetados do FXML (interface gráfica)
    @FXML private Text titleText; // Texto do título principal
    @FXML private Label subtitleLabel; // Subtítulo
    @FXML private Label allyTankCountLabel; // Contador de tanques aliados
    @FXML private Label enemyTankCountLabel; // Contador de tanques inimigos
    @FXML private Label battleCountLabel; // Contador de batalhas

    // Botões do menu
    @FXML private Button createTankButton; // Botão para criar tanque
    @FXML private Button battleModeButton; // Botão para modo batalha
    @FXML private Button teamModeButton; // Botão para modo equipe
    @FXML private Button statsButton; // Botão para estatísticas
    @FXML private Button quickTrainingButton; // Botão para treino rápido
    @FXML private Button randomBattleButton; // Botão para batalha aleatória
    @FXML private Button settingsButton; // Botão para configurações
    @FXML private Button exitButton; // Botão para sair

    // Elementos de layout para exibir tanques
    @FXML private ScrollPane tankScrollPane; // Painel de rolagem
    @FXML private HBox tankContainer; // Container horizontal para cartões de tanques
    @FXML private VBox noTanksPlaceholder; // Placeholder quando não há tanques

    // Atributo para contar batalhas realizadas
    private int battleCount = 0;

    // Método chamado ao inicializar o controlador
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAnimations(); // Configura animações iniciais
        setupHoverEffects(); // Configura efeitos de hover nos botões
        refreshTankDisplay(); // Atualiza exibição dos tanques
        updateStats(); // Atualiza estatísticas
    }

    // Método chamado pela MainApp para configurar o controlador
    public void setMainApp() {
        refreshTankDisplay(); // Atualiza exibição
        updateStats(); // Atualiza stats
    }

    // Inicializa animações de entrada (fade in)
    private void initializeAnimations() {
        // Animação de fade para o título
        FadeTransition titleFade = new FadeTransition(Duration.seconds(1), titleText);
        titleFade.setFromValue(0.0); // Começa invisível
        titleFade.setToValue(1.0); // Fica visível
        titleFade.play(); // Executa a animação

        // Animação de fade para o subtítulo com delay
        FadeTransition subtitleFade = new FadeTransition(Duration.seconds(1.5), subtitleLabel);
        subtitleFade.setFromValue(0.0);
        subtitleFade.setToValue(1.0);
        subtitleFade.setDelay(Duration.seconds(0.5)); // Delay de 0.5s
        subtitleFade.play();
    }

    // Configura efeitos de hover para os botões
    private void setupHoverEffects() {
        // Aplica efeito de hover a cada botão
        setupButtonHover(createTankButton);
        setupButtonHover(battleModeButton);
        setupButtonHover(statsButton);
        setupButtonHover(quickTrainingButton);
        setupButtonHover(randomBattleButton);
        setupButtonHover(settingsButton);
        setupButtonHover(exitButton);
    }

    // Método auxiliar para configurar hover em um botão específico
    private void setupButtonHover(Button button) {
        // Animação de aumento ao passar o mouse
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.05); // Aumenta 5% em X
        scaleIn.setToY(1.05); // Aumenta 5% em Y

        // Animação de volta ao tamanho normal
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1.0); // Volta ao normal
        scaleOut.setToY(1.0);

        // Eventos de mouse
        button.setOnMouseEntered(e -> scaleIn.play()); // Ao entrar, aumenta
        button.setOnMouseExited(e -> scaleOut.play()); // Ao sair, volta
    }

    // Atualiza as estatísticas exibidas na interface
    private void updateStats() {
        // Executa na thread da UI para evitar problemas de concorrência
        Platform.runLater(() -> {
            allyTankCountLabel.setText(String.valueOf(MainApp.getTanquesAliado().size())); // Conta aliados
            enemyTankCountLabel.setText(String.valueOf(MainApp.getTanqueInimigo().size())); // Conta inimigos
            battleCountLabel.setText(String.valueOf(battleCount)); // Conta batalhas
        });
    }

    // Atualiza a exibição dos tanques no container
    private void refreshTankDisplay() {
        // Executa na thread da UI
        Platform.runLater(() -> {
            tankContainer.getChildren().clear(); // Limpa o container

            // Calcula total de tanques
            int totalTanks = MainApp.getTanquesAliado().size() + MainApp.getTanqueInimigo().size();

            if (totalTanks == 0) {
                // Se não há tanques, mostra placeholder
                tankContainer.getChildren().add(noTanksPlaceholder);
            } else {
                // Remove placeholder e adiciona cartões de tanques
                tankContainer.getChildren().remove(noTanksPlaceholder);

                // Adiciona cartões para aliados
                for (Tanque tank : MainApp.getTanquesAliado()) {
                    VBox tankCard = createTankCard(tank, "ALIADO", "#4facfe"); // Azul para aliados
                    tankContainer.getChildren().add(tankCard);
                }

                // Adiciona cartões para inimigos
                for (Tanque tank : MainApp.getTanqueInimigo()) {
                    VBox tankCard = createTankCard(tank, "INIMIGO", "#ff6b6b"); // Vermelho para inimigos
                    tankContainer.getChildren().add(tankCard);
                }
            }

            updateStats(); // Atualiza stats após exibir
        });
    }

    // Cria um cartão visual para representar um tanque
    private VBox createTankCard(Tanque tank, String type, String color) {
        VBox card = new VBox(10); // Layout vertical com espaçamento
        card.getStyleClass().add("tank-card"); // Classe CSS
        card.setPrefWidth(250); // Largura preferida
        card.setPrefHeight(200); // Altura preferida
        card.setPadding(new Insets(15)); // Preenchimento interno

        // Label para o nome do tanque
        Label titleLabel = new Label("🚗 " + (tank.getCodinome() != null ? tank.getCodinome() : "Tanque"));
        titleLabel.getStyleClass().add("title-secondary");
        titleLabel.setStyle("-fx-text-fill: " + color + ";"); // Cor personalizada

        // Label para o tipo (aliado/inimigo)
        Label typeLabel = new Label(type);
        typeLabel.getStyleClass().add("subtitle");

        // Determina a classe do tanque baseado no tipo
        String tankClass = "Desconhecido";
        if (tank instanceof Leve) tankClass = "⚡ LEVE";
        else if (tank instanceof Medio) tankClass = "⚔️ MÉDIO";
        else if (tank instanceof Pesado) tankClass = "🛡️ PESADO";

        // Label para a classe
        Label classLabel = new Label(tankClass);
        classLabel.getStyleClass().add("stat-label");

        // Box para estatísticas
        VBox statsBox = new VBox(5);
        statsBox.getChildren().addAll(
            createStatRow("🛡️ Blindagem:", String.valueOf(tank.getBlindagem())),
            createStatRow("⚡ Velocidade:", String.valueOf(tank.getVelocidade())),
            createStatRow("💥 Poder de Fogo:", String.valueOf(tank.getPoderDeFogo()))
        );

        // Adiciona todos os elementos ao cartão
        card.getChildren().addAll(titleLabel, typeLabel, classLabel, statsBox);

        // Animação de fade in para o cartão
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), card);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        return card;
    }

    // Cria uma linha de estatística (label + valor)
    private HBox createStatRow(String label, String value) {
        HBox row = new HBox(10); // Layout horizontal

        // Label para o nome da stat
        Label labelComp = new Label(label);
        labelComp.getStyleClass().add("stat-label");
        labelComp.setStyle("-fx-font-size: 12px;");

        // Label para o valor
        Label valueComp = new Label(value);
        valueComp.getStyleClass().add("stat-value");
        valueComp.setStyle("-fx-font-size: 12px;");

        // Adiciona à linha
        row.getChildren().addAll(labelComp, valueComp);
        return row;
    }

    // Manipulador para o botão de criar tanque
    @FXML
    private void handleCreateTank() {
        MainApp.showTankCreation(); // Navega para tela de criação
    }

    // Manipulador para o botão de modo batalha
    @FXML
    private void handleBattleMode() {
        // Verifica se há tanques suficientes
        if (MainApp.getTanquesAliado().isEmpty() || MainApp.getTanqueInimigo().isEmpty()) {
            showAlert("Atenção!", "Você precisa ter pelo menos um tanque aliado e um inimigo para batalhar!", Alert.AlertType.WARNING);
            return;
        }
        MainApp.showArenaSelection(); // Navega para seleção de arena
    }

    // Manipulador para o botão de estatísticas
    @FXML
    private void handleStats() {
        // Mostra alerta com estatísticas formatadas
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

    // Manipulador para treino rápido
    @FXML
    private void handleQuickTraining() {
        // Verifica tanques
        if (MainApp.getTanquesAliado().isEmpty() || MainApp.getTanqueInimigo().isEmpty()) {
            showAlert("Treino Indisponível", "Crie tanques aliados e inimigos primeiro!", Alert.AlertType.WARNING);
            return;
        }

        battleCount++; // Incrementa contador
        updateStats(); // Atualiza UI

        // Simula resultado aleatório
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

    // Manipulador para batalha aleatória
    @FXML
    private void handleRandomBattle() {
        // Verifica tanques
        if (MainApp.getTanquesAliado().isEmpty() || MainApp.getTanqueInimigo().isEmpty()) {
            showAlert("Batalha Indisponível", "Crie tanques aliados e inimigos primeiro!", Alert.AlertType.WARNING);
            return;
        }

        battleCount++; // Incrementa
        updateStats(); // Atualiza
        MainApp.showBattle(); // Navega para batalha
    }

    // Manipulador para modo equipe
    @FXML
    private void handleTeamMode() {
        MainApp.showTeamMode(); // Navega para modo equipe
    }

    // Manipulador para configurações
    @FXML
    private void handleSettings() {
        showAlert("⚙️ Configurações", "Configurações do sistema serão implementadas em breve!", Alert.AlertType.INFORMATION);
    }

    // Manipulador para sair
    @FXML
    private void handleExit() {
        // Alerta de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Deseja realmente sair?");
        alert.setContentText("Todos os dados não salvos serão perdidos.");

        // Se confirmado, sai da aplicação
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit(); // Fecha a aplicação
            }
        });
    }

    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); // Espera o usuário fechar
    }
}
