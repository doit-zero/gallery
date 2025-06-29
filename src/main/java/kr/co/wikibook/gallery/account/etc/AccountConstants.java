package kr.co.wikibook.gallery.account.etc;
/**
 * 추후 구현할 기능에서 반복적으로 사용될 회원 아이디의 이름을 정의한 문자열 상수
 * */
public class AccountConstants {
    // 회원 아이디 이름
    public static final String MEMBER_ID_NAME = "memberId";

    // 엑세스 토큰 이름
    public static final String ACCESS_TOKEN_NAME = "accessToken";

    // 리프레시 토큰 이름
    public static final String REFRESH_TOKEN_NAME = "refreshToken";

    // 액세스 토큰 유효 시간(1분)
    public static final int ACCESS_TOKEN_MINUTES = 10;

    // 리프레시 토큰 유효 시간 (24시간)
    public static final int REFRESH_TOKEN_EXP_MINUTES = 60 * 24;
}
