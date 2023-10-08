package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @Test
    void add_manual_test() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        // when
        System.out.println(">>>> 키오스크에 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>>> 키오스크에 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());

        // then
    }

    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    @Test
    void add() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();

        // When
        cafeKiosk.add(new Americano());

        // Then
        // assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("음료 2개를 추가하면 추가한 만큼 주문 목록에 추가 된다.")
    @Test
    void addSeveralBeverages() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // When
        cafeKiosk.add(americano, 2);

        // Then
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
    }

    @DisplayName("음료는 1잔 이상 주문할 수 있다.")
    @Test
    void addZeroBeverages() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // When & Then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.")
        ;
    }

    @DisplayName("음료를 1잔 추가하고, 삭제하면 주문 목록이 비어있다.")
    @Test
    void remove() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // When & Then
        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        // When & Then
        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("음료를 추가하고 clear하면 주문 목록이 비어있다.")
    @Test
    void clear() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        // When & Then
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        // When & Then
        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
    @Test
    void calculateTotalPrice() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        // When
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        int totalPrice = cafeKiosk.calculateTotalPrice();

        // Then
        assertThat(totalPrice).isEqualTo(8500);
    }

//    @DisplayName("음료를 추가하고, 주문을 시도하게 되면 추가한 음료가 주문이 생성된다.")
//    @Test
//    void createOrder() {
//        CafeKiosk cafeKiosk = new CafeKiosk();
//        Americano americano = new Americano();
//
//        cafeKiosk.add(americano);
//
//        Order order = cafeKiosk.createOrder();
//        assertThat(order.getBeverages()).hasSize(1);
//        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
//    }

    @DisplayName("영업 시간일때만 주문을 생성 할 수 있다.")
    @Test
    void createOrderWithCurrentTime() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();


        // When
        cafeKiosk.add(americano);
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2023, 10, 6, 10,0));

        // Then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("영업 시간이 아닐때는 주문을 생성 할 수 없다.")
    @Test
    void createOrderOutsideOpenTime() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();


        // When & Then
        cafeKiosk.add(americano);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2023, 10, 6, 9,59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }

}