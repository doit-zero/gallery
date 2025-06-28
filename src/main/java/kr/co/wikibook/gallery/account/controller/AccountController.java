package kr.co.wikibook.gallery.account.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.gallery.account.etc.AccountConstants;
import kr.co.wikibook.gallery.block.service.BlockService;
import kr.co.wikibook.gallery.common.util.HttpUtils;
import kr.co.wikibook.gallery.common.util.TokenUtils;
import kr.co.wikibook.gallery.member.dto.AccountJoinRequest;
import kr.co.wikibook.gallery.member.dto.AccountLoginRequest;
import kr.co.wikibook.gallery.member.helper.AccountHelper;
import kr.co.wikibook.gallery.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AccountController {
    private final AccountHelper accountHelper;
    private final BlockService blockService;
    private final MemberService memberService;

    @PostMapping("/api/account/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinRequest joinReq){
        // 입력값이 하나라도 비어있다면 BAD_REQUEST로 전달
        System.out.println(joinReq.toString());
        if(!StringUtils.hasLength(joinReq.getName()) || !StringUtils.hasLength(joinReq.getLoginId()) || !StringUtils.hasLength(joinReq.getLoginPw())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 중복 로그인 아이디가 있으면
        if(memberService.find(joinReq.getLoginId()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 중복 아이디가 있으면 상태 코드 409를 리턴
        }

        accountHelper.join(joinReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse res, @RequestBody AccountLoginRequest loginReq){
        if(!StringUtils.hasLength(loginReq.getLoginId()) || !StringUtils.hasLength(loginReq.getLoginPw())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String output = accountHelper.login(loginReq,req,res);

        if(output == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(output,HttpStatus.OK);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(HttpServletRequest req){
        return new ResponseEntity<>(accountHelper.isLoggedIn(req),HttpStatus.OK);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity<?> logout(HttpServletRequest req,HttpServletResponse res){
        accountHelper.logout(req,res);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 리프레시 토큰을 통해 액세스 토큰 재발급
    @GetMapping("/api/account/token")
    public ResponseEntity<?> regenerate(HttpServletRequest req){
        String accessToken = "";
        String refreshToken = HttpUtils.getCookieValue(req, AccountConstants.REFRESH_TOKEN_NAME);

        // 리프레시 토큰이 유효하다면
        if(StringUtils.hasLength(refreshToken) && TokenUtils.isValid(refreshToken) && !blockService.has(refreshToken)){
            // 리프레시 토큰의 내부 값 조회
            Map<String,Object> tokenBody = TokenUtils.getBody(refreshToken);

             // 리프레시 토큰의 회원 아이디 조회
             Integer memberId = (Integer) tokenBody.get(AccountConstants.MEMBER_ID_NAME);

             // 액세스 토큰 발급
            TokenUtils.generate(AccountConstants.ACCESS_TOKEN_NAME,AccountConstants.MEMBER_ID_NAME,memberId,AccountConstants.ACCESS_TOKEN_MINUTES);
        }
        return new ResponseEntity<>(accessToken,HttpStatus.OK);

        //
    }
}
