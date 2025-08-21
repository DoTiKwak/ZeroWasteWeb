package org.mbc.czo.cart;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mbc.czo.function.cart.domain.CartItem;
import org.mbc.czo.function.cart.dto.CartItemDTO;
import org.mbc.czo.function.cart.repository.CartItemRepository;
import org.mbc.czo.function.cart.service.CartService;
import org.mbc.czo.function.member.domain.Member;
import org.mbc.czo.function.member.dto.MemberJoinDTO;
import org.mbc.czo.function.member.repository.MemberJpaRepository;
import org.mbc.czo.function.member.service.MemberAuthService;
import org.mbc.czo.function.product.constant.ItemSellStatus;
import org.mbc.czo.function.product.domain.Item;
import org.mbc.czo.function.product.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@Log4j2
@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    CartItemDTO cartItemDTO;


    public Item saveItem() {

        Optional<Item> result = itemRepository.findByItemNm("테스트 상품");

        Item item = result.get();

        return item;

        /*Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.saveAndFlush(item);*/
    }

    public Member saveMember() {

        Optional<Member> result = memberJpaRepository.findByMemail("testID@com");

        Member member = result.get();

        return member;

        /*MemberJoinDTO dto = new MemberJoinDTO();
        dto.setMid("test01");
        dto.setMname("테스트 유저");
        dto.setMphoneNumber("01012345678");
        dto.setMemail("test@test.com");
        dto.setMpassword("1234");
        dto.setMaddress("서울시 강남구");

        Member member = Member.createMember(dto, passwordEncoder);
        return memberJpaRepository.saveAndFlush(member);*/
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart() {

        Member member = saveMember(); // DB에 없으면 저장까지 함
        Item item = saveItem();       // DB에 없으면 저장까지 함

        String memail = member.getMemail();
        int count = 2;

        // CartItemDTO 객체 생성 및 초기화
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(item.getId());
        cartItemDTO.setCount(count);

        // 장바구니에 상품 추가
        Long cartItemId = cartService.addCart(cartItemDTO, memail);

        // 저장된 CartItem 조회
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        // 검증
        assertEquals(item.getId(), cartItem.getItem().getId(), "상품 ID가 일치해야 함");
        assertEquals(count, cartItem.getCount(), "수량이 일치해야 함");
        assertEquals(member.getMid(), cartItem.getCart().getMember().getMid(), "회원 ID가 일치해야 함");

        log.info("장바구니 추가 성공: {}", cartItem);

    }

    @Test
    @DisplayName("장바구니 조회 테스트")
    public void getCartItemsTest() {
        // given: 회원과 상품 저장
        Member member = saveMember(); // 너가 만든 테스트용 회원 생성 메서드
        Item item = saveItem();       // 상품 저장 (DB에 이미 있다면 get 방식으로 불러와도 됨)

        // when: 장바구니에 상품 추가
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(item.getId());
        cartItemDTO.setCount(3);  // 수량

        cartService.addCart(cartItemDTO, member.getMemail());

        // then: 장바구니 조회
        List<CartItemDTO> cartItems = cartService.getCartItems(member.getMemail());

        assertEquals(1, cartItems.size(), "장바구니에 상품이 1개 있어야 합니다.");
        CartItemDTO retrievedItem = cartItems.get(0);

        assertEquals(item.getId(), retrievedItem.getItemId(), "상품 ID가 일치해야 합니다.");
        assertEquals(item.getItemNm(), retrievedItem.getItemNm(), "상품 이름이 일치해야 합니다.");
        assertEquals(5, retrievedItem.getCount(), "담은 수량이 일치해야 합니다.");
        assertEquals(item.getPrice(), retrievedItem.getPrice(), "상품 가격이 일치해야 합니다.");

        log.info("장바구니 조회 결과: {}", retrievedItem);
    }

    @Test
    @DisplayName("장바구니 아이템 수량 수정 테스트")
    public void updateCartItemCountTest() {
        // given
        Member member = saveMember();
        Item item = saveItem();

        // 장바구니 담기
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(item.getId());
        cartItemDTO.setCount(2);
        Long cartItemId = cartService.addCart(cartItemDTO, member.getMemail());

        // when: 수량 수정
        int updatedCount = 5;
        cartService.updateCartItemCount(cartItemId, updatedCount, member.getMemail());

        // then: 수정된 결과 조회
        CartItem updatedCartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니 아이템을 찾을 수 없습니다."));

        assertEquals(updatedCount, updatedCartItem.getCount(), "수정된 수량이 일치해야 합니다.");
    }


    @Test
    @DisplayName("장바구니 개별 상품 삭제 테스트")
    public void removeCartItemTest() {
        // given: 테스트 회원과 상품 등록
        Member member = saveMember();
        Item item = saveItem();

        // 장바구니에 상품 추가
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(item.getId());
        cartItemDTO.setCount(2);
        Long cartItemId = cartService.addCart(cartItemDTO, member.getMemail());

        // 삭제 전, 장바구니에 1개 있어야 함
        List<CartItemDTO> beforeDelete = cartService.getCartItems(member.getMemail());
        assertEquals(1, beforeDelete.size(), "삭제 전 장바구니에 상품이 1개 있어야 합니다.");

        // when: 개별 상품 삭제
        cartService.removeCartItem(cartItemId, member.getMemail());

        // then: 장바구니가 비었는지 확인
        List<CartItemDTO> afterDelete = cartService.getCartItems(member.getMemail());
        assertEquals(0, afterDelete.size(), "삭제 후 장바구니가 비어 있어야 합니다.");
    }

    @Test
    @DisplayName("장바구니 전체 비우기 테스트")
    public void clearCartTest() {
        // given
        Member member = saveMember(); // 테스트용 회원 (DB에 이미 있다면 그대로 사용)
        Item item = saveItem();       // 테스트용 상품

        // 장바구니에 상품 추가
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(item.getId());
        cartItemDTO.setCount(3);
        cartService.addCart(cartItemDTO, member.getMemail());

        // ensure 장바구니에 상품이 들어갔는지 확인
        List<CartItemDTO> beforeClear = cartService.getCartItems(member.getMemail());
        assertEquals(1, beforeClear.size(), "초기 장바구니에 상품이 1개 있어야 함");

        // when: 전체 삭제
        cartService.clearCart(member.getMemail());

        // then: 장바구니가 비었는지 확인
        List<CartItemDTO> afterClear = cartService.getCartItems(member.getMemail());
        assertEquals(0, afterClear.size(), "장바구니가 비어 있어야 함");
    }

}
