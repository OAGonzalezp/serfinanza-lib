package com.bancoserfinanza.bookbank.model;

import lombok.Getter;

import java.io.Serializable;
import java.time.temporal.Temporal;

@Getter
public class ColumnModel implements Serializable {
    private String header;
    private String property;
    private String type;
    private Class<?> klazz;

    public ColumnModel(String header, String property, Class klazz) {
        this.header = header;
        this.property = property;
        this.klazz = klazz;
        initType();
    }

    private void initType() {
        if (Temporal.class.isAssignableFrom(klazz)) {
            type = "date";
        }
        else if (klazz.isEnum()) {
            type = "enum";
        }
    }
}
