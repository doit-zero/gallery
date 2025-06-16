package kr.co.wikibook.gallery.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {
    // 세션 입력
    /**
     * key는 세션의 키
     * value는 세션의 값
     * 용도는 로그인에 성공한 사용자의 정보를 저장할 떄 사용함
     */
    public static void setSession(HttpServletRequest req, String key,Object value){
        req.getSession().setAttribute(key,value);
    }

    // 세션 값 조회
    public static Object getSessionValue(HttpServletRequest req,String key){
        return req.getSession().getAttribute(key);
    }

    // 세션 삭제
    public static void removeSession(HttpServletRequest req,String key){
        req.getSession().removeAttribute(key);
    }
}
