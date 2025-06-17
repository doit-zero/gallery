package kr.co.wikibook.gallery.member.dto;

import lombok.Getter;

/**
 * 로그인 요청시 사용하는 DTO
 * */

@Getter
public class AccountLoginRequest {
    private String loginId;
    private String loginPw;
}
