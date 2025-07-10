package kr.co.wikibook.gallery.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Coupon {
    @Id
    private Integer id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private DiscountType discount_type;

    @Column
    private int discount_value;

    @Column
    private int total_quantity;

    @Column
    private int issued_quantity;

    @Column
    private LocalDateTime valid_from;

    @Column
    private LocalDateTime valid_to;

    @Column
    @CreationTimestamp
    private LocalDateTime created_at;
}
