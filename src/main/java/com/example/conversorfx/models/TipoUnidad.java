package com.example.conversorfx.models;

import java.util.Map;

public abstract class TipoUnidad {
    Map<String, Double> unidades;

    /**
     * Convierte un valor de tipo X a tipo Y.
     *
     * @param value Original amount to convert.
     * @param from Original amount type.
     * @param to Conversion target type.
     *
     * @return Returns the conversion result in double type.
     */
    public abstract double convert(double value, String from, String to);

    /**
     * Transforma un valor numérico al formato adecuado para el tipo de unidad.
     * Ej. divisa: 3000.0 -> $3,000.00
     *
     * @param value Valor original.
     *
     * @return String del valor original formateado.
     */
    public abstract String format(double value);
}
