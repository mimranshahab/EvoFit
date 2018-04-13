package edu.aku.family_hifazat.enums;

public enum BaseURLTypes {
    PACS_VIEWER,
    AHFA_BASE_URL,
    PACS_IMAGE_DOWNLOAD,
    PAYMENT_GATEWAY_URL;


    public String canonicalForm() {
        return this.name().toLowerCase();
    }

    public static BaseURLTypes fromCanonicalForm(String canonical) {
        return (BaseURLTypes) valueOf(BaseURLTypes.class, canonical.toUpperCase());
    }
}