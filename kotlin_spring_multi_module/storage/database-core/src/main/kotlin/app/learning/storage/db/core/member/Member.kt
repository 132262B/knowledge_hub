package app.learning.storage.db.core.member

import app.learning.storage.db.core.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    val email: String,
    password: String,
    name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseEntity() {

    var password: String = password
        protected set

    var name: String = name
        protected set

}