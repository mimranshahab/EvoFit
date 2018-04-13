package edu.aku.family_hifazat.models.wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {
    @Expose
    @SerializedName("bill_to_address_country")
    private String billToAddressCountry;
    @Expose
    @SerializedName("bill_to_address_city")
    private String billToAddressCity;
    @Expose
    @SerializedName("bill_to_address_line1")
    private String billToAddressLine1;
    @Expose
    @SerializedName("bill_to_email")
    private String billToEmail;
    @Expose
    @SerializedName("bill_to_surname")
    private String billToSurname;
    @Expose
    @SerializedName("bill_to_forename")
    private String billToForename;
    @Expose
    @SerializedName("card_cvn")
    private String cardCvn;
    @Expose
    @SerializedName("card_expiry_date")
    private String cardExpiryDate;
    @Expose
    @SerializedName("card_number")
    private String cardNumber;
    @Expose
    @SerializedName("card_type")
    private String cardType;
    @Expose
    @SerializedName("payment_method")
    private String paymentMethod;
    @Expose
    @SerializedName("unsigned_field_names")
    private String unsignedFieldNames;
    @Expose
    @SerializedName("signed_field_names")
    private String signedFieldNames;
    @Expose
    @SerializedName("signed_date_time")
    private String signedDateTime;
    @Expose
    @SerializedName("transaction_uuid")
    private String transactionUuid;
    @Expose
    @SerializedName("profile_id")
    private String profileId;
    @Expose
    @SerializedName("access_key")
    private String accessKey;
    @Expose
    @SerializedName("locale")
    private String locale;
    @Expose
    @SerializedName("amount")
    private String amount;
    @Expose
    @SerializedName("currency")
    private String currency;
    @Expose
    @SerializedName("transaction_type")
    private String transactionType;
    @Expose
    @SerializedName("reference_number")
    private String referenceNumber;
    @Expose
    @SerializedName("bill_to_address_postal_code")
    private String billToAddressPostalCode;
    @Expose
    @SerializedName("bill_to_address_state")
    private String billToAddressState;


    public String getBill_to_address_postal_code() {
        return billToAddressPostalCode;
    }

    public void setBill_to_address_postal_code(String bill_to_address_postal_code) {
        this.billToAddressPostalCode = bill_to_address_postal_code;
    }

    public String getBill_to_address_state() {
        return billToAddressState;
    }

    public void setBill_to_address_state(String bill_to_address_state) {
        this.billToAddressState = bill_to_address_state;
    }

    public String getBillToAddressCountry() {
        return billToAddressCountry;
    }

    public void setBillToAddressCountry(String billToAddressCountry) {
        this.billToAddressCountry = billToAddressCountry;
    }

    public String getBillToAddressCity() {
        return billToAddressCity;
    }

    public void setBillToAddressCity(String billToAddressCity) {
        this.billToAddressCity = billToAddressCity;
    }

    public String getBillToAddressLine1() {
        return billToAddressLine1;
    }

    public void setBillToAddressLine1(String billToAddressLine1) {
        this.billToAddressLine1 = billToAddressLine1;
    }

    public String getBillToEmail() {
        return billToEmail;
    }

    public void setBillToEmail(String billToEmail) {
        this.billToEmail = billToEmail;
    }

    public String getBillToSurname() {
        return billToSurname;
    }

    public void setBillToSurname(String billToSurname) {
        this.billToSurname = billToSurname;
    }

    public String getBillToForename() {
        return billToForename;
    }

    public void setBillToForename(String billToForename) {
        this.billToForename = billToForename;
    }

    public String getCardCvn() {
        return cardCvn;
    }

    public void setCardCvn(String cardCvn) {
        this.cardCvn = cardCvn;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getUnsignedFieldNames() {
        return unsignedFieldNames;
    }

    public void setUnsignedFieldNames(String unsignedFieldNames) {
        this.unsignedFieldNames = unsignedFieldNames;
    }

    public String getSignedFieldNames() {
        return signedFieldNames;
    }

    public void setSignedFieldNames(String signedFieldNames) {
        this.signedFieldNames = signedFieldNames;
    }

    public String getSignedDateTime() {
        return signedDateTime;
    }

    public void setSignedDateTime(String signedDateTime) {
        this.signedDateTime = signedDateTime;
    }

    public String getTransactionUuid() {
        return transactionUuid;
    }

    public void setTransactionUuid(String transactionUuid) {
        this.transactionUuid = transactionUuid;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
