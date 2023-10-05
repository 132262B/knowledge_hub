package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;

import static org.junit.jupiter.api.Assertions.*;

class CafeKioskTest {

    @Test
    void add() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        // when
        System.out.println(">>>> 키오스크에 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>>> 키오스크에 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());

        // then
    }

}