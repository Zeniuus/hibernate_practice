package kr.co.vcnc.swan.study.hibernate.practice.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SimpleEntity(
    @get:Id
    var id: Long?
)
