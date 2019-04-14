package kr.co.vcnc.swan.study.hibernate.practice.model

import org.hibernate.annotations.Immutable
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Immutable
data class Event(
    @get:Id
    var id: Long,

    var createdAt: Instant,

    var message: String
)
