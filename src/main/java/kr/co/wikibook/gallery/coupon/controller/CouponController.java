package kr.co.wikibook.gallery.coupon.controller;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    @PostMapping("/api/coupons")
    public ResponseEntity<?> issue(@RequestBody CouponCreateRequest request){
        Coupon coupon = couponService.issue(request);
        return new ResponseEntity<>(coupon.getCode(),HttpStatus.OK);
    }
}
