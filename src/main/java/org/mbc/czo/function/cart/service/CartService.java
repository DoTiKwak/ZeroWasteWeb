package org.mbc.czo.function.cart.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mbc.czo.function.cart.domain.Cart;
import org.mbc.czo.function.cart.domain.CartItem;
import org.mbc.czo.function.cart.domain.Order;
import org.mbc.czo.function.cart.dto.CartDetailDTO;
import org.mbc.czo.function.cart.dto.CartItemDTO;
import org.mbc.czo.function.cart.dto.OrderHistDTO;
import org.mbc.czo.function.cart.repository.CartItemRepository;
import org.mbc.czo.function.cart.repository.CartRepository;
import org.mbc.czo.function.cart.repository.OrderRepository;
import org.mbc.czo.function.member.domain.Member;
import org.mbc.czo.function.member.repository.MemberJpaRepository;
import org.mbc.czo.function.product.domain.Item;
import org.mbc.czo.function.product.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    /**
     * 장바구니에 상품 추가
     */
    public Long addCart(CartItemDTO cartItemDTO, String memail) {
        // 상품 조회
        Item item = itemRepository.findById(cartItemDTO.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("상품이 존재하지 않습니다."));

        // 회원 조회
        Member member = memberJpaRepository.findByMemail(memail)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다: " + memail));

        // 회원의 장바구니 조회 (없으면 생성)
        Cart cart = cartRepository.findByMemberMemail(member.getMemail())
                .orElseGet(() -> cartRepository.save(Cart.createCart(member)));

        // 장바구니에 동일 상품 있는지 확인
        CartItem savedCartItem = cartItemRepository.findByCart_IdAndItem_Id(cart.getId(), item.getId())
                .orElse(null);

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDTO.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDTO.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }

    }


    /**
     * 장바구니 조회
     */
    @Transactional(readOnly = true)
    public List<CartItemDTO> getCartItems(String memail) {
        // 회원 조회
        Member member = memberJpaRepository.findByMemail(memail)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다: " + memail));

        // 장바구니 조회
        Cart cart = cartRepository.findByMemberMemail(member.getMemail())
                .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));

        // 장바구니 아이템이 없다면 빈 리스트 반환
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            return Collections.emptyList();
        }

        // 변환 및 반환
        return cart.getCartItems().stream()
                .map(cartItem -> {
                    CartItemDTO dto = new CartItemDTO();
                    dto.setItemId(cartItem.getItem().getId());
                    dto.setItemNm(cartItem.getItem().getItemNm());
                    dto.setCount(cartItem.getCount());
                    dto.setPrice(cartItem.getItem().getPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    /**
     * 주문 내역 조회
     */
    @Transactional(readOnly = true)
    public List<OrderHistDTO> getOrderHist(String memail) {
        // 회원 조회
        Member member = memberJpaRepository.findByMemail(memail)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다: " + memail));

        // 주문 조회
        List<Order> orders = orderRepository.findByMember(member);

        // 주문 → DTO 변환
        return orders.stream()
                .map(order -> {
                    OrderHistDTO dto = new OrderHistDTO();
                    dto.setOrderId(order.getId());
                    dto.setOrderDate(order.getOrderDate());
                    dto.setStatus(order.getOrderStatus().toString());

                    // 주문 아이템도 DTO 변환
                    List<OrderHistDTO.OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                            .map(orderItem -> {
                                OrderHistDTO.OrderItemDTO itemDTO = new OrderHistDTO.OrderItemDTO();
                                itemDTO.setItemNm(orderItem.getItem().getItemNm());
                                itemDTO.setCount(orderItem.getCount());
                                itemDTO.setOrderPrice(orderItem.getOrderPrice());
                                return itemDTO;
                            })
                            .collect(Collectors.toList());

                    dto.setOrderItems(orderItemDTOs);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * 장바구니 아이템 수량 수정
     */
    @Transactional
    public void updateCartItemCount(Long cartItemId, int count, String email) {
        // 장바구니 아이템 조회
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니 아이템이 존재하지 않습니다."));

        // 본인 장바구니인지 확인
        Member member = memberJpaRepository.findByMemail(email)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다: " + email));

        if (!cartItem.getCart().getMember().getMemail().equals(email)) {
            throw new IllegalArgumentException("다른 회원의 장바구니는 수정할 수 없습니다.");
        }

        // 수량 업데이트
        cartItem.updateCount(count);
    }


    /** DELETE: 특정 CartItem 삭제 */
    public void removeCartItem(Long cartItemId, String memail) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니 항목이 존재하지 않습니다: " + cartItemId));

        // 소유자 검증
        String owner = cartItem.getCart().getMember().getMemail();
        if (!owner.equals(memail)) {
            throw new IllegalStateException("본인 장바구니가 아닙니다.");
        }

        // 양방향/고아 제거(orphanRemoval=true) 고려
        cartItem.getCart().getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
    }

    /** DELETE: 장바구니 전체 비우기 */
    public void clearCart(String memail) {
        // 회원의 장바구니 조회
        Cart cart = cartRepository.findByMemberMemail(memail)
                .orElseThrow(() -> new EntityNotFoundException("장바구니가 없습니다."));

        if (!cart.getMember().getMemail().equals(memail)) {
            throw new IllegalStateException("본인 장바구니가 아닙니다.");
        }

        // orphanRemoval = true 이므로 clear()만으로 자식 CartItem 삭제됨
        cart.getCartItems().clear();
        // 필요시 명시적 삭제를 원하면 다음 방식도 가능:
        // cart.getCartItems().forEach(cartItemRepository::delete);
        // cart.getCartItems().clear();
    }

}
