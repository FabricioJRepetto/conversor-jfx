package com.example.conversorfx.models;

import java.util.Objects;

public class Temperatura extends TipoUnidad {
    public Temperatura() {
        super.unidades.put("Fahrenheit", 0.0);
        super.unidades.put("Celsius", 0.0);
        super.unidades.put("Kelvin", 0.0);
    }

    @Override
    public double convert(double value, String from, String to) {
        String K = "Kelvin";
        String F = "Fahrenheit";
        String C = "Celsius";

        if (Objects.equals(from, C) && Objects.equals(to, F)) {
            // ! Celsius -> Fahrenheit
            return (value * 9/5) + 32;

        } else if (Objects.equals(from, C) && Objects.equals(to, K)) {
            // ? Celsius -> Fahrenheit
            return value + 273.15;

        } else if (Objects.equals(from, K) && Objects.equals(to, C)) {
            // ? Kelvin -> Celsius
            return value - 273.15;

        } else if (Objects.equals(from, K) && Objects.equals(to, F)) {
            // * Kelvin -> Fahrenheit
            return (value - 273.15) * 9/5 + 32;

        } else if (Objects.equals(from, F) && Objects.equals(to, C)) {
            // ! Fahrenheit -> Celsius
            return (value - 32) * 5/9;

        } else if (Objects.equals(from, F) && Objects.equals(to, K)) {
            // * Fahrenheit -> Kelvin
            return (value - 32) * 5/9 + 273.15;

        } else {
            // same
            return value;
        }
    }

    @Override
    public String format(double value) {
        return String.format("%.1f", value) + "º";
    }
}
