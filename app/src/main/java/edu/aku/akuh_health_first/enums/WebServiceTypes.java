package edu.aku.akuh_health_first.enums;

public enum WebServiceTypes {
    ONLY_TOKEN,
    TOKEN_AND_BEARER,
    THIRD;


    public String canonicalForm() {
        return this.name().toLowerCase();
    }

    public static WebServiceTypes fromCanonicalForm(String canonical) {
        return (WebServiceTypes) valueOf(WebServiceTypes.class, canonical.toUpperCase());
    }
}