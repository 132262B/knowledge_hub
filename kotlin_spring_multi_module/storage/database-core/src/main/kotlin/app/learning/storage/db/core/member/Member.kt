package app.learning.storage.db.core.member

import app.learning.storage.db.core.base.BaseEntity
import jakarta.persistence.Entity

@Entity
class Member(
    val email: String,
    password: String,
    name: String,
) : BaseEntity() {

    var password: String = password
        protected set

    var name: String = name
        protected set

}