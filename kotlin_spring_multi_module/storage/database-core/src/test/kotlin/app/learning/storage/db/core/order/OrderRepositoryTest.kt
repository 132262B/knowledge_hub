package app.learning.storage.db.core.order

import app.learning.TestConfiguration
import app.learning.enumerated.OrderStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource


@ContextConfiguration(classes = [TestConfiguration::class])
@TestPropertySource("classpath:database-core-test.yml")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Test
    fun `주문번호로 주문 정보를 조회한다`() {

        // given
        val memberId = 1L
        val productId = 1L
        val quantity = 2

        val order = Order(memberId, productId, quantity)
        val saveOrderId = orderRepository.save(order).id !!

        // when
        val findOrder = orderRepository.findOrderById(saveOrderId)

        // then
        assertThat(findOrder.id).isEqualTo(saveOrderId)
        assertThat(findOrder.memberId).isEqualTo(memberId)
        assertThat(findOrder.productId).isEqualTo(productId)
        assertThat(findOrder.quantity).isEqualTo(quantity)
        assertThat(findOrder.status).isEqualTo(OrderStatus.SUBMITTED)
    }

    @DisplayName("없는 주문번호를 조회하는 경우, RuntimeException이 발생한다.")
    @Test
    fun findOrderByIdExceptionTest() {

        // given
        val orderId = - 1L

        // when // then
        assertThatThrownBy { orderRepository.findOrderById(orderId) }
            .isInstanceOf(RuntimeException::class.java)
    }
}