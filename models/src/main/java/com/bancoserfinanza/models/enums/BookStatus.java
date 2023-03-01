package com.bancoserfinanza.models.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BookStatus {
	ACTIVE("Activo"),
	INACTIVE("Inactivo"),
	TAKEN("Prestado"),
	DELIVERED("Entregado");

	private final String name;

    private BookStatus(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }

}
