package kr.co.wikibook.gallery.coupon.respository;

import kr.co.wikibook.gallery.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    List<Coupon> findAll();
}
