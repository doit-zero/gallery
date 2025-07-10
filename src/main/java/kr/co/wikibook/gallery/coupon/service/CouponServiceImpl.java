package kr.co.wikibook.gallery.coupon.service;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.respository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Override
    public Coupon issue(CouponCreateRequest request) {
        Coupon coupon = request.toCouponEntity();
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon;
    }

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }
}
