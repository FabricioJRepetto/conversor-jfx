package com.example.conversorfx.models;

import java.text.NumberFormat;
import java.util.*;

public class Divisa extends TipoUnidad {
//    private final Map<String, Double> unidades = new HashMap<>();

    public Divisa() {
        super();
        super.unidades.put("USD", 1.0);
        super.unidades.put("ARS", 0.0037); //TODO cambiar a 0.0019
        super.unidades.put("EUR", 1.1235);
        super.unidades.put("GBP", 1.3093);
        super.unidades.put("JPY", 0.0072);
        super.unidades.put("KRW", 0.000789);
    }

    public void refreshExchanges() {
        // TODO API call
        // API http://api.exchangeratesapi.io/v1/latest?access_key=2ab9bcc2c716f02242742cff0819960e&symbols=USD,ARS,EUR,GBP,JPY,KRW
        // ! Base EURO
    }

    @Override
    public double convert(double value, String from, String to) {
        // convertir a usd
        double base = value * unidades.get(from);

        if (!Objects.equals(to, "USD")) {
            // convertir de base a moneda final
            return base / unidades.get(to);
        }
        return base;
    }

    @Override
    public String format(double value) {
        if (value >= 0.01) {
            double correctDecimals = Double.parseDouble(String.format("%.2f", value));
            String correctFormat = NumberFormat.getNumberInstance(Locale.US).format(correctDecimals);

            return "$"+correctFormat;
        } else {
        // TODO revisar cantidad de 0 después del punto?
            return "$" + String.format("%.5f", value);
        }
    }
}
