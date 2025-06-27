package kr.co.wikibook.gallery.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  회원가입 요청시 사용하는 dto
 * */
@Getter
@Setter
@ToString
public class AccountJoinRequest {
    private String name;
    private String loginId;
    private String loginPw;
}
