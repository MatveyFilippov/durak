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
		String[] parts = card_name.split("_"); // Узаеём что это за карта ->
		this.nominal.setText(parts[0]); // Запоминаем
    	this.mast.setText(parts[1]); // Запоминаем
		if (parts[1].contains("♦️") || parts[1].contains("♥️"))
			this.mast.setStyle("-fx-text-fill: red;"); // Меняем цвет текста если масть того требует
		this.gameWindow = gameWindow;
    	this.cardPane = cardPane;
    }

	/**
	 * Возращает номинал карты
	 * При переходе всех функций на приём одной перменной этот блок будет удалён
	 */
    public String getNominal() {
    	return this.nominal.getText();
    }

	/**
	 * Возращает масть карты
	 * При переходе всех функций на приём одной перменной этот блок будет удалён
	 */
    public String getMast() {
    	return this.mast.getText();
    }

	@FXML
    void replaceCardToTable() throws IOException { // Если на карту нажали
    	gameWindow.addCardOnTable(this); // То перемещаем её на стол
    	cardPane.setVisible(false);
    	FlowPane firstPlayerPane = (FlowPane) cardPane.getParent();
    	firstPlayerPane.getChildren().remove(cardPane);
    }
}
