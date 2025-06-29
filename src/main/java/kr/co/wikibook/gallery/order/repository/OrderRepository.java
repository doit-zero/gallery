package kr.co.wikibook.gallery.order.repository;

import kr.co.wikibook.gallery.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    // 주문 목록 조회
    Page<Order> findAllByMemberIdOrderByIdDesc(Integer memberId, Pageable pageable);

    // 특정 회원의 특정 주문 조회
    Optional<Order> findByIdAndMemberId(Integer id,Integer memberId);
}
