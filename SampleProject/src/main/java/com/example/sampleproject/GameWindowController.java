package com.example.sampleproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameWindowController {
    @FXML
    private ImageView rubashka_koloda;

    @FXML
    private Label left_cards;

    private static int who_attac; // Хранит информацию о том, кто сейчас атттакует (1 или 2)

    @FXML
    private Button add1;

    @FXML
    private Button add2;

    @FXML
    private Label attac_first;

    @FXML
    private Label attac_second;

    private final ArrayList<String> already_use = new ArrayList<>(); // Ведёт учёт использованных карт, чтоб больше не повторяться
    private final ArrayList<String> has_first = new ArrayList<>(); // Ведёт учёт карт, которые прямо сейчас на руке у первого игрока
    private final ArrayList<String> has_second = new ArrayList<>(); // Ведёт учёт карт, которые прямо сейчас на руке у второго игрока

    @FXML
    private GridPane deskAttackCardPane;

    @FXML
    private GridPane deskAnswerCardPane;
    
    @FXML
    private ScrollPane firstPlayerScroll;

    @FXML
    private ScrollPane secondPlayerScroll;

    @FXML
    private FlowPane firstPlayerPane;

    @FXML
    private FlowPane secondPlayerPane;

    @FXML
    void addCard1() throws IOException { // Реагирует на левую конопку "add card" для первого игрока
        while (has_first.size() < 6) { // Если на руке у 1 игрока карт меньше чем 6, то выдаём ему до 6
            String card = availableCard(); // Получаем карту
            if (!card.equals("")){
                has_first.add(card); // Сохраняем в руку
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Card.fxml"));

                Pane firstPane = loader.load();
                CardController cardController = loader.getController();
                cardController.setCardParameters(card, this, firstPane);
                firstPlayerPane.getChildren().add(firstPane);
                firstPlayerScroll = new ScrollPane();
                firstPlayerScroll.setContent(firstPlayerPane);
            }
        }
        if (who_attac == 1){ // Подписываем кто атакует (в будущем можно это оптимизировать и переместить в функцию бита)
            attac_first.setVisible(true);
            attac_second.setVisible(false);
        }else{
            attac_second.setVisible(true);
            attac_first.setVisible(false);
        }
        add1.setDisable(true); // Первый игрок получил достаточное кол-во карт, кнопка ему пока что не нужна
    }
    @FXML
    void addCard2() throws IOException { // Реагирует на правую конопку "add card" для второго игрока
        while (has_second.size() < 6) { // Если на руке у 2 игрока карт меньше чем 6, то выдаём ему до 6
            String card = availableCard(); // Получаем карту
            if (!card.equals("")){
                has_second.add(card); // Сохраняем в руку
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Card.fxml"));

                Pane secondPane = loader.load();
                CardController cardController = loader.getController();
                cardController.setCardParameters(card, this, secondPane);
                secondPlayerPane.getChildren().add(secondPane);
                secondPlayerScroll = new ScrollPane();
                secondPlayerScroll.setContent(secondPlayerPane);
            }
        }
        if (who_attac == 1){
            attac_first.setVisible(true); // Подписываем кто атакует (в будущем можно это оптимизировать и переместить в функцию бита)
            attac_second.setVisible(false);
        }else{
            attac_second.setVisible(true);
            attac_first.setVisible(false);
        }
        add2.setDisable(true); // Первый игрок получил достаточное кол-во карт, кнопка ему пока что не нужна
    }

    /**
     * Переносит карту игрока в одно из полей (атака защита)
     */
    public void addCardOnTable(CardController card) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Card.fxml"));
    	Pane newPane = loader.load();
    	CardController cardController = loader.getController();
        String str_card = card.getNominal() + "_" + card.getMast(); // Требует оптимизации
    	cardController.setCardParameters(str_card, this, newPane);
        if (has_first.contains(str_card)) { // Проверяет кому принадлежит карта
            if (who_attac == 1)
                deskAttackCardPane.add(newPane, deskAttackCardPane.getChildren().size(), 0);
            else
                deskAnswerCardPane.add(newPane, deskAnswerCardPane.getChildren().size(), 0);
            has_first.remove(str_card);
            if (has_first.size() < 6) // Включает кнопку добавить карты
                add1.setDisable(false);
        }else{
            if (who_attac == 2){
                deskAttackCardPane.add(newPane, deskAttackCardPane.getChildren().size(), 0);
            }else{
                deskAnswerCardPane.add(newPane, deskAnswerCardPane.getChildren().size(), 0);
            }
            has_second.remove(str_card);
            if (has_second.size() < 6) // Включает кнопку добавить карты
                add2.setDisable(false);
        }
    }

    /**
     * Генерирует рандомно кто ходит первым в самом начале игры
     */
    public static void attac_is_when_start(){
        Random random = new Random();
        who_attac = random.nextInt(2) + 1; // Записали в глобальную переменную
    }

    /**
     * Генерирует рандомную карту по шаблону и записывает её в использованную
     * Если карт не осталось, то он возращает пустую строчку и выключает 2 кноки и коллоду карт
     * НОМИНАЛ_МАСТЬ (так выглядит карта)
     */
    private String availableCard(){
        String[] nominal_mas = new String[]{"6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз"}; // Все номиналы
        String[] mast_mas = new String[]{"♣️БУБИ", "♦️ЧЕРВИ", "♥️КРЕСТИ", "♠️ПИКИ"}; // Все масти

        Random random = new Random();
        String card = "";
        while (already_use.size() < 36) {
            int nominalIndex = random.nextInt(nominal_mas.length);
            int mastIndex = random.nextInt(mast_mas.length);
            card = nominal_mas[nominalIndex] + "_" + mast_mas[mastIndex]; // Рандомно собрали карту
            if (!already_use.contains(card)) { // Нашли карту -> закончили
                already_use.add(card);
                break;
            }
        }
        if (already_use.size() < 36) // Написали сколько карт осталось
            left_cards.setText(String.valueOf(36-already_use.size()));
        else{ // Не отсалось карт -> выключили всё что к ним относится
            rubashka_koloda.setVisible(false);
            left_cards.setVisible(false);
            add1.setVisible(false);
            add2.setVisible(false);
        }
        return card;
    }
}
