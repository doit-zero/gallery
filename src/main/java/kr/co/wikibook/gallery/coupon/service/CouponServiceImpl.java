package kr.co.wikibook.gallery.coupon.service;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.dto.CouponResponse;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.entity.IssuedCoupon;
import kr.co.wikibook.gallery.coupon.respository.CouponRepository;
import kr.co.wikibook.gallery.coupon.respository.IssuedCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;
    private final IssuedCouponRepository issuedCouponRepository;
    @Override
    public Coupon create(CouponCreateRequest request) {
        Coupon coupon = request.toCouponEntity();
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon;
    }

    @Override
    public List<CouponResponse> findAll(Integer memberId) {
        List<CouponResponse> couponResponseList = new ArrayList<>();

        List<Coupon> couponList = couponRepository.findAll();

        List<IssuedCoupon> IssuedCouponList = issuedCouponRepository.findByMemberId(memberId);
        Set<Integer> issuedCouponIds = IssuedCouponList.stream()
                .map(IssuedCoupon::getCouponId)
                .collect(Collectors.toSet());

        if(!couponList.isEmpty()){
            for (Coupon coupon : couponList) {
                CouponResponse couponResponse = coupon.toCouponResponse();

                // 발급된 쿠폰인지 확인
                if (issuedCouponIds.contains(coupon.getId())) {
                    couponResponse.setIsIssued(true);
                } else {
                    couponResponse.setIsIssued(false);
                }
                couponResponseList.add(couponResponse);
            }
            return couponResponseList;
        }

        return couponResponseList;
    }
}
