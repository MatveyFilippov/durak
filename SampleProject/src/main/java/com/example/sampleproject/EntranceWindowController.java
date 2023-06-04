package com.example.sampleproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntranceWindowController {

    @FXML
    void startGame(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GameWindow.fxml"));
        try
        {
            GameWindowController.attac_is();
            loader.load();
        } catch (IOException e)
        {
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
    }

}
