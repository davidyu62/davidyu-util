package org.util;


//import java.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringUtil {

    /**
     * string to bytes[]
     * */
    public static byte[] stringToByte(String str){
        return str.getBytes();
    }

    public static String base64Encrypt(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(str.getBytes("UTF-8"));
        return encodedString;
    }

    public static String base64Decrypt(String str) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[]  decodedBytes = decoder.decode(str);
        return new String(decodedBytes,"UTF-8");
    }

    public static String sha256Encrypt(String str) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA 256");
        byte[] result = mDigest.digest(str.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0; i<result.length; i++){
            stringBuffer.append(Integer.toString((result[i] & 0xFF) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }


}
