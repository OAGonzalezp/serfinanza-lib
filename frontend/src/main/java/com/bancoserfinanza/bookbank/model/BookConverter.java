package com.bancoserfinanza.bookbank.model;

import com.bancoserfinanza.bookbank.util.Utilities;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.CustomerResponse;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@FacesConverter(value = "bookConverter")
public class BookConverter implements Converter {
    @Override
    public BookResponse getAsObject(FacesContext context, UIComponent component, String value) {
        return Utilities.convertResponse(value, BookResponse.class);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return Utilities.marshalRequest(value);
    }
}
