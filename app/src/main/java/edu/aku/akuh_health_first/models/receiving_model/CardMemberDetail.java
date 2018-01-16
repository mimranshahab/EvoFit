package edu.aku.akuh_health_first.models.receiving_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.reactivestreams.Subscriber;

import java.util.List;

/**
 * Created by aqsa.sarwar on 1/16/2018.
 */

public class CardMemberDetail {

    @SerializedName("CardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("CardTypeID")
    @Expose
    private String cardTypeID;
    @SerializedName("CardTypeDescription")
    @Expose
    private String cardTypeDescription;
    @SerializedName("CardStatusID")
    @Expose
    private String cardStatusID;
    @SerializedName("CardHolderName")
    @Expose
    private String cardHolderName;
    @SerializedName("CardCreationDateTime")
    @Expose
    private String cardCreationDateTime;
    @SerializedName("CardIssueDateTime")
    @Expose
    private String cardIssueDateTime;
    @SerializedName("CardExpiryDateTime")
    @Expose
    private String cardExpiryDateTime;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("TempURL")
    @Expose
    private String tempURL;
    @SerializedName("InitialEmailSent")
    @Expose
    private String initialEmailSent;
    @SerializedName("TempSMSCode")
    @Expose
    private String tempSMSCode;
    @SerializedName("CellPhoneVerified")
    @Expose
    private String cellPhoneVerified;
    @SerializedName("EmailVerified")
    @Expose
    private String emailVerified;
    @SerializedName("PortalAccessStatus")
    @Expose
    private String portalAccessStatus;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ConfirmPassword")
    @Expose
    private String confirmPassword;
    @SerializedName("PasswordSetDateTime")
    @Expose
    private String passwordSetDateTime;
    @SerializedName("AllowAccToPortal")
    @Expose
    private String allowAccToPortal;
    @SerializedName("AllowAccToPrivilege")
    @Expose
    private String allowAccToPrivilege;
    @SerializedName("CardEmailAddress")
    @Expose
    private String cardEmailAddress;
    @SerializedName("CardCellPhoneNumber")
    @Expose
    private String cardCellPhoneNumber;
    @SerializedName("MotherName")
    @Expose
    private String motherName;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("RecordFound")
    @Expose
    private Boolean recordFound;
    @SerializedName("Subscriber")
    @Expose
    private Subscriber subscriber;
    @SerializedName("FamilyMembersList")
    @Expose
    private List<FamilyMembersList> familyMembersList = null;
    @SerializedName("_token")
    @Expose
    private String token;
    @SerializedName("LastFileDateTime")
    @Expose
    private String lastFileDateTime;
    @SerializedName("LastFileUser")
    @Expose
    private String lastFileUser;
    @SerializedName("LastFileTerminal")
    @Expose
    private String lastFileTerminal;
    @SerializedName("Active")
    @Expose
    private String active;
    private final static long serialVersionUID = -4253790918055442626L;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardTypeID() {
        return cardTypeID;
    }

    public void setCardTypeID(String cardTypeID) {
        this.cardTypeID = cardTypeID;
    }

    public String getCardTypeDescription() {
        return cardTypeDescription;
    }

    public void setCardTypeDescription(String cardTypeDescription) {
        this.cardTypeDescription = cardTypeDescription;
    }

    public String getCardStatusID() {
        return cardStatusID;
    }

    public void setCardStatusID(String cardStatusID) {
        this.cardStatusID = cardStatusID;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardCreationDateTime() {
        return cardCreationDateTime;
    }

    public void setCardCreationDateTime(String cardCreationDateTime) {
        this.cardCreationDateTime = cardCreationDateTime;
    }

    public String getCardIssueDateTime() {
        return cardIssueDateTime;
    }

    public void setCardIssueDateTime(String cardIssueDateTime) {
        this.cardIssueDateTime = cardIssueDateTime;
    }

    public String getCardExpiryDateTime() {
        return cardExpiryDateTime;
    }

    public void setCardExpiryDateTime(String cardExpiryDateTime) {
        this.cardExpiryDateTime = cardExpiryDateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTempURL() {
        return tempURL;
    }

    public void setTempURL(String tempURL) {
        this.tempURL = tempURL;
    }

    public String getInitialEmailSent() {
        return initialEmailSent;
    }

    public void setInitialEmailSent(String initialEmailSent) {
        this.initialEmailSent = initialEmailSent;
    }

    public String getTempSMSCode() {
        return tempSMSCode;
    }

    public void setTempSMSCode(String tempSMSCode) {
        this.tempSMSCode = tempSMSCode;
    }

    public String getCellPhoneVerified() {
        return cellPhoneVerified;
    }

    public void setCellPhoneVerified(String cellPhoneVerified) {
        this.cellPhoneVerified = cellPhoneVerified;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPortalAccessStatus() {
        return portalAccessStatus;
    }

    public void setPortalAccessStatus(String portalAccessStatus) {
        this.portalAccessStatus = portalAccessStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPasswordSetDateTime() {
        return passwordSetDateTime;
    }

    public void setPasswordSetDateTime(String passwordSetDateTime) {
        this.passwordSetDateTime = passwordSetDateTime;
    }

    public String getAllowAccToPortal() {
        return allowAccToPortal;
    }

    public void setAllowAccToPortal(String allowAccToPortal) {
        this.allowAccToPortal = allowAccToPortal;
    }

    public String getAllowAccToPrivilege() {
        return allowAccToPrivilege;
    }

    public void setAllowAccToPrivilege(String allowAccToPrivilege) {
        this.allowAccToPrivilege = allowAccToPrivilege;
    }

    public String getCardEmailAddress() {
        return cardEmailAddress;
    }

    public void setCardEmailAddress(String cardEmailAddress) {
        this.cardEmailAddress = cardEmailAddress;
    }

    public String getCardCellPhoneNumber() {
        return cardCellPhoneNumber;
    }

    public void setCardCellPhoneNumber(String cardCellPhoneNumber) {
        this.cardCellPhoneNumber = cardCellPhoneNumber;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getRecordFound() {
        return recordFound;
    }

    public void setRecordFound(Boolean recordFound) {
        this.recordFound = recordFound;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public List<FamilyMembersList> getFamilyMembersList() {
        return familyMembersList;
    }

    public void setFamilyMembersList(List<FamilyMembersList> familyMembersList) {
        this.familyMembersList = familyMembersList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastFileDateTime() {
        return lastFileDateTime;
    }

    public void setLastFileDateTime(String lastFileDateTime) {
        this.lastFileDateTime = lastFileDateTime;
    }

    public String getLastFileUser() {
        return lastFileUser;
    }

    public void setLastFileUser(String lastFileUser) {
        this.lastFileUser = lastFileUser;
    }

    public String getLastFileTerminal() {
        return lastFileTerminal;
    }

    public void setLastFileTerminal(String lastFileTerminal) {
        this.lastFileTerminal = lastFileTerminal;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}


