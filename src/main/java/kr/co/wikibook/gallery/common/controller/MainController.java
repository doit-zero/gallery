package kr.co.wikibook.gallery.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    /**
     * 정규 표현식을 이용한 정적 파일 포워드
     * SPA 와 같은 프론트엔드 라우팅을 사용하는 앱에서는 브라우저가 새로고침 하거나 직접 URL 접근 시 서버가 해당 경로를 처리하려다 404가 나는 것을 방지하고
     * -> 무조건 /index.html을 반환해서 프론트엔드 라우터가 이어받도록 하는 패턴
     * @GetMapping(value = {
     *     "/",                         // 루트 요청
     *     "{path:[^.]*}",              // `/abc` 처럼 .이 없는 단일 경로
     *     "{path1:[^.]*}/{path2:[^.]*}" // `/abc/def` 처럼 .이 없는 2단계 경로
     * })
     * */
    @GetMapping(value = {"/","{path:[^.]*}","{path1:[^.]*}/{path2:[^.]*}"})
    public String home(){
        return "/index.html";
    }
}
