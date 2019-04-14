package kr.co.vcnc.swan.study.hibernate.practice

import kr.co.vcnc.swan.study.hibernate.practice.model.Batch
import kr.co.vcnc.swan.study.hibernate.practice.model.Event
import kr.co.vcnc.swan.study.hibernate.practice.repository.BatchRepository
import kr.co.vcnc.swan.study.hibernate.practice.repository.EventRepository
import org.hibernate.HibernateException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant

class ImmutableCollection : AbstractHibernateTest() {
    @Autowired
    lateinit var eventRepository: EventRepository

    @Autowired
    lateinit var batchRepository: BatchRepository

    @Before
    fun cleanUp() {
        batchRepository.deleteAll()
        eventRepository.deleteAll()
    }

    @Test
    fun example_262_persisting_an_immutable_collection() {
        doInJPA { em ->
            val batch = Batch(
                id = 1L,
                name = "Change request"
            )
            val event1 = Event(
                id = 1L,
                createdAt = Instant.now(),
                message = "Update Hibernate User Guide"
            )
            val event2 = Event(
                id = 2L,
                createdAt = Instant.now(),
                message = "Update Hibernate Getting Started Guide"
            )

            batch.events = listOf(event1, event2)

            em.persist(batch)
        }
    }

    @Test
    fun example_263_changing_the_mutable_entity() {
        doInJPA { em ->
            val batch = Batch(
                id = 1L,
                name = "Change request"
            )
            val event1 = Event(
                id = 1L,
                createdAt = Instant.now(),
                message = "Update Hibernate User Guide"
            )
            val event2 = Event(
                id = 2L,
                createdAt = Instant.now(),
                message = "Update Hibernate Getting Started Guide"
            )

            batch.events = listOf(event1, event2)

            em.persist(batch)
        }
        doInJPA { em ->
            val batch = em.find(Batch::class.java, 1L)
            batch.name = "Proposed change request"
        }
    }

    @Test()
    fun example_264_immutable_collections_cannot_be_modified() {
        doInJPA { em ->
            val batch = Batch(
                id = 1L,
                name = "Change request"
            )
            val event1 = Event(
                id = 1L,
                createdAt = Instant.now(),
                message = "Update Hibernate User Guide"
            )
            val event2 = Event(
                id = 2L,
                createdAt = Instant.now(),
                message = "Update Hibernate Getting Started Guide"
            )

            batch.events = listOf(event1, event2)

            em.persist(batch)
        }
        doInJPA { em ->
            val batch = em.find(Batch::class.java, 1L)
            println(batch.events)
            batch.events = emptyList()
        }
    }
}
