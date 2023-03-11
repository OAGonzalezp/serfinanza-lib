package com.bancoserfinanza.bookbank.model;

import com.bancoserfinanza.bookbank.util.Utilities;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.CustomerResponse;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter {
    @Override
    public CustomerResponse getAsObject(FacesContext context, UIComponent component, String value) {
        return Utilities.convertResponse(value, CustomerResponse.class);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return Utilities.marshalRequest(value);
    }
}
