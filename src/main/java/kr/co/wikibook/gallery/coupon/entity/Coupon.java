package kr.co.wikibook.gallery.coupon.entity;

import jakarta.persistence.*;
import kr.co.wikibook.gallery.coupon.CouponConstant;
import kr.co.wikibook.gallery.coupon.dto.CouponResponse;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static kr.co.wikibook.gallery.coupon.entity.DiscountType.FIXED;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Table(name = "coupon")
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50,nullable = false)
    private String code;

    @Column(length = 100,nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountType discountType = FIXED;

    @Column
    private Integer discountValue;

    @Column
    private Integer totalQuantity;

    @Column
    private Integer issuedQuantity = 0;

    @Column
    private LocalDateTime validFrom;

    @Column
    private LocalDateTime validTo;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    public CouponResponse toCouponResponse(){
        return CouponResponse.builder()
                .id(id)
                .code(code)
                .name(name)
                .discountType(discountType)
                .discountValue(discountValue)
                .validFrom(validFrom)
                .validTo(validTo)
                .build();
    };

    public void updateToTalQuantity(){
        if(this.totalQuantity == 0){
            throw new RuntimeException(CouponConstant.COUPON_EXHUASTION);
        }
        totalQuantity = totalQuantity - 1;
    }

    public void updateIssuedQuantity(){
        issuedQuantity = issuedQuantity + 1;
    }
}
