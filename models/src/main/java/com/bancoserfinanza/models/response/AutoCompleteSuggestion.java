package com.bancoserfinanza.models.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AutoCompleteSuggestion implements Serializable {
    private String value;
    private String label;
    private Object rawValue;

    public AutoCompleteSuggestion setLabel(String label) {
        this.label = label;
        return this;
    }

    public AutoCompleteSuggestion setRawValue(Object rawValue) {
        this.rawValue = rawValue;
        return this;
    }

    public AutoCompleteSuggestion setValue(String value) {
        this.value = value;
        return this;
    }
}
