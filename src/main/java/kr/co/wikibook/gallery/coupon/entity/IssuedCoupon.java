package kr.co.wikibook.gallery.coupon.entity;

import jakarta.persistence.*;
import lombok.Getter;
/**
 * 발급된 쿠폰 관리 엔터티
 * */
@Entity
@Getter
@Table(
        name = "issued_coupons",
        indexes = {
                @Index(name = "idx_member_id", columnList = "memberId"),
                @Index(name = "idx_coupon_id", columnList = "couponId")
        }
)
public class IssuedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer memberId;

    @Column
    private Integer couponId;
}
