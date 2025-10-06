// Pacote do projeto
package gradle;

// Importações para JavaFX: aplicação, carregamento de FXML, cenas, imagens, stages
import javafx.application.Application; // Classe base para aplicações JavaFX
import javafx.fxml.FXMLLoader; // Para carregar arquivos FXML
import javafx.scene.Parent; // Nó raiz da cena
import javafx.scene.Scene; // Cena da aplicação
import javafx.scene.image.Image; // Para ícones
import javafx.stage.Stage; // Janela principal
import javafx.stage.StageStyle; // Estilos de stage (não usado)
import javafx.scene.paint.Color; // Cores para a cena

// Importação para listas
import java.util.ArrayList; // Para listas de tanques

/**
 * 🎮 Tank Battle Arena - JavaFX Application
 * Interface gráfica moderna para o sistema de combate de tanques
 */
// Classe principal da aplicação JavaFX, estende Application
public class MainApp extends Application {

    // Atributos estáticos para compartilhamento entre telas
    private static Stage primaryStage; // Stage principal da aplicação
    private static ArrayList<Tanque> tanquesAliado = new ArrayList<>(); // Lista de tanques aliados
    private static ArrayList<Tanque> tanqueInimigo = new ArrayList<>(); // Lista de tanques inimigos
    private static Modos modos; // Instância da classe Modos para gerenciar modos de jogo

    // Método sobrescrito para iniciar a aplicação JavaFX
    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage; // Define o stage principal

            // Inicializar sistema de modos com listas vazias
            modos = new Modos(null, 0, 0, tanquesAliado, tanqueInimigo);

            // Configurar propriedades do stage principal
            primaryStage.setTitle("🎮 Tank Battle Arena"); // Título da janela
            primaryStage.setResizable(true); // Permite redimensionar
            primaryStage.setMinWidth(1200); // Largura mínima
            primaryStage.setMinHeight(800); // Altura mínima

            // Tentar carregar ícone da aplicação
            try {
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/tank-icon.png")));
            } catch (Exception e) {
                System.out.println("Ícone não encontrado, usando padrão"); // Mensagem se ícone não existir
            }

            // Carregar e exibir o menu principal
            showMainMenu();

        } catch (Exception e) {
            e.printStackTrace(); // Imprimir stack trace em caso de erro
            System.err.println("Erro ao inicializar a aplicação: " + e.getMessage());
        }
    }

    /**
     * Exibe o menu principal
     */
    public static void showMainMenu() {
        try {
            // Carregar o FXML do menu principal
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MainMenu.fxml"));
            Parent root = loader.load(); // Carregar o nó raiz

            // Obter e configurar o controlador
            MainMenuController controller = loader.getController();
            controller.setMainApp(); // Passar referência para a aplicação principal

            // Criar cena com tamanho específico
            Scene scene = new Scene(root, 1400, 900);
            scene.setFill(Color.TRANSPARENT); // Fundo transparente
            // Adicionar folha de estilo CSS
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Definir cena no stage e mostrar
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar menu principal: " + e.getMessage());
        }
    }

    /**
     * Navega para tela de criação de tanques
     */
    public static void showTankCreation() {
        try {
            // Carregar FXML de criação de tanques
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/TankCreation.fxml"));
            Parent root = loader.load();

            // Configurar controlador
            TankCreationController controller = loader.getController();
            controller.setMainApp();

            // Criar cena
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Definir cena no stage
            primaryStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar criação de tanques: " + e.getMessage());
        }
    }

    /**
     * Navega para seleção de arena
     */
    public static void showArenaSelection() {
        try {
            // Carregar FXML de seleção de arena
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ArenaSelection.fxml"));
            Parent root = loader.load();

            // Configurar controlador
            ArenaSelectionController controller = loader.getController();
            controller.setMainApp();

            // Criar cena
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Definir cena
            primaryStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar seleção de arena: " + e.getMessage());
        }
    }

    /**
     * Navega para a tela de batalha
     */
    public static void showBattle() {
        try {
            // Carregar FXML de batalha
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/BattleArena.fxml"));
            Parent root = loader.load();

            // Configurar controlador
            BattleArenaController controller = loader.getController();
            controller.setMainApp();

            // Criar cena
            Scene scene = new Scene(root, 1400, 900);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Definir cena
            primaryStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar arena de batalha: " + e.getMessage());
        }
    }

    /**
     * Navega para o modo equipe
     */
    public static void showTeamMode() {
        try {
            // Carregar FXML do modo equipe
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/TeamMode.fxml"));
            Parent root = loader.load();

            // Configurar controlador
            TeamModeController controller = loader.getController();
            controller.setMainApp();

            // Criar cena
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Definir cena
            primaryStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar modo equipe: " + e.getMessage());
        }
    }

    // Getters para acessar dados compartilhados entre telas
    public static ArrayList<Tanque> getTanquesAliado() {
        return tanquesAliado; // Retorna lista de aliados
    }

    public static ArrayList<Tanque> getTanqueInimigo() {
        return tanqueInimigo; // Retorna lista de inimigos
    }

    public static Modos getModos() {
        return modos; // Retorna instância de Modos
    }

    public static Stage getPrimaryStage() {
        return primaryStage; // Retorna stage principal
    }

    /**
     * Gera um ID único para tanque
     */
    private static int nextTankId = 1000; // Contador para IDs únicos
    public static int getNextTankId() {
        return nextTankId++; // Incrementa e retorna próximo ID
    }

    /**
     * Inicia batalha em equipe
     */
    public static void startTeamBattle(java.util.List<Tanque> alliedTeam, java.util.List<Tanque> enemyTeam, boolean isManual) {
        if (isManual) {
            // Se manual, mostrar batalha manual
            showManualBattle(alliedTeam, enemyTeam);
        } else {
            // TODO: Implementar batalha automática (ainda não implementada)
            System.out.println("Batalha automática ainda não implementada");
            showMainMenu(); // Voltar ao menu
        }
    }

    /**
     * Navega para a tela de batalha manual
     */
    public static void showManualBattle(java.util.List<Tanque> alliedTeam, java.util.List<Tanque> enemyTeam) {
        try {
            // Carregar FXML de batalha manual
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ManualBattle.fxml"));
            Parent root = loader.load();

            // Configurar controlador com times
            ManualBattleController controller = loader.getController();
            controller.setTeams(alliedTeam, enemyTeam); // Passar times para o controlador

            // Criar cena
            Scene scene = new Scene(root, 1400, 900);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Definir cena
            primaryStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar batalha manual: " + e.getMessage());
        }
    }

    /**
     * Método principal para lançar a aplicação JavaFX
     */
    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
    }
}
