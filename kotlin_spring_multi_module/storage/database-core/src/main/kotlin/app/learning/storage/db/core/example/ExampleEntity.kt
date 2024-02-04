package app.learning.storage.db.core.example

import app.learning.storage.db.core.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ExampleEntity(
    @Column
    val exampleColumn: String,
) : BaseEntity()