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
    val id : Long,
) {

    fun issueCoupon() {
        this.quantity =  this.quantity - 1
    }

}