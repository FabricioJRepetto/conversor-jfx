package com.example.conversorfx.utils;

import com.dlsc.formsfx.model.validators.RegexValidator;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class AcceptsNegative {
    private static final String lista = "Temperaturas";

    public static boolean check(String s) {
        return lista.contains(s);
    }
}
