package kr.co.wikibook.gallery.coupon.integration;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.entity.DiscountType;
import kr.co.wikibook.gallery.coupon.respository.CouponRepository;
import kr.co.wikibook.gallery.coupon.service.CouponServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("쿠폰 관련 통합 테스트")
@Transactional
public class CouponIntegrationTest {
    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰 발급 및 조회 테스트")
    void couponIntegrationTest(){
        // given
        CouponCreateRequest request = new CouponCreateRequest();
        request.setName("여름특가쿠폰");
        request.setDiscountType(DiscountType.FIXED);
        request.setDiscountValue(3000);
        request.setTotalQuantity(100);
        request.setValidFrom(LocalDateTime.of(2025, 7, 10, 0, 0));
        request.setValidTo(LocalDateTime.of(2025, 7, 31, 23, 59));

        // when
        Coupon issuedCoupon = couponService.create(request);

        // then
        List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).contains(issuedCoupon);
    }
}
