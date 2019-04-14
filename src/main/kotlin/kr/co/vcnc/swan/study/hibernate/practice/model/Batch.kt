package kr.co.vcnc.swan.study.hibernate.practice.model

import jdk.nashorn.internal.ir.annotations.Immutable
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Batch(
    @get:Id
    var id: Long,

    var name: String,

    @get:OneToMany(cascade = [CascadeType.ALL])
    @get:Immutable
    var events: List<Event> = emptyList()
)
