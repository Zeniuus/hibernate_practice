package kr.co.vcnc.swan.study.hibernate.practice

import kr.co.vcnc.swan.study.hibernate.practice.model.Event
import kr.co.vcnc.swan.study.hibernate.practice.repository.EventRepository
import org.hibernate.testing.transaction.TransactionUtil
import org.hibernate.testing.transaction.TransactionUtil.doInJPA
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.function.Supplier
import javax.persistence.EntityManagerFactory

@RunWith(SpringRunner::class)
@SpringBootTest
class ImmutableEntity {
    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory

    @Autowired
    lateinit var eventRepository: EventRepository

    @After
    fun cleanUp() {
        eventRepository.deleteAll()
    }

    @Test
    fun example_259_persisting_an_immutable_entity() {
        val supplier = Supplier { entityManagerFactory }
        doInJPA(supplier, TransactionUtil.JPATransactionVoidFunction { em ->
            val event = Event(
                id = 1L,
                createdAt = Instant.now(),
                message = "Hibernate User Guide rocks!"
            )
            em.persist(event)
        })
    }
}