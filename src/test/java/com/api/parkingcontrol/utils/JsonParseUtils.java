package com.api.parkingcontrol.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParseUtils {

    public static <T> String asJsonString(T dto) {

        ObjectMapper mapper = new ObjectMapper();
        String result = "";

        try {
            result = mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
