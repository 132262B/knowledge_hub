package com.example

import autoparams.kotlin.AutoKotlinSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest

class AutoParamsTest {

    @ParameterizedTest
    @AutoKotlinSource
    fun parameterizedTest(a: Int, b: Int) {
        val sut = Calculator()
        val actual: Int = sut.add(a, b)
        assertThat(a + b).isEqualTo(actual)
        println("a=${a} / b=${b}")
    }

    @ParameterizedTest
    @AutoKotlinSource
    fun parameterizedTest2(arg : ComplexObject) {
        println(arg) // ComplexObject(value1=1128145627, value2=fdd57810-d44f-4e3d-83a2-d89e66575e31)
    }

    @ParameterizedTest
    @AutoKotlinSource
    fun parameterizedTest3(arg : OrderEntity) {
        println(arg) // OrderEntity(id=9173970940502329006, quantity=1778814199, customerId=7616073837117899571, status=CANCEL)
    }

}