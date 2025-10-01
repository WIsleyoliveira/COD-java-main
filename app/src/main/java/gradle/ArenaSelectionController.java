package gradle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ArenaSelectionController {
    
    public void setMainApp() {
        // Configurações específicas se necessário
    }
    
    @FXML
    private void handleBack() {
        MainApp.showMainMenu();
    }
    
    @FXML
    private void handleDesertArena() {
        showAlert("Arena Deserto", "Batalha no deserto iniciada!", Alert.AlertType.INFORMATION);
        MainApp.showBattle();
    }
    
    @FXML
    private void handleUrbanArena() {
        showAlert("Arena Urbana", "Batalha urbana iniciada!", Alert.AlertType.INFORMATION);
        MainApp.showBattle();
    }
    
    @FXML
    private void handleOpenFieldArena() {
        showAlert("Campo Aberto", "Batalha em campo aberto iniciada!", Alert.AlertType.INFORMATION);
        MainApp.showBattle();
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}