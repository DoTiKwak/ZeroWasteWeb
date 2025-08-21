package org.mbc.czo.function.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class OrderHistDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private String status; // 주문 상태 (예: ORDER, CANCEL)
    private List<OrderItemDTO> orderItems;

    @Getter @Setter
    public static class OrderItemDTO {
        private String itemNm;
        private int count;
        private int orderPrice;
    }
}
