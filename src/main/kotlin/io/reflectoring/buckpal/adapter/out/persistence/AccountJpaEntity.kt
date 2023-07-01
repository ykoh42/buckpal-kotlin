package io.reflectoring.buckpal.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "account")
class AccountJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}
