package app.learning.storage.db.core.example

import app.learning.storage.db.core.BaseEntity
import jakarta.persistence.*

@Entity
class ExampleEntity(
    @Column
    val exampleColumn: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseEntity()