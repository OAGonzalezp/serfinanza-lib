package com.bancoserfinanza.models.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AutoCompleteSuggestionResponse implements Serializable {
    private List<AutoCompleteSuggestion> suggestions = new ArrayList();
    private Boolean moreAvailable = false;

    public AutoCompleteSuggestionResponse(List<AutoCompleteSuggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public void addSuggestion(AutoCompleteSuggestion suggestion) {
        this.suggestions.add(suggestion);
    }

}
