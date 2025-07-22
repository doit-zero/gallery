package kr.co.wikibook.gallery.coupon.service;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.dto.CouponResponse;
import kr.co.wikibook.gallery.coupon.entity.Coupon;

import java.util.List;

public interface CouponService {
    // 쿠폰 생성
    Coupon create(CouponCreateRequest request);

    // 쿠폰 발급
    Coupon issue(Integer memberId,Integer couponId);

    // 사용가능한 쿠폰들 모두 가져오기
    List<CouponResponse> findAll(Integer memberId);
}
