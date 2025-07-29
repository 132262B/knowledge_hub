package app.demo.coupon

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Coupon(
    val name : String,
    var quantity : Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
) {

    fun issuance() {
        validateQuantity()
        this.quantity =  this.quantity - 1
    }

    private fun validateQuantity() {
        if(quantity < 1) {
            throw RuntimeException("coupon quantity is greater than zero")
        }
    }

}