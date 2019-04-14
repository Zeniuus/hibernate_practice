package kr.co.vcnc.swan.study.hibernate.practice

import kr.co.vcnc.swan.study.hibernate.practice.model.Event
import kr.co.vcnc.swan.study.hibernate.practice.repository.EventRepository
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant

class ImmutableEntity : AbstractHibernateTest() {
    @Autowired
    lateinit var eventRepository: EventRepository

    @After
    fun cleanUp() {
        eventRepository.deleteAll()
    }

    @Test
    fun example_259_persisting_an_immutable_entity() {
        doInJPA { em ->
            val event = Event(
                id = 1L,
                createdAt = Instant.now(),
                message = "Hibernate User Guide rocks!"
            )
            em.persist(event)
        }
    }

    @Test
    fun example_260_the_immutable_entity_ignores_any_update() {
        doInJPA { em ->
            val event = Event(
                id = 1L,
                createdAt = Instant.now(),
                message = "Hibernate User Guide rocks!"
            )
            em.persist(event)
        }
        doInJPA { em ->
            val event = em.find(Event::class.java, 1L)
            event.message = "Hibernate User Guide"
        }
        doInJPA  { em ->
            val event = em.find(Event::class.java, 1L)
            Assert.assertEquals("Hibernate User Guide rocks!", event.message)
        }
    }
}
