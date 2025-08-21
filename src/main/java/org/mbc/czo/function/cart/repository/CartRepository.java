package org.mbc.czo.function.cart.repository;

import org.mbc.czo.function.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Member의 이메일로 장바구니 조회
    Optional<Cart> findByMemberMemail(String memail);

}
