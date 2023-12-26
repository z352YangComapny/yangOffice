package com.yangworld.app.common.jwt;

public class JwtProperties {
    public static String SECRET = "양소영그는신인가";
    public static int EXPIRATION_TIME = 1000*60*60*2;
    public static String TOKEN_PREFIX = "Bearer ";
    public static String ACC_HEADER_STRING = "Authorization";
    public static String REF_HEADER_STRING = "Refresh";
    public static int REF_EXPIRATION_TIME = 1000*60*60*24*14;
}