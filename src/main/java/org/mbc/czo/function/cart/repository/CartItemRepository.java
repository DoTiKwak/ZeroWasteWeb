package org.mbc.czo.function.cart.repository;

import org.mbc.czo.function.cart.domain.CartItem;
import org.mbc.czo.function.cart.dto.CartDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // cartId + itemId 로 조회
    Optional<CartItem> findByCart_IdAndItem_Id(Long cartId, Long itemId);

    @Query("SELECT new org.mbc.czo.function.cart.dto.CartDetailDTO(" +
            "ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "FROM CartItem ci " +
            "JOIN ci.item i " +
            "JOIN ItemImg im ON im.item.id = i.id " +
            "WHERE ci.cart.id = :cartId " +
            "AND im.repimgYn = 'Y' " +
            "ORDER BY ci.regTime DESC")
    List<CartDetailDTO> findCartDetailDTOList(Long cartId);

    // 특정 회원의 장바구니 아이템 개수 조회
    long countByCart_Member_Memail(String email);


}
