package com.bancoserfinanza.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.bancoserfinanza.models.response.DataResponse;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCodes {
    OK(1, null),
    NOK_BOOK_NOT_CREATED(1001, "No se pudo crear el libro"),
    NOK_BOOK_NOT_FOUND(1002, "No se encontró el libro"),
    NOK_BOOK_TAKEN(1003, "El libro se encuentra prestado"),
    NOK_CUSTOMER_NOT_CREATED(2001, "No se pudo crear el clioente"),
    NOK_LOAN_BOOK_NOT_CREATED(3001, "No se pudo crear el registro de prestamo libro"),
    NOK_PALINDROME_BOOK_NAME(1004, "Los libros palíndromos solo se pueden utilizar en la biblioteca"),
    NOK_INVALID_DELIVERY_DATE(1003, "Fecha de entrega no valida");

    private DataResponse data = null;

    private int code;
    private String message;

    ResponseCodes(Integer code, String msg) {
        data = new DataResponse(code, msg);
        this.code = code;
        this.message = msg;
    }

    @JsonValue
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final DataResponse value() {
        return data;
    }

}
