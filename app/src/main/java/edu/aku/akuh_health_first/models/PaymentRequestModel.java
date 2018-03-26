package edu.aku.akuh_health_first.models;

import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.util.UUID;

import edu.aku.akuh_health_first.managers.DateManager;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by aqsa.sarwar on 2/23/2018.
 */

public class PaymentRequestModel {

    private String Signature;
    private String AccessKey = "5584828bcca73288a5b758619b9e299b";
    private String ProfileID = "3700ABA3-08F4-4D24-9730-08E949C82044";
    private String TransactionUUID = UUID.randomUUID().toString();
//    private String TransactionUUID = "HFM-636558589041890204";
    private String ReferenceNo = "168-" + TransactionUUID;

    private String SignedFieldNames = "reference_number,transaction_type,currency,amount,locale,access_key," +
            "profile_id,transaction_uuid,signed_date_time,signed_field_names,unsigned_field_names,payment_method," +
            "card_type,card_expiry_date,card_cvn,bill_to_forename,bill_to_surname,bill_to_email,bill_to_address_line1," +
            "bill_to_address_city,bill_to_address_country,bill_to_address_postal_code,bill_to_address_state" ;

//    private String SignedFieldNames = "reference_number,transaction_type,currency,amount,locale,access_key," +
//            "profile_id,transaction_uuid,signed_date_time,signed_field_names,unsigned_field_names,payment_method," +
//            "bill_to_forename,bill_to_surname,bill_to_email,bill_to_address_line1," +
//            "bill_to_address_city,bill_to_address_country" ;

    private String UnsignedFieldNames = "";
    private String SignedDateTimeString = DateManager.getCurrentUTCDateTime();
    private String Locale = "en";
    private String BillAddressLine = "Aga Khan Hospital";
    private String BillAddressCity = "Karachi";
    private String BillAddressCountry = "PK";
    private String BillEmailAddress = "mahrukh.mehmood@aku.edu";
    private String BillSurName = "Sarah Khan";
    private String BillForeName = "Sarah Khan";
    private String BillPhone = "+923332184946";
    private String BillCompanyName = "AGA Khan University";
    private String BillState = "SINDH";
    private String BillPostalAddress = "75300";
    private String ConsumerID = "1234-5648-9970";
    private String TransactionType = "create_payment_token";
    private String Amount = "2000.00";
    private String CardExpirydate = "02-2020";
    private String CVN = "123";
    private String Currency = "PKR";
    private String CardNumber = "4111111111111111";
    private String CardType = "001";
    private String PaymentMethod = "card";



    public String getBillState() {
        return BillState;
    }

    public void setBillState(String billState) {
        BillState = billState;
    }

    public String getBillPostalAddress() {
        return BillPostalAddress;
    }

    public void setBillPostalAddress(String billPostalAddress) {
        BillPostalAddress = billPostalAddress;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }


    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCardExpirydate() {
        return CardExpirydate;
    }

    public void setCardExpirydate(String cardExpirydate) {
        CardExpirydate = cardExpirydate;
    }

    public String getCVN() {
        return CVN;
    }

    public void setCVN(String CVN) {
        this.CVN = CVN;
    }



    public String getAccessKey() {
        return AccessKey;
    }

    public void setAccessKey(String accessKey) {
        AccessKey = accessKey;
    }

    public String getProfileID() {
        return ProfileID;
    }

    public void setProfileID(String profileID) {
        ProfileID = profileID;
    }

    public String getTransactionUUID() {
        return TransactionUUID;
    }

    public void setTransactionUUID(String transactionUUID) {
        TransactionUUID = transactionUUID;
    }

    public String getSignedFieldNames() {
        return SignedFieldNames;
    }

    public void setSignedFieldNames(String signedFieldNames) {
        SignedFieldNames = signedFieldNames;
    }

    public String getUnsignedFieldNames() {
        return UnsignedFieldNames;
    }

    public void setUnsignedFieldNames(String unsignedFieldNames) {
        UnsignedFieldNames = unsignedFieldNames;
    }

    public String getSignedDateTimeString() {
        return SignedDateTimeString;
    }

    public void setSignedDateTimeString(String signedDateTimeString) {
        SignedDateTimeString = signedDateTimeString;
    }

    public String getLocale() {
        return Locale;
    }

    public void setLocale(String locale) {
        Locale = locale;
    }

    public String getBillAddressLine() {
        return BillAddressLine;
    }

    public void setBillAddressLine(String billAddressLine) {
        BillAddressLine = billAddressLine;
    }

    public String getBillAddressCity() {
        return BillAddressCity;
    }

    public void setBillAddressCity(String billAddressCity) {
        BillAddressCity = billAddressCity;
    }

    public String getBillAddressCountry() {
        return BillAddressCountry;
    }

    public void setBillAddressCountry(String billAddressCountry) {
        BillAddressCountry = billAddressCountry;
    }

    public String getBillEmailAddress() {
        return BillEmailAddress;
    }

    public void setBillEmailAddress(String billEmailAddress) {
        BillEmailAddress = billEmailAddress;
    }

    public String getBillSurName() {
        return BillSurName;
    }

    public void setBillSurName(String billSurName) {
        BillSurName = billSurName;
    }

    public String getBillForeName() {
        return BillForeName;
    }

    public void setBillForeName(String billForeName) {
        BillForeName = billForeName;
    }

    public String getBillPhone() {
        return BillPhone;
    }

    public void setBillPhone(String billPhone) {
        BillPhone = billPhone;
    }

    public String getBillCompanyName() {
        return BillCompanyName;
    }

    public void setBillCompanyName(String billCompanyName) {
        BillCompanyName = billCompanyName;
    }

    public String getConsumerID() {
        return ConsumerID;
    }

    public void setConsumerID(String consumerID) {
        ConsumerID = consumerID;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        ReferenceNo = referenceNo;
    }

    public String getAmount() {
        return Amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

}
