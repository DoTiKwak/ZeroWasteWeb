package org.mbc.czo.function.cart.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {

    private Long id;
    private String memberId;
    private List<CartItemDTO> items;
    private BigDecimal cartTotal;

}
