package com.fsm.backend.Exceptions;

public class IllegalDirectoryNameException extends RuntimeException {

    public IllegalDirectoryNameException() {
        super("Directory name must not contain .");
    }

}