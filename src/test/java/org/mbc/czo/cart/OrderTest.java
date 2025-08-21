package org.mbc.czo.cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbc.czo.function.cart.domain.Order;
import org.mbc.czo.function.cart.domain.OrderItem;
import org.mbc.czo.function.cart.repository.OrderRepository;
import org.mbc.czo.function.member.domain.Member;
import org.mbc.czo.function.member.repository.MemberJpaRepository;
import org.mbc.czo.function.product.constant.ItemSellStatus;
import org.mbc.czo.function.product.domain.Item;
import org.mbc.czo.function.product.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class OrderTest {

   /* @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @PersistenceContext
    private EntityManager em;

    public Item createItem() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public Member createMember() {
        Member member = new Member();
        member.setMid("testId");
        member.setMname("테스트회원");
        member.setMphoneNumber("01012345678");
        member.setMemail("test@test.com");
        member.setMpassword("1234");
        member.setMaddress("서울시 강남구");
        return memberJpaRepository.save(member);
    }

    @Test
    @DisplayName("주문 저장 테스트")
    public void saveOrder() {
        Item item = createItem();
        Member member = createMember();

        // 주문 상품 리스트 생성
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, 10);
        orderItemList.add(orderItem);

        // 주문 생성
        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        em.flush();
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(1, savedOrder.getOrderItems().size());
        assertEquals(item.getPrice() * 10, savedOrder.getTotalPrice());
    }*/
}
