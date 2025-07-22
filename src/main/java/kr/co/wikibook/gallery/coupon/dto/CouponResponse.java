package kr.co.wikibook.gallery.coupon.dto;

import kr.co.wikibook.gallery.coupon.entity.DiscountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class CouponResponse {
    private Integer id;
    private String code;
    private String name;
    private DiscountType discountType;
    private Integer discountValue;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private Boolean isIssued;
}
