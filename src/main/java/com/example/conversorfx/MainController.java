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

    @FXML
    private Label divisa_error_amount;

    @FXML
    private Label divisa_error_from;

    @FXML
    private Label divisa_error_to;


    private String from = null;
    private String to = null;
    private Double amount = null;


    @FXML
    protected void checkInput(InputMethodEvent event) {
        String aux = event.getCommitted();
        System.out.println(event.getComposed());
        System.out.println(aux);

        if (aux.matches("\\D")) {
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
            this.clearError();

            String inputString = divisa_input.getText();

            if (inputString.length() > 0) {
                amount = (double) Integer.parseInt(inputString.replaceAll("\\D", ""));
                divisa_input.setText(amount.toString());
            }

            if (checkNoErrors()) {
                System.out.println(amount);

                divisa_summary_label.setText("$" + divisa_input.getText() + " " + from + " =");
                divisa_result_label.setText("$X.XXX.xx" + " " + to);
                divisa_1.setText("1 " + from + " = X " + to);
                divisa_2.setText("1 " + to + " = X " + from);
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void swapCurrencies() {
        if (from != null && to != null) {
            String code_aux = from;
            String text_aux = divisa_from.getText();

            divisa_from.setText(divisa_to.getText());
            divisa_to.setText(text_aux);

            from = to;
            to = code_aux;

            System.out.println("from " + from + ", to " + to);
        }
    }

    private void clearError() {
        divisa_error_amount.setText("");
        divisa_error_from.setText("");
        divisa_error_to.setText("");
    }

    private boolean checkNoErrors() {
        boolean flag = true;

        if (from == null) {
            divisa_error_from.setText("divisa necesaria");
            flag = false;
        }
        if (to == null) {
            divisa_error_to.setText("divisa necesaria");
            flag = false;
        }
        if (amount == null) {
            divisa_error_amount.setText("Ingrese un monto inicial");
            flag = false;
        } else if (amount <= 0) {
            divisa_error_amount.setText("Monto debe ser mayor a 0");
            flag = false;
        }
        return flag;
    }
}
