package dev.melik.patikabootcampproject.domain.security;

public class SecurityConstant {
    public static final String SECRET = "xrN2wrFwZr";
    public static final long EXPIRATION_TIME = 30*24*60*60*1000L; // 30 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
