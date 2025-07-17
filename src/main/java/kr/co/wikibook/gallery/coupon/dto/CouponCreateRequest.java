package kr.co.wikibook.gallery.coupon.dto;

import jakarta.persistence.*;
import kr.co.wikibook.gallery.coupon.entity.Coupon;
import kr.co.wikibook.gallery.coupon.entity.DiscountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static kr.co.wikibook.gallery.coupon.entity.DiscountType.FIXED;

@Getter
@Setter
@ToString
public class CouponCreateRequest {
    private String code;

    private String name;

    private DiscountType discountType;

    private int discountValue;

    private int totalQuantity;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private LocalDateTime createdAt;

    public Coupon toCouponEntity() {
        return Coupon.builder()
                .code(generateCode())
                .name(this.name)
                .discountType(this.discountType)
                .discountValue(this.discountValue)
                .totalQuantity(this.totalQuantity)
                .validFrom(this.validFrom)
                .validTo(this.validTo)
                .build();
    }

    private String generateCode() {
        String base = name != null ? name.replaceAll("[^A-Za-z0-9]", "").toUpperCase() : "COUPON";
        String prefix = base.length() > 4 ? base.substring(0, 4) : String.format("%-4s", base).replace(' ', 'X');
        String random = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8).toUpperCase();
        return prefix + random; // 총 12자리
    }

}