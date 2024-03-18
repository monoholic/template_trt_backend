package kr.co.trito.utils;

import KISA.SHA256;

import java.util.Base64;

public class SecurityUtil {
    public static String makeSHA256(String sPlainText) {
        if( sPlainText != null && !sPlainText.isEmpty()) {
            SHA256 s = new SHA256(sPlainText.getBytes());

            return Base64.getEncoder().encodeToString(s.GetHashCode());
        } else {
            return "";
        }
    }
}
