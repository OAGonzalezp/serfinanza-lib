package com.bancoserfinanza.models.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class BookRequest implements Serializable {

    @Valid
    @NotNull(message = "idlib can not null")
    private String idlib;

    @Valid
    @NotNull(message = "bookName can not null")
    private String bookName;

}
