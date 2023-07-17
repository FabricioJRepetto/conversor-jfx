package com.example.conversorfx.models;

import java.util.Objects;

public class Longitud extends TipoUnidad {

    public Longitud() {
        super();
        super.unidades.put("Kilómetros", 1000.0);
        super.unidades.put("Metros", 1.0);
        super.unidades.put("Centímetros", 0.01);
        super.unidades.put("Milímetros", 0.001);
        super.unidades.put("Micrómetros", 0.0000001);
        super.unidades.put("Nanómetros", 0.0000000001);
        super.unidades.put("Millas", 1609.34);
        super.unidades.put("Yardas", 0.9144);
        super.unidades.put("Pies", 0.3048);
        super.unidades.put("Pulgadas", 0.0254);
//        super.unidades.put("Kilómetros", 0.001);
//        super.unidades.put("Metros", 1.0);
//        super.unidades.put("Centímetros", 100.0);
//        super.unidades.put("Milímetros", 1000.0);
//        super.unidades.put("Micrómetros", 1000000.0);
//        super.unidades.put("Nanómetros", 1000000000.0);
//        super.unidades.put("Millas", 0.000621371);
//        super.unidades.put("Yardas", 1.09361);
//        super.unidades.put("Pies", 3.28084);
//        super.unidades.put("Pulgadas", 39.3701);
    }

    @Override
    public double convert(double value, String from, String to) {
        // Convertir a metros
        double base = value * this.unidades.get(from);

        if (!Objects.equals(to, "Metros")) {
            return base / this.unidades.get(to);
        }
        return base;
    }

    @Override
    public String format(double value) {
        if (value >= 1) {
            return String.format("%.2f", value);
        } else if (value < 0.01) {
            return String.format("%.5f", value);
        } else return String.format("%.3f", value);
    }
}
