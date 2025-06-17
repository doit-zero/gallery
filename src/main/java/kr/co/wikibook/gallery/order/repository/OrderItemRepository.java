package kr.co.wikibook.gallery.order.repository;

import kr.co.wikibook.gallery.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    // 주문 아이디로 주문 상품 데이터 조회
    List<OrderItem> findAllByOrderId(Integer orderId);
}
