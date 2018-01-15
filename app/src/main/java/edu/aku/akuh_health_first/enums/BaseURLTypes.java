package edu.aku.akuh_health_first.enums;

public enum BaseURLTypes {
    PACS,
    AHFA,
    THIRD;


    public String canonicalForm() {
        return this.name().toLowerCase();
    }

    public static BaseURLTypes fromCanonicalForm(String canonical) {
        return (BaseURLTypes) valueOf(BaseURLTypes.class, canonical.toUpperCase());
    }
}