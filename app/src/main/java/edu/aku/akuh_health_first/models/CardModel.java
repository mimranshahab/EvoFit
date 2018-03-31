package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class CardModel
        implements IsRecordFound {

    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;
    @Override
    public boolean isRecordFound() {
        return getRecordfound().equals("true");
    }

    public String getRecordmessage() {
        return recordmessage;
    }

    public void setRecordmessage(String recordmessage) {
        this.recordmessage = recordmessage;
    }

    public String getRecordfound() {
        return recordfound;
    }

    public void setRecordfound(String recordfound) {
        this.recordfound = recordfound;
    }

    @SerializedName("CardNumber")
    @Expose
    private Object cardNumber;
    @SerializedName("Password")
    @Expose
    private Object password;
    @SerializedName("ConfirmPassword")
    @Expose
    private Object confirmPassword;
    @SerializedName("CardEmailAddress")
    @Expose
    private Object cardEmailAddress;
    @SerializedName("CardCellPhoneNumber")
    @Expose
    private Object cardCellPhoneNumber;
    @SerializedName("MotherName")
    @Expose
    private Object motherName;
    @SerializedName("BirthDate")
    @Expose
    private Object birthDate;
    @SerializedName("CardHolderName")
    @Expose
    private Object cardHolderName;
    @SerializedName("CardCreationDateTime")
    @Expose
    private Object cardCreationDateTime;
    @SerializedName("CardIssueDateTime")
    @Expose
    private Object cardIssueDateTime;
    @SerializedName("CardExpiryDateTime")
    @Expose
    private Object cardExpiryDateTime;
    @SerializedName("RecordFound")
    @Expose
    private Boolean recordFound;
    @SerializedName("TempSMSCode")
    @Expose
    private Object tempSMSCode;
    @SerializedName("_token")
    @Expose
    private String token;
    private final static long serialVersionUID = -3272047034082274996L;

    public Object getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Object cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(Object confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Object getCardEmailAddress() {
        return cardEmailAddress;
    }

    public void setCardEmailAddress(Object cardEmailAddress) {
        this.cardEmailAddress = cardEmailAddress;
    }

    public Object getCardCellPhoneNumber() {
        return cardCellPhoneNumber;
    }

    public void setCardCellPhoneNumber(Object cardCellPhoneNumber) {
        this.cardCellPhoneNumber = cardCellPhoneNumber;
    }

    public Object getMotherName() {
        return motherName;
    }

    public void setMotherName(Object motherName) {
        this.motherName = motherName;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public Object getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(Object cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Object getCardCreationDateTime() {
        return cardCreationDateTime;
    }

    public void setCardCreationDateTime(Object cardCreationDateTime) {
        this.cardCreationDateTime = cardCreationDateTime;
    }

    public Object getCardIssueDateTime() {
        return cardIssueDateTime;
    }

    public void setCardIssueDateTime(Object cardIssueDateTime) {
        this.cardIssueDateTime = cardIssueDateTime;
    }

    public Object getCardExpiryDateTime() {
        return cardExpiryDateTime;
    }

    public void setCardExpiryDateTime(Object cardExpiryDateTime) {
        this.cardExpiryDateTime = cardExpiryDateTime;
    }

    public Boolean getRecordFound() {
        return recordFound;
    }

    public void setRecordFound(Boolean recordFound) {
        this.recordFound = recordFound;
    }

    public Object getTempSMSCode() {
        return tempSMSCode;
    }

    public void setTempSMSCode(Object tempSMSCode) {
        this.tempSMSCode = tempSMSCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
