package app.learning

import app.learning.storage.db.core.example.ExampleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExampleService(
    private val exampleRepository : ExampleRepository
) {

    fun test(id : Long): String {
        val entity = exampleRepository.findByIdOrNull(id) ?: throw RuntimeException("없음")
        return entity.exampleColumn
    }

}