package edu.aku.akuh_health_first.models.receiving_model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.annotations.Ignore;

public class
UserDetailModel implements Serializable {
    transient boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @SerializedName("CardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("_token")
    @Expose
    private Object token;
    @SerializedName("MemberURN")
    @Expose
    private Object memberURN;
    @SerializedName("MembershipTypeID")
    @Expose
    private String membershipTypeID;
    @SerializedName("MembershipTypeDescription")
    @Expose
    private Object membershipTypeDescription;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FirstName")
    @Expose
    private Object firstName;
    @SerializedName("MiddleName")
    @Expose
    private Object middleName;
    @SerializedName("LastName")
    @Expose
    private Object lastName;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("GenderDescription")
    @Expose
    private String genderDescription;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("RelationshipID")
    @Expose
    private String relationshipID;
    @SerializedName("RelationshipDescription")
    @Expose
    private String relationshipDescription;
    @SerializedName("OtherRelationshipID")
    @Expose
    private String otherRelationshipID;
    @SerializedName("NationalityID")
    @Expose
    private String nationalityID;
    @SerializedName("NationalityDescription")
    @Expose
    private String nationalityDescription;
    @SerializedName("NationalityTypeID")
    @Expose
    private Object nationalityTypeID;
    @SerializedName("NationalityTypeDescription")
    @Expose
    private Object nationalityTypeDescription;
    @SerializedName("CNICNumber")
    @Expose
    private String cNICNumber;
    @SerializedName("PassportNumber")
    @Expose
    private String passportNumber;
    @SerializedName("EmailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("CellPhoneNumber")
    @Expose
    private String cellPhoneNumber;
    @SerializedName("LandlineNumber")
    @Expose
    private String landlineNumber;
    @SerializedName("CurrentAddress")
    @Expose
    private String currentAddress;
    @SerializedName("CurrentCity")
    @Expose
    private String currentCity;
    @SerializedName("CurrentCountryID")
    @Expose
    private String currentCountryID;
    @SerializedName("CurrentCountryDescription")
    @Expose
    private String currentCountryDescription;
    @SerializedName("CompleteCurrentAddress")
    @Expose
    private String completeCurrentAddress;
    @SerializedName("PermanentAddress")
    @Expose
    private String permanentAddress;
    @SerializedName("PermanentCity")
    @Expose
    private String permanentCity;
    @SerializedName("PermanentCountryID")
    @Expose
    private String permanentCountryID;
    @SerializedName("PermanentCountryDescription")
    @Expose
    private String permanentCountryDescription;
    @SerializedName("CompletePermanentAddress")
    @Expose
    private String completePermanentAddress;
    @SerializedName("MRNumber")
    @Expose
    private String mRNumber;
    @SerializedName("ProfileImage")
    @Expose
    private String profileImage;
    @SerializedName("ProfileImagePath")
    @Expose
    private Object profileImagePath;
    @SerializedName("SequenceNumber")
    @Expose
    private String sequenceNumber;
    @SerializedName("AllowAccToPortal")
    @Expose
    private Object allowAccToPortal;
    @SerializedName("AllowAccToPrivilege")
    @Expose
    private Object allowAccToPrivilege;
    @SerializedName("strBirthDate")
    @Expose
    private String strBirthDate;
    @SerializedName("AttachmentList")
    @Expose
    private Object attachmentList;
    @SerializedName("VisitDetail")
    @Expose
    private VisitDetail visitDetail;
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
    @SerializedName("RecordFound")
    @Expose
    private Object recordFound;
    @SerializedName("RecordMessage")
    @Expose
    private Object recordMessage;
    private final static long serialVersionUID = -9201088907195944131L;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getMemberURN() {
        return memberURN;
    }

    public void setMemberURN(Object memberURN) {
        this.memberURN = memberURN;
    }

    public String getMembershipTypeID() {
        return membershipTypeID;
    }

    public void setMembershipTypeID(String membershipTypeID) {
        this.membershipTypeID = membershipTypeID;
    }

    public Object getMembershipTypeDescription() {
        return membershipTypeDescription;
    }

    public void setMembershipTypeDescription(Object membershipTypeDescription) {
        this.membershipTypeDescription = membershipTypeDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getMiddleName() {
        return middleName;
    }

    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRelationshipID() {
        return relationshipID;
    }

    public void setRelationshipID(String relationshipID) {
        this.relationshipID = relationshipID;
    }

    public String getRelationshipDescription() {
        return relationshipDescription;
    }

    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }

    public String getOtherRelationshipID() {
        return otherRelationshipID;
    }

    public void setOtherRelationshipID(String otherRelationshipID) {
        this.otherRelationshipID = otherRelationshipID;
    }

    public String getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getNationalityDescription() {
        return nationalityDescription;
    }

    public void setNationalityDescription(String nationalityDescription) {
        this.nationalityDescription = nationalityDescription;
    }

    public Object getNationalityTypeID() {
        return nationalityTypeID;
    }

    public void setNationalityTypeID(Object nationalityTypeID) {
        this.nationalityTypeID = nationalityTypeID;
    }

    public Object getNationalityTypeDescription() {
        return nationalityTypeDescription;
    }

    public void setNationalityTypeDescription(Object nationalityTypeDescription) {
        this.nationalityTypeDescription = nationalityTypeDescription;
    }

    public String getCNICNumber() {
        return cNICNumber;
    }

    public void setCNICNumber(String cNICNumber) {
        this.cNICNumber = cNICNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentCountryID() {
        return currentCountryID;
    }

    public void setCurrentCountryID(String currentCountryID) {
        this.currentCountryID = currentCountryID;
    }

    public String getCurrentCountryDescription() {
        return currentCountryDescription;
    }

    public void setCurrentCountryDescription(String currentCountryDescription) {
        this.currentCountryDescription = currentCountryDescription;
    }

    public String getCompleteCurrentAddress() {
        return completeCurrentAddress;
    }

    public void setCompleteCurrentAddress(String completeCurrentAddress) {
        this.completeCurrentAddress = completeCurrentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentCountryID() {
        return permanentCountryID;
    }

    public void setPermanentCountryID(String permanentCountryID) {
        this.permanentCountryID = permanentCountryID;
    }

    public String getPermanentCountryDescription() {
        return permanentCountryDescription;
    }

    public void setPermanentCountryDescription(String permanentCountryDescription) {
        this.permanentCountryDescription = permanentCountryDescription;
    }

    public String getCompletePermanentAddress() {
        return completePermanentAddress;
    }

    public void setCompletePermanentAddress(String completePermanentAddress) {
        this.completePermanentAddress = completePermanentAddress;
    }

    public String getMRNumber() {
        return mRNumber;
    }

    public void setMRNumber(String mRNumber) {
        this.mRNumber = mRNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Object getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(Object profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Object getAllowAccToPortal() {
        return allowAccToPortal;
    }

    public void setAllowAccToPortal(Object allowAccToPortal) {
        this.allowAccToPortal = allowAccToPortal;
    }

    public Object getAllowAccToPrivilege() {
        return allowAccToPrivilege;
    }

    public void setAllowAccToPrivilege(Object allowAccToPrivilege) {
        this.allowAccToPrivilege = allowAccToPrivilege;
    }

    public String getStrBirthDate() {
        return strBirthDate;
    }

    public void setStrBirthDate(String strBirthDate) {
        this.strBirthDate = strBirthDate;
    }

    public Object getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(Object attachmentList) {
        this.attachmentList = attachmentList;
    }

    public VisitDetail getVisitDetail() {
        return visitDetail;
    }

    public void setVisitDetail(VisitDetail visitDetail) {
        this.visitDetail = visitDetail;
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

    public Object getRecordFound() {
        return recordFound;
    }

    public void setRecordFound(Object recordFound) {
        this.recordFound = recordFound;
    }

    public Object getRecordMessage() {
        return recordMessage;
    }

    public void setRecordMessage(Object recordMessage) {
        this.recordMessage = recordMessage;
    }
   }