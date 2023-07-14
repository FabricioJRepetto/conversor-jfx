package com.example.conversorfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.Arrays;

public class MainController {
    @FXML
    private TextField divisa_input;
    @FXML
    private Label divisa_result_label;
    @FXML
    private Label divisa_summary_label;
    @FXML
    private Label divisa_1;
    @FXML
    private Label divisa_2;
    @FXML
    private MenuButton divisa_from;
    @FXML
    private MenuButton divisa_to;

    private String from;
    private String to;

    @FXML
    protected void checkInput(KeyEvent event) {
        if (event.getCharacter().matches("\\D")) {
            event.consume();
            divisa_input.setStyle("-fx-border-color: red");
        } else {
            divisa_input.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    protected void divisaFromMenu(ActionEvent event) {
        MenuItem menuOption = (MenuItem) event.getSource();
        String text = menuOption.getText();
        String code = text.substring(0, 3); // MenuItem text
        divisa_from.setText(text);
        from = code;
    }
    @FXML
    protected void divisaToMenu(ActionEvent event) {
        MenuItem menuOption = (MenuItem) event.getSource();
        String text = menuOption.getText();
        String code = text.substring(0, 3);
        divisa_to.setText(text);
        to = code;
    }
    @FXML
    protected void convertButtonClick() {
        try {
            Double amount = (double) Integer.parseInt(divisa_input.getText());
            if (amount > 0) {
                System.out.println(amount);

                divisa_summary_label.setText("$" + divisa_input.getText() + " " + from + " =");
                divisa_result_label.setText("$X.XXX.xx" + " " + to);
                divisa_1.setText("1 " + from + " = X " + to);
                divisa_2.setText("1 " + to + " = X " + from);

            } else System.out.println("Ingrese un monto inicial");

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
