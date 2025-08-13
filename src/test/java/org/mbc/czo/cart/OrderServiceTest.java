package org.mbc.czo.cart;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbc.czo.function.cart.domain.Order;
import org.mbc.czo.function.cart.domain.OrderItem;
import org.mbc.czo.function.cart.dto.OrderDTO;
import org.mbc.czo.function.cart.repository.CartItemRepository;
import org.mbc.czo.function.cart.repository.OrderRepository;
import org.mbc.czo.function.cart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")

public class OrderServiceTest {

/*
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem(){
        Item item = new Item();
        item.setItemNm("테스트상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepostiory.save(member);

        @Test
                @DisplayName("주문테스트")
        public void order(){
            Item item = saveItem();
            Member member = saveMember();

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCount(10);
            orderDTO.setItemId(item.getId());

            Long orderId = orderService.order(orderDTO, member.getEmail());

            Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

            List<OrderItem> orderItems = order.getOrderItems();

            int totalPrice = orderDTO.getCount()*item.getPrice();

            assertEquals(totalPrice, order.getTotalPrice());

        }

    }
    */
}

