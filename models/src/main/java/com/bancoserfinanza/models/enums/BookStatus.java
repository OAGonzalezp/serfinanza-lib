package com.bancoserfinanza.models.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BookStatus {
	ACTIVE("Active"),
	INACTIVE("Inactive"),
	TAKEN("Taken"),
	DELIVERED("Delivered");

	private final String name;

    private BookStatus(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }

}
