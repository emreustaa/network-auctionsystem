package com.fsm.backend.Utils.Request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fsm.backend.Annotation.QueryParam;
import com.fsm.backend.Utils.ControllerUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParamHandler {

    public static Map<String, Object> prepareGetParams(String query) {
        Map<String, Object> result = new HashMap<>();
        //query = query == null ? "" : query;
        if (query == null) return result;
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public static Object getObjectFrom(String query, Class<?> paramType) {
        try {
            return ControllerUtils.getMapper()
                    .readValue(query, paramType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getParamType(Method method) {
        return method
                .getParameters()[0]
                .getAnnotation(QueryParam.class)
                .type();
    }

    public static Map<String, Object> getParams(HttpExchange httpExchange) {
        String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equals("GET")) {
            String query = httpExchange.getRequestURI().getQuery();
            return prepareGetParams(query);
        }
        return Collections.emptyMap();
    }

    private static Map<String, Object> preparePostParamMap(String body) {
        try {
            return ControllerUtils.getMapper().readValue(body, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static String getBody(HttpExchange httpExchange) {
        try {
            return new String(httpExchange.getRequestBody().readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
