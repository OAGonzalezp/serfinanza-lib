package com.bancoserfinanza.bookbank.util;

import com.bancoserfinanza.models.response.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Utilities {
    public static <T>T unmarshalResponse(ResponseBody body, Class<? extends T> clazz) {

        ObjectMapper mapper = new ObjectMapper();
        DataResponse<T> response = new DataResponse<>();
        try {
            return mapper.readValue(body.string(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String marshalRequest(Object request)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
