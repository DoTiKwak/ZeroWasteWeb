package org.mbc.czo.function.product.repository;

import org.mbc.czo.function.product.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
    Optional<Item> findByItemNm(String itemNm);
// Repository를 인터페이스로 만들지 않으면, Spring Data JPA가 구현체를 자동 생성하지 못해서 서비스 주입 시 Bean이 없어 오류가 발생
}
