package com.example.demo.engine

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractModel(
        @CreatedBy var createdBy: Long? = null,
        @CreatedDate var createdDate: Instant? = Instant.now(),
        @LastModifiedBy var modifiedBy: Long? = null,
        @LastModifiedDate var modifiedDate: Instant? = null,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null
)

@Entity
data class Condition(
        var name: String,
        var description: String? = null,
        var script: String,
        @OneToMany(cascade = [CascadeType.ALL])
        var conditionVars: Set<ConditionVars>?
) : AbstractModel()


@Entity
data class ConditionVars(
        var name: String,
        var value: String
) : AbstractModel()

interface ConditionRepository : JpaRepository<Condition, Long>
