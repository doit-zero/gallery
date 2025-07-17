package kr.co.wikibook.gallery.coupon.service;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.dto.CouponResponse;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.respository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Override
    public Coupon create(CouponCreateRequest request) {
        Coupon coupon = request.toCouponEntity();
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon;
    }


    @Override
    public List<CouponResponse> findAll() {
        List<CouponResponse> couponResponseList = new ArrayList<>();
        List<Coupon> couponList = couponRepository.findAll();

        if(!couponList.isEmpty()){
            for (Coupon coupon : couponList) {
                CouponResponse couponResponse = coupon.toCouponResponse();
                couponResponseList.add(couponResponse);
            }
            return couponResponseList;
        }

        return couponResponseList;
    }
}
