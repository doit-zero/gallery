package kr.co.wikibook.gallery.coupon.service;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.entity.DiscountType;
import kr.co.wikibook.gallery.coupon.respository.CouponRepository;
import kr.co.wikibook.gallery.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("쿠폰 서비스 단위 테스트")
class CouponServiceImplTest {
    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    @DisplayName("쿠폰 발급 테스트")
    void shouldReturnCoupon_WhenIssueSuccess() {
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

        when(couponRepository.save(any(Coupon.class))).thenReturn(fakeCoupon);

        // when
        Coupon savedCoupon = couponService.issue(request);

        // then
        assertEquals(savedCoupon,fakeCoupon);
    }

    @Test
    @DisplayName("발급된 전체 쿠폰 조회 테스트")
    void shouldReturnCouponList_WhenFindAllSuccess(){
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

        List<Coupon> mockResult = List.of(coupon1,coupon2);
        when(couponRepository.findAll()).thenReturn(mockResult);

        // when
        List<Coupon> result = couponService.findAll();

        // then
        assertEquals(2, result.size());
        assertEquals("FIXED101alr1", result.get(0).getCode());
        verify(couponRepository, times(1)).findAll();
    }

}