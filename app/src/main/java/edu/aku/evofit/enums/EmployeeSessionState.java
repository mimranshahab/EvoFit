package edu.aku.evofit.enums;

public enum EmployeeSessionState {
    ENROLLED,
    SCHEDULED,
    INPROGRESS,
    CLOSED;

    public String canonicalForm() {
        return this.name().toLowerCase();
    }

    public static EmployeeSessionState fromCanonicalForm(String canonical) {
        return (EmployeeSessionState) valueOf(EmployeeSessionState.class, canonical.toUpperCase());
    }
}