package com.logs.parser.exceptions;

public class LogFileDoesNotExistException extends Exception {
    public LogFileDoesNotExistException() {super("Log File Does Not Exist! Please Upload.");}
    public LogFileDoesNotExistException(String message) {super(message);}
}
