package kr.co.wikibook.gallery.coupon.respository;

import kr.co.wikibook.gallery.coupon.entity.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon,Integer> {
    @Query("SELECT ic FROM IssuedCoupon ic WHERE ic.memberId = :memberId")
    List<IssuedCoupon> findByMemberId(@Param("memberId") Integer memberId);

    // couponId로 조회
    @Query("SELECT c FROM IssuedCoupon c WHERE c.couponId = :couponId")
    List<IssuedCoupon> findByCouponId(@Param("couponId") Integer couponId);
}
