package kr.co.vcnc.swan.study.hibernate.practice.repository

import kr.co.vcnc.swan.study.hibernate.practice.model.Batch
import org.springframework.data.repository.CrudRepository

interface BatchRepository : CrudRepository<Batch, Long>
