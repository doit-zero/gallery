package kr.co.wikibook.gallery.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.gallery.member.helper.AccountHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * preHandler가 true를 반환하면 apiConfig에 등록한 URL 경로에 맞는 컨트롤러 메서드가 실행됨
 * 동작 순서
 * 1. preHandler() -> 반환
 * 2. 컨트롤러 메서드 실행
 * 3. 컨트롤러가 응답을 반환하면 postHandle() 실행 (@Overide해서 구현한 경우)
 * 4. 뷰 렌더링 후 afterCompletion() 실행 (@Overide해서 구현한 경우)
 * */
@Component
@RequiredArgsConstructor
public class ApiInterceptor implements HandlerInterceptor {
    private final AccountHelper accountHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 로그인 회원 아이디가 없으면
        if(accountHelper.getMemberId(request) == null){
            response.setStatus(401);
            return false;
        }

        return true;
    }
}
