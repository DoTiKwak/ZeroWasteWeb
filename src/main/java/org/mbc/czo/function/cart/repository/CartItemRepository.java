package org.mbc.czo.function.cart.repository;
/*
import org.mbc.czo.function.cart.domain.Cart;
import org.mbc.czo.function.cart.domain.CartItem;
import org.mbc.czo.function.cart.dto.CartDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Cart findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new org.mbc.czo.function.cart.dto.CartDetailDTO(ci.id, i.itemNm, i.price, ci.count, im.imgUrl)"
    + "from CartItem ci, ItemImg im " + "join ci.item i " + "where ci.cart.id = :cartId " + "and im.item.id = ci.item.id " +
    "and im.repigmgYn = 'Y' " + "order by ci.regTime desc")
    List<CartDetailDTO> findCartDetailsByCartId(Long cartId);

}
*/
