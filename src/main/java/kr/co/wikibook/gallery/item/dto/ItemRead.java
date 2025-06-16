package kr.co.wikibook.gallery.item.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 상품 데이터를 조회할 때 사용할 DTO
 * */

@Getter
@Builder
public class ItemRead {
    private Integer id;
    private String name;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
    private LocalDateTime created;
}
