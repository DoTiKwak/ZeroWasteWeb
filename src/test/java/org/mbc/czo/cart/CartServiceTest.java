package org.mbc.czo.cart;
/*
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbc.czo.function.cart.dto.CartItemDTO;
import org.mbc.czo.function.cart.repository.CartItemRepository;
import org.mbc.czo.function.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem() {
        AbstractReadWriteAccess.Item item = new AbstractReadWriteAccess.Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);

    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart() {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCount(5);
        cartItemDTO.setItemId(item.getId());

        Long cartItemId = cartService.addCart(cartItemDTO, member.getEmail());

        CartItem cartItem = cartItemRepository.findBy(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDTO.getCount(), cartItem.getCount());

    }

}*/
