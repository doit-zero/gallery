package kr.co.wikibook.gallery.coupon.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery.account.helper.AccountHelper;
import kr.co.wikibook.gallery.common.interceptor.ApiInterceptor;
import kr.co.wikibook.gallery.coupon.CouponConstant;
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
    private final AccountHelper accountHelper;
    @PostMapping("/api/coupons")
    public ResponseEntity<?> create(@RequestBody CouponCreateRequest request){
        Coupon coupon = couponService.create(request);
        return new ResponseEntity<>(coupon.getCode(),HttpStatus.CREATED);
    }

    @PostMapping("/api/coupons/{id}")
    public ResponseEntity<?> issue(HttpServletRequest request,@PathVariable("id") Integer couponId){
        Integer memberId = accountHelper.getMemberId(request);
        Coupon issue = couponService.issue(memberId, couponId);
        return new ResponseEntity<>(CouponConstant.ISSUE_SUCCESS,HttpStatus.OK);
    }

    /**
     * 만들어진 쿠폰이 없다면 [] 반환
     * */
    @GetMapping("/api/coupons")
    public ResponseEntity<?> findAll(HttpServletRequest request){
        Integer memberId = accountHelper.getMemberId(request);
        List<CouponResponse> couponList = couponService.findAll(memberId);
        return new ResponseEntity<>(couponList,HttpStatus.OK);
    }

}
