package kr.co.wikibook.gallery.coupon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static kr.co.wikibook.gallery.coupon.entity.DiscountType.FIXED;

@Entity
@Getter
@Table(name = "coupon")
@Builder
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
    private int discountValue;

    @Column
    private int totalQuantity;

    @Column
    private int issuedQuantity = 0;

    @Column
    private LocalDateTime validFrom;

    @Column
    private LocalDateTime validTo;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
}
