package kr.co.wikibook.gallery.cart.service;

import kr.co.wikibook.gallery.cart.dto.CartRead;
import kr.co.wikibook.gallery.cart.dto.CartRequest;
import kr.co.wikibook.gallery.cart.entity.Cart;

import java.util.List;

public interface CartService {
    // 특정 회원의 장바구니 상품 데이터 목록 조회
    List<CartRead> findAll(Integer memberId);

    // 특정회원의 장바구니에 담긴 특정 상품 조회
    CartRead find(Integer memberId,Integer itemId);

    // 특정 회원의 장바구니에 담긴 모든 상품 데이터 삭제
    void removeAll(Integer memberId);

    // 특정 회원의 장바구니에 담긴 특정 상품 삭제
    void remove(Integer memberId, Integer itemId);

    // 장바구니에 상품 저장
    void save(Cart cart);
}
