package kr.co.wikibook.gallery.common.config;

import kr.co.wikibook.gallery.common.interceptor.ApiInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor) // 메서드에 적용할 인터셉터 지정
                .addPathPatterns("/v1/api/**")  // 인터셉터를 적용할 URL 경로를 지정
                .excludePathPatterns("/v1/api/account/**","/v1/api/items/**"); // 인터셉터를 적용하지 않을 URL 경로를 지정
    }
}
