package org.mbc.czo.function.cart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mbc.czo.function.cart.dto.CartItemDTO;
import org.mbc.czo.function.cart.dto.OrderHistDTO;
import org.mbc.czo.function.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /** 장바구니 담기 */
    @PostMapping(value = "/cart")
    public @ResponseBody ResponseEntity<?> order(
            @RequestBody @Valid CartItemDTO cartItemDTO,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cartItemId;
        try {
            cartItemId = cartService.addCart(cartItemDTO, email);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
    }

    /** 장바구니 보기 */
    @GetMapping("/cart")
    public String showCart(Model model, Principal principal) {
        String email = principal.getName();
        List<CartItemDTO> cartItems = cartService.getCartItems(email);
        model.addAttribute("cartItems", cartItems);
        return "cart/cartList";  // ✅ templates/cart/cartList.html
    }

    /** 주문내역 보기 */
    @GetMapping("/cart/orders")
    public String showOrderHistory(Model model, Principal principal) {
        String email = principal.getName();
        List<OrderHistDTO> orderHistList = cartService.getOrderHist(email); // 서비스에서 주문내역 가져오기
        model.addAttribute("orders", orderHistList);
        return "cart/orderHist";  // ✅ templates/cart/orderHist.html
    }

    /**
     * 장바구니 아이템 수량 수정
     */
    @PatchMapping("/cart/{cartItemId}")
    public @ResponseBody ResponseEntity<?> updateCartItem(
            @PathVariable("cartItemId") Long cartItemId,
            @RequestBody @Valid CartItemDTO cartItemDTO,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        try {
            cartService.updateCartItemCount(cartItemId, cartItemDTO.getCount(), email);
            return new ResponseEntity<>("수량이 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // POST /cart/{cartItemId}/delete  -> 삭제 후 장바구니 화면으로 리다이렉트
    @PostMapping("/cart/{cartItemId}/delete")
    public String removeCartItemByPost(
            @PathVariable Long cartItemId, Principal principal, Model model) {
        try {
            cartService.removeCartItem(cartItemId, principal.getName());
            return "redirect:/cart";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "cart/cartList";
        }
    }

    // POST /cart/clear -> 전체 비우고 장바구니 화면으로 리다이렉트
    @PostMapping("/cart/clear")
    public String clearCartByPost(Principal principal, Model model) {
        try {
            cartService.clearCart(principal.getName());
            return "redirect:/cart";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "cart/cartList";
        }
    }

}
