package app.blog.igor.global.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
@EnableJpaRepositories
class QuerydslConfig {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(entityManager)
}