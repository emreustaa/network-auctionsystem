package com.fsm.backend.Objects.Message;

public class Message {

    private Event event;
    private Command command;
    private Error error;
    private Response response;

    public Message() {}

    public Event getEvent() {
        return event;
    }

    public Command getCommand() {
        return command;
    }

    public Error getError() {
        return error;
    }

    public Response getResponse() {
        return response;
    }

    public Message setResponse(Response response) {
        this.response = response;
        return this;
    }

    public Message setEvent(Event event) {
        this.event = event;
        return this;
    }

    public Message setCommand(Command command) {
        this.command = command;
        return this;
    }

    public Message setError(Error error) {
        this.error = error;
        return this;
    }

    public boolean isError() {
        return error != null;
    }

    public boolean isEvent() {return event != null;}

    public boolean isCommand() {return command != null;}

}
