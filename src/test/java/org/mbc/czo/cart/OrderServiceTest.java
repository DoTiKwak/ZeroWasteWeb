package org.mbc.czo.cart;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbc.czo.function.cart.domain.Order;
import org.mbc.czo.function.cart.dto.OrderDTO;
import org.mbc.czo.function.cart.repository.OrderRepository;
import org.mbc.czo.function.cart.service.OrderService;
import org.mbc.czo.function.member.domain.Member;
import org.mbc.czo.function.member.repository.MemberJpaRepository;
import org.mbc.czo.function.product.constant.ItemSellStatus;
import org.mbc.czo.function.product.domain.Item;
import org.mbc.czo.function.product.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class OrderServiceTest {
/*
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Item createItem() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(20000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(50);
        return itemRepository.save(item);
    }

    public Member createMember() {
        Member member = new Member();
        member.setMid("user01");
        member.setMname("테스트회원");
        member.setMphoneNumber("01099998888");
        member.setMemail("user01@test.com");
        member.setMpassword("1234");
        member.setMaddress("서울시 강남구");
        return memberJpaRepository.save(member);
    }

    @Test
    @DisplayName("주문 서비스 테스트")
    public void orderTest() {
        Item item = createItem();
        Member member = createMember();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setItem_id(item.getId());
        orderDTO.setCount(3);

        Long orderId = orderService.order(orderDTO, member.getMemail());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(1, order.getOrderItems().size());
        assertEquals(item.getPrice() * 3, order.getTotalPrice());
    }*/
}
