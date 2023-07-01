package io.reflectoring.buckpal.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime
import java.util.*

interface ActivityRepository : JpaRepository<ActivityJpaEntity, Long> {
    @Query(
        """
        select a from ActivityJpaEntity a 
        where a.ownerAccountId = :ownerAccountId 
        and a.timestamp >= :since
	    """
    )
    fun findByOwnerSince(ownerAccountId: Long, since: LocalDateTime): List<ActivityJpaEntity>

    @Query(
        """
        select sum(a.amount) from ActivityJpaEntity a
        where a.targetAccountId = :accountId
        and a.ownerAccountId = :accountId
        and a.timestamp < :until
        """
    )
    fun getDepositBalanceUntil(accountId: Long, until: LocalDateTime): Long

    @Query(
        """
        select sum(a.amount) from ActivityJpaEntity a
        where a.sourceAccountId = :accountId
        and a.ownerAccountId = :accountId
        and a.timestamp < :until
        """
    )
    fun getWithdrawalBalanceUntil(accountId: Long, until: LocalDateTime): Long
}
