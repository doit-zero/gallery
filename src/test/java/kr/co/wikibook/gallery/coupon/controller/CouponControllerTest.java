package kr.co.wikibook.gallery.coupon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.wikibook.gallery.common.config.WebMvcConfig;
import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.entity.DiscountType;
import kr.co.wikibook.gallery.coupon.service.CouponService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CouponController.class)
@ContextConfiguration(classes = { CouponController.class })  // WebMvcConfig를 제외
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CouponService couponService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("쿠폰 생성 테스트")
    void shouldReturnCouponCodeWhenIssueSuccess() throws Exception {
        // given
        CouponCreateRequest request = new CouponCreateRequest();
        request.setName("여름특가쿠폰");
        request.setDiscountType(DiscountType.FIXED);
        request.setDiscountValue(3000);
        request.setTotalQuantity(100);
        request.setValidFrom(LocalDateTime.of(2025, 7, 10, 0, 0));
        request.setValidTo(LocalDateTime.of(2025, 7, 31, 23, 59));

        Coupon fakeCoupon = Coupon.builder()
                .id(1)
                .code("SUMM1234ABCD")
                .name(request.getName())
                .discountType(request.getDiscountType())
                .discountValue(request.getDiscountValue())
                .totalQuantity(request.getTotalQuantity())
                .validFrom(request.getValidFrom())
                .validTo(request.getValidTo())
                .build();

        when(couponService.issue(any(CouponCreateRequest.class))).thenReturn(fakeCoupon);

        // when & then
        mockMvc.perform(post("/v1/api/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("SUMM1234ABCD"));
    }
}