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
        val accountRepository: AccountRepository,
        val currencyRepository: CurrencyRepository) {

    @Test
    fun testSaveAllCurrencies() {
        val dollar = Currency("dollar")
        val ruble = Currency("ruble")
        currencyRepository.saveAll(listOf(dollar, ruble))
        val found = currencyRepository.findAll()
        assertThat(found.count()).isEqualTo(2)
        assertThat(found.elementAt(0)).isEqualTo(dollar)
        assertThat(found.elementAt(1)).isEqualTo(ruble)
    }

    @Test
    fun testFindAccountByUserId() {
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
