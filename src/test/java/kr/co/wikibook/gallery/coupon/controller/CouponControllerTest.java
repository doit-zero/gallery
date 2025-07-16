package kr.co.wikibook.gallery.coupon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.wikibook.gallery.common.config.WebMvcConfig;
import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.dto.CouponResponse;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CouponController.class)
@ContextConfiguration(classes = { CouponController.class })  // WebMvcConfig를 제외
@DisplayName("쿠폰 컨트롤러 단위 테스트")
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CouponService couponService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("쿠폰 발급 테스트")
    void shouldReturnCouponCode_WhenIssueSuccess() throws Exception {
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

        when(couponService.create(any(CouponCreateRequest.class))).thenReturn(fakeCoupon);

        // when & then
        mockMvc.perform(post("/v1/api/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("SUMM1234ABCD"));
    }

    @Test
    @DisplayName("발급된 전체 쿠폰 조회 테스트")
    void shouldReturnCouponList_WhenFindAllSuccess() throws Exception {
        // given
        Coupon coupon1 = Coupon.builder()
                .id(1)
                .code("FIXED101alr1")
                .name("10% 할인 쿠폰")
                .discountType(DiscountType.FIXED)
                .discountValue(1000)
                .totalQuantity(100)
                .issuedQuantity(10)
                .validFrom(LocalDateTime.now().minusDays(1))
                .validTo(LocalDateTime.now().plusDays(7))
                .build();

        Coupon coupon2 = Coupon.builder()
                .id(2)
                .code("SHIPFREE1234")
                .name("무료배송 쿠폰")
                .discountType(DiscountType.PERCENT)
                .discountValue(0)
                .totalQuantity(50)
                .issuedQuantity(5)
                .validFrom(LocalDateTime.now().minusDays(2))
                .validTo(LocalDateTime.now().plusDays(5))
                .build();

        List<CouponResponse> mockResult = List.of(coupon1.toCouponResponse(),coupon2.toCouponResponse());

        when(couponService.findAll()).thenReturn(mockResult);

        // when & then
        mockMvc.perform(get("/v1/api/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockResult)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].code").value("FIXED101alr1"))
                .andExpect(jsonPath("$[1].code").value("SHIPFREE1234"));

    }
}