package com.bancoserfinanza.models.request;

import com.bancoserfinanza.models.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class FeedbackQuestionRequest implements Serializable {

    @Valid
    @NotNull(message = "Question description can not null")
    private String questionStr;

    @Valid
    @NotNull(message = "Question type can not null")
    private BookStatus type;

    private List<FeedbackOptionAnswerRequest> answers;
}
