package kr.co.wikibook.gallery.member.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.gallery.member.dto.AccountJoinRequest;
import kr.co.wikibook.gallery.member.dto.AccountLoginRequest;
import kr.co.wikibook.gallery.member.entity.Member;
import kr.co.wikibook.gallery.member.etc.AccountConstants;
import kr.co.wikibook.gallery.member.service.MemberService;
import kr.co.wikibook.gallery.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component // 컨트롤러에서 사용해야 하므로 빈으로 등록 시켜야함
@RequiredArgsConstructor
public class SessionAccountHelper implements AccountHelper{
    private final MemberService memberService;
    @Override
    public void join(AccountJoinRequest joinReq) {
        memberService.save(joinReq.getName(), joinReq.getLoginId(), joinReq.getLoginPw());
    }

    @Override
    public String login(AccountLoginRequest loginReq, HttpServletRequest req, HttpServletResponse res) {
        Member member = memberService.find(loginReq.getLoginId(), loginReq.getLoginPw());

        if(member == null){
            return null;
        }

        HttpUtils.setSession(req, AccountConstants.MEMBER_ID_NAME,member.getId());
        return member.getLoginId();
    }
    // 회원 아이디 조회
    @Override
    public Integer getMemberId(HttpServletRequest req) {
        Object memberId = HttpUtils.getSessionValue(req, AccountConstants.MEMBER_ID_NAME);
        if(memberId != null){
            return (int) memberId;
        }

        return null;
    }

    // 로그인 여부 확인
    @Override
    public boolean isLoggedIn(HttpServletRequest req) {
        return getMemberId(req) != null;
    }

    // 로그아웃
    @Override
    public void logout(HttpServletRequest req, HttpServletResponse res) {
        HttpUtils.removeSession(req,AccountConstants.MEMBER_ID_NAME);
    }
}
