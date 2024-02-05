package app.learning.storage.db.core.example

import app.learning.storage.db.core.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ExampleEntity(
    @Column
    val exampleColumn: String,
) : BaseEntity()