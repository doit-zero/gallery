package kr.co.wikibook.gallery.item.repository;

import kr.co.wikibook.gallery.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    // 메서드 이름 끝부분 In은 매개변수가 배열이나 리스트 타입일 때 해당 값을 모두 포함하는 조건임
    List<Item> findAllByIdIn(List<Integer> ids);

}
