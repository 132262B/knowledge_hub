package app.learning.order

import app.learning.storage.db.core.order.Order
import app.learning.storage.db.core.order.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class OrderCreateServiceTest {

    @Mock
    lateinit var orderRepository: OrderRepository

    @InjectMocks
    lateinit var orderCreateService: OrderCreateService

    @Test
    fun createTest() {
        // given
        val memberId : Long = 1
        val newOrder = NewOrder(1L, 1);
        val savedOrder = Order(memberId = memberId, productId = newOrder.productId, quantity = newOrder.quantity)

        given(orderRepository.save(any())).willReturn(savedOrder)

        //when
        val createOrder  = orderCreateService.create(memberId, newOrder)

        // then
        verify(orderRepository, times(1)).save(any(Order::class.java))
        assertThat(createOrder).isEqualTo(null)
    }

}
