package kr.co.wikibook.gallery.coupon.controller;

import kr.co.wikibook.gallery.coupon.dto.CouponCreateRequest;
import kr.co.wikibook.gallery.coupon.dto.CouponResponse;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    @PostMapping("/api/coupons")
    public ResponseEntity<?> create(@RequestBody CouponCreateRequest request){
        Coupon coupon = couponService.create(request);
        return new ResponseEntity<>(coupon.getCode(),HttpStatus.CREATED);
    }

    /**
     * 발급된 쿠폰이 없다면 [] 반환함
     * */
    @GetMapping("/api/coupons")
    public ResponseEntity<?> findAll(){
        List<CouponResponse> couponList = couponService.findAll();
        return new ResponseEntity<>(couponList,HttpStatus.OK);
    }
}
