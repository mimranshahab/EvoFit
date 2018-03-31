package edu.aku.akuh_health_first.helperclasses;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import edu.aku.akuh_health_first.constatnts.WebServiceConstants;


public class CyberSoftSecurityHelper {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String SECRET_KEY = WebServiceConstants.Secret_token_paymentGatway;

    public String sign(HashMap params) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return sign(buildDataToSign(params), SECRET_KEY);
    }

    private String sign(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), HMAC_SHA256);
//        Mac mac = Mac.getInstance(HMAC_SHA256);
//        mac.init(secretKeySpec);
//
//        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
//        return org.apache.commons.codec.binary.Base64.encodeBase64(rawHmac).toString();


        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(data.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return new String(Hex.encodeHex(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
//
//        byte[] bytes = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
//        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private String buildDataToSign(HashMap params) {
        String[] signedFieldNames = String.valueOf(params.get("signed_field_names")).split(",");
        ArrayList<String> dataToSign = new ArrayList<String>();
        for (String signedFieldName : signedFieldNames) {
            dataToSign.add(signedFieldName + "=" + String.valueOf(params.get(signedFieldName)));
        }
        return commaSeparate(dataToSign);
    }

    private String commaSeparate(ArrayList<String> dataToSign) {
        StringBuilder csv = new StringBuilder();
        for (Iterator<String> it = dataToSign.iterator(); it.hasNext(); ) {
            csv.append(it.next());
            if (it.hasNext()) {
                csv.append(",");
            }
        }
        return csv.toString();

    }
}