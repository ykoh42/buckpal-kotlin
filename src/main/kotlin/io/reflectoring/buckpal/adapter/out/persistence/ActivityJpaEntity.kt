package io.reflectoring.buckpal.adapter.out.persistence

import io.reflectoring.buckpal.application.domain.model.AccountId
import io.reflectoring.buckpal.application.domain.model.ActivityId
import io.reflectoring.buckpal.application.domain.model.Money
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "activity")
class ActivityJpaEntity(
    val timestamp: LocalDateTime,
    val ownerAccountId: Long,
    val sourceAccountId: Long,
    val targetAccountId: Long,
    val amount: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}
