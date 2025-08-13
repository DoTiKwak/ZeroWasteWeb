package org.mbc.czo.function.cart.service;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mbc.czo.function.cart.domain.Cart;
import org.mbc.czo.function.cart.domain.CartItem;
import org.mbc.czo.function.cart.dto.CartDetailDTO;
import org.mbc.czo.function.cart.dto.CartItemDTO;
import org.mbc.czo.function.cart.dto.CartOrderDTO;
import org.mbc.czo.function.cart.repository.CartItemRepository;
import org.mbc.czo.function.cart.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    /*private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public long addCart(CartItemDTO cartItemDTO, String email) {
        Item item = itemRepository.findById(cartItemDTO.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDTO.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDTO.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }

    }

    @Transactional(readOnly = true)
    public List<CartDetailDTO> getCartList(String email) {
        List<CartDetailDTO> cartDetailDTOList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){
            return cartDetailDTOList;
        }
        cartDetailDTOList = cartItemRepository.findCartDetailDTOList(cart.getId());

        return cartDetailDTOList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        Member curMember = memberRepository.findByEmail(email);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();

        if(!StringUtils.equals(curMember.getEmail(),savedMember.getEmail())) {
         return false;
        }
        return true;
    }
    public void updateCartItemCount(Long cartItemid, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemid)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemid){
        CartItem cartItem = cartItemRepository.findById(cartItemid)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    public Long orderCartItem(List<CartOrderDTO> cartOrderDTOList, String email){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (CartOrderDTO cartOrderDTO : cartOrderDTOList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDTO.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setItemId(cartItem.getItem().getId());
            orderDTO.setCount(cartItem.getCount());
            orderDTOList.add(orderDTO);
        }
        Long orderId = orderService.orders(orderDTOList, email);

        for(CartOrderDTO carOrderDTO : cartOrderDTOList){
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDTO.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }
        return orderId;
    }
*/
}
