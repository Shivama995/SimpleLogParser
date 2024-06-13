package com.logs.parser.exceptions;

public class InvalidQueryException extends Exception{
    public InvalidQueryException() {super("Invalid Query Provided.");}
    public InvalidQueryException(String message) {super(message);}
}
