package kr.co.vcnc.swan.study.hibernate.practice.model

import org.hibernate.annotations.LazyGroup
import javax.persistence.Basic
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
data class LazyEntity(
    @get:Id
    var id: Long,

    @get:Basic(fetch = FetchType.LAZY)
    var group1Field1: String,

    @get:Basic(fetch = FetchType.LAZY)
    var group1Field2: String,

    @get:Basic(fetch = FetchType.LAZY)
    @get:LazyGroup("other")
    var group2Field1: String,

    @get:Basic(fetch = FetchType.LAZY)
    @get:LazyGroup("other")
    var group2Field2: String
)
