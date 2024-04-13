package com.asavchenko.expensetracker

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val accountRepository: AccountRepository) {

    @Test
    fun `When findByUserId then return FinancialAccount`() {
        val testUserId = 123L
        val currency = Currency("ruble")
        val newAccount = FinancialAccount("johnDoe", testUserId, LocalDateTime.now(), currency)
        entityManager.persist(currency)
        entityManager.persist(newAccount)
        entityManager.flush()
        val found = accountRepository.findByUserId(testUserId)
        assertThat(found.count()).isEqualTo(1)
        assertThat(found.firstOrNull()).isEqualTo(newAccount)
    }
}