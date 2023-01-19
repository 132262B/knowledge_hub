package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.dto.SimpleOrderDto;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleController {

    private final OrderService orderService;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> simpleOrders() {
        return orderService.findOrders();
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderService.findOrders().stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        return orderService.findAndWithMemberDelivery().stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

}
