package kr.co.wikibook.gallery.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 장바구니의 데이터를 조회할 때 사용하는 DTO
 * */

@Getter
@Setter
@Builder
public class CartRead {
    private Integer id;
    private Integer itemId;

}
