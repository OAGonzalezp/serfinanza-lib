package com.bancoserfinanza.bookbank.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ActionModel implements Serializable {

    private String icon;
    private String color;
    private String tooltip;

    public ActionModel() {
    }

    public ActionModel(String icon, String color, String tooltip) {
        this.icon = icon;
        this.color = color;
        this.tooltip = tooltip;
    }
}
