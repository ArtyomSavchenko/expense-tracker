package com.asavchenko.expensetracker

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class FinancialAccount(
        var accountName: String,
        var userId: Long,
        var createdDate: LocalDateTime = LocalDateTime.now(),
        @ManyToOne var currency: Currency,
        @Id @GeneratedValue var id: Long? = null
)

@Entity
class FinancialOperation(
        var name: String,
        var date: LocalDateTime = LocalDateTime.now(),
        var amount: Long,
        @ManyToOne var category: OperationCategory,
        @ManyToOne var account: FinancialAccount,
        @Id @GeneratedValue var id: Long? = null
)

//TODO: The model is incomplete and currency conversion is not supported
@Entity
class Currency(
    var name: String,
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class OperationCategory(
        var name: String,
        var description: String,
        @Id @GeneratedValue var id: Long? = null
)
