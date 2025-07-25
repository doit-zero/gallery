package kr.co.wikibook.gallery.account.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.gallery.member.dto.AccountJoinRequest;
import kr.co.wikibook.gallery.member.dto.AccountLoginRequest;

/**
 * 계정 컨트롤러 등에서 계정 기능을 보조
 * */
public interface AccountHelper {
    // 회원가입
    void join(AccountJoinRequest joinReq);

    // 로그인
    String login(AccountLoginRequest loginReq, HttpServletRequest req, HttpServletResponse res);

    // 회원 아이디 조회
    Integer getMemberId(HttpServletRequest req);

    // 로그인 여부 확인
    boolean isLoggedIn(HttpServletRequest req);

    // 로그아웃
    void logout(HttpServletRequest req,HttpServletResponse res);
}
