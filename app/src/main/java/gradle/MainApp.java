// Declara o pacote do projeto
package gradle;

// Importa Application para JavaFX
import javafx.application.Application;
// Importa FXMLLoader para carregar FXML
import javafx.fxml.FXMLLoader;
// Importa Parent para nó raiz
import javafx.scene.Parent;
// Importa Scene para cena
import javafx.scene.Scene;
// Importa Image para ícones
import javafx.scene.image.Image;
// Importa Stage para janela
import javafx.stage.Stage;
// Importa StageStyle para estilos
import javafx.stage.StageStyle;
// Importa Color para cores
import javafx.scene.paint.Color;

// Importa ArrayList para listas
import java.util.ArrayList;

// Define a classe MainApp que estende Application
public class MainApp extends Application {

    // Declara atributo primaryStage como Stage
    private static Stage primaryStage;
    // Declara atributo tanquesAliado como ArrayList
    private static ArrayList<Tanque> tanquesAliado = new ArrayList<>();
    // Declara atributo tanqueInimigo como ArrayList
    private static ArrayList<Tanque> tanqueInimigo = new ArrayList<>();
    // Declara atributo modos como Modos
    private static Modos modos;

    // Sobrescreve o método start
    @Override
    public void start(Stage stage) {
        // Inicia bloco try
        try {
            // Atribui stage a primaryStage
            primaryStage = stage;

            // Instancia Modos
            modos = new Modos(null, 0, 0, tanquesAliado, tanqueInimigo);

            // Define título do primaryStage
            primaryStage.setTitle("🎮 Tank Battle Arena");
            // Define resizable como true
            primaryStage.setResizable(true);
            // Define minWidth
            primaryStage.setMinWidth(1200);
            // Define minHeight
            primaryStage.setMinHeight(800);

            // Inicia bloco try para ícone
            try {
                // Adiciona ícone ao primaryStage
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/tank-icon.png")));
            // Catch para Exception
            } catch (Exception e) {
                // Imprime mensagem
                System.out.println("Ícone não encontrado, usando padrão");
            }

            // Chama showMainMenu
            showMainMenu();

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao inicializar a aplicação: " + e.getMessage());
        }
    }

    // Define o método showMainMenu
    public static void showMainMenu() {
        // Inicia bloco try
        try {
            // Cria FXMLLoader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MainMenu.fxml"));
            // Carrega root
            Parent root = loader.load();

            // Obtém controller
            MainMenuController controller = loader.getController();
            // Chama setMainApp
            controller.setMainApp();

            // Cria Scene
            Scene scene = new Scene(root, 1400, 900);
            // Define fill como TRANSPARENT
            scene.setFill(Color.TRANSPARENT);
            // Adiciona stylesheet
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Define scene no primaryStage
            primaryStage.setScene(scene);
            // Mostra primaryStage
            primaryStage.show();

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao carregar menu principal: " + e.getMessage());
        }
    }

    // Define o método showTankCreation
    public static void showTankCreation() {
        // Inicia bloco try
        try {
            // Cria FXMLLoader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/TankCreation.fxml"));
            // Carrega root
            Parent root = loader.load();

            // Obtém controller
            TankCreationController controller = loader.getController();
            // Chama setMainApp
            controller.setMainApp();

            // Cria Scene
            Scene scene = new Scene(root, 1200, 800);
            // Adiciona stylesheet
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Define scene no primaryStage
            primaryStage.setScene(scene);

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao carregar criação de tanques: " + e.getMessage());
        }
    }

    // Define o método showArenaSelection
    public static void showArenaSelection() {
        // Inicia bloco try
        try {
            // Cria FXMLLoader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ArenaSelection.fxml"));
            // Carrega root
            Parent root = loader.load();

            // Obtém controller
            ArenaSelectionController controller = loader.getController();
            // Chama setMainApp
            controller.setMainApp();

            // Cria Scene
            Scene scene = new Scene(root, 1200, 800);
            // Adiciona stylesheet
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Define scene no primaryStage
            primaryStage.setScene(scene);

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao carregar seleção de arena: " + e.getMessage());
        }
    }

    // Define o método showBattle
    public static void showBattle() {
        // Inicia bloco try
        try {
            // Cria FXMLLoader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/BattleArena.fxml"));
            // Carrega root
            Parent root = loader.load();

            // Obtém controller
            BattleArenaController controller = loader.getController();
            // Chama setMainApp
            controller.setMainApp();

            // Cria Scene
            Scene scene = new Scene(root, 1400, 900);
            // Adiciona stylesheet
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Define scene no primaryStage
            primaryStage.setScene(scene);

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao carregar arena de batalha: " + e.getMessage());
        }
    }

    // Define o método showTeamMode
    public static void showTeamMode() {
        // Inicia bloco try
        try {
            // Cria FXMLLoader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/TeamMode.fxml"));
            // Carrega root
            Parent root = loader.load();

            // Obtém controller
            TeamModeController controller = loader.getController();
            // Chama setMainApp
            controller.setMainApp();

            // Cria Scene
            Scene scene = new Scene(root, 1200, 800);
            // Adiciona stylesheet
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Define scene no primaryStage
            primaryStage.setScene(scene);

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao carregar modo equipe: " + e.getMessage());
        }
    }

    // Define o método getTanquesAliado
    public static ArrayList<Tanque> getTanquesAliado() {
        // Retorna tanquesAliado
        return tanquesAliado;
    }

    // Define o método getTanqueInimigo
    public static ArrayList<Tanque> getTanqueInimigo() {
        // Retorna tanqueInimigo
        return tanqueInimigo;
    }

    // Define o método getModos
    public static Modos getModos() {
        // Retorna modos
        return modos;
    }

    // Define o método getPrimaryStage
    public static Stage getPrimaryStage() {
        // Retorna primaryStage
        return primaryStage;
    }

    // Declara atributo nextTankId
    private static int nextTankId = 1000;
    // Define o método getNextTankId
    public static int getNextTankId() {
        // Retorna nextTankId e incrementa
        return nextTankId++;
    }

    // Define o método startTeamBattle
    public static void startTeamBattle(java.util.List<Tanque> alliedTeam, java.util.List<Tanque> enemyTeam, boolean isManual) {
        // Se isManual
        if (isManual) {
            // Chama showManualBattle
            showManualBattle(alliedTeam, enemyTeam);
        // Senão
        } else {
            // Imprime mensagem
            System.out.println("Batalha automática ainda não implementada");
            // Chama showMainMenu
            showMainMenu();
        }
    }

    // Define o método showManualBattle
    public static void showManualBattle(java.util.List<Tanque> alliedTeam, java.util.List<Tanque> enemyTeam) {
        // Inicia bloco try
        try {
            // Cria FXMLLoader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ManualBattle.fxml"));
            // Carrega root
            Parent root = loader.load();

            // Obtém controller
            ManualBattleController controller = loader.getController();
            // Chama setTeams
            controller.setTeams(alliedTeam, enemyTeam);

            // Cria Scene
            Scene scene = new Scene(root, 1400, 900);
            // Adiciona stylesheet
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());

            // Define scene no primaryStage
            primaryStage.setScene(scene);

        // Catch para Exception
        } catch (Exception e) {
            // Imprime stack trace
            e.printStackTrace();
            // Imprime erro
            System.err.println("Erro ao carregar batalha manual: " + e.getMessage());
        }
    }

    // Define o método main
    public static void main(String[] args) {
        // Chama launch
        launch(args);
    }
}
