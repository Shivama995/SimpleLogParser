package com.logs.parser.runtimeConfig;

public class Regex {
    public static final String QuotedStringPattern = "\"(?:[^\"\\\\]|\\\\.)*\"";
    public static final String NumberPattern = "\\d+(?:\\.\\d+)?";
    public static final String TimestampPattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(?:\\.\\d{6})?Z";
    public static final String IpAddressPattern = "(?:\\d{1,3}\\.){3}\\d{1,3}";
    public static final String UrlPattern = "(?:https?|ftp):\\/\\/[^\\s/$.?#].[^\\s]*";

    //    public static final String CombinedPattern = "(" + QuotedStringPattern + "|" + NumberPattern + "|" + TimestampPattern + "|" + IpAddressPattern + "|" + UrlPattern + ")";
    public static final String CombinedPattern = "\"([^\"]*)\"|(\\S+)";
}