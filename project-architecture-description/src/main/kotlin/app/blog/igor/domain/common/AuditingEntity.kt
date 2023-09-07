package app.blog.igor.domain.common

import org.hibernate.annotations.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity : BaseTimeEntity() {

    @Comment("등록자")
    @CreatedBy
    @Column(updatable = false)
    val createdBy: Long? = null

    @Comment("수정자")
    @LastModifiedBy
    @Column(insertable = false)
    val updatedBy: Long? = null
}

@DynamicInsert
@DynamicUpdate
@MappedSuperclass
abstract class BaseTimeEntity {

    @Comment(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Comment("등록일")
    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    @Comment("수정일")
    @Column(insertable = false)
    lateinit var updatedAt: LocalDateTime

    @ColumnDefault("false")
    @Comment(value = "삭제유무")
    @Column(nullable = false)
    val isDeleted: Boolean = false

    @PrePersist
    fun prePersist() {
        this.createdAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        this.updatedAt = LocalDateTime.now()
    }
}