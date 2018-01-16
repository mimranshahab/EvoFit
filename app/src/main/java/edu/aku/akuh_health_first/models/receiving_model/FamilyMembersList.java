package edu.aku.akuh_health_first.models.receiving_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamilyMembersList
{

@SerializedName("CardNumber")
@Expose
private String cardNumber;
@SerializedName("_token")
@Expose
private String token;
@SerializedName("MemberURN")
@Expose
private String memberURN;
@SerializedName("MembershipTypeID")
@Expose
private String membershipTypeID;
@SerializedName("MembershipTypeDescription")
@Expose
private String membershipTypeDescription;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("FirstName")
@Expose
private String firstName;
@SerializedName("MiddleName")
@Expose
private String middleName;
@SerializedName("LastName")
@Expose
private String lastName;
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
@SerializedName("CardTypeID")
@Expose
private String cardTypeID;
@SerializedName("CardTypeDescription")
@Expose
private String cardTypeDescription;
@SerializedName("MotherName")
@Expose
private String motherName;
@SerializedName("SequenceNumber")
@Expose
private String sequenceNumber;
@SerializedName("strBirthDate")
@Expose
private String strBirthDate;
@SerializedName("IsInpersonRegister")
@Expose
private String isInpersonRegister;
@SerializedName("AttachmentList")
@Expose
private String attachmentList;
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
private final static long serialVersionUID = -5265383219118010918L;

public String getCardNumber() {
return cardNumber;
}

public void setCardNumber(String cardNumber) {
this.cardNumber = cardNumber;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public String getMemberURN() {
return memberURN;
}

public void setMemberURN(String memberURN) {
this.memberURN = memberURN;
}

public String getMembershipTypeID() {
return membershipTypeID;
}

public void setMembershipTypeID(String membershipTypeID) {
this.membershipTypeID = membershipTypeID;
}

public String getMembershipTypeDescription() {
return membershipTypeDescription;
}

public void setMembershipTypeDescription(String membershipTypeDescription) {
this.membershipTypeDescription = membershipTypeDescription;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getMiddleName() {
return middleName;
}

public void setMiddleName(String middleName) {
this.middleName = middleName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
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

public String getMotherName() {
return motherName;
}

public void setMotherName(String motherName) {
this.motherName = motherName;
}

public String getSequenceNumber() {
return sequenceNumber;
}

public void setSequenceNumber(String sequenceNumber) {
this.sequenceNumber = sequenceNumber;
}

public String getStrBirthDate() {
return strBirthDate;
}

public void setStrBirthDate(String strBirthDate) {
this.strBirthDate = strBirthDate;
}

public String getIsInpersonRegister() {
return isInpersonRegister;
}

public void setIsInpersonRegister(String isInpersonRegister) {
this.isInpersonRegister = isInpersonRegister;
}

public String getAttachmentList() {
return attachmentList;
}

public void setAttachmentList(String attachmentList) {
this.attachmentList = attachmentList;
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