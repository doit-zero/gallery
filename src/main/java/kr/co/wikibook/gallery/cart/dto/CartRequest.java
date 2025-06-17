package kr.co.wikibook.gallery.cart.dto;

import kr.co.wikibook.gallery.cart.entity.Cart;
import lombok.Getter;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 장바구니에 데이터 입력을 요청할 때 사용하는 DTO
 */
@Getter
public class CartRequest {
    private Integer itemId;

    // 엔터티 객체로 변환 추후 장바군 서비스에서 데이터를 저장할 때 사용됨
    public Cart toEntity(Integer memberId){
        return new Cart(memberId,itemId);
    }
}
