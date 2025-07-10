package kr.co.wikibook.gallery.coupon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "member_coupon")
public class MemberCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer couponId;

    @Column
    private CouponState state = CouponState.ISSUED;

    @Column
    @CreationTimestamp
    private LocalDateTime issuedAt;

    @Column
    private LocalDateTime usedAt;
}
