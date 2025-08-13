package org.mbc.czo.function.cart.service;

/*
import lombok.RequiredArgsConstructor;
import org.mbc.czo.function.cart.domain.Order;
import org.mbc.czo.function.cart.domain.OrderItem;
import org.mbc.czo.function.cart.dto.OrderItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;


    public Long order(OrderDTO orderDTO, String eamil){
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundExceptionion::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();

    }

    @Transactional (readOnly = true)
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable){
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrders(email);

        List<OrderHistDTO> orderHistDTOList = new ArrayList<>();

        for (Order order : orders)
            OrderHistDTO orderHistDTO = new OrderHistDTO(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems){
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn
                        (orderItem.getItem().getId(), "Y");
                OrderItemDTO orderItemDTO =
                        new OrderItemDTO(orderItem, itemImg.getImgUrl());
                orderHistDTO.addOrderItemDTO(orderItemDTO);
        }
            orderHistDTOs.add(orderHistDTO);
    }
    return new PageImpl<OrderHistDTO>(orderHistDTOs, pageable, totalCount);
}

}*/