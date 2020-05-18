package com.fsm.backend.Utils.Request;

import com.fsm.backend.Enums.TYPE;
import com.sun.net.httpserver.HttpHandler;

import java.util.Map;

public class Request {

    private String action;
    private Map<String, Object> params;
    private Class<? extends HttpHandler> controller;
    private TYPE type = TYPE.GET;
    private String body;

    public String getAction() {
        return action;
    }


    public Map<String, Object> getParams() {
        return params;
    }

    public Class<? extends HttpHandler> getController() {
        return controller;
    }

    @Override
    public String toString() {
        return "Request{" +
                "action='" + action + '\'' +
                ", params=" + params +
                ", controller=" + controller.getSimpleName() +
                '}';
    }

    public TYPE getType() {
        return type;
    }

    public Request setType(TYPE type) {
        this.type = type;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Request setBody(String body) {
        this.body = body;
        return this;
    }

    public Request setAction(String action) {
        this.action = action;
        return this;
    }

    public Request setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public Request setController(Class<? extends HttpHandler> controller) {
        this.controller = controller;
        return this;
    }
}
