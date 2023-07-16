package com.example.conversorfx.models;

import java.util.Objects;

public class Longitud extends TipoUnidad {

    public Longitud() {
        super();
        super.unidades.put("Kilómetros", 1000.0);
        super.unidades.put("Metros", 1.0);
        super.unidades.put("Centímetros", 0.01);
        super.unidades.put("Milímetros", 0.001);
        super.unidades.put("Micrómetros", 0.000001);
        super.unidades.put("Nanómetros", 0.000000001);
        super.unidades.put("Millas", 0.000621371);
        super.unidades.put("Yardas", 1.09361);
        super.unidades.put("Pies", 3.28084);
        super.unidades.put("Pulgadas", 39.3701);
    }

    @Override
    public double convert(double value, String from, String to) {

        if (Objects.equals(to, "Metro")) {
            return value * this.unidades.get(from);
        } else {
            double base = value * this.unidades.get(from);
            return base / this.unidades.get(to);
        }
    }

    @Override
    public String format(double value) {
        return String.format("%.2f", value);
    }
}
