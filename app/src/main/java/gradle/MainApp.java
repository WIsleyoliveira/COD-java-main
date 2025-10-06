package gradle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * 🎮 Tank Battle Arena - JavaFX Application
 * Interface gráfica moderna para o sistema de combate de tanques
 */
public class MainApp extends Application {
    
    private static Stage primaryStage;
    private static ArrayList<Tanque> tanquesAliado = new ArrayList<>();
    private static ArrayList<Tanque> tanqueInimigo = new ArrayList<>();
    private static Modos modos;
    
    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage;
            
            // Inicializar sistema de modos
            modos = new Modos(null, 0, 0, tanquesAliado, tanqueInimigo);
            
            // Configurar stage principal
            primaryStage.setTitle("🎮 Tank Battle Arena");
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(1200);
            primaryStage.setMinHeight(800);
            
            // Carregar ícone da aplicação (se existir)
            try {
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/tank-icon.png")));
            } catch (Exception e) {
                System.out.println("Ícone não encontrado, usando padrão");
            }
            
            // Carregar tela principal
            showMainMenu();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao inicializar a aplicação: " + e.getMessage());
        }
    }
    
    /**
     * Exibe o menu principal
     */
    public static void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MainMenu.fxml"));
            Parent root = loader.load();
            
            // Configurar controlador
            MainMenuController controller = loader.getController();
            controller.setMainApp();
            
            Scene scene = new Scene(root, 1400, 900);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());
            
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
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/TankCreation.fxml"));
            Parent root = loader.load();
            
            TankCreationController controller = loader.getController();
            controller.setMainApp();
            
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());
            
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
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/ArenaSelection.fxml"));
            Parent root = loader.load();
            
            ArenaSelectionController controller = loader.getController();
            controller.setMainApp();
            
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());
            
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
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/BattleArena.fxml"));
            Parent root = loader.load();
            
            BattleArenaController controller = loader.getController();
            controller.setMainApp();
            
            Scene scene = new Scene(root, 1400, 900);
            scene.getStylesheets().add(MainApp.class.getResource("/css/main-style.css").toExternalForm());
            
            primaryStage.setScene(scene);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar arena de batalha: " + e.getMessage());
        }
    }
    
    // Getters e setters para dados compartilhados
    public static ArrayList<Tanque> getTanquesAliado() {
        return tanquesAliado;
    }
    
    public static ArrayList<Tanque> getTanqueInimigo() {
        return tanqueInimigo;
    }
    
    public static Modos getModos() {
        return modos;
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Método principal
     */
    public static void main(String[] args) {
        launch(args);
    }
}