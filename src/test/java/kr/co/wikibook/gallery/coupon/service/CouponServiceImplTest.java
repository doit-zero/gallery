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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponServiceImplTest {
    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    @DisplayName("쿠폰 발급 테스트")
    void shouldReturnCouponWhenIssueSuccess() {
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
}