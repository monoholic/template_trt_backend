package kr.co.trito.utils;


public class ComUtil {

    public ComUtil() {}

    // 파라미터를 DB 컬럼 양식으로 변환
    public String changeForm(String str){
        String res = str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();
        return res;
    }
}
