package com.example.conversorfx;

import com.example.conversorfx.models.Divisa;
import com.example.conversorfx.models.Longitud;
import com.example.conversorfx.models.Temperatura;
import com.example.conversorfx.models.TipoUnidad;
import com.example.conversorfx.utils.AcceptsNegative;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;

public class MainController {
    @FXML
    private MenuButton method_menu;
    @FXML
    private TextField amount_input;
    @FXML
    private MenuButton from_menu;
    @FXML
    private MenuButton to_menu;
    @FXML
    private Label summary_label;
    @FXML
    private Label result_label;
    @FXML
    private Label unit_1;
    @FXML
    private Label unit_2;

    @FXML
    private Label error_amount_label;
    @FXML
    private Label error_from_label;
    @FXML
    private Label error_to_label;

    private String from = null;
    private String to = null;
    private Double amount = null;
    private String selected_method = "Divisas";
    private TipoUnidad m = new Divisa();

    @FXML
    protected void selectMethod(ActionEvent event) {
        MenuItem menuOption = (MenuItem) event.getSource();
        String text = menuOption.getText();

        method_menu.setText(text); // MenuItem text

        if (!Objects.equals(text, selected_method)) { // Clear all inputs
            clearLabels();
            clearInputs();

            selected_method = text;

            // TODO REFACTOR
            // ? Cambiar menuItems
            from_menu.getItems().forEach(this::change_options);
            to_menu.getItems().forEach(this::change_options);
        }

        // Cambiar métodos de clase
        if (Objects.equals(text, "Divisas")) {
            m = new Divisa();
        } else if (Objects.equals(text, "Longitudes")) {
            m = new Longitud();
        } else {
            m = new Temperatura();
        }

    }

    // TODO REFACTOR
    private final String[] divisa_opt = {
            "USD - Dólar",
            "ARS - Peso Argentino",
            "EUR - Euro",
            "GBP - Libras Esterlinas",
            "JPY - Yen Japonés",
            "KRW - Won Surcoreano"
    };
    // TODO REFACTOR
    private final String[] temp_opt = {
            "Fahrenheit",
            "Celsius",
            "Kelvin"
    };
    // TODO REFACTOR
    private final String[] long_opt = {
            "Kilometro",
            "Metro",
            "Centímetro",
            "Milimetro",
            "Micrómetro",
            "Nanómetro",
            "Milla",
            "Yarda",
            "Pies",
            "Pulgada"
    };
    // TODO REFACTOR
    private void change_options(MenuItem m) {
        m.setVisible(true);
        int index = Integer.parseInt(m.getId());

        if (Objects.equals(selected_method, "Divisas")) {
            if (index < 6) {
                m.setText(divisa_opt[index]);
            } else {
                m.setVisible(false);
            }
        } else if (Objects.equals(selected_method, "Longitudes")) {
            if (index < 10) {
                m.setText(long_opt[index]);
            } else {
                m.setVisible(false);
            }
        } else {
            if (index < 3) {
                m.setText(temp_opt[index]);
            } else {
                m.setVisible(false);
            }
        }
    }

    @FXML
    protected void fromUnitMenuHandler(ActionEvent event) {
        MenuItem menuOption = (MenuItem) event.getSource();
        String text = menuOption.getText();
        String code = text.split(" - ")[0];

        from_menu.setText(text); // MenuItem text
        from = code; // Conversion code
    }
    @FXML
    protected void toUnitMenuHandler(ActionEvent event) {
        MenuItem menuOption = (MenuItem) event.getSource();
        String text = menuOption.getText();
        String code = text.split(" - ")[0];

        to_menu.setText(text);
        to = code;
    }
    @FXML
    protected void conversion() {
        try {
            this.clearLabels();
            String inputString = amount_input.getText().replaceAll(AcceptsNegative.check(selected_method)
                    ?"[^\\d.-]":"[^\\d.]", "");

            if (inputString.length() > 0) {
                amount = Double.parseDouble(inputString);
                amount_input.setText(amount.toString());
            }

            if (checkNoErrors()) {
                double res = m.convert(amount, from, to);

                summary_label.setText(m.format(amount) + " " + from + " =");
                result_label.setText(m.format(res) + " " + to);

                if (amount != 1.0) {
                    String aux = m.format(m.convert(1, from, to));
                    unit_1.setText("1 " + from + " = " + aux + " " + to);
                }
                if (!Objects.equals(from, to)) {
                    String aux = m.format(m.convert(1, to, from));
                    (amount == 1.0 ? unit_1 : unit_2).setText("1 " + to + " = " + aux + " " + from);
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void swapCurrencies() {
        if (from != null && to != null) {
            String code_aux = from;
            String text_aux = from_menu.getText();

            from_menu.setText(to_menu.getText());
            to_menu.setText(text_aux);

            from = to;
            to = code_aux;

            if (!Objects.equals(from, to)) conversion();
        }
    }

    private void clearInputs() {
        amount_input.setText("");
        from_menu.setText("Selecciona unidad");
        to_menu.setText("Selecciona unidad");
    }

    private void clearLabels() {
        // res
        summary_label.setText("");
        result_label.setText("");
        unit_1.setText("");
        unit_2.setText("");
        // errors
        error_amount_label.setText("");
        error_from_label.setText("");
        error_to_label.setText("");
    }

    private boolean checkNoErrors() {
        boolean flag = true;

        if (from == null) {
            error_from_label.setText("divisa necesaria");
            flag = false;
        }
        if (to == null) {
            error_to_label.setText("divisa necesaria");
            flag = false;
        }
        if (amount == null) {
            error_amount_label.setText("Ingrese un monto inicial");
            flag = false;
        }
        // else if (!AcceptsNegative.check(selected_method) && amount <= 0) {
        //     divisa_error_amount.setText("Monto debe ser mayor a 0");
        //     flag = false;
        // }
        return flag;
    }
}