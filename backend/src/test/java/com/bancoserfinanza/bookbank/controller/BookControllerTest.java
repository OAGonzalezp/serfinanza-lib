package com.bancoserfinanza.bookbank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bancoserfinanza.bookbank.service.BookService;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.request.FeedbackOptionAnswerRequest;
import com.bancoserfinanza.models.request.FeedbackQuestionRequest;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@EnableWebMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCustomerWithDescriptionNull() throws Exception {
        this.mockMvc.perform(post("/v0/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildRequest(TestType.NOT_DESCRIPTION)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        IsEqual.equalTo("Description can not null")));
    }

    private String buildRequest(TestType testType) throws JsonProcessingException {
        FeedbackOptionAnswerRequest option = new FeedbackOptionAnswerRequest();
        option.setAnswerDescription("Yes");

        FeedbackQuestionRequest question = new FeedbackQuestionRequest();
        question.setQuestionStr("Your will pass this challenge?");
        question.setAnswers(testType == TestType.ANSWER_OPTIONS_EMPTY ? new ArrayList<>() : Arrays.asList(option));

        BookRequest request = new BookRequest();

        return objectMapper.writeValueAsString(request);
    }

    private enum TestType {
        NOT_DESCRIPTION,
        EMPTY_QUESTIONS,
        ANSWER_OPTIONS_EMPTY
    }
}
