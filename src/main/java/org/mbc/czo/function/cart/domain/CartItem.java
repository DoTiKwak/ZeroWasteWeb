package org.mbc.czo.function.cart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.mbc.czo.function.common.entity.BaseEntity;
import org.mbc.czo.function.product.domain.Item;

@Entity
@Getter
@Setter
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    // 생성 메서드
    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    // 수량 증가
    public void addCount(int count) {
        this.count += count;
    }

    // 수량 변경
    public void updateCount(int count) {
        this.count = count;
    }
}
