package com.logs.parser.exceptions;

public class WhiteSpaceNotAllowedException extends Exception{

    public WhiteSpaceNotAllowedException() {super("White Spaces not allowed");}
    public WhiteSpaceNotAllowedException(String message) {super(message);}
}
