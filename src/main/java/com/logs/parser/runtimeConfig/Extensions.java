package com.logs.parser.runtimeConfig;

public class Extensions {

    public static boolean IsNull(String arg)
    {
        return arg == null;
    }


    public static String[] split(String arg, String identifier, boolean trimWhiteSpaces)
    {
        if(trimWhiteSpaces) return arg.split("\\s*" + identifier + "\\s*");
        return arg.split(identifier);
    }
}
