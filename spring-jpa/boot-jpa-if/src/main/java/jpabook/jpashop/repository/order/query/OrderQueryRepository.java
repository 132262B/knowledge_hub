package jpabook.jpashop.repository.order.query;

import jpabook.jpashop.dto.OrderItemQueryDto;
import jpabook.jpashop.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;


    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrder();
        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });

        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(" " +
                        "select new jpabook.jpashop.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) " +
                        " from OrderItem oi " +
                        " join oi.item i " +
                        " where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrder() {
        return em.createQuery(" " +
                        "select new jpabook.jpashop.dto.OrderQueryDto(o.id,m.name,o.orderDate,o.status, d.address) " +
                        " from Order o " +
                        " join o.member m " +
                        "join o.delivery d ", OrderQueryDto.class)
                .getResultList();
    }

//    public List<OrderQueryDto> findAllByDtos_optimize() {
//        List<OrderQueryDto> result = findOrder();
//
//        List<Long> orderIds = result.stream().map(OrderQueryDto::getOrderId).collect(Collectors.toList());
//
//        List<OrderItemQueryDto> orderItems = em.createQuery(" " +
//                        "select new jpabook.jpashop.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) " +
//                        " from OrderItem oi " +
//                        " join oi.item i " +
//                        " where oi.order.id in :orderIds", OrderItemQueryDto.class)
//                .setParameter("orderIds", orderIds)
//                .getResultList();
//
//        orderItems.stream().collect(Collectors.groupingBy(OrderItemQueryDto::getId));
//    }

}
