package kr.co.wikibook.gallery.item.entity;

import jakarta.persistence.*;
import kr.co.wikibook.gallery.item.dto.ItemRead;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(length = 100,nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPer;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp // 데이터 삽입 시 값이 없다면 현재 시각이 들어가게 함
    private LocalDateTime created;

    // 상품 조회 DTO로 변환
    public ItemRead toRead(){
        return ItemRead.builder()
                .id(id)
                .name(name)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .build();
    }
}
