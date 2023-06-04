package com.example.sampleproject;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class CardController {
	Pane cardPane;
	
	GameWindowController gameWindow;
	@FXML
    private Label nominal;

	@FXML
    private Label mast;

    public void setCardParameters(String card_name, GameWindowController gameWindow, Pane cardPane) {
		String[] parts = card_name.split("_");
		this.nominal.setText(parts[0]);
    	this.mast.setText(parts[1]);
    	this.gameWindow = gameWindow;
    	this.cardPane = cardPane;
    }
    
    public String getNominal() {
    	return this.nominal.getText();
    }
    
    public String getMast() {
    	return this.mast.getText();
    }

	@FXML
    void replaceCardToTable() throws IOException {
    	gameWindow.addCardOnTable(this);
    	cardPane.setVisible(false);
    	FlowPane firstPlayerPane = (FlowPane) cardPane.getParent();
    	firstPlayerPane.getChildren().remove(cardPane);
    }
}
