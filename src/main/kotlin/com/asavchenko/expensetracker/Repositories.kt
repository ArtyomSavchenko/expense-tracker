package com.asavchenko.expensetracker

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<FinancialAccount, Long> {
    fun findByUserId(userId: Long): Iterable<FinancialAccount>
}