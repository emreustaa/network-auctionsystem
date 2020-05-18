package com.fsm.backend.Interfaces;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Utils.ControllerUtils;
import com.fsm.backend.Utils.MainUtils;
import com.fsm.backend.Utils.Request.Request;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public interface MyHttpHandler extends HttpHandler {

    @Override
    default void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        Request request = ControllerUtils.getRequest(httpExchange);
        String response = getResponseAsJson(request);
        httpExchange.sendResponseHeaders(200, response.length());
        MainUtils.writeResponse(httpExchange, response);
    }

    @Action()
    default String index() {
        return "index";
    }

    default String getResponseAsJson(Request request) {
        if (request.getAction().equals("index")) {
            System.out.println("index requested");
            return index();
        }
        String response = ControllerUtils.responseAsJsonString(request);
        System.out.println("SERVER RESPONSE:");
        System.out.println(response);
        return response;
    }

}
