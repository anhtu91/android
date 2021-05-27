package org.owntracks.android.support;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class JWTUtils {
    public static String decodeJWT(String JWTEncoded) throws Exception{
        String[] split = JWTEncoded.split("\\.");
        return getJson(split[1]);
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
