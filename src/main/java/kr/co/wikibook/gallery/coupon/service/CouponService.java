package kr.co.wikibook.gallery.coupon.service;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.entity.Coupon;

import java.util.List;

public interface CouponService {
    // 쿠폰 발급
    Coupon issue(CouponCreateRequest request);

    // 모든 쿠폰 가져오기
    List<Coupon> findAll();
}
