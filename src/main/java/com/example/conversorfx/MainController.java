package com.example.conversorfx;

import com.example.conversorfx.models.Divisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainController {
    @FXML
    private TextField divisa_input;
    @FXML
    private MenuButton divisa_from;
    @FXML
    private MenuButton divisa_to;
    @FXML
    private Label divisa_summary_label;
    @FXML
    private Label divisa_result_label;
    @FXML
    private Label divisa_1;
    @FXML
    private Label divisa_2;

    @FXML
    private Label divisa_error_amount;
    @FXML
    private Label divisa_error_from;
    @FXML
    private Label divisa_error_to;

    private String from = null;
    private String to = null;
    private Double amount = null;
    private Map<String, Object> metodo = new HashMap<>();

    @FXML
    protected void divisaFromMenu(ActionEvent event) {
        MenuItem menuOption = (MenuItem) event.getSource();
        String text = menuOption.getText();
        String code = text.split(" - ")[0];

        divisa_from.setText(text); // MenuItem text
        from = code; // Conversion code
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
            this.clearLabels();
            String inputString = divisa_input.getText().replaceAll("[^\\d.]", "");

            if (inputString.length() > 0) {
                amount = Double.parseDouble(inputString);
                divisa_input.setText(amount.toString());
            }

            if (checkNoErrors()) {
                Divisa d = new Divisa();
                double res = d.convert(amount, from, to);

                divisa_summary_label.setText(d.format(amount) + " " + from + " =");
                divisa_result_label.setText(d.format(res) + " " + to);

                if (amount != 1.0) {
                    String aux = d.format(d.convert(1, from, to));
                    divisa_1.setText("1 " + from + " = " + aux + " " + to);
                }
                if (!Objects.equals(from, to)) {
                    String aux = d.format(d.convert(1, to, from));
                    (amount == 1.0 ? divisa_1 : divisa_2).setText("1 " + to + " = " + aux + " " + from);
                }
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

            if (!Objects.equals(from, to)) convertButtonClick();
        }
    }

    private void clearLabels() {
        // res
        divisa_summary_label.setText("");
        divisa_result_label.setText("");
        divisa_1.setText("");
        divisa_2.setText("");
        // errors
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
