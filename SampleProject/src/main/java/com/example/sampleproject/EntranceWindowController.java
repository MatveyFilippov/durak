package com.example.sampleproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EntranceWindowController {
    @FXML
    private Button start_button; // id конопки добавлено ради получения окна, в котором оно находится
    @FXML
    void startGame(){ // Начать игру
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GameWindow.fxml"));
        try {
            Stage stage = (Stage) start_button.getScene().getWindow(); // Узнаём какое окно сейчас запущено
            loader.load(); // Запускает окно игры
            GameWindowController.attac_is_when_start(); // Решает кто аттакует первым
            stage.close(); // Закрывает входное окно
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void exitGame() {
    	System.exit(0);
    } // Просто выход из игры, больше сказать нечего

}
