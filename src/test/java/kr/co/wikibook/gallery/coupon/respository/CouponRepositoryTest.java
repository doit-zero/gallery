package kr.co.wikibook.gallery.coupon.respository;

import kr.co.wikibook.gallery.coupon.controller.CouponController;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.entity.DiscountType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CouponRepositoryTest {
    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰이 저장되고 조회되는지 확인")
    void saveAndFindAll(){
        // given
        Coupon coupon = Coupon.builder()
                .code("TESTCODE1234")
                .name("테스트쿠폰")
                .discountType(DiscountType.FIXED)
                .discountValue(5000)
                .totalQuantity(100)
                .validFrom(LocalDateTime.of(2025, 7, 10, 0, 0))
                .validTo(LocalDateTime.of(2025, 7, 31, 23, 59))
                .build();
        // when
        couponRepository.save(coupon);

        List<Coupon> allCoupons = couponRepository.findAll();

        // then
        assertThat(allCoupons).isNotEmpty();
        assertThat(allCoupons.get(0).getCode()).isEqualTo("TESTCODE1234");

    }
}